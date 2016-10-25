	// UserForm.java --- For Handling User Transactions

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class UserForm extends JFrame implements ActionListener
 {
         JLabel l1,l2;
         JComboBox jc;
         JTextField t1;
         JButton jb1,jb2;
         BufferedReader br=null;
         PrintWriter pw=null;

         public UserForm(String str)
          {
	super(str);

	Container con=getContentPane();
	con.setLayout(null);

	l1=new JLabel("Select Function : ");
	l2=new JLabel("Enter Input Value : ");
	jc=new JComboBox();
	jc.addItem("--Select--");
	jc.addItem("Prime");
	jc.addItem("Factorial");
	jc.addItem("ArmStrong");
	jc.addItem("Even/Odd");
	jc.addItem("Premium Calculator");
	jc.addItem("Synonyms Fetcher");
	jc.addItem("Money Converter");

	t1=new JTextField();
	jb1=new JButton("SEND");
	jb2=new JButton("CLOSE");

	l1.setBounds(50,50,120,20);
	l2.setBounds(50,80,120,20);
	jc.setBounds(155,52,120,20);
	t1.setBounds(155,82,120,20);
	jb1.setBounds(70,125,90,20);
	jb2.setBounds(165,125,90,20);
	con.add(l1);   con.add(l2);    con.add(jc);    con.add(t1);    con.add(jb1);   con.add(jb2);	

	jb1.addActionListener(this);
	jb2.addActionListener(this);

	try
	 {
                      br=new BufferedReader(new InputStreamReader(StartUser.cli.getInputStream()));
              pw=new PrintWriter(new OutputStreamWriter(StartUser.cli.getOutputStream()),true);
	 }
	catch(Exception e)
	 {}
          }

         public void actionPerformed(ActionEvent ae)
          {
	String str=ae.getActionCommand();
	if(str.equals("CLOSE"))
	 {
	        try
	         {
		br.close();
		pw.close();
		StartUser.cli.close();
	         }
	        catch(Exception e)
	          { }
	        System.exit(0);
	 }
	else
	 {
	          String x=(String)jc.getSelectedItem();   // Takes the selected function
	          String y=t1.getText().trim();     // Accepts the input value for the function
	          if(!x.equals("--Select--"))
	           {
		 if(y.length()!=0)
	 	  {
		      try
		       {

	                   str=StartUser.uname+","+StartUser.priv+","+x+","+Integer.parseInt(y);
System.out.println("req-->" + str);
			pw.println(str);   // Sends the data as request to the Server
			str=br.readLine();  // Reads the data sent from Server as output
			JOptionPane.showMessageDialog(this,str);
		       }
		      catch(Exception e)
		       {
	      	     JOptionPane.showMessageDialog(this,"Error in Data Request..."+e);
		       }
		  }
		 else
		  {
	      	     JOptionPane.showMessageDialog(this,"No Input Value is Specified...");
		  }
	           }
	          else
	           {
	          JOptionPane.showMessageDialog(this,"No Function is Selected to Request...");
	           }
	 }
          }

        public static void main(String arg[])
         {
				UserForm uf=new UserForm("USER FORM");
				uf.setSize(400,250);
				uf.setLocation(200,100);
				uf.setResizable(false);
				uf.setVisible(true);
         }
 }