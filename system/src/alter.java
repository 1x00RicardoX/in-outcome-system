
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class alter extends JFrame{
    private static String[] labelWord={"账号:","密码:","确认密码:","姓名:","性别:","电话:","邮箱:","地址:"};
    private String account,password,name,sex,phone,mail,address;//保存用户数据
    private String _account,_password,_name,_sex,_phone,_mail,_address;//保存用户修改后数据
    JButton xiugai_huifu,tijiao;//只能修改除id，account，portrait的部分
    JLabel[] jl=new JLabel[8];//密码第一次显示为******
    JPasswordField ppa1,ppa2;
    JComboBox cse;
    JTextField tac,tna,tse,tph,tma,tad;
    JLabel biaoti;
    JPanel panel;
    JButton person_button,data_button,research_button;

    private void init(int i){
    	final int id=i;
        setTitle("收支管理系统信息修改");
        setSize(600, 400);
        setLocationRelativeTo(null);

        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        person_button = new JButton("信息管理");
		person_button.setBounds(0, 10, 100, 30);
		person_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                person_Infor(id);
            }
        });
		data_button =new JButton("财务管理");
		data_button.setBounds(0, 50, 100, 30);
		data_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	research_Infor(id);
            }
        });
		research_button =new JButton("财务分析");
		research_button.setBounds(0, 90, 100, 30);
		research_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data_Infor(id);
            }
        });

        panel=new JPanel();
        add(panel);
        panel.add(data_button);
        panel.add(person_button);
        panel.add(research_button);

        panel.setLayout(null);
        biaoti=new JLabel("用户信息修改");
        biaoti.setFont(new Font("宋体",Font.BOLD,28));
        biaoti.setBounds(200,10,200,30);
        panel.add(biaoti);

        int count=70;
        int countn=0;
        for(JLabel label:jl){
            label=new JLabel(labelWord[countn++]);
            label.setBounds(120,count,80,25);
            count+=30;
            panel.add(label);
        }

        count=70;

        tac=new JTextField(account);
        tac.setBounds(250,count,175,25);
        tac.setEditable(false);
        count+=30;
        panel.add(tac);

        ppa1=new JPasswordField("123");
        ppa1.setBounds(250,count,175,25);
        ppa1.setEchoChar('●');
        ppa1.setEditable(false);
        count+=30;
        panel.add(ppa1);

        ppa2=new JPasswordField("123");
        ppa2.setBounds(250,count,175,25);
        ppa2.setEchoChar('●');
        ppa2.setEditable(false);
        count+=30;
        panel.add(ppa2);

        tna=new JTextField(name);
        tna.setBounds(250,count,175,25);
        tna.setEditable(false);
        count+=30;
        panel.add(tna);

        String[] s={"男","女"};
        cse=new JComboBox(s);
        cse.setBounds(250,count,175,25);
        cse.setEditable(false);
        count+=30;
        panel.add(cse);

        tph=new JTextField(phone);
        tph.setBounds(250,count,175,25);
        tph.setEditable(false);
        count+=30;
        panel.add(tph);

        tma=new JTextField(mail);
        tma.setBounds(250,count,175,25);
        tma.setEditable(false);
        count+=30;
        panel.add(tma);

        tad=new JTextField(address);
        tad.setBounds(250,count,175,25);
        tad.setEditable(false);
        count+=30;
        panel.add(tad);

        // 创建修改按钮
        xiugai_huifu = new JButton("修改");
        xiugai_huifu.setBounds(150, 320, 100, 35);
        xiugai_huifu.setFont(new Font("黑体",Font.BOLD,20));
        xiugai_huifu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(xiugai_huifu.getText()=="修改"){
                    ppa1.setEditable(true);
                    ppa2.setEditable(true);
                    tad.setEditable(true);
                    tma.setEditable(true);
                    tna.setEditable(true);
                    tph.setEditable(true);
                    xiugai_huifu.setText("重置");
                }
                else{
                    tad.setText(address);
                    tma.setText(mail);
                    tna.setText(name);
                    tph.setText(phone);
                    ppa1.setText(password);
                    ppa2.setText(password);
                }
            }
        });
        panel.add(xiugai_huifu);

        // 创建保存按钮
        tijiao = new JButton("保存");
        tijiao.setBounds(270, 320, 100, 35);
        tijiao.setFont(new Font("黑体",Font.BOLD,20));
        tijiao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	char[] password=ppa1.getPassword();
            	_password=String.valueOf(password);
            	_name=tna.getText();
            	_sex=(String)cse.getSelectedItem();
            	_phone=tph.getText();
            	_mail=tma.getText();
            	_address=tad.getText();
            	String[] ss=zhuanhuan();
                for(String s:ss)
                	System.out.println(s);
            	sql s=new sql();
            	shaenc enc=new shaenc();
            	if(!_password.equals(password))
            		_password=enc.SHA(_password, "SHA-256");
            	System.out.print(_password);
            	String[] src=zhuanhuan();
            	if(!ppa1.getText().equals(ppa2.getText()))
                    JOptionPane.showMessageDialog(null, "两次密码输入不匹配！  请重新输入！", "修改提示",JOptionPane.WARNING_MESSAGE);           	
            	else if(sql.alter(id,src)!=0) {
            		JOptionPane.showMessageDialog(null, "修改成功！", "修改提示",JOptionPane.INFORMATION_MESSAGE);
                    int n = JOptionPane.showConfirmDialog(null, "是否回到系统?", "修改提示",JOptionPane.YES_NO_OPTION);
                    if(n==0)
                    	research_Infor(id);
            	}
            	else
            		JOptionPane.showMessageDialog(null, "修改失败！  请重新输入！", "修改提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(tijiao);

    }

    public alter(int i){
        sql s=new sql();
        String[] gets=sql.researInAll(i);
        zhuanhuan(gets);
        init(i);
    }

    private void zhuanhuan(String[] get){
        account=get[0];
        password=get[1];
        name=get[2];
        sex=get[3];
        phone=get[4];
        mail=get[5];
        address=get[6];
        _account=get[0];
        _password=get[1];
        _name=get[2];
        _sex=get[3];
        _phone=get[4];
        _mail=get[5];
        _address=get[6];
    }


    private String[] zhuanhuan(){
        String[] post=new String[7];
        post[0]=_account;
        post[1]=_password;
        post[2]=_name;
        post[3]=_sex;
        post[4]=_phone;
        post[5]=_mail;
        post[6]=_address;
        return post;
    }
    
    public void person_Infor(int i) {
		alter a=new alter(i);
		this.dispose();
	}
    
	public void data_Infor(int i) {
		Data_Manage d=new Data_Manage(i);
		this.dispose();
	}
	
	public void research_Infor(int i) {
		Research r=new Research(i);
		this.dispose();
	}
}

