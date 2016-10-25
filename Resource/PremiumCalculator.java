import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
public class PremiumCalculator extends Frame implements ItemListener , ActionListener
{

	TextField t1,t2,t3,t4,t5,t6;
	Button b1,b2,b3;
	Label l,l1,l2,l3,l4,l5,l6,l7,l8;
	Choice c1;
	String str,str1,str2,str3,str4,str5,str6;
	BufferedReader br;
	PrintWriter pw;

	PremiumCalculator(BufferedReader br,PrintWriter pw)
	{
		this.br=br;
		this.pw=pw;

		l1=new Label("Premium Calculator");

		l2=new Label("Plan");

		c1=new Choice();
		c1.add("---Please Select a Plan ---");		
		c1.add("New Jeevan Anand");
		c1.add("New Bima Bachat");
		
		
		l3=new Label("Age");
		t1=new TextField(20);
		
		l4=new Label("Term");
		t2=new TextField(20);
				
		l5=new Label("Sum Assured");
		t3=new TextField(20);
		
		l=new Label("Premium to be paid");

		l6=new Label("4 months");
		t4=new TextField(20);

		l7=new Label("6 months");
		t5=new TextField(20);
	
		l8=new Label("12 months");
		t6=new TextField(20);

		
		b1=new Button("Calculate Premium");
		b2=new Button("Cancel");
		b3=new Button("Close");
				
		setLayout(null);
		
		
		l1.setBounds(80,50,190,50);
		l2.setBounds(80,100,100,30);
		c1.setBounds(200,100,200,30);
		l3.setBounds(80,150,100,30);
		t1.setBounds(200,150,100,30);
		l4.setBounds(80,200,100,30);
		t2.setBounds(200,200,200,30);
		l5.setBounds(80,250,100,30);
		t3.setBounds(200,250,100,30);
		
		b1.setBounds(100,300,120,30);
		b2.setBounds(240,300,120,30);
		
		l.setBounds(80,350,200,30);
		l6.setBounds(80,400,100,30);
		t4.setBounds(200,400,100,30);
		l7.setBounds(80,450,100,30);
		t5.setBounds(200,450,100,30);
		l8.setBounds(80,500,100,30);
		t6.setBounds(200,500,100,30);
		
		b3.setBounds(200,550,80,30);
		
		add(l1);
		add(l2);
		add(c1);
		add(l3);
		add(t1);
		add(l4);
		add(t2);
		add(l5);
		add(t3);
		add(b1);
		add(b2);
		
		add(l);
		add(l6);
		add(t4);
		add(l7);
		add(t5);
		add(l8);
		add(t6);
		
		add(b3);		

		setSize(600,600);
		setVisible(true);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		c1.addItemListener(this);		
	}
	public static void main(String args[]) throws Exception
	{
		
		//PremiumCalculator pc=new PremiumCalculator();
	}
	public void itemStateChanged(ItemEvent ie)
	{
		try
		{
			str1=(String)c1.getSelectedItem();			
	        	}
		catch(Exception e)
		{
			System.out.println(e);
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
			JOptionPane.showMessageDialog(this,"Thank you");
			setVisible(false);	
		}
		else
		{
			try
			{
				str="Premium Calculator";
				str2=t1.getText();
				str3=t2.getText();
				str4=t3.getText();		
				str5=str+","+str1+","+str2+","+str3+","+str4;
		
				System.out.println(" calling function :");
				premiumCalc(str1,str2,str3,str4);		
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}		
		}//else close
	
	}
	void premiumCalc(String plan,String age,String term,String sum)
	{
		System.out.println(" executing :"+plan);
		try
		{
		int age1=Integer.parseInt(age);
		int term1=Integer.parseInt(term);
		int sum1=Integer.parseInt(sum);
		
if( plan.equals("New Jeevan Anand") && (age1>=18 || age1<=45 ) && (sum1>=500000))
		{
			System.out.println(" executing1 :"+plan);
			int n,quarter,half,year,fixed_amt=5000;
			n=(term1-15)*100;
			quarter=fixed_amt+(n*3);
			half=(fixed_amt+(n*6))*2;
			year=(fixed_amt+(n*12))*4;
			String res=quarter+","+half+","+year;

			t4.setText(String.valueOf(quarter));
			t5.setText(String.valueOf(half));
			t6.setText(String.valueOf(year));

		System.out.println(" executing :"+str);
			pw.println(res);			


		}
	if( plan.equals("New Jeevan Anand") && (age1>=18 || age1<=45 ) && (sum1>=1000000))
		{
			System.out.println(" executing2 :"+plan);
			int n,quarter,half,year,fixed_amt=3000;
			n=(term1-15)*100;
			quarter=fixed_amt+(n*4);
			half=(fixed_amt+(n*6))*2;
			year=(fixed_amt+(n*12))*4;

			
			t4.setText(String.valueOf(quarter));
			t5.setText(String.valueOf(half));
			t6.setText(String.valueOf(year));

			String res=quarter+","+half+","+year;
			System.out.println(" executing :"+res);

			pw.println(res);			

		}

		if( plan.equals("New Jeevan Anand") && (age1>45) && (sum1>=500000))
		{
			System.out.println(" executing3 :"+plan);

			int n,quarter,half,year,fixed_amt=7000;
			n=(term1-15)*100;
			quarter=fixed_amt+(n*4);
			half=(fixed_amt+(n*6))*2;
			year=(fixed_amt+(n*12))*4;

			t4.setText(String.valueOf(quarter));
			t5.setText(String.valueOf(half));
			t6.setText(String.valueOf(year));

			String res=quarter+","+half+","+year;
			System.out.println(" executing :"+res);
			pw.println(res);			

		}
		if( plan.equals("New Jeevan Anand") && (age1>45) && (sum1>=1000000))
		{

			System.out.println(" executing4 :"+plan);

			int n,quarter,half,year,fixed_amt=9000;
			n=(term1-15)*100;
			quarter=fixed_amt+(n*4);
			half=(fixed_amt+(n*6))*2;
			year=(fixed_amt+(n*12))*4;

			t4.setText(String.valueOf(quarter));
			t5.setText(String.valueOf(half));
			t6.setText(String.valueOf(year));

			String res=quarter+","+half+","+year;	
			System.out.println(" executing :"+res);
			pw.println(res);			

		}

	if( plan.equals("New Bima Bachat") && (age1>=18 || age1<=45 ) && (sum1>=500000) )
		{
			System.out.println(" executing5 :"+plan);

			int n,quarter,half,year,fixed_amt=4000;
			n=(term1-15)*100;
			quarter=fixed_amt+(n*4);
			half=(fixed_amt+(n*6))*2;
			year=(fixed_amt+(n*12))*4;
			String res=quarter+","+half+","+year;
			
			t4.setText(String.valueOf(quarter));
			t5.setText(String.valueOf(half));
			t6.setText(String.valueOf(year));

			System.out.println(" executing :"+res);
			pw.println(res);			

		}
		if( plan.equals("New Bima Bachat") && (age1>=18 || age1<=45 ) && (sum1>=1000000) )
		{
		System.out.println(" executing6 :"+plan);

			int n,quarter,half,year,fixed_amt=2000;
			n=(term1-15)*100;
			quarter=fixed_amt+(n*4);
			half=(fixed_amt+(n*6))*2;
			year=(fixed_amt+(n*12))*4;
			String res=quarter+","+half+","+year;

			t4.setText(String.valueOf(quarter));
			t5.setText(String.valueOf(half));
			t6.setText(String.valueOf(year));

			System.out.println(" executing :"+res);
			pw.println(res);			

		}
		if( plan.equals("New Bima Bachat") && (age1>45)  && (sum1>=500000))
		{
			System.out.println(" executing7 :"+plan);

			int n,quarter,half,year,fixed_amt=8000;
			n=(term1-15)*100;
			quarter=fixed_amt+(n*4);
			half=(fixed_amt+(n*6))*2;
			year=(fixed_amt+(n*12))*4;
			String res=quarter+","+half+","+year;

			t4.setText(String.valueOf(quarter));
			t5.setText(String.valueOf(half));
			t6.setText(String.valueOf(year));

			System.out.println(" executing :"+res);
			pw.println(res);			

		}
		if( plan.equals("New Bima Bachat") && (age1>45)  && (sum1>=1000000))
		{
			System.out.println(" executing8 :"+plan);

			int n,quarter,half,year,fixed_amt=6000;
			n=(term1-15)*100;
			quarter=fixed_amt+(n*4);
			half=(fixed_amt+(n*6))*2;
			year=(fixed_amt+(n*12))*4;

			
			t4.setText(String.valueOf(quarter));
			t5.setText(String.valueOf(half));
			t6.setText(String.valueOf(year));


			String res=quarter+","+half+","+year;

			System.out.println(" executing :"+res);
			pw.println(res);			

		}
		//Thread.sleep(30000);	
		System.out.println("completed successfully");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
}