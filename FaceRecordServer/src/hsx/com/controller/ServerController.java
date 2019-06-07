package hsx.com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import hsx.com.entity.FaceRecord;
import hsx.com.service.FaceRecordService;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/server")
public class ServerController{

	@Resource
	private FaceRecordService faceRecordService;
	
	@RequestMapping("/face")	
	public void FaceRecordService(HttpServletRequest request, HttpServletResponse response) throws IOException {
	 String personId=request.getParameter("personId");
     String deviceKey=request.getParameter("deviceKey");
     String type=request.getParameter("type");
     String ip=request.getParameter("ip");
     String gettime=request.getParameter("gettime");
     String path=request.getParameter("path");    
     
     long stamp = Long.parseLong(gettime);
     String timestamp=String.valueOf(stamp/1000);
            
     String time = timeStamp2Date(timestamp, "yyyy-MM-dd HH:mm:ss"); //时间戳转换成日期格式字符串 
     
     FaceRecord faceRecord=new FaceRecord(personId,deviceKey,type,ip,time,path);
     
     System.out.println("接收成功："+"personId："+personId+";"+"deviceKey："+deviceKey+";"+"type："+type+";"+"ip："+ip+";"+"time："+time+";"+"path："+path);
    		     
     faceRecordService.facerecordset(faceRecord);//将数据存入数据库
     
	}
	
	@RequestMapping("/read")	
	public void ReadFaceRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
	   try{
		   ArrayList<FaceRecord> result=faceRecordService.readfacerecordset();//读取数据库
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter out=response.getWriter();
		   JSONObject resultJson=new JSONObject();
		   resultJson.put("result", result);
		   out.println(resultJson);
		   out.flush();
		   out.close();		  
	   }catch (Exception e) {
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter out=response.getWriter();
		   JSONObject resultJson=new JSONObject();
		   resultJson.put("result", 0);
		   out.println(resultJson);
		   out.flush();
		   out.close();	
		  }    		            
	}
	
		@RequestMapping("/delete")	
		public void deletepb(HttpServletRequest request, HttpServletResponse response) throws IOException {
			Integer id=Integer.parseInt(request.getParameter("recordId"));	
		try{
			faceRecordService.deleterecordset(id);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			JSONObject resultJson=new JSONObject();
			resultJson.put("result",1);
			out.println(resultJson);
			out.flush();
			out.close();
		}catch (Exception e) {
		    response.setContentType("text/html;charset=utf-8");
		    PrintWriter out=response.getWriter();
		    JSONObject resultJson=new JSONObject();
		    resultJson.put("result",0);
		    out.println(resultJson);
		    out.flush();
		    out.close(); 
		    e.printStackTrace();		
		}
	  }		
		
		@RequestMapping("/deleteall")	
		public void deleteall(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			faceRecordService.deleteallset();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			JSONObject resultJson=new JSONObject();
			resultJson.put("result",1);
			out.println(resultJson);
			out.flush();
			out.close();
		}catch (Exception e) {
		    response.setContentType("text/html;charset=utf-8");
		    PrintWriter out=response.getWriter();
		    JSONObject resultJson=new JSONObject();
		    resultJson.put("result",0);
		    out.println(resultJson);
		    out.flush();
		    out.close(); 
		    e.printStackTrace();		
		}
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
}

    

