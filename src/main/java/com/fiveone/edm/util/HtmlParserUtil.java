package com.fiveone.edm.util;

import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fiveone.edm.common.utils.DESUtil;

import java.util.Random;

/**
 * html转换工具类
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月10日 下午3:36:36
 * @version: 1.0
 * @since: JDK1.7
 */
public class HtmlParserUtil {	
	
	private static final Logger log = Logger.getLogger(HtmlParserUtil.class);
	
	/**
	 * 替换原html中的职位招聘中a链接地址
	 * @param htmlContent
	 * @param server
	 * @param params
	 * @return
	 */
	public static String replaceAllLinkTag(String htmlContent,String server_address,String params) {
		Document doc = Jsoup.parse(htmlContent);
		params = Encrypter.encrypt(params);
		//log.error("_UID参数为："+params);
		Elements ele = doc.select("a[tags]");
		for (Element element : ele) {
			String href = element.attr("href");
			href = Encrypter.encrypt(href);//加密（des）字符
			//log.error("_FORM参数为："+href);
			//log.error("替换前链接为："+element.attr("href"));
			element.attr("href",server_address+"/edmserver/view?_FORM="+href+"&_UID="+params);
		}
		doc.select("div.Subscribe").append("<img src='"+server_address+"/edmserver/image?_FORM="+Encrypter.encrypt("exit.jpg")+"&_UID="+params+"' width='0' height='0' style='display:none' >");
		doc.select("div.Subscribe > a").first().attr("href",server_address+"/edmserver/subscribeto?_UID="+params);
		doc.select("div.Subscribe > a").last().attr("href",server_address+"/edmserver/unsubscribe?_UID="+params);
		return doc.html();
	}
	
	/**
	 * 将需要替换的内容替换
	 * @param htmlContent
	 * @param server
	 * @param params
	 * @return
	 */
	public static String replaceLinkTag(String htmlContent,String server,String params){
		if(htmlContent == null) {
			return "";
		}
		try {  
		      Parser parser = new Parser();  
		      parser.setInputHTML(htmlContent);
		      parser.setEncoding("UTF-8");  
		      NodeList list = parser.parse(null);  
		      resourseTag(list,server,params);
		      return list.toHtml();
		} catch (Exception e) {  
		      e.printStackTrace(); 
		      return null;
		}  
	}

	/**
	 * 替换链接地址或图片地址
	 * @param list
	 * @param server
	 * @param params
	 * @return
	 */
	private static NodeList resourseTag(final NodeList list,final String server,final String params) {
	    if(list == null) {  
	        return null;  
	    }  
	    Node node = null;  
	    SimpleNodeIterator iterator = list.elements();  
	    while(iterator.hasMoreNodes()) {  
	        node = iterator.nextNode();  
	        if(node==null) { 
	            break;
	        }
	        //如果是链接
	        if(node instanceof LinkTag) {  
	        	LinkTag tag = (LinkTag)node;  
	        	String link = tag.getAttribute("href");
	        	link = DESUtil.encrypt(link, getCharAndNum(8));
	        	String newlink = server + "/edmserver/view?_FORM=" + link + "&_UID=" + params+"&"+getCharAndNum(6)+"="+getCharAndNum(8);
	        	tag.setLink(newlink);
	        } 
	        //如果是图片
	        if(node instanceof ImageTag) {
	        	ImageTag img = (ImageTag)node; 
	        	String imgurl = img.getImageURL();
	        	imgurl =  DESUtil.encrypt(imgurl, getCharAndNum(8));
	        	String newurl = server + "/edmserver/image?_FORM=" + imgurl + "&_UID=" + params+"&"+getCharAndNum(6)+"="+getCharAndNum(8);
	        	img.setImageURL(newurl);
	        }
	        //递归调用
	        resourseTag(node.getChildren(),server,params);  
	    }  
	    return null;  
	}  
    
	/**
	 * 生成自定义长度的字符或数字
	 * @param length
	 * @return
	 */
	private static String getCharAndNum(int length) {
		String str = "";
		Random random = new Random();
		for(int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; 
			 // 字符串
			if("char".equalsIgnoreCase(charOrNum)) {
				//取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; 
				str += (char) (choice + random.nextInt(26));
			}
			// 数字
			else if("num".equalsIgnoreCase(charOrNum)) {
				str += String.valueOf(random.nextInt(10));
			}
		}
		return str;
	}

	/*
    public static void main(String []args){
		 try {  
		 	Parser parser = new Parser();
		    parser.setResource("C:\\Users\\Guy\\Desktop\\email.html");
		    parser.setEncoding("UTF-8");
	        NodeList list = parser.parse(null);
	        resourseTag(list,"http://127.0.0.1:8080","fjg840fk");	        
	    } catch (Exception e) {  
	      e.printStackTrace();  
	    }  
	 }*/
}
