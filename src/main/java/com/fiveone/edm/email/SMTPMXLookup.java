package com.fiveone.edm.email;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.log4j.Logger;

/**
 * 封装邮件服务器形成Socket，发送命令，根据返回值来判断邮件地址是否合法
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月5日 下午3:06:00
 * @version: 1.0
 * @since: JDK1.7
 */
/**
 *  从客户机传输到服务器，当我们向一台服务器发送邮件时，
 * 	邮件服务器会首先验证邮件发送地址是否真的存在于本服务器上。  
 * 
 * 	连接服务器的25端口（如果没有邮件服务，连了也是白连）  
	发送helo问候  
	发送mail from命令，如果返回250表示正确可以，连接本服务器，否则则表示服务器需要发送人验证。  
	发送rcpt to命令，如果返回250表示则Email存在  
	发送quit命令，退出连接  
 */
public class SMTPMXLookup {
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(SMTPMXLookup.class); 
	
	
	private static int hear(BufferedReader in) throws IOException {
		String line = null;
		int res = 0;

		while ((line = in.readLine()) != null) {
			String pfx = line.substring(0, 3);
			try {
				res = Integer.parseInt(pfx);
			} catch (Exception ex) {
				res = -1;
			}
			if (line.charAt(3) != '-')
				break;
		}
		return res;
	}

	private static void say(BufferedWriter wr, String text) throws IOException {
		wr.write(text + "\r\n");
		wr.flush();
		return;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static ArrayList getMX(String hostName) throws NamingException {
		// Perform a DNS lookup for MX records in the domain
		Hashtable env = new Hashtable();
		env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
		DirContext ictx = new InitialDirContext(env);
		Attributes attrs = ictx.getAttributes(hostName, new String[] { "MX" });
		Attribute attr = attrs.get("MX");

		// if we don't have an MX record, try the machine itself
		if ((attr == null) || (attr.size() == 0)) {
			attrs = ictx.getAttributes(hostName, new String[] { "A" });
			attr = attrs.get("A");
			if (attr == null)
				throw new NamingException("No match for name '" + hostName + "'");
		}
		// Huzzah! we have machines to try. Return them as an array list
		// NOTE: We SHOULD take the preference into account to be absolutely
		// correct. This is left as an exercise for anyone who cares.
		ArrayList res = new ArrayList();
		NamingEnumeration en = attr.getAll();

		while (en.hasMore()) {
			String mailhost;
			String x = (String) en.next();
			String f[] = x.split(" ");
			// THE fix *************
			if (f.length == 1)
				mailhost = f[0];
			else if (f[1].endsWith("."))
				mailhost = f[1].substring(0, (f[1].length() - 1));
			else
				mailhost = f[1];
			// THE fix *************
			res.add(mailhost);
		}
		return res;
	}

	@SuppressWarnings({ "rawtypes", "resource"})
	public static boolean isAddressValid(String address) {
		// Find the separator for the domain name
		int pos = address.indexOf('@');

		// If the address does not contain an '@', it's not valid
		if (pos == -1)
			return false;

		// Isolate the domain/machine name and get a list of mail exchangers
		String domain = address.substring(++pos);
		ArrayList mxList = null;
		try {
			mxList = getMX(domain);
		} catch (NamingException ex) {
			return false;
		}

		// Just because we can send mail to the domain, doesn't mean that the
		// address is valid, but if we can't, it's a sure sign that it isn't
		if (mxList.size() == 0)
			return false;

		// Now, do the SMTP validation, try each mail exchanger until we get
		// a positive acceptance. It *MAY* be possible for one MX to allow
		// a message [store and forwarder for example] and another [like
		// the actual mail server] to reject it. This is why we REALLY ought
		// to take the preference into account.
		for (int mx = 0; mx < mxList.size(); mx++) {
			boolean valid = false;
			try {
				int res;
				//
				Socket skt = new Socket((String) mxList.get(mx), 25);
				BufferedReader rdr = new BufferedReader(new InputStreamReader(skt.getInputStream()));
				BufferedWriter wtr = new BufferedWriter(new OutputStreamWriter(skt.getOutputStream()));

				res = hear(rdr);
				if (res != 220)
					throw new Exception("Invalid header");
				say(wtr, "EHLO rgagnon.com");

				res = hear(rdr);
				if (res != 250)
					throw new Exception("Not ESMTP");

				// validate the sender address
				say(wtr, "MAIL FROM: <tim@orbaker.com>");
				res = hear(rdr);
				if (res != 250)
					throw new Exception("Sender rejected");

				say(wtr, "RCPT TO: <" + address + ">");
				res = hear(rdr);

				// be polite
				say(wtr, "RSET");
				hear(rdr);
				say(wtr, "QUIT");
				hear(rdr);
				if (res != 250)
					throw new Exception("Address is not valid!");

				valid = true;
				rdr.close();
				wtr.close();
				skt.close();
			} catch (Exception ex) {
				// Do nothing but try next host
				ex.printStackTrace();
			} finally {
				if (valid)
					return true;
			}
		}
		return false;
	}

	public static void main(String args[]) {
		String[] testData = { 
				"595971102@qq.com", //true
				"18124591229@163.com", //true
				"18067991619@yahoo.com", //false
				"3456754782@126.com", //false
				"nosuchaddress@yahoo.com", //false
				"webmaster@51jrq1.vip", //true
				"123@qq.com", //true
				"2131231123@qq.com", //false,
				"xuetingsina@06179.com", //false
				"example@079.com", //false
		};

		for (int ctr = 0; ctr < testData.length; ctr++) {
			System.out.println(testData[ctr] + " is valid? " + isAddressValid(testData[ctr]));
		}
		return;
	}

	
	//邮箱session
  	/*private transient static Session session;*/
	
	/**
	 * 如果邮件地址不合法，则将邮件地址从收件人列表中剔除
	 * @param addresses
	 * @param mailFrom
	 * @return
	 *//*
	private static String[] removeInvalidateAddress(String[] addresses, String mailFrom) {
		ArrayList<String> validateAddresses = new ArrayList<String>();
		String normalAddress = null;
		int code;

		SMTPTransport smptTrans = null;
		if (StringUtils.isEmpty(mailFrom) || null == addresses) {
			return new String[0];
		}
		String sendCmd = "MAIL FROM:" + normalizeAddress(mailFrom);
		try {
			smptTrans = (SMTPTransport) session.getTransport("smtp");
			smptTrans.connect();
			code = smptTrans.simpleCommand(sendCmd);
			if (code != 250 && code != 251) {
				logger.error("send from invalidate" + mailFrom);
			} else {
				for (String address : addresses) {
					normalAddress = normalizeAddress(address);
					String cmd = "RCPT TO:" + normalAddress;
					code = smptTrans.simpleCommand(cmd);
					if (code == 250 || code == 251) {
						validateAddresses.add(address);
					}
				}
			}
		} catch (MessagingException e) {
			logger.error("Validate mail address error. send from " + mailFrom, e);
			e.printStackTrace();
		}

		String[] result = validateAddresses.toArray(new String[validateAddresses.size()]);
		return result;
	}

	private static String normalizeAddress(String addr) {
		if ((!addr.startsWith("<")) && (!addr.endsWith(">")))
			return "<" + addr + ">";
		else
			return addr;
	}*/
}
