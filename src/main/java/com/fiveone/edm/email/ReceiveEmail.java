package com.fiveone.edm.email;

import com.fiveone.edm.util.ParseMailUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 使用Javamail来接收电子邮件
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月17日 上午10:33:02
 * @version: 1.0
 * @since: JDK1.7
 */
public class ReceiveEmail {
	
	private static MimeMessage mimeMessage = null;

	//邮件接收协议
    private static final String MAIL_RECEIVE_PROTOCOL = "imap"; 

	//POP3邮件服务器，接收邮件的POP3服务器的IP(或主机地址)
	//private static final String MAIL_POP3_HOST = "45.62.97.124";
    
    //POP3的默认用户名。
    //private static final String MAIL_POP3_USER = "webmail";
    
    //POP3的默认密码
    //private static final String MAIL_POP3_PASSWORD = "1q2w3e4r";

	//要连接的POP3服务器端口，如果connect()方法没有显式指定一个。默认为110。
    //private static final String MAIL_POP3_PORT = "995"; 
    
    //如果设置，指定扩展javax.net.ssl.SSLSocketFactory类的类的名称。此类将用于创建POP3 SSL套接字。
    //private static final String MIAL_POP3_SOCKET_FACTORY_CLASS = "javax.net.ssl.SSLSocketFactory";
    
    //指定在使用指定的套接字工厂时要连接到的端口。如果未设置，将使用默认端口。
    //private static final String MAIL_POP3_SOCKET_FACTORY_PORT = "995";
    
    //是否需要身份验证 
    //private static final String MAIL_POP3_AUTH = "true"; 
    
    //初始化连接邮件服务器的会话信息 
    private static Properties props = null;  
    
    /**
     * 收取所有邮件，并解析
     * @param host		接收邮件的IMAP服务器的IP(或主机地址)
     * @param user		接收邮件的用户名
     * @param password	接收邮件的密码
     * @throws Exception
     */
    public static List<Map<String,String>> receiveEmails(String host,String user,String password) throws Exception {
    	//设置接收邮件环境
    	props = new Properties();
    	//添加SSL，本项目未采用加SSL进去
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        props.setProperty("mail.store.protocol", MAIL_RECEIVE_PROTOCOL); 
        props.setProperty("mail.imap.host", host); 
        props.setProperty("mail.imap.port", "143"); 
        props.setProperty("mail.smtp.starttls.enable", "true"); 
        
    	//使用Properties对象获得Session对象 ，建立邮件对话
//        Session session = Session.getDefaultInstance(props, null);//在linux服务器运行时会报错！取默认session 而下面则创建一个新的session
        Session session = Session.getInstance(props, null);
        //session.setDebug(true);  
        
        //设置连接邮件仓库的环境
        URLName url = new URLName(MAIL_RECEIVE_PROTOCOL, host, 143, null, user, password);
        Store store = null;
        Folder folder = null;
        
        //得到邮件仓库并连接
        store = session.getStore(url);
        store.connect();
        
        //得到收件箱并抓取邮件
        folder = store.getFolder("INBOX"); 
        /** 
         * Folder.READ_ONLY：只读权限 
         * Folder.READ_WRITE：可读可写（可以修改邮件的状态） 
         */  
        folder.open(Folder.READ_WRITE);
        
        FetchProfile profile = new FetchProfile();
        profile.add(FetchProfile.Item.ENVELOPE);
        
        //由于POP3协议无法获知邮件的状态,所以getUnreadMessageCount得到的是收件箱的邮件总数           
        //由于POP3协议无法获知邮件的状态,所以下面得到的结果始终都是为0  
        //System.out.println("删除邮件数: " + folder.getDeletedMessageCount());  
        //System.out.println("新邮件数: " + folder.getNewMessageCount()); 
        //System.out.println("未读邮件数: " + folder.getUnreadMessageCount()); 
          
        //获得收件箱中的邮件总数  
        //System.out.println("邮件总数: " + folder.getMessageCount());  
        
        //得到收件箱中的所有邮件,并解析  
//        Message[] messages = folder.getMessages();
        
        //获取未读邮件  
        Message[] messages = folder.getMessages(folder.getMessageCount()-folder.getUnreadMessageCount()+1,folder.getMessageCount());

        for (int i = 0; i < messages.length; i++) {
            ParseMailUtil re = new ParseMailUtil((MimeMessage) messages[i]);
            System.out.println("邮件　" + i + "　主题:　" + re.getSubject());
            System.out.println("邮件　" + i + "　发送时间:　" + re.getSentDate());
            System.out.println("邮件　" + i + "　发送人地址:　" + re.getFrom());
            System.out.println("邮件　" + i + "　收信人地址:　" + re.getMailAddress("to"));
            re.setDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("邮件　" + i + "　发送时间:　" + re.getSentDate());
            re.getMailContent((Part) messages[i]);
            System.out.println("邮件　" + i + "　正文内容:　\r\n" + re.getBodyText());
        }

        folder.fetch(messages, profile);
        List<Map<String,String>> list = parseMessages(messages);


        //释放资源  
        if (folder != null) 
        folder.close(true);  
        if (store != null)
        store.close();  
        return list;
    }
    
    /**
     * 解析所有邮件
     * @param messages
     * @throws Exception
     */
	private static List<Map<String,String>> parseMessages(Message ...messages) throws Exception {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		if (messages == null || messages.length < 1) {  
            return null;  
		}  
        // 解析所有邮件  
        for (int i = 0, count = messages.length; i < count; i++) {
            mimeMessage = (MimeMessage) messages[i];
            mimeMessage.setFlag(Flags.Flag.SEEN, true);  
            //System.out.println("------------------解析第" + mimeMessage.getMessageNumber() + "封邮件-------------------- ");
            Map<String,String> map = getMailContent(mimeMessage);
            list.add(map);
        } 
        return list;
	}
    
    /**
     * 解析所有邮件
     * @param messages
     * @throws Exception
     */
	@SuppressWarnings("unused")
	private static void parseMessage(Message ...messages) throws Exception {
		if (messages == null || messages.length < 1) {  
            throw new MessagingException("未找到要解析的邮件!");  
		}  
        // 解析所有邮件  
        for (int i = 0, count = messages.length; i < count; i++) {
            mimeMessage = (MimeMessage) messages[i];
            //System.out.println("------------------解析第" + mimeMessage.getMessageNumber() + "封邮件-------------------- ");
            //System.out.println( getMailContent(mimeMessage));
        }  
	}
	
	/**  
     * 获得邮件主题  
     */   
    public static String getSubject() throws MessagingException {    
        String subject = "";    
        try {    
            subject = MimeUtility.decodeText(mimeMessage.getSubject());    
            if (subject == null) {   
                subject = ""; 
            }
        } catch (Exception e) {}    
        return subject;    
    } 
	
	/**  
	 * 获取发件人的地址和姓名  
	 * @return
	 * @throws MessagingException 
	 * @throws Exception
	 */
    public static String getFrom() throws MessagingException {    
        InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();    
        String from = address[0].getAddress();    
        if (from == null)    
            from = "";    
        String personal = address[0].getPersonal();    
        if (personal == null)    
            personal = "";    
        String fromAddr = personal + "<" + from + ">";    
        return fromAddr;    
    }    
     
    /**  
     * 获得邮件的收件人，抄送，和密送的地址和姓名，
     * 根据所传递的参数的不同 "to"----收件人 "cc"---抄送人地址 "bcc"---密送人地址     
     * @param type
     * @return
     * @throws Exception
     */
    public static String getReceiveAddress(String type) throws Exception {    
        String mailAddr = "";    
        String addType = type.toUpperCase();    
        InternetAddress[] address = null;    
        if (addType.equals("TO") || addType.equals("CC")|| addType.equals("BCC")) {    
            if (addType.equals("TO")) {    
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.TO);    
            } else if (addType.equals("CC")) {    
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.CC);    
            } else {    
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.BCC);    
            }    
            if (address != null) {    
                for (int i = 0; i < address.length; i++) {    
                    String email = address[i].getAddress();    
                    if (email == null)    
                        email = "";    
                    else {    
                        email = MimeUtility.decodeText(email);    
                    }    
                    String personal = address[i].getPersonal();    
                    if (personal == null)    
                        personal = "";    
                    else {    
                        personal = MimeUtility.decodeText(personal);    
                    }    
                    String compositeto = personal + "<" + email + ">";    
                    mailAddr += "," + compositeto;    
                }    
                mailAddr = mailAddr.substring(1);    
            }    
        } else {    
            throw new Exception("Error emailaddr type!");    
        }    
        return mailAddr;    
    }    
     
    /**  
     * 获得邮件发送日期  
     */   
    public static String getSentDate() throws Exception {    
        Date sentdate = mimeMessage.getSentDate();    
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
        return format.format(sentdate);    
    }    
     
    /**  
     * 判断此邮件是否已读，如果未读返回返回false,反之返回true  
     * @return
     * @throws MessagingException
     */
    public static boolean isRead() throws MessagingException {    
        boolean isRead = false;    
        Flags flags = ((Message) mimeMessage).getFlags();    
        Flags.Flag[] flag = flags.getSystemFlags();    
        //System.out.println("flags's length: " + flag.length);    
        for (int i = 0; i < flag.length; i++) {    
            if (flag[i] == Flags.Flag.SEEN) {    
            	isRead = true;    
                //System.out.println("seen Message.......");    
                break;    
            }    
        }    
        return isRead;    
    }    
     
    /** 
     * 获得邮件的优先级 
     * @return 1(High):紧急  3:普通(Normal)  5:低(Low) 
     * @throws MessagingException  
     */  
    public static String getPriority() throws MessagingException {  
        String priority = "普通";  
        String[] headers = mimeMessage.getHeader("X-Priority");  
        if (headers != null) {  
            String headerPriority = headers[0];  
            if (headerPriority.indexOf("1") != -1 || headerPriority.indexOf("High") != -1)  
                priority = "紧急";  
            else if (headerPriority.indexOf("5") != -1 || headerPriority.indexOf("Low") != -1)  
                priority = "低";  
            else  
                priority = "普通";  
        }  
        return priority;  
    }
    
    /**    
     * 判断此邮件是否需要回执，如果需要回执返回"true",否则返回"false"     
     * @return
     * @throws MessagingException
     */
    public static boolean isReplySign() throws MessagingException {    
        boolean replySign = false;    
        String needreply[] = mimeMessage    
                .getHeader("Disposition-Notification-To");    
        if (needreply != null) {    
            replySign = true;    
        }    
        return replySign;    
    } 
    
    /** 
     * 判断邮件中是否包含附件 
     * @param msg 邮件内容 
     * @return 邮件中存在附件返回true，不存在返回false 
     * @throws MessagingException 
     * @throws IOException 
     */  
    public static boolean isContainAttachment(Part part) throws Exception {  
        boolean flag = false;  
        if (part.isMimeType("multipart/*")) {  
            MimeMultipart multipart = (MimeMultipart) part.getContent();  
            int partCount = multipart.getCount();  
            for (int i = 0; i < partCount; i++) {  
                BodyPart bodyPart = multipart.getBodyPart(i);  
                String disp = bodyPart.getDisposition();  
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {  
                    flag = true;  
                } else if (bodyPart.isMimeType("multipart/*")) {  
                    flag = isContainAttachment(bodyPart);  
                } else {  
                    String contentType = bodyPart.getContentType();  
                    if (contentType.indexOf("application") != -1) {  
                        flag = true;  
                    }    
                      
                    if (contentType.indexOf("name") != -1) {  
                        flag = true;  
                    }   
                }  
                  
                if (flag) break;  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            flag = isContainAttachment((Part)part.getContent());  
        }  
        return flag;  
    }  
     
    /**  
     * 保存附件  
     * @param part 邮件中多个组合体中的其中一个组合体  
     * @param destDir  附件保存目录  
     * @throws UnsupportedEncodingException  
     * @throws MessagingException  
     * @throws FileNotFoundException  
     * @throws IOException  
     */  
    public static void saveAttachment(Part part, String destDir) throws Exception {  
        if (part.isMimeType("multipart/*")) {  
            Multipart multipart = (Multipart) part.getContent();    //复杂体邮件  
            //复杂体邮件包含多个邮件体  
            int partCount = multipart.getCount();  
            for (int i = 0; i < partCount; i++) {  
                //获得复杂体邮件中其中一个邮件体  
                BodyPart bodyPart = multipart.getBodyPart(i);  
                //某一个邮件体也有可能是由多个邮件体组成的复杂体  
                String disp = bodyPart.getDisposition();  
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {  
                    InputStream is = bodyPart.getInputStream();  
                    saveFile(is, destDir, MimeUtility.decodeText(bodyPart.getFileName()));  
                } else if (bodyPart.isMimeType("multipart/*")) {  
                    saveAttachment(bodyPart,destDir);  
                } else {  
                    String contentType = bodyPart.getContentType();  
                    if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {  
                        saveFile(bodyPart.getInputStream(), destDir, MimeUtility.decodeText(bodyPart.getFileName()));  
                    }  
                }  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            saveAttachment((Part) part.getContent(),destDir);  
        }  
    } 
    
    private final static String Diagnostic_Code ="diagnostic-code: smtp; ";
    private final static String Final_Recipient ="final-recipient: rfc822; ";
    
    //截取数字  
    public static String getNumbers(String content) {  
        Pattern pattern = Pattern.compile("\\d+");  
        Matcher matcher = pattern.matcher(content);  
        while (matcher.find()) {  
           return matcher.group(0);  
        }  
        return "";  
    }  
      
    // 截取非数字  
    public static String splitNotNumber(String content) {  
        Pattern pattern = Pattern.compile("\\D+");  
        Matcher matcher = pattern.matcher(content);  
        while (matcher.find()) {  
            return matcher.group(0);  
        }  
        return "";  
    }
      
    // 判断一个字符串是否含有数字
    public static boolean HasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }
    
	/**
	 * 解析邮件内容，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件 
	 * 主要是根据MimeType类型的不同执行不同的操作，一步一步的解析
	 */
	public static Map<String, String> getMailContent(Part part) throws Exception {
		String data = null;
		String error_file = ReceiveEmail.class.getResource("/").getPath();
		Map<String, String> result = new HashMap<String,String>();
		try{
			byte[] bits = new byte[20480];
		    int size = part.getInputStream().read(bits);
		    data = new String(bits,0,size,"GBK");
		    String[] datas = data.split("\r\n");
		    StringBuilder msgstr = new StringBuilder();
		    int flag = 0;
		    for(int i = 0, length = datas.length; i < length; i++) {
		    	if(datas[i].toLowerCase().indexOf(Diagnostic_Code) > -1) {
		    		flag = 1;
		    	    String msg = datas[i].toLowerCase().replace(Diagnostic_Code, "").split(": ")[0];
		    	    result.put("code",getNumbers(msg));
		    	    msgstr.append(datas[i]);
		    	}
		    	if(flag == 1 && !datas[i].equals("")){
		    		msgstr.append(datas[i]);
		    	}else if(flag == 1){
		    		break;
		    	}
		    	
		    	result.put("msg",msgstr.toString());
		    }
		    for(int i = 0, length = datas.length; i < length; i++) {
		    	if(datas[i].toLowerCase().indexOf(Final_Recipient) > -1) {
		    		result.put("email",datas[i].toLowerCase().replace(Final_Recipient, ""));
		    	}
		    }
		    
		    if((result.get("code").equals("") && result.get("msg").equals("")) ||
		    		result.get("email") == null || result.get("email").equals("")  ){
		    	saveFile(data, error_file, File.separator + System.currentTimeMillis()+".eml");
		    }
		}catch(Exception e){
			saveFile(data, error_file, File.separator + System.currentTimeMillis()+".eml");
		}
		return result;
	}
    
    /**  
     * 读取输入流中的数据保存至指定目录  
     * @param is 输入流  
     * @param fileName 文件名  
     * @param destDir 文件存储目录  
     * @throws FileNotFoundException  
     * @throws IOException  
     */  
    private static void saveFile(InputStream is, String destDir, String fileName) throws Exception {  
        BufferedInputStream bis = new BufferedInputStream(is);  
        BufferedOutputStream bos = new BufferedOutputStream(  
                new FileOutputStream(new File(destDir + fileName)));  
        int len = -1;  
        while ((len = bis.read()) != -1) {  
            bos.write(len);  
            bos.flush();  
        }  
        bos.close();  
        bis.close();  
    }   
    
    /**  
     * 读取输入流中的数据保存至指定目录  
     * @param content 写入内容
     * @param fileName 文件名  
     * @param destDir 文件存储目录  
     * @throws FileNotFoundException  
     * @throws IOException  
     */  
    private static void saveFile(String content, String destDir, String fileName) throws Exception {   
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(destDir + fileName)));  
        bos.write(content.getBytes());  
        bos.flush();
        bos.close();
    }  
  
	public MimeMessage getMimeMessage() {
		return mimeMessage;
	}
	
	/*public static void main(String[] args) throws Exception {
		System.out.println(receiveEmails("imap.51jrq4.vip","webmail","1q2w3e4r"));
	}*/

}
