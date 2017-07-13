package com.fiveone.edm.service;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.servlet.http.HttpUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CaoYongHao.
 * Date: 2017/04/06
 */
public class JsoupTest {


    private Logger logger=Logger.getLogger(JsoupTest.class);


    int pageid = 1;

    int i = 0;
    int j = 0;

    @Test
    public void wuYiJob() throws Exception {
        WritableWorkbook wwb = null;
        WritableSheet ws=null;
        wwb = Workbook.createWorkbook(new File("E:\\excel2.xls"));
        Map<String,List<String>> map=new HashMap<>();
        ws  = wwb.createSheet("sheet1", 0);

        for (int page = 1; page <= 3; page++) {
            String url = "http://search.51job.com/jobsearch/search_result.php?fromJs=1&" +
                    "jobarea=020000%2C00&district=000000&funtype=0000&industrytype=03&issuedate=9&providesalary=99&keywordtype=2" +
                    "&curr_page=" +page+
                    "&lang=c&stype=1" +
                    "&postchannel=0000&workyear=99&" +
                    "cotype=99&degreefrom=02&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&list_type=0&fromType=14&dibiaoid=0&confirmdate=9";

            Document doc = Jsoup
                    .connect(url)
                    .timeout(10000)
                    .get();
            Elements divs = doc.select("div[class=el]");
            for (Element div : divs) {
                System.out.println("**************************href:" + div.select("span[class=t2]").select("a").attr("href"));

                String href = div.select("span[class=t2]").select("a").attr("href");
                String name = div.select("span[class=t2]").select("a").attr("title");
                String addr = div.select("span[class=t3]").text();
                if (null != href && href.contains("http")&&!map.containsKey(name)&&addr.contains("上海")) {

                    Document docC = Jsoup
                            .connect(href)
                            .timeout(10000)
                            .get();
                    String companyName="";
                     companyName = docC.select("div.tHeader.tHCop").select("div.in").select("h1").attr("title");//公司名称
                    if ("".equals(companyName)) {
                        companyName = docC.select("div.tHeader.tHCop").select("div.in.img_on").select("h1").attr("title");//公司名称
                    }
                    String type = docC.select("p[class=ltype]").text().replace(Jsoup.parse("&nbsp;").text(), "") ;
                    String str[]=type.split("\\|");
                    String ty=str.length>0?str[0]:"";
                    String count=str.length>1?str[1]:"";
                    String industry=str.length>2?str[2]:"";
                    String content = docC.select("div[class=con_msg]").select("div[class=in]").select("p").text();
                    String address = docC.select("div.bmsg.inbox").select("p.fp").text().replace("公司地址：", "");

                    List<String> list=new ArrayList<>();
                    list.add(ty);
                    list.add(count);
                    list.add(industry);
                    list.add(content);
                    list.add(address);
                    list.add(addr);

                    map.put(companyName,list);

                    System.out.println("第" + map.size() + "个数据");

                }

            }

        System.out.println("爬取完第" + page + "页");
    }


        if (wwb != null) {
            for (String companyName : map.keySet()) {
                //创建一个可写入的工作表
                //Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
                //这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行
                List<String> list=map.get(companyName);
                Label label1 = new Label(0, i, companyName);
                Label label2 = new Label(1, i, list.get(0));
                Label label3 = new Label(2, i, list.get(1));
                Label label4 = new Label(3, i, list.get(2));
                Label label5 = new Label(4, i, list.get(3));
                Label label6 = new Label(5, i, list.get(4));
                Label label7 = new Label(6, i, list.get(5));
                try {
                    //将生成的单元格添加到工作表中
                    ws.addCell(label1);
                    ws.addCell(label2);
                    ws.addCell(label3);
                    ws.addCell(label4);
                    ws.addCell(label5);
                    ws.addCell(label6);
                    ws.addCell(label7);
                    i++;
                    System.out.println("写入" + i + "行");
                } catch (RowsExceededException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            //从内存中写入文件中
            wwb.write();
            //关闭资源，释放内存
            wwb.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }

    System.out.println("爬取完成");
}






    @Test
    public void wuYiJobss() throws Exception {
        WritableWorkbook wwb = null;
        WritableSheet ws=null;

        Map<String,List<String>> map=new HashMap<>();
        wwb = Workbook.createWorkbook(new File("E:\\51Job2.xls"));
        ws  = wwb.createSheet("sheet1", 0);

        //初中
        for (int page = 1; page <= 6; page++) {
            String url = "http://search.51job.com/jobsearch/search_result.php?fromJs=1&jobarea=020000%2C00&" +
                    "district=000000&funtype=0000&industrytype=03&issuedate=9&providesalary=99&keywordtype=2" +
                    "&curr_page=" +page+
                    "&lang=c&stype=1" +
                    "&postchannel=0000&workyear=99&cotype=99&degreefrom=01&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&list_type=0&fromType=14&dibiaoid=0&confirmdate=9";

            Document doc = Jsoup
                    .connect(url)
                    .timeout(10000)
                    .get();
            Elements divs = doc.select("div[class=el]");
            for (Element div : divs) {
                System.out.println("**************************href:" + div.select("span[class=t2]").select("a").attr("href"));

                String href = div.select("span[class=t2]").select("a").attr("href");
                String name = div.select("span[class=t2]").select("a").attr("title");
                String addr = div.select("span[class=t3]").text();
                if (null != href && href.contains("http")&&!map.containsKey(name)&&addr.contains("上海")) {

                    Document docC = Jsoup
                            .connect(href)
                            .timeout(10000)
                            .get();
                    String companyName="";
                    companyName = docC.select("div.tHeader.tHCop").select("div.in").select("h1").attr("title");//公司名称
                    if ("".equals(companyName)) {
                        companyName = docC.select("div.tHeader.tHCop").select("div.in.img_on").select("h1").attr("title");//公司名称
                    }
                    String type = docC.select("p[class=ltype]").text().replace(Jsoup.parse("&nbsp;").text(), "") ;
                    String str[]=type.split("\\|");
                    String ty=str.length>0?str[0]:"";
                    String count=str.length>1?str[1]:"";
                    String industry=str.length>2?str[2]:"";
                    String content = docC.select("div[class=con_msg]").select("div[class=in]").select("p").text();
                    String address = docC.select("div.bmsg.inbox").select("p.fp").text().replace("公司地址：", "");
                    List<String> list=new ArrayList<>();
                    list.add(ty);
                    list.add(count);
                    list.add(industry);
                    list.add(content);
                    list.add(address);
                    list.add(addr);

                    map.put(companyName,list);

                    System.out.println("第" + map.size() + "个数据");
                }

            }

            System.out.println("爬取完第" + page + "页");
        }


        //高中
        for (int page = 1; page <= 227; page++) {
            String url = "http://search.51job.com/jobsearch/search_result.php?fromJs=1&" +
                    "jobarea=020000%2C00&district=000000&funtype=0000&industrytype=03&issuedate=9&providesalary=99&keywordtype=2" +
                    "&curr_page=" +page+
                    "&lang=c&stype=1" +
                    "&postchannel=0000&workyear=99&" +
                    "cotype=99&degreefrom=02&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&list_type=0&fromType=14&dibiaoid=0&confirmdate=9";

            Document doc = Jsoup
                    .connect(url)
                    .timeout(10000)
                    .get();
            Elements divs = doc.select("div[class=el]");
            for (Element div : divs) {
                System.out.println("**************************href:" + div.select("span[class=t2]").select("a").attr("href"));

                String href = div.select("span[class=t2]").select("a").attr("href");
                String name = div.select("span[class=t2]").select("a").attr("title");
                String addr = div.select("span[class=t3]").text();
                if (null != href && href.contains("http")&&!map.containsKey(name)&&addr.contains("上海")) {

                    Document docC = Jsoup
                            .connect(href)
                            .timeout(10000)
                            .get();
                    String companyName="";
                    companyName = docC.select("div.tHeader.tHCop").select("div.in").select("h1").attr("title");//公司名称
                    if ("".equals(companyName)) {
                        companyName = docC.select("div.tHeader.tHCop").select("div.in.img_on").select("h1").attr("title");//公司名称
                    }
                    String type = docC.select("p[class=ltype]").text().replace(Jsoup.parse("&nbsp;").text(), "") ;
                    String str[]=type.split("\\|");
                    String ty=str.length>0?str[0]:"";
                    String count=str.length>1?str[1]:"";
                    String industry=str.length>2?str[2]:"";
                    String content = docC.select("div[class=con_msg]").select("div[class=in]").select("p").text();
                    String address = docC.select("div.bmsg.inbox").select("p.fp").text().replace("公司地址：", "");
                    List<String> list=new ArrayList<>();
                    list.add(ty);
                    list.add(count);
                    list.add(industry);
                    list.add(content);
                    list.add(address);
                    list.add(addr);

                    map.put(companyName,list);

                    System.out.println("第" + map.size() + "个数据");
                }

            }

            System.out.println("爬取完第" + page + "页");
        }



        //大专
        for (int page = 1; page <= 980; page++) {
            String url = "http://search.51job.com/jobsearch/search_result.php?" +
                    "fromJs=1&jobarea=020000%2C00&district=000000&funtype=0000&" +
                    "industrytype=03&issuedate=9&providesalary=99&keywordtype=2&curr_page=" +page+
                    "&lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=03&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&list_type=0&fromType=14&dibiaoid=0&confirmdate=9";

            Document doc = Jsoup
                    .connect(url)
                    .timeout(10000)
                    .get();
            Elements divs = doc.select("div[class=el]");
            for (Element div : divs) {
                System.out.println("**************************href:" + div.select("span[class=t2]").select("a").attr("href"));

                String href = div.select("span[class=t2]").select("a").attr("href");
                String name = div.select("span[class=t2]").select("a").attr("title");
                String addr = div.select("span[class=t3]").text();
                if (null != href && href.contains("http")&&!map.containsKey(name)&&addr.contains("上海")) {

                    Document docC = Jsoup
                            .connect(href)
                            .timeout(10000)
                            .get();
                    String companyName="";
                    companyName = docC.select("div.tHeader.tHCop").select("div.in").select("h1").attr("title");//公司名称
                    if ("".equals(companyName)) {
                        companyName = docC.select("div.tHeader.tHCop").select("div.in.img_on").select("h1").attr("title");//公司名称
                    }
                    String type = docC.select("p[class=ltype]").text().replace(Jsoup.parse("&nbsp;").text(), "") ;
                    String str[]=type.split("\\|");
                    String ty=str.length>0?str[0]:"";
                    String count=str.length>1?str[1]:"";
                    String industry=str.length>2?str[2]:"";
                    String content = docC.select("div[class=con_msg]").select("div[class=in]").select("p").text();
                    String address = docC.select("div.bmsg.inbox").select("p.fp").text().replace("公司地址：", "");
                    List<String> list=new ArrayList<>();
                    list.add(ty);
                    list.add(count);
                    list.add(industry);
                    list.add(content);
                    list.add(address);
                    list.add(addr);

                    map.put(companyName,list);

                    System.out.println("第" + map.size() + "个数据");
                }

            }

            System.out.println("爬取完第" + page + "页");
        }


        //本科
        for (int page = 1; page <= 471; page++) {
            String url = "http://search.51job.com/jobsearch/search_result.php?fromJs=1&" +
                    "jobarea=020000%2C00&district=000000&funtype=0000&industrytype=03&issuedate=9&providesalary=99&keywordtype=2&curr_page=" +page+
                    "&lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=04&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&list_type=0&fromType=14&dibiaoid=0&confirmdate=9";

            Document doc = Jsoup
                    .connect(url)
                    .timeout(10000)
                    .get();
            Elements divs = doc.select("div[class=el]");
            for (Element div : divs) {
                System.out.println("**************************href:" + div.select("span[class=t2]").select("a").attr("href"));

                String href = div.select("span[class=t2]").select("a").attr("href");
                String name = div.select("span[class=t2]").select("a").attr("title");
                String addr = div.select("span[class=t3]").text();
                if (null != href && href.contains("http")&&!map.containsKey(name)&&addr.contains("上海")) {

                    Document docC = Jsoup
                            .connect(href)
                            .timeout(10000)
                            .get();
                    String companyName="";
                    companyName = docC.select("div.tHeader.tHCop").select("div.in").select("h1").attr("title");//公司名称
                    if ("".equals(companyName)) {
                        companyName = docC.select("div.tHeader.tHCop").select("div.in.img_on").select("h1").attr("title");//公司名称
                    }
                    String type = docC.select("p[class=ltype]").text().replace(Jsoup.parse("&nbsp;").text(), "") ;
                    String str[]=type.split("\\|");
                    String ty=str.length>0?str[0]:"";
                    String count=str.length>1?str[1]:"";
                    String industry=str.length>2?str[2]:"";
                    String content = docC.select("div[class=con_msg]").select("div[class=in]").select("p").text();
                    String address = docC.select("div.bmsg.inbox").select("p.fp").text().replace("公司地址：", "");
                    List<String> list=new ArrayList<>();
                    list.add(ty);
                    list.add(count);
                    list.add(industry);
                    list.add(content);
                    list.add(address);
                    list.add(addr);

                    map.put(companyName,list);

                    System.out.println("第" + map.size() + "个数据");
                }

            }

            System.out.println("爬取完第" + page + "页");
        }


        //硕士
        for (int page = 1; page <= 38; page++) {
            String url = "http://search.51job.com/jobsearch/search_result.php?fromJs=1" +
                    "&jobarea=020000%2C00&district=000000&funtype=0000&industrytype=03&" +
                    "issuedate=9&providesalary=99&keywordtype=2&curr_page=" +page+
                    "&lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=05&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&list_type=0&fromType=14&dibiaoid=0&confirmdate=9";

            Document doc = Jsoup
                    .connect(url)
                    .timeout(10000)
                    .get();
            Elements divs = doc.select("div[class=el]");
            for (Element div : divs) {
                System.out.println("**************************href:" + div.select("span[class=t2]").select("a").attr("href"));

                String href = div.select("span[class=t2]").select("a").attr("href");
                String name = div.select("span[class=t2]").select("a").attr("title");
                String addr = div.select("span[class=t3]").text();
                if (null != href && href.contains("http")&&!map.containsKey(name)&&addr.contains("上海")) {

                    Document docC = Jsoup
                            .connect(href)
                            .timeout(10000)
                            .get();
                    String companyName="";
                    companyName = docC.select("div.tHeader.tHCop").select("div.in").select("h1").attr("title");//公司名称
                    if ("".equals(companyName)) {
                        companyName = docC.select("div.tHeader.tHCop").select("div.in.img_on").select("h1").attr("title");//公司名称
                    }
                    String type = docC.select("p[class=ltype]").text().replace(Jsoup.parse("&nbsp;").text(), "") ;
                    String str[]=type.split("\\|");
                    String ty=str.length>0?str[0]:"";
                    String count=str.length>1?str[1]:"";
                    String industry=str.length>2?str[2]:"";
                    String content = docC.select("div[class=con_msg]").select("div[class=in]").select("p").text();
                    String address = docC.select("div.bmsg.inbox").select("p.fp").text().replace("公司地址：", "");
                    List<String> list=new ArrayList<>();
                    list.add(ty);
                    list.add(count);
                    list.add(industry);
                    list.add(content);
                    list.add(address);
                    list.add(addr);

                    map.put(companyName,list);

                    System.out.println("第" + map.size() + "个数据");
                }

            }

            System.out.println("爬取完第" + page + "页");
        }



        //博士
        for (int page = 1; page <= 1; page++) {
            String url = "http://search.51job.com/list/020000,000000,0000,03,9,99,%2B,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=06&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=7&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

            Document doc = Jsoup
                    .connect(url)
                    .timeout(10000)
                    .get();
            Elements divs = doc.select("div[class=el]");
            for (Element div : divs) {
                System.out.println("**************************href:" + div.select("span[class=t2]").select("a").attr("href"));

                String href = div.select("span[class=t2]").select("a").attr("href");
                String name = div.select("span[class=t2]").select("a").attr("title");
                String addr = div.select("span[class=t3]").text();
                if (null != href && href.contains("http")&&!map.containsKey(name)&&addr.contains("上海")) {

                    Document docC = Jsoup
                            .connect(href)
                            .timeout(10000)
                            .get();
                    String companyName="";
                    companyName = docC.select("div.tHeader.tHCop").select("div.in").select("h1").attr("title");//公司名称
                    if ("".equals(companyName)) {
                        companyName = docC.select("div.tHeader.tHCop").select("div.in.img_on").select("h1").attr("title");//公司名称
                    }
                    String type = docC.select("p[class=ltype]").text().replace(Jsoup.parse("&nbsp;").text(), "") ;
                    String str[]=type.split("\\|");
                    String ty=str.length>0?str[0]:"";
                    String count=str.length>1?str[1]:"";
                    String industry=str.length>2?str[2]:"";
                    String content = docC.select("div[class=con_msg]").select("div[class=in]").select("p").text();
                    String address = docC.select("div.bmsg.inbox").select("p.fp").text().replace("公司地址：", "");
                    List<String> list=new ArrayList<>();
                    list.add(ty);
                    list.add(count);
                    list.add(industry);
                    list.add(content);
                    list.add(address);
                    list.add(addr);

                    map.put(companyName,list);

                    System.out.println("第" + map.size() + "个数据");
                }

            }

            System.out.println("爬取完第" + page + "页");
        }


        System.out.println("总共第" + map.size() + "个数据");



        if (wwb != null) {
            for (String companyName : map.keySet()) {
                //创建一个可写入的工作表
                //Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
                //这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行
                List<String> list=map.get(companyName);
                Label label1 = new Label(0, i, companyName);
                Label label2 = new Label(1, i, list.get(0));
                Label label3 = new Label(2, i, list.get(1));
                Label label4 = new Label(3, i, list.get(2));
                Label label5 = new Label(4, i, list.get(3));
                Label label6 = new Label(5, i, list.get(4));
                Label label7 = new Label(6, i, list.get(5));
                try {
                    //将生成的单元格添加到工作表中
                    ws.addCell(label1);
                    ws.addCell(label2);
                    ws.addCell(label3);
                    ws.addCell(label4);
                    ws.addCell(label5);
                    ws.addCell(label6);
                    ws.addCell(label7);
                    i++;
                    System.out.println("写入" + i + "行");
                } catch (RowsExceededException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            //从内存中写入文件中
            wwb.write();
            //关闭资源，释放内存
            wwb.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }

        System.out.println("爬取完成");
    }






    @Test
    public void textExcel() throws Exception {
        writeExcel("E:\\e.xls");
    }


    /**
     * 生成一个Excel文件
     *
     * @param fileName 要生成的Excel文件名
     */
    public void writeExcel(String fileName) throws Exception {
        WritableWorkbook wwb = null;
        try {
            //首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
            wwb = Workbook.createWorkbook(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (wwb != null) {
            //创建一个可写入的工作表
            //Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
            WritableSheet ws = wwb.createSheet("sheet1", 0);

            //下面开始添加单元格
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 5; j++) {
                    //这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行
                    Label labelC = new Label(j, i, "这是第" + (i + 1) + "行，第" + (j + 1) + "列");
                    try {
                        //将生成的单元格添加到工作表中
                        ws.addCell(labelC);
                    } catch (RowsExceededException e) {
                        e.printStackTrace();
                    } catch (WriteException e) {
                        e.printStackTrace();
                    }

                }
            }

            try {
                //从内存中写入文件中
                wwb.write();
                //关闭资源，释放内存
                wwb.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
    }





    @Test
    public void zhiLian() throws Exception {
        WritableWorkbook wwb = null;
        WritableSheet ws=null;
        wwb = Workbook.createWorkbook(new File("E:\\zhiLian.xls"));
        Map<String,List<String>> map=new HashMap<>();
        ws  = wwb.createSheet("sheet1", 0);

        for (int page = 1; page <= 100; page++) {
            String url = "http://sou.zhaopin.com/jobs/searchresult.ashx?" +
                    "in=180000%3b180100%3b300500%3b300900&jl=上海&sm=0&isfilter=0&fl=538" +
                    "&isadv=0&sg=f265fedecafd4291af29226f5f88e76a&p="+page;

            Document doc = Jsoup
                    .connect(url)
                    .timeout(10000)
                    .get();
            Elements divs = doc.select("td.gsmc");
            for (Element div : divs) {
                System.out.println("**************************href:" + div.select("a").attr("href"));

                String href = div.select("a").attr("href");
                String name = div.select("a").text().trim();
                String addr = div.nextElementSibling().nextElementSibling().text();
                if (null != href && href.contains("http")&&!map.containsKey(name)&&addr.contains("上海")) {
                    Document docC=null;
                    try {
                     docC = Jsoup
                            .connect(href)
                            .timeout(10000)
                            .get();
                    }catch (Exception e){
                        logger.error(e.getMessage());
                        continue;
                    }

                    String ty=docC.select("table.comTinyDes").select("span:contains(" + "公司性质" + ")").parents().next("td").text();
                    String count=docC.select("table.comTinyDes").select("span:contains("+"公司规模"+")").parents().next("td").text();
                    String industry=docC.select("table.comTinyDes").select("span:contains(" + "公司行业" + ")").parents().next("td").text();
                    String content = docC.select("div.company-content").text();
                    String address = docC.select("table.comTinyDes").select("span.comAddress").text().replace("公司地址：", "");
                    String site = docC.select("table.comTinyDes").select("span:contains(" + "公司网站" + ")").parents().next("td").text();

                    if (null!=address&&!"".equals(address)) {
                        List<String> list = new ArrayList<>();
                        list.add(ty);
                        list.add(count);
                        list.add(industry);
                        list.add(content);
                        list.add(address);
                        list.add(addr);
                        list.add(site);

                        map.put(name, list);
                    }
                    System.out.println("第" + map.size() + "个数据");

                }

            }

            System.out.println("爬取完第" + page + "页");
        }


        if (wwb != null) {
            for (String companyName : map.keySet()) {
                //创建一个可写入的工作表
                //Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
                //这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行
                List<String> list=map.get(companyName);
                Label label1 = new Label(0, i, companyName);
                Label label2 = new Label(1, i, list.get(0));
                Label label3 = new Label(2, i, list.get(1));
                Label label4 = new Label(3, i, list.get(2));
                Label label5 = new Label(4, i, list.get(3));
                Label label6 = new Label(5, i, list.get(4));
                Label label7 = new Label(6, i, list.get(5));
                Label label8 = new Label(7, i, list.get(6));
                try {
                    //将生成的单元格添加到工作表中
                    ws.addCell(label1);
                    ws.addCell(label2);
                    ws.addCell(label3);
                    ws.addCell(label4);
                    ws.addCell(label5);
                    ws.addCell(label6);
                    ws.addCell(label7);
                    ws.addCell(label8);
                    i++;
                    System.out.println("写入" + i + "行");
                } catch (RowsExceededException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            //从内存中写入文件中
            wwb.write();
            //关闭资源，释放内存
            wwb.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }

        System.out.println("爬取完成");
    }


}
