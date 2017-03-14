package com.fiveone.edm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

/**
 * HttpClient通常用来作为一个模拟http请求的工具。
 * 我们最常见的get, put, post, delete等方法在其中都通过具体定义的类来实现。
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月10日 下午1:58:15
 * @version: 1.0
 * @since: JDK1.7
 */
public class HttpClientUtil {
	
	/**
	 * 通过HttpClient发送Http请求的步骤
	 * 1. 创建HttpClient对象。
	 * 2. 构造Http请求对象。
	 * 3. 执行HttpClient对象的execute方法，将Http请求对象作为该方法的参数。
	 * 4. 读取execute方法返回的HttpResponse结果并解析。
	 */
	
	private static final CloseableHttpClient httpClient;
	
    public static final String CHARSET = "UTF-8";
 
    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }
 
    public static String doGet(String url, Map<String, String> params){
        return doGet(url, params,CHARSET);
    }
    
    public static String doPost(String url, Map<String, String> params){
        return doPost(url, params,CHARSET);
    }
    
    /**
     * HTTP Get 		获取内容
     * @param url  		请求的url地址 ?之前的地址
     * @param params 	请求的参数
     * @param charset   编码格式
     * @return    		页面内容
     */
    public static String doGet(String url,Map<String,String> params,String charset){
        if(StringUtils.isBlank(url)) {
            return null;
        }
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, CHARSET);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
    /**
     * HTTP Post 		获取内容
     * @param url  		请求的url地址 ?之前的地址
     * @param params 	请求的参数
     * @param charset   编码格式
     * @return    		页面内容
     */
    public static String doPost(String url,Map<String,String> params,String charset){
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if(params != null && !params.isEmpty()){
                pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            if(pairs != null && pairs.size() > 0){
                httpPost.setEntity(new UrlEncodedFormEntity(pairs,CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, CHARSET);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    public static void main(String []args) throws Exception{
        String getData = doGet("http://www.51jrq.com/",null);
        System.out.println(getData);
        System.out.println("----------------------分割线-----------------------");
        String postData = doPost("http://www.51jrq.com/",null);
        System.out.println(postData);
        
    	Parser parser = new Parser(); 	   	  
	    parser.setResource("C:\\Users\\Guy\\Desktop\\email.html");
	    parser.setEncoding("UTF-8");  
        NodeList list = parser.parse(null);  
    	
    	String content =list.toHtml();
    	
    	Map<String,String> params = new HashMap<String, String>();
    	params.put("pro_id", "2");
		params.put("subject", "subject");
		params.put("content", content);
		params.put("recipient", "chenz@chyjr.com");
		params.put("username", "5329422@qq.com");
		params.put("password", "353455");
		params.put("smtpHostName", "smtp.qq.com");
		
        String postData = doPost("http://127.0.0.1:8080/edm/send",params);
        System.out.println(postData);
    }*/
}
