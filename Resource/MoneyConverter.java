import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;


public class MoneyConverter extends Frame implements ItemListener , ActionListener
{

	TextField t1,t2,t3;
	Button b1,b2,b3;
	Label l1,l2,l3,l4,l5,l6;
	Choice c1,c2;
	String str,str1,str2,str3,str4,str5;
	BufferedReader br;
	PrintWriter pw;

	MoneyConverter(BufferedReader br,PrintWriter pw)
	{
		this.br=br;
		this.pw=pw;
		l1=new Label("Money Converter");

		l2=new Label("Source Country");

		c1=new Choice();
		c1.add("--- select ---");		
		c1.add("India");
		
		
		l3=new Label("Denomination");
		t1=new TextField(20);
		
		l4=new Label("Target Country");

		c2=new Choice();
		c2.add("--- select ---");		
		c2.add("Kuwait");
		c2.add("Japan");
		c2.add("United States of America");
		c2.add("Saudi Arabia");

		l5=new Label("Denomination");
		t2=new TextField(20);
						
		l6=new Label("Enter value");	
		t3=new TextField(20);
		t3.setEnabled(true);
		
		b1=new Button("Compute");
		b2=new Button("Cancel");
		b3=new Button("Close");		
				
		setLayout(null);
		
		l1.setBounds(80,100,190,50);
		l2.setBounds(80,150,100,30);
		c1.setBounds(200,150,100,30);
		l3.setBounds(80,200,100,30);
		t1.setBounds(200,200,100,30);
		l4.setBounds(80,250,100,30);
		c2.setBounds(200,250,200,30);
		l5.setBounds(80,300,100,30);
		t2.setBounds(200,300,100,30);
		l6.setBounds(80,350,100,30);
		t3.setBounds(200,350,100,30);
		b1.setBounds(140,400,80,30);
		b2.setBounds(240,400,80,30);
		b3.setBounds(200,450,80,30);
		
		
		add(l1);
		add(l2);
		add(c1);
		add(l3);
		add(t1);
		add(l4);
		add(c2);
		add(l5);
		add(t2);
		add(l6);
		add(t3);
		add(b1);
		add(b2);
		add(b3);
		

		setSize(500,500);
		setVisible(true);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		c1.addItemListener(this);
		c2.addItemListener(this);

		
	}
	public static void main(String args[]) throws Exception
	{
		
		//MoneyConverter mc=new MoneyConverter();
	}
	public void itemStateChanged(ItemEvent ie)
	{
		try
		{
		str1=(String)c1.getSelectedItem();
		if(str1.equals("India"))
			t1.setText("Rupee" );
		
		str2=(String)c2.getSelectedItem();
		if(str2.equals("Kuwait"))
			t2.setText("Dinar");
		else if(str2.equals("Japan"))
			t2.setText("Yen");
		else if(str2.equals("United States of America"))
			t2.setText("Dollar ");
		else if(str2.equals("Saudi Arabia"))
			t2.setText("Riyal");				
                }
		catch(Exception e)
		{
		}		
		
	}
	public void actionPerformed(ActionEvent ae)
	{	
		String s=ae.getActionCommand();
		if(s.equals("Cancel"))
		{
			t1.setText("");
			t2.setText("");
			t3.setText("");
		}
		else if(s.equals("Close"))
		{	
			//System.out.println("close button");
			JOptionPane.showMessageDialog(this,"Thank you");
			setVisible(false);
		}
		else
		{
			try
			{
				/*str="Money Converter";
				str3=t3.getText();		
				str4=str+","+str1+","+str2+","+str3;
			
				pw.println(str4);
	JOptionPane.showMessageDialog(this,str4+"ur request is sent to server");			
				
				str5=br.readLine();
				JOptionPane.showMessageDialog(this,str5);
				System.out.println("received response");*/
				
				String src=str1;
				String target=(String)c2.getSelectedItem();		
				String amt=t3.getText();

		System.out.println("calling money conversion");
			moneyConversion(src,target,amt);
		System.out.println("called money conversion");			
			}
			catch(Exception e)
			{
				System.out.println(e);
			}		
		}//else ends
	
	}
	void  moneyConversion(String src,String target,String amt)
	{
		System.out.println("entered into money conversion");
		try
		{

		String res;
		
		if(src.equals("India") && target.equals("Kuwait"))
		{
			float val=Float.parseFloat(amt);
			float value=val/220;
			res=String.valueOf(value);
			System.out.println(val+" Rupees equals "+res+" Riyals");
			pw.println(val+" Rupees equals "+res+" Dinars");
		}	
		else if(src.equals("India") && target.equals("Japan"))
		{
			double val=Double.parseDouble(amt);
			double value;
			value=val/0.6;
			res=String.valueOf(value);
			System.out.println(val+" Rupees equals "+res+" Riyals");
			pw.println(val+" Rupees equals "+res+" Yen");
		}	
		else if(src.equals("India") && target.equals("United States of America"))
		{
			float val=Float.parseFloat(amt);
			float value;
			value=val/62;
			res=String.valueOf(value);
			System.out.println(val+" Rupees equals "+res+" Riyals");
			pw.println(val+" Rupees equals "+res+" Dollars");
		}
		else if(src.equals("India") && target.equals("Saudi Arabia"))
		{
			double val=Double.parseDouble(amt);
			double value;
			value=val/16.58;
			res=String.valueOf(value);
			System.out.println(val+" Rupees equals "+res+" Riyals");
			pw.println(val+" Rupees equals "+res+" Riyals");
		}		
		System.out.println("completed successfully");
	            }		
	            catch(Exception e)
	            {
			System.out.println(e);
	            }		
	}		
}