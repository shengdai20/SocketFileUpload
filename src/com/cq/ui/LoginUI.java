package com.cq.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.cq.entity.User;
import com.cq.socket.Client;
import com.cq.util.CommandTranser;

/** 
 * @author scx 
 *  客户端登录界面 
 */  
public class LoginUI extends JFrame implements ActionListener {  
    private static final long serialVersionUID = -2686222552198867018L;  
    private Box box1, box2, box3, baseBox;  
    private JLabel username, password;  
    private JTextField username_txt;  
    private JPasswordField password_txt;  
    private JButton login_btn, register_btn;  
    
    public LoginUI() {  
        setLayout(new FlowLayout());  
        init();  
        setTitle("客户端");  
        setSize(200, 150);  
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setResizable(false);  
        setVisible(true);  
    }  
  
    private void init() {  
        username = new JLabel("账户");  
        password = new JLabel("密码");  
        username_txt = new JTextField(10);  //将“账户”输入框显示出来
        password_txt = new JPasswordField(10);  //将“密码”输入框显示出来
        login_btn = new JButton("登录");  //将“登录”按钮显示出来
        register_btn = new JButton("注册");  //将“注册”按钮显示出来
        
        box1 = Box.createHorizontalBox();  
        box2 = Box.createHorizontalBox();  
        box3 = Box.createHorizontalBox();  
        
        box1.add(username);  
        box1.add(Box.createHorizontalStrut(8));  
        box1.add(username_txt);  
        
        box2.add(password);  
        box2.add(Box.createHorizontalStrut(8));  
        box2.add(password_txt);  
        
        box3.add(login_btn);  
        box3.add(Box.createHorizontalStrut(8));  
        box3.add(register_btn);  
        
        baseBox = Box.createVerticalBox();  
        baseBox.add(box1);  
        baseBox.add(Box.createVerticalStrut(5));  
        baseBox.add(box2);  
        baseBox.add(Box.createVerticalStrut(5));  
        baseBox.add(box3);  
        baseBox.add(Box.createVerticalStrut(5));  
        add(baseBox);  
        
        login_btn.addActionListener(this);  
        register_btn.addActionListener(this);  
    }  
  
    public void actionPerformed(ActionEvent e) {  
        //如果点击了登录按钮  判断账户在服务器是否存在  
        if(e.getSource()==login_btn){  
            User user=new User();  
            user.setUsername(username_txt.getText().trim());  //从输入框获取输入的用户名信息
            user.setPassword(new String(password_txt.getPassword()).trim());  //从输入框获取输入的密码信息
            CommandTranser cmd =new CommandTranser();  
            cmd.setData(user);  //将输入的user用户信息加入到cmd中用于向服务器发送
            cmd.setCmd("login");  //将登录操作命令加入到cmd中
            try {  
                Client.socket=new Socket(Client.address, Client.port);  //获取客户端的ip地址和端口号
                //向服务器发送数据  
                Client.sendData(cmd);  
                //获得服务器发送的数据  
                cmd =Client.getData();  
                JOptionPane.showMessageDialog(null, cmd.getResult());  
                //如果登录成功  关闭此窗口 开启上传窗口  
                if(cmd.isFlag()){  
                    this.dispose();  
                    new UploadUI(user.getUsername());  
                }  
            } catch (UnknownHostException e1) {  
                JOptionPane.showMessageDialog(null, "服务端未开启！");  
            } catch (IOException e1) {  
                JOptionPane.showMessageDialog(null, "服务端未开启！");  
            }  
        }  
        
        //如果点击了注册按钮  打开注册界面  
        if(e.getSource()==register_btn){  
            new RegisterUI();  
        }  
    }  
  
      
}  