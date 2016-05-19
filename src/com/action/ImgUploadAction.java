package com.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ImgUploadAction extends ActionSupport {  
	private static final long serialVersionUID = 1L;
	private String err = "";  
    private String msg;              //������Ϣ  
    private File fileData;           //�ϴ��ļ�  
    private String fileDataFileName; //�ļ���  
  
    public String imgUpload() {  
        //��ȡresponse��request����  
        ActionContext ac = ActionContext.getContext();  
        HttpServletResponse response = (HttpServletResponse) ac.get(ServletActionContext.HTTP_RESPONSE);  
        HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);  
          
        response.setContentType("text/html;charset=gbk");  
          
        PrintWriter out = null;  
        try {  
            out = response.getWriter();  
        } catch (IOException e1) {  
            e1.printStackTrace();  
        }  
        
        //�õ�ͼƬ����·�������·���Ƿ���������Ŀ·����upload
        String saveRealFilePath = ServletActionContext.getServletContext().getRealPath("/upload");  
        File fileDir = new File(saveRealFilePath);  
        if (!fileDir.exists()) { //��������� �򴴽�   
            fileDir.mkdirs();  
        }  
        File savefile;  
        savefile = new File(saveRealFilePath + "/" + fileDataFileName); 
        try {  
            FileUtils.copyFile(fileData, savefile);  
        } catch (IOException e) {  
            err = "����"+e.getMessage();  
            e.printStackTrace();  
        }  
        String file_Name = request.getContextPath() + "/upload/" + fileDataFileName;  
        
        msg = "{\"success\":\"" + true + "\",\"file_path\":\"" + file_Name + "\"}";  
        out.print(msg); //����msg��Ϣ  
        return null;  
    }  
  
    public String getErr() {  
        return err;  
    }  
    public void setErr(String err) {  
        this.err = err;  
    }  
    
    public String getMsg() {  
        return msg;  
    }  
    public void setMsg(String msg) {  
        this.msg = msg;  
    }
    
	public File getFileData() {
		return fileData;
	}
	public void setFileData(File fileData) {
		this.fileData = fileData;
	}
	
	public String getFileDataFileName() {
		return fileDataFileName;
	}
	public void setFileDataFileName(String fileDataFileName) {
		this.fileDataFileName = fileDataFileName;
	}  
}   
