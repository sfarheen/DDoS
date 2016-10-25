	// StartUser.java   -- Starts the Node

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.net.*;

class StartUser extends JFrame implements ActionListener
 { 
      
       JLabel l1,l2,l3,l4;
       JTextField t1,t3;
       JPasswordField t2;
       JButton jb1,jb2,jb3;
       Connection cn=null;
       Statement stmt=null;
       public static int priv=0;
       public static Socket cli=null;
        public static String uname="";

       public StartUser(String str)
        {
	super(str);
	Container con=getContentPane();
	con.setLayout(null);

	l1=new JLabel("UserName : ");
	l2=new JLabel("PassWord : ");
	l3=new JLabel("Server Name : ");
	l4=new JLabel("New User  :? ");
	t1=new JTextField();
	t2=new JPasswordField();
	t3=new JTextField();
	jb1=new JButton("LOGIN");
	jb2=new JButton("EXIT");
	jb3=new JButton("Register");

	l1.setBounds(30,30,80,20);
	l2.setBounds(30,60,80,20);
	l3.setBounds(20,90,90,20);	l4.setBounds(20,170,90,20);
	t1.setBounds(110,32,150,20);	jb3.setBounds(145,170,120,20);
	t2.setBounds(110,62,150,20);
	t3.setBounds(110,92,150,20);
	jb1.setBounds(60,130,80,20);
	jb2.setBounds(145,130,80,20);

	

	con.add(l1);     con.add(l2);    con.add(t1);     con.add(t2);  con.add(l3);   con.add(t3);
	con.add(jb1);    con.add(jb2);con.add(l4);con.add(jb3);

	jb1.addActionListener(this);
	jb2.addActionListener(this);
	jb3.addActionListener(this);

	try
	 {
	            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	            cn=DriverManager.getConnection("jdbc:odbc:DDOS");
	            stmt=cn.createStatement();
	 }
	catch(Exception e)
	 {
	         JOptionPane.showMessageDialog(this,"Unable to Connect to Database...");
	         jb1.setEnabled(false);
	 }
        }

      public void actionPerformed(ActionEvent ae)
       {
	String str=ae.getActionCommand();
	if(str.equals("EXIT"))
	 {
	         try
	          {
		stmt.close();
		cn.close();
	          }
	         catch(Exception e)
	          {  }
	         System.exit(0);
	 }
	else if(str.equals("LOGIN"))
	 {
	         try
	          {
		String x=t1.getText().trim();
		String y=t2.getText().trim();
		String z=t3.getText().trim();

		if(x.length()!=0)
		 {
		       if(y.length()!=0)
		        {
		              if(z.length()!=0)
		               {
	str="Select Previleges from LoginInfo where UName='"+x+"' and PWord='"+y+"'";
			ResultSet rs=stmt.executeQuery(str);
			int c=0;
			if(rs.next())
			 {
			           c++;
			           uname=x;
			           priv=rs.getInt(1);
		    	 }

			if(c==0)
			 {
                 JOptionPane.showMessageDialog(this,"InValid UserName/PassWord are Entered...");
			           t1.setText("");
			           t2.setText("");
			 }	
			else
			 {
		       cli=new Socket(z,2000);   // connect to router	        
	                          JOptionPane.showMessageDialog(this,"User Login is Successfull....");
				try
				 {
					stmt.close();
					cn.close();
	          			 }
			                  catch(Exception e)
			                  {  }
				UserForm uf=new UserForm("USER FORM : "+x);
				uf.setSize(700,400);
				uf.setLocation(200,100);
				uf.setResizable(false);
				uf.setVisible(true);
			                 setVisible(false);
				dispose();
			 }
		               }
		              else
		               {
	                   JOptionPane.showMessageDialog(this,"Server Name is Not Specified...");
		               }
		        }
		       else
		        {
		      JOptionPane.showMessageDialog(this,"PassWord is Not Specified...");
		        }
	 	 }
		else
		 {
		     JOptionPane.showMessageDialog(this,"UserName is Not Specified...");
		 }		
	          }
	         catch(Exception e)
	          {
                  JOptionPane.showMessageDialog(this,"Error in Processing UserName/PassWord..");
	          }
	 }
	else if(str.equals("Register"))
	{
		InsertDemo3 id=new InsertDemo3();
	}	
       }
       public static void main(String arg[])
        {
	StartUser su=new StartUser("USER LOGIN");
	su.setSize(300,300);
	su.setLocation(300,200);
	su.setResizable(false);
	su.setVisible(true);
        }
 }