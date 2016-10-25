//package splashscreentest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.geom.Rectangle2D;
import javax.swing.*;


public class splashScreenModule
{   
	//static loginModule lm;
    public static SplashScreen loadingScreen;
    public static Rectangle2D.Double loadingTextArea;
    public static Rectangle2D.Double loadingProgressArea;
    public static Graphics2D loadingGraphics;
    
    public static void loadingMethod()
    {   
	loadingScreen=SplashScreen.getSplashScreen();
        if(loadingScreen!=null)
        {   
	Dimension dim=loadingScreen.getSize();
            	int ht=dim.height;
            	int wd=dim.width;
            
            	loadingTextArea=new Rectangle2D.Double(2, ht*0.75, wd*0.5, 25);
            	loadingProgressArea=new Rectangle2D.Double(2, ht*0.85, wd-3, 3);
            	loadingGraphics=loadingScreen.createGraphics();            
        }
    }    
    public static void loadingText(String s)
    {  
	 if(loadingScreen!=null)
        	{   
		loadingGraphics.setPaint(Color.cyan);
            		loadingGraphics.fill(loadingTextArea);
            		loadingGraphics.setPaint(Color.BLACK);
   loadingGraphics.drawString(s, (int) loadingTextArea.getX()+10, (int) loadingTextArea.getY()+20);
            loadingScreen.update();
        }
    }
    
    public static void loadingProgress(int prog)
    {   if(loadingScreen!=null)
        {   loadingGraphics.setPaint(Color.LIGHT_GRAY);
            loadingGraphics.fill(loadingProgressArea);
            loadingGraphics.setPaint(Color.WHITE);
            loadingGraphics.draw(loadingProgressArea);
            
            int x=(int) loadingProgressArea.getMinX();
            int y=(int) loadingProgressArea.getMinY();
            
            int width=(int) loadingProgressArea.getWidth();
            int height=(int) loadingProgressArea.getHeight();
            
            int doneProg=prog*width/100;
            loadingGraphics.setPaint(Color.RED);
            loadingGraphics.fillRect(x, y, doneProg, height);
            loadingScreen.update();
        }
    }
    
    public static void mainMethod()
    {   int i=1;
        while(i>=1 && i<=10)
        {   loadingText("Reading module storage... on");
            loadingProgress(i);
            i++;
        }
        while(i>=11 && i<=30)
        {   loadingText("Turning on modules ...");
            loadingProgress(i);
            i++;
        }
        while(i>=31 && i<=50)
        {   loadingText("Loading module services ...");
            loadingProgress(i);
            i++;
        }
        while(i>=51 && i<=70)
        {   loadingText("Loading modules ...");
            loadingProgress(i);
            i++;
        }
        while(i>=71 && i<=90)
        {   loadingText("Starting modules ...");
            loadingProgress(i);
            i++;
        }
        while(i>=91 && i<=100)
        {   loadingText("Done loading modules ...");
            loadingProgress(i);
            i++;
        }
    }
    
    public static void main(String[] args)
    {   
	loadingMethod();
        	mainMethod();
        if(loadingScreen!=null)
        {   	
	loadingScreen.close();
	StartUser su=new StartUser("USER LOGIN");
	su.setSize(300,300);
	su.setLocation(300,200);
	su.setResizable(false);
	su.setVisible(true);		
        }
    }
}