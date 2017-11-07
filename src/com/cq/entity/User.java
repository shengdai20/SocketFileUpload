package com.cq.entity;

import java.io.Serializable;  

public class User implements Serializable{  
    /** 
     * 用户的实体类 
     */  
    private static final long serialVersionUID = 4015036439904829095L;  
    private String username;  
    private String password;  
  
    public User() {  
        super();  
        // TODO Auto-generated constructor stub  
    }  
  
    public User(String username, String password) {  
        super();  
        this.username = username;  
        this.password = password;  
    }  
  
    public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
}  