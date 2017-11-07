package com.cq.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.cq.entity.File;
import com.cq.socket.Client;
import com.cq.util.CommandTranser;

/** 
 * @author scx 
 *  上传文件界面 
 */  
public class UploadUI extends JFrame implements ActionListener {  
    private static final long serialVersionUID = -8830003248247613172L;  
    private JFileChooser fileChooser;//文件选择对话框  
    private JButton upload_btn, choose_btn;//上传按钮 和选择按钮  
    private JTextField path_txt;//文件的绝对路径  
    private String username;//成功登录的用户名  
  
    public UploadUI(String username) {  
        this.username = username;  
        init();  
        setTitle("上传文件");  
        setSize(250, 200);  
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setVisible(true);  
        setResizable(false);  
    }  
  
    private void init() {  
        setLayout(new FlowLayout());  
        choose_btn = new JButton("选择");  
        upload_btn = new JButton("上传");  
        fileChooser = new JFileChooser();  
        path_txt = new JTextField(20);  
        add(path_txt);  
        add(choose_btn);  
        add(upload_btn);  
        choose_btn.addActionListener(this);  
        upload_btn.addActionListener(this);  
    }  
  
    public void actionPerformed(ActionEvent e) {  
        // 如果点击了选择文件  
        if (e.getSource() == choose_btn) {  
            //打开选择文件对话框  
            int state = fileChooser.showSaveDialog(null);  
            if (state == 0) {  
                String pathChoose = fileChooser.getSelectedFile().getPath();  
                path_txt.setText(pathChoose);  
            }  
        }  
        
        // 如果点击了上传按钮  
        if (e.getSource() == upload_btn) {  
            if(path_txt.getText().trim()==null||"".equals(path_txt.getText().trim())){  
                JOptionPane.showMessageDialog(null, "请先选择文件再上传！！");  
                return ;  
            }  
            uploadFile();  
        }  
    }  
    
    //上传文件  
    private void uploadFile() {  
        File file = null;  
        FileInputStream fis = null;  
        BufferedInputStream bis = null;  
        String path = path_txt.getText().trim();  
        String fname = path.substring(path.lastIndexOf("\\") + 1);  
        try {  
            fis = new FileInputStream(path);  
            // fis.available返回文件的总大小  
            byte[] fcontent = new byte[fis.available()];  
            bis = new BufferedInputStream(fis);  
            //读取文件内容  
            bis.read(fcontent);  
            //实例化file对象  
            file = new File(fname, username, fcontent);  
  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (bis != null)  
                try {  
                    bis.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
        }  
        
        //向服务器发送文件  
        CommandTranser cmd = new CommandTranser();  
        cmd.setData(file);  
        cmd.setCmd("uploadFile");  //设置“上传文件”命令
        try {  
            Client.socket = new Socket(Client.address, Client.port);  
            //向服务器发送数据  
            Client.sendData(cmd);  
            //获得服务器反馈信息  
            cmd = Client.getData();  
            JOptionPane.showMessageDialog(null, cmd.getResult());  
        } catch (UnknownHostException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }  
}  