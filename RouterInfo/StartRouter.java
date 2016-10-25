	//   StartRouter --  Router Initialization

import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;

class Sample implements Runnable
 {
         Thread t=null;
         Socket cli=null;
         BufferedReader br=null;
         PrintWriter pw=null;
         Connection cn=null;
         Statement stmt=null;
         Socket cli1=null;
         BufferedReader br1=null;
         PrintWriter pw1=null;

         public Sample(Socket cli,String str)
          {
               try
                {
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	cn=DriverManager.getConnection("jdbc:odbc:DDOS1");
	stmt=cn.createStatement();

	this.cli=cli;
	t=new Thread(this,str);
	br=new BufferedReader(new InputStreamReader(cli.getInputStream()));
	pw=new PrintWriter(new OutputStreamWriter(cli.getOutputStream()),true);
	t.start();
                }
               catch(Exception e)
                { }
          }

        public void run() 
         {
	try
	 {
		String str="";
		while(true)
		 {
		          InetAddress inet=cli.getInetAddress();
		          String hname=inet.getHostName();
	         
	          	           str=br.readLine();      // Accepts the input sent from Connected Node
	        	           if(str.equals("Bye"))
			break;

	          StringTokenizer st1=new StringTokenizer(str,","); // st1[0]=fname , st1[1]=value
	         	           String uname="",fname="",val="";
	         	           int priv=0;
	          	            while(st1.hasMoreTokens())
	           	              {
	      		uname=st1.nextToken();
	      		priv=Integer.parseInt(st1.nextToken());
	      		fname=st1.nextToken();
	      		val=st1.nextToken();
	          	               }
	         String str1="Select * from Transactions where HostName='"+hname+"' and Status='Processing'";
	         System.out.println(str1);
	  		ResultSet rs=stmt.executeQuery(str1);
			int c=0;
			if(rs.next())
		   	      c++;

		System.out.println("C Value : "+c);

	   		  if(c==0)
                     		    {
	//===================================================
	str="Select * from Transactions where UName='"+uname+"' and Status='Processing'";
	System.out.println(str);
		rs=stmt.executeQuery(str);
		int y=0;
		if(rs.next())
		          y++;

	//===================================================
	        if(y==0)
	         {
		str1="Select Max(Req_Id) from Transactions";
		rs=stmt.executeQuery(str1);
		int tid=0;
		try
	 	 {
		            if(rs.next())	
			tid=Integer.parseInt(rs.getString(1))+1;
		 }
		catch(Exception e)
	  	 {
		        tid=1;
		 }
        str1="insert into Transactions values("+tid+",'"+hname+"','"+uname+"','"+fname+"','Processing')";
				stmt.executeUpdate(str1);

	       		                  str=fname+","+val;
	          			System.out.println(str);
	          			System.out.println("Request is Recieved...");

	          	 if(cli1==null)
	         	   {
	           	             cli1=new Socket("localhost",3000);   // Connect to Resource
	br1=new BufferedReader(new InputStreamReader(cli1.getInputStream()));
	pw1=new PrintWriter(new OutputStreamWriter(cli1.getOutputStream()),true);
		System.out.println("Connected to Resource : "+cli1);
	            	 }
		System.out.println("sending to to resource :"+str);
	 	pw1.println(str);   // Sends request to Resource
		System.out.println("sent to to resource");
		str=br1.readLine();   // Accepts the output sent from Resource
		System.out.println("received from resource"+str);
		str1="update Transactions set Status='Completed' where HostName='"+hname+"' and UName='"+uname+"' and Status='Processing' and Req_Id="+tid;
		stmt.executeUpdate(str1);
		pw.println(str);   // Sends the output to User
	              }
	             else
	               {
        str="Select distinct(HostName) from Transactions where UName='"+uname+"' and Status='Processing'";
		rs=stmt.executeQuery(str);
		 String ip="",ips="";
		while(rs.next())
		 {
		       ips=ips+rs.getString(1)+",";                  
		 }		
		
		if(ips.length()>0)
		 {
		         ips=ips.substring(0,ips.length()-1);
		        StringTokenizer stn=new StringTokenizer(ips,",");
		        while(stn.hasMoreTokens())
		         {
			String tkn=stn.nextToken();
			inet=InetAddress.getByName(tkn);
			ip=ip+inet.getHostAddress()+",";
		         }
		 }
	                  pw.println("This User is Already Logged in From Another Machine..."+ip);
	               }
	           }
	          else
	           {
		try
		 {
		       str="Select count(*) from Transactions where UName='"+uname+"' and HostName='"+hname+"' and Status='Processing'";
		        rs=stmt.executeQuery(str);
		        int c1=0;
		        if(rs.next())
		              c1=rs.getInt(1);

		         if(c1==0)
		          {
            pw.println("One Request in Already Under process, Cannot Process second Request...");
		          }
		        else
		         {
			if(c1==priv)
			 {
            pw.println(uname+", U Already Reached Ur Maximum Request Limit... Cannot Process the Current Request...");
			 }
			else
			 {
//=====================
		str1="Select Max(Req_Id) from Transactions";
		rs=stmt.executeQuery(str1);
		int tid=0;
		try
	 	 {
		            if(rs.next())	
			tid=Integer.parseInt(rs.getString(1))+1;
		 }
		catch(Exception e)
	  	 {
		        tid=1;
		 }
		System.out.println(tid);
        str1="insert into Transactions values("+tid+",'"+hname+"','"+uname+"','"+fname+"','Processing')";
				stmt.executeUpdate(str1);

	       		                  str=fname+","+val;
	          			System.out.println(str);
	          			System.out.println("Request is Recieved...");

	          	 if(cli1==null)
	         	   {
	           	             cli1=new Socket("localhost",3000);
	br1=new BufferedReader(new InputStreamReader(cli1.getInputStream()));
	pw1=new PrintWriter(new OutputStreamWriter(cli1.getOutputStream()),true);
		System.out.println("Connected to Resource : "+cli1);
	            	 }
	 	pw1.println(str);   // Sends request to Resource
		str=br1.readLine();   // Accepts the output sent from Resource
		str1="update Transactions set Status='Completed' where HostName='"+hname+"' and UName='"+uname+"' and Status='Processing' and Req_Id="+tid;
		stmt.executeUpdate(str1);
		pw.println(str);   // Sends the output to User
//======================
			 }
		         }
		 }
		catch(Exception e)
	 	 {
	System.out.println("Error in Handling Second Request : "+e);
		 }
 //          pw.println("One Request in Already Under process, Cannot Process second Request...");
	           }
	 }
	 }
	catch(Exception e)
	 {
System.out.println(e.getMessage());
	 }
         }
 }

class StartRouter 
 {
      public static void main(String arg[])
       {
            try
             {
	ServerSocket ser=new ServerSocket(2000);   // Starts the service
	int i=1;
             while(true)
               {
System.out.println("Recvng request");
	Socket cli=ser.accept();   // waits for Client(User) to make connection
System.out.println("Recvd request");
	System.out.println("Connected to Client : "+cli);

	Sample s=new Sample(cli,String.valueOf(i++));
	if(i==-1)
	      break;
               }
	ser.close();
             }
            catch(Exception e)
             {
	System.out.println("Error in Server Initialization...." + e.getMessage());
             }
       }
 }