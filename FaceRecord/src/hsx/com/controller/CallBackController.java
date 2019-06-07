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

	//������־����
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
     
     //System.out.println("ʱ�����ֵ ��"+stamp);
     //System.out.println("ʱ��� ��"+timestamp);        
     String time = timeStamp2Date(timestamp, "yyyy-MM-dd HH:mm:ss"); //ʱ���ת�������ڸ�ʽ�ַ��� 
     //System.out.println("ʶ��ʱ��:"+time);
	 //��ӡ��־
	 ((Category) logger).info("ʶ��ʱ��:"+time);
     
     String url=path; 
     String pic=personId+"_"+timestamp;
     //System.out.println(pic);
     String download="c:/FaceImg/"+pic+".jpg";
     downloadPicture(url,download);//������ʶ���¼��ͼƬ���ص�����  
     //String encode=downloadPicture(url,download);//������ʶ���¼��ͼƬ���ص�����     
     //System.out.println(encode);//���Base64�ַ���
     
     //FaceRecord faceRecord=new FaceRecord(personId,deviceKey,type,ip,time,path);
     
     //System.out.println("ʶ���¼��"+"personId��"+personId+";"+"deviceKey��"+deviceKey+";"+"type��"+type+";"+"ip��"+ip+";"+"time��"+time+";"+"path��"+path);
	 //��ӡ��־
	 ((Category) logger).info("ʶ���¼��"+"personId��"+personId+";"+"deviceKey��"+deviceKey+";"+"type��"+type+";"+"ip��"+ip+";"+"time��"+time+";"+"path��"+path);
     
     //���� POST ����
     String sr=sendPost("http://hsxdy123.work/FaceRecordServer/server/face.do", "personId="+personId+"&deviceKey="+deviceKey+"&type="+type+"&ip="+ip+"&gettime="+gettime+"&path="+path);
     System.out.println(sr);
	 	 
     //faceRecordService.facerecordset(faceRecord);//�����ݴ������ݿ�
     
     //�ص���ַ�ӿڽ��յ�ʶ�����ݺ󣬷���{"result":1,"success":true}
     response.setContentType("text/html;charset=utf-8");
	 PrintWriter out=response.getWriter();
	 JSONObject resultJson=new JSONObject();
	 resultJson.put("result",1);
	 resultJson.put("success",true);
	 out.println(resultJson);
	 out.flush();
	 out.close();
	 	 
  }
	 
    //ʱ���ת�������ڸ�ʽ�ַ��� 
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
    
    //������ʶ���¼����Ƭ���ص�����
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
            String encode = encoder.encode(buffer);//����Base64��������ֽ������ַ���
            //System.out.println(encode);
            fileOutputStream.write(output.toByteArray());//Base64ת�����ļ����ص�����
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
     * ��ָ�� URL����POST����������
     * @param url ��������� URL
     * @param param ����������������Ӧ���� name1=value1&name2=value2����ʽ��
     * @return ������Զ����Դ����Ӧ���
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // �򿪺�URL֮�������
            URLConnection conn = realUrl.openConnection();
            // ����ͨ�õ���������
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����POST�������������������
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // ��ȡURLConnection�����Ӧ�������
            out = new PrintWriter(conn.getOutputStream());
            // �����������
            out.print(param);
            // flush������Ļ���
            out.flush();
            // ����BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("���� POST��������쳣��"+e);
            e.printStackTrace();
        }
        //ʹ��finally�����ر��������������
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

    

