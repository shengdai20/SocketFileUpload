package com.cq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** 
 * @author scx 
 *  数据库 
 */  
public class DBHelper {  
    private static final String driver="com.mysql.jdbc.Driver";  
    private static final String url="jdbc:mysql://localhost:3306/file_upload?useUnicode=true&charcterEncoding=UTF-8";  
    private static final String username="root";  
    private static final String password="root";  
    private static  Connection con=null;  
    //静态块代码负责加载驱动  
    static  
    {  
        try {  
            Class.forName(driver);  
        } catch (ClassNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
    public static Connection getConnection(){  
  
        if(con==null){  
            try {  
                con=DriverManager.getConnection(url, username, password);  
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
        return con;  
    }  
}     