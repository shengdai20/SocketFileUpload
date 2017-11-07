package com.cq.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.cq.socket.Service;

/** 
 * 开启服务端界面 
 */  
public class StartServer extends JFrame implements ActionListener{  
    private static final long serialVersionUID = 3254784569816648178L;  
    private JButton startServer_btn;  
    private JButton endServer_btn;  
    private Service startService;  
    
    public StartServer(){  
        setLayout(new FlowLayout());  
        startServer_btn=new JButton("开启服务");  
        endServer_btn=new JButton("关闭服务");  
        add(startServer_btn);  //将“开启服务”按钮显示出来
        add(endServer_btn);  //将“关闭服务”按钮显示出来
        setTitle("服务端");  
        setSize(300, 200);  //显示框大小
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setVisible(true);  
        startServer_btn.addActionListener(this);  //给“开启服务”按钮添加监听器
        endServer_btn.addActionListener(this);  //给“关闭服务”按钮添加监听器
    }  
    
    public static void main(String[] args) {  
        new StartServer();  
    }  

    public void actionPerformed(ActionEvent e) {  
        if(e.getSource()==startServer_btn){  //如果点击了“开启服务”按钮
            if(startService==null){  //开启线程
                /* 
                 * 在线程中开启服务器  避免使用main线程 服务器一直开启  
                 * main线程一直阻塞  无法对其它事物进行处理 
                 */  
                new startServerThread().start();  
            }  
        }  
        //退出服务器  
        if(e.getSource()==endServer_btn){  //如果点击了“关闭服务”按钮
            startService=null;  
            System.exit(0);  
        }  
    }  
    private class startServerThread extends Thread{  
        public void run() {  
            startService=new Service();  
        }  
    }  
}  