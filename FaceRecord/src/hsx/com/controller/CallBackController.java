package hsx.com.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import hsx.com.entity.FaceRecord;
import hsx.com.service.FaceRecordService;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;


@Controller
@RequestMapping("/face")
public class CallBackController{

	//声明日志对象
    Logger logger=Logger.getLogger(CallBackController.class);
	
	@Resource
	private FaceRecordService faceRecordService;
	
	@RequestMapping("/record")	
	public void FaceRecordService(HttpServletRequest request, HttpServletResponse response) throws IOException {
	 String personId=request.getParameter("personId");
     String deviceKey=request.getParameter("deviceKey");
     String type=request.getParameter("type");
     String ip=request.getParameter("ip");
     String gettime=request.getParameter("time");
     String path=request.getParameter("path");    
     
     long stamp = Long.parseLong(gettime);
     String timestamp=String.valueOf(stamp/1000);
     
     //System.out.println("时间戳初值 ："+stamp);
     //System.out.println("时间戳 ："+timestamp);        
     String time = timeStamp2Date(timestamp, "yyyy-MM-dd HH:mm:ss"); //时间戳转换成日期格式字符串 
     //System.out.println("识别时间:"+time);
	 //打印日志
	 ((Category) logger).info("识别时间:"+time);
     
     String url=path; 
     String pic=personId+"_"+timestamp;
     //System.out.println(pic);
     String download="c:/FaceImg/"+pic+".jpg";
     downloadPicture(url,download);//将人脸识别记录的图片下载到本地  
     //String encode=downloadPicture(url,download);//将人脸识别记录的图片下载到本地     
     //System.out.println(encode);//输出Base64字符串
     
     //FaceRecord faceRecord=new FaceRecord(personId,deviceKey,type,ip,time,path);
     
     //System.out.println("识别记录："+"personId："+personId+";"+"deviceKey："+deviceKey+";"+"type："+type+";"+"ip："+ip+";"+"time："+time+";"+"path："+path);
	 //打印日志
	 ((Category) logger).info("识别记录："+"personId："+personId+";"+"deviceKey："+deviceKey+";"+"type："+type+";"+"ip："+ip+";"+"time："+time+";"+"path："+path);
     
     //发送 POST 请求
     String sr=sendPost("http://hsxdy123.work/FaceRecordServer/server/face.do", "personId="+personId+"&deviceKey="+deviceKey+"&type="+type+"&ip="+ip+"&gettime="+gettime+"&path="+path);
     System.out.println(sr);
	 	 
     //faceRecordService.facerecordset(faceRecord);//将数据存入数据库
     
     //回调地址接口接收到识别数据后，返回{"result":1,"success":true}
     response.setContentType("text/html;charset=utf-8");
	 PrintWriter out=response.getWriter();
	 JSONObject resultJson=new JSONObject();
	 resultJson.put("result",1);
	 resultJson.put("success",true);
	 out.println(resultJson);
	 out.flush();
	 out.close();
	 	 
  }
	 
    //时间戳转换成日期格式字符串 
    public static String timeStamp2Date(String seconds,String format) {  
	  if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
		      return "";  
	       }  
	  if(format == null || format.isEmpty()){
		   format = "yyyy-MM-dd HH:mm:ss";
		        }   
		   SimpleDateFormat sdf = new SimpleDateFormat(format);  
		      return sdf.format(new Date(Long.valueOf(seconds+"000")));  
	  } 
    
    //将人脸识别记录的照片下载到本地
    private static String downloadPicture(String urlList,String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(buffer);//返回Base64编码过的字节数组字符串
            //System.out.println(encode);
            fileOutputStream.write(output.toByteArray());//Base64转换成文件下载到本地
            dataInputStream.close();
            fileOutputStream.close();  
            return encode;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 向指定 URL发送POST方法的请求
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
}

    

