 	//   Resource1.java

import java.net.*;
import java.io.*;
import java.util.*;

class Sample implements Runnable
 {
       Socket cli=null;
       Thread t=null;
       BufferedReader br=null;
       PrintWriter pw=null;


       public Sample(Socket cli,String str)
        {
	try
	 {
	          this.cli=cli;
	          t=new Thread(this,str);
	          br=new BufferedReader(new InputStreamReader(cli.getInputStream()));
	          pw=new PrintWriter(new OutputStreamWriter(cli.getOutputStream()),true);
	          t.start();
	 }
	catch(Exception e)
	 {}
        }

       public void run()
        {
	try
	 {
		String str="";
		while(true)
		 {
		           	str=br.readLine();
			System.out.println("str-->" + str);
	  	         	if(str.equals("Bye"))
			break;

	        	        StringTokenizer st=new StringTokenizer(str,",");
	        	        String fname="";
	       	         int val=0;
	        	         while(st.hasMoreTokens())
	        	           {
			fname=st.nextToken();
			val=Integer.parseInt(st.nextToken());
	           	            }

		        if(fname.equals("Prime"))
		        {
	               	                  str=Sample.Prime(val);   // Invoking the function
			System.out.println("sent to router :"+str);
			 pw.println(str);    // Sends output to Router
		        }
		        else if(fname.equals("Factorial"))
		        {
	               	                  str=Sample.Factorial(val);   // Invoking the function
			System.out.println("sent to router :"+str);
			 pw.println(str);    // Sends output to Router
		        }
		        else if(fname.equals("ArmStrong"))
		        {
	               	                  str=Sample.ArmStrong(val);   // Invoking the function
			System.out.println("sent to router :"+str);
			 pw.println(str);    // Sends output to Router
		         }
		        else if(fname.equals("Even/Odd"))
		        {
	               	                  str=Sample.Even(val);   // Invoking the function
			System.out.println("sent to router :"+str);
			 pw.println(str);    // Sends output to Router
		        }
		       else if(fname.equals("Money Converter"))
		      {
			int i=Resource1.i;
			Sample s=new Sample(cli,String.valueOf(i++));
			
			MoneyConverter mc=new MoneyConverter(br,pw);
		     }
		     else if(fname.equals("Synonyms Fetcher"))
		   {
				int i=Resource1.i;
				Sample s=new Sample(cli,String.valueOf(i++));
				SynonymsFetcher sf=new SynonymsFetcher(br,pw);
		   }
		  else if(fname.equals("Premium Calculator"))
		 {	int i=Resource1.i;
			Sample s=new Sample(cli,String.valueOf(i++));
			PremiumCalculator pc=new PremiumCalculator(br,pw);
		 }
		        try
		         {
			Thread.sleep(10000);
	         	         }
	        	       catch(InterruptedException e)
	         	         {

		         }	                                 
	 	   }
	 }
	catch(Exception e)
	 {
 	       pw.println("Error at Resource in Handling the Process...");
	 }
        }
       public static String Prime(int no)
         {
	int c=0;
	for(int i=1;i<=no;i++)
	 {
	          if(no%i==0)
		c++;
	 }

	if(c==2)
	       return "Given Number is Prime";
	else
	       return "Given Number is Composite...";
         }
       public static String Factorial(int n)
        {
	long f=1;
	for(int i=1;i<=n;i++)
	        f=f*i;

	return "Factorial of "+n+" is "+f;
        }
       public static String ArmStrong(int n)
        {
	int no,tot=0;
	no=n;

	while(n>0)
	 {
	          int re=n%10;
	          tot=tot+re*re*re;
	          n=(int)n/10;
	 }

	if(no==tot)
	        return "Given Number is ArmStrong...";
	else
	        return "Given Number is Not ArmStrong...";
        }
       public static String Even(int n)
       {
	if(n%2==0)
	        return "Given Number is Even....";
	else
	        return "Given Number is Odd....";
       }
 }
class Resource1
 {
	static  int i=1;
        public static void main(String arg[])
         {
              try
               {
   	ServerSocket ser=new ServerSocket(3000);
	
	while(true)
	 {
		Socket cli=ser.accept();   // Waits for Grid Connection
		System.out.println("Connected to Router : "+cli);
		
		Sample s=new Sample(cli,String.valueOf(i++));
		if(i==-1)
		   break;
	 }
               }
              catch(Exception e)
               {
	    System.out.println("Errr in Initializing the Resource..."+e);
               }
         }
 }