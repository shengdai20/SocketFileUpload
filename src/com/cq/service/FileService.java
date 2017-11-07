package com.cq.service;

import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;

import com.cq.entity.File;
import com.cq.util.DBHelper;  
  
  
/** 
 * @author scx 
 * 文件服务类 
 */  
public class FileService {  
    //上传文件  
    public boolean uploadFile(File file) {  
        Connection conn = null;  
        PreparedStatement ps = null;  
        conn = DBHelper.getConnection();  
        String sql = "insert into file (username,filename,filecontent) values(?,?,?)";  
        try {  
            ps = conn.prepareStatement(sql);  
            ps.setString(1, file.getUsername().trim());  
            ps.setString(2, file.getFname().trim());  
            ps.setBytes(3, file.getFcontent());  
            int n=ps.executeUpdate();  
            if(n>0){  
                return true;  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                if(conn!=null)  
                    conn.close();  
                if(ps!=null)  
                    ps.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        return false;  
    }  
}  