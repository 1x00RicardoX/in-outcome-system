

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.imageio.*;
import java.util.Arrays;


public class Login{
    private static JFrame jf;
    private static String vcode;
    public static void main(String[] args) {
        String user,password;
        
        JFrame frame = new JFrame("收支管理系统登陆");
        // Setting the width and height of frame
        frame.setSize(300, 260);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        
        JPanel panel = new JPanel();
       
        frame.add(panel);

        placeComponents(panel);

        
        frame.setVisible(true);
        jf=frame;
    }

    private static void placeComponents(JPanel panel) {

        panel.setLayout(null);

        

        // 创建 JLabel
        JLabel userLabel = new JLabel("用户:");
        
        userLabel.setBounds(10,15,80,25);
        panel.add(userLabel);

        
        JTextField userText = new JTextField(20);
        userText.setBounds(100,15,165,25);
        panel.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(10,45,80,25);

        panel.add(passwordLabel);

        
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,45,165,25);
        panel.add(passwordText);


        JLabel jym = new JLabel("校验码:");
        jym.setBounds(10,85,80,25);
        panel.add(jym);

        // 验证码
        VCodeGenerator vc=new VCodeGenerator(4);
        vcode=vc.generatorVCode();
        Image image=vc.generatorVCodeImage(vcode,true);
        JLabel Jimg=new JLabel();
        ImageIcon imgi=new ImageIcon(image);
        Jimg.setIcon(imgi);
        Jimg.setBounds(120,80,200,40);
        panel.add(Jimg);

        JLabel jly=new JLabel("验证码:");
        jly.setBounds(10, 130, 80, 25);
        JTextField jy=new JTextField();
        jy.setBounds(100,130,165,25);
        panel.add(jy);
        panel.add(jly);


        // 创建登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(50, 160, 200, 35);
        loginButton.setFont(new Font("黑体",Font.BOLD,20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                char[] password=passwordText.getPassword();
                String pass=String.valueOf(password);
                
                login(userText.getText(),pass,jy.getText());
            }
        });
        panel.add(loginButton);

        // 创建注册按钮
        JButton registButton = new JButton("没有账号？点此注册");
        registButton.setBounds(120, 200, 150, 15);
        registButton.setFont(new Font("楷体",Font.ITALIC,12));
        registButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regist();
            }
        });
        panel.add(registButton);


    }

    public static void login(String user,String password,String jy){
        if(!jy.equals(vcode))
            JOptionPane.showMessageDialog(null, "验证码错误！  请重新输入！", "注册提示",JOptionPane.WARNING_MESSAGE);
        else if(user.equals("")||password.equals("")){
            JOptionPane.showMessageDialog(null, "未输入用户名或密码！  请重新输入！", "登录提示",JOptionPane.WARNING_MESSAGE);
        }
        else{
            sql sql1=new sql();
            shaenc enc=new shaenc();
            System.out.println("user   "+user+"password   "+password);
            int id=sql1.login(user,enc.SHA(password, "SHA-256"));
            if(id!=0){
                JOptionPane.showMessageDialog(null, "登录成功！", "登录提示",JOptionPane.INFORMATION_MESSAGE);
                // 进入系统
                research(id);
            }
            else
                JOptionPane.showMessageDialog(null, "用户名或密码错误！  请重新输入！", "登录提示",JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void regist(){
        regist reg=new regist();
        jf.dispose();
    }
    
    //进入系统
    public static void research(int i) {
    	Research res=new Research(i);
    	jf.dispose();
    }
}