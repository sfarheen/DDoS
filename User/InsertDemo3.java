import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class InsertDemo3 extends JFrame implements ActionListener
{
	JLabel l1,l2,l3;
	JTextField t1,t2,t3;
	JButton b1,b2;
	Container con;
	Connection cn;
	Statement st=null;
	InsertDemo3()
	{
		super("Insert Demo");

		l1=new JLabel("UserName");
		l2=new JLabel("Password");
		l3=new JLabel("Privileges");


		t1=new JTextField(15);
		t2=new JTextField(15);
		t3=new JTextField(15);

		b1=new JButton("Insert");
		b2=new JButton("Cancel");

		con=getContentPane();
		con.setLayout(new FlowLayout());

		con.add(l1);con.add(t1);
		con.add(l2);con.add(t2);
		con.add(l3);con.add(t3);
		con.add(b1);con.add(b2);
	         try
	         {
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		cn=DriverManager.getConnection("jdbc:odbc:DDOS");
	         }	
	        catch(Exception e)
	        {
	            JOptionPane.showMessageDialog(this,"Data Base connection Error :"+e);	
	        }
	    	
		b1.addActionListener(this);
		b2.addActionListener(this);

		setSize(300,300);	
		setVisible(true);        
	}
	public void actionPerformed(ActionEvent ae)	
	{
		String s=ae.getActionCommand();
		//String s=ae.getSource();

	      if(s.equals("Insert"))
	      {

		String s1=t1.getText().trim();
		String s2=t2.getText().trim();		
		String s3=t3.getText().trim();
		if(s1.length()!=0 && s2.length()!=0 &&  s3.length()!=0)
		{
		       try
		       {
			
			
			int priv=Integer.parseInt(s3);
			st=cn.createStatement();
	           		String str="insert into LoginInfo values('"+s1+"','"+s2+"',"+priv+")";
			int x=st.executeUpdate(str);			
		JOptionPane.showMessageDialog(this,x+"User Created successfully");	
	           
	            		t1.setText("");	
			t2.setText("");
			t3.setText("");
	                          }
		       catch(Exception e)
	 	       {
		JOptionPane.showMessageDialog(this,"invalid inputs entered"+e);
		       }
		}	
		else
		{
	                     JOptionPane.showMessageDialog(this,"All Fields are Mandatory");
		}
	      }
	     else if(s.equals("Cancel"))
	     {	
	                          try
		       {
	JOptionPane.showMessageDialog(this,"Thank u for using this application");
			st.close();
			cn.close();	
			setVisible(false);			
		      }
		      catch(Exception e)
		      {
			 
		      } 		  
	     }
	}
	public static void main(String args[])
	{
		InsertDemo3 i=new InsertDemo3();
	}
}