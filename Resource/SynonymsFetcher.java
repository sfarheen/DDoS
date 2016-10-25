import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class SynonymsFetcher extends Frame implements ItemListener , ActionListener
{

	TextField t1;
	Button b1,b2,b3;
	Label l1,l2,l3;
	Choice c;
	BufferedReader br;
	PrintWriter pw;
	String word,str,str1;
	int num_words,i,j=0;	

	 Connection con;
	 ResultSet rs;
	 Statement st;

	SynonymsFetcher(BufferedReader br,PrintWriter pw)
	{
		this.br=br;
		this.pw=pw;

		l1=new Label("Synonyms Fetcher");
		l2=new Label("Word for Synonym");
		l3=new Label("Synonyms");
		
		t1=new TextField(20);

		c=new Choice();
	
		b1=new Button("Search");
		b2=new Button("Cancel");
		b3=new Button("Close");
				
		setLayout(null);
			
		l1.setBounds(80,100,190,50);
		l2.setBounds(80,150,100,30);
		t1.setBounds(80,200,100,30);
		b1.setBounds(80,250,70,30);//compute button
		b2.setBounds(170,250,70,30);//cancel button
		l3.setBounds(80,300,100,30);
		c.setBounds(80,350,100,30);
		b3.setBounds(150,400,80,30);		

		add(l1);
		add(l2);
		add(t1);
		add(b1);
		add(b2);
		add(l3);
		add(c);
		add(b3);

		setSize(500,500);
		setVisible(true);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		c.addItemListener(this);

		try
		{			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:DDOS1");
			System.out.println("connected to database");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	/*public static void main(String args[]) throws Exception
	{
		
		//SynonymsFetcher sf=new SynonymsFetcher();
		
	}*/
	public void itemStateChanged(ItemEvent ie)
	{
		try
		{
		
		
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
			c.removeAll();
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
				c.removeAll();		
				word=t1.getText();			
				synonymsFetcher(word);	
			
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}//else ends		
      	}
	void synonymsFetcher(String param)
	{
		try
		{	int flag=0;
			String query,str,str1;
			st=con.createStatement();
			query="select * from Synonyms where word='"+param+"' ";
			rs=st.executeQuery(query);
			if(rs.next())
			{
				str=rs.getString(3);
				flag=1;
				addit(str);
				pw.println(str);
			}
			else if(flag==0)
			{

				query="select * from Synonyms";
				rs=st.executeQuery(query);
			        l1: while(rs.next())
				    {
					str1=rs.getString(3);
			StringTokenizer st=new StringTokenizer(str1,",");
					while(st.hasMoreTokens())
					{
						String temp=st.nextToken();
						if(temp.equals(param))	
						{										      str1=str1+","+rs.getString(2);
							flag=1;
							addit(str1);
							pw.println(str1);
							break l1;
						}
					}
				     }
				
			}
			if(flag==0)
			{	String msg="Oops sorry..!! Word not found ";
				pw.println(msg);
			}
		//Thread.sleep(15000);	
		System.out.println("completed successfully");
		}
		catch(Exception e)
		{
			System.out.println("update error"+e);
		}
		
	}
	public void addit(String str1)
	{
			JOptionPane.showMessageDialog(this,str1);
	
			StringTokenizer st=new StringTokenizer(str1,",");
			num_words=st.countTokens();
			System.out.println(num_words);

			
			String arr[]=new String[num_words];
			i=0;
			while(st.hasMoreTokens())
			{
				arr[i]=st.nextToken();	
				System.out.println(arr[i]);
				i++;		
				
			}
			c.add("");
			for(j=0;j<num_words;j++)
			{
				if(!arr[j].equals(word))
				{	
						c.add(arr[j]);
				}
				
			}
			System.out.println("received response");

	}
}