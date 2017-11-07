package com.cq.socket;

import java.io.IOException;  
import java.io.ObjectInputStream;  
import java.io.ObjectOutputStream;  
import java.net.Socket;

import com.cq.util.CommandTranser;  
  
  
/** 
 * @author scx 
 *  客户端 
 */  
public class Client {  
    public static Socket socket=null;  
    public static int port=8888;  
    public static String address="127.0.0.1";  
    
    //获得服务器的数据  
    public static CommandTranser getData() {  
        ObjectInputStream ois=null;  
        CommandTranser res=null;  
        try {  
            ois=new ObjectInputStream(Client.socket.getInputStream());  
            res=(CommandTranser) ois.readObject();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }finally{  
            if(ois!=null){  
                try {  
                    ois.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return res;  
    }  
    
    //向服务器发送数据  
    public static void sendData(CommandTranser cmd) {  
        ObjectOutputStream oos=null;  
        try {  
            oos=new ObjectOutputStream(Client.socket.getOutputStream());  
            oos.writeObject(cmd);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  