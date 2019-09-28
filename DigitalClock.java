import java.awt.*;  
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.text.*;

class MyThread implements Runnable{
    String str;
    int min,sec,hour;
    static Date d;
    SimpleDateFormat sdf;
    String format;
    static{
       d = new Date();
    }
    //public static volatile boolean running = false;
    MyThread(String str){
        this.str = str;
        sdf = new SimpleDateFormat("hhmmss");
        format = sdf.format(d);
    }
    /*public void stopRunning(){
         running = false;
    }*/
    public void run(){
       //running=true;
       sec = (format.charAt(4)-'0')*10+(format.charAt(5)-'0');
       min = (format.charAt(2)-'0')*10+(format.charAt(3)-'0');
       hour = (format.charAt(0)-'0')*10+(format.charAt(1)-'0');
       synchronized(this){
           
           for(int i=0;;i++)
           {
              try{
     
                     DigitalClock.displaySec.setText(":       " + (sec+i)%60);
                     DigitalClock.displayMin.setText(":       " + (min+((sec+i)/60))%60 );
                     DigitalClock.displayHour.setText("       " +(hour+(min+(sec+i)/60)/60)%24);
                     Thread.currentThread().sleep(1000);
                     
               }
               catch(InterruptedException e){
                  System.out.println(e);
                  //running = false;
               }
           }
       }
    
    }
}
class DigitalClock{

    private JFrame mainFrame;    // main frame
    private JPanel contentPane;  // content pane used to hold all the panes
    private JPanel clockPanel;    // clock panel
    //private JPanel sidePanel;    // score board panel
    
    public static JLabel displaySec,displayMin,displayHour;
    //private JButton startButton,stopButton;
    
    MyThread mt;
    Thread t;
    
   private DigitalClock(){
      mt = new MyThread("Time"); 
      t = new Thread(mt);
      
      displaySec = new JLabel();
      displaySec.setForeground(Color.BLUE);
      displaySec.setFont(new Font("Serif", Font.PLAIN, 40));
      
      
      
      displayMin = new JLabel();
      displayMin.setForeground(Color.BLUE);
      displayMin.setFont(new Font("Serif", Font.PLAIN, 40));

      displayHour = new JLabel();
      displayHour.setForeground(Color.BLUE);
      displayHour.setFont(new Font("Serif", Font.PLAIN, 40));
      
      /*startButton = new JButton("Start");
      startButton.setPreferredSize(new Dimension(126, 126));
      startButton.setFont(new Font("Serif", Font.PLAIN, 40));
      startButton.addActionListener(this);

      stopButton = new JButton("Stop");
      stopButton.setPreferredSize(new Dimension(126, 126));
      stopButton.setFont(new Font("Serif", Font.PLAIN, 40));
      stopButton.addActionListener(this);*/
      
      clockPanel =new JPanel(new GridLayout(1,3));
      clockPanel.setPreferredSize(new Dimension(762, 257));
      
      
      clockPanel.add(displayHour);
      clockPanel.add(displayMin);
      clockPanel.add(displaySec);
      
     /* sidePanel = new JPanel(new GridLayout(2,1));
      sidePanel.setPreferredSize(new Dimension(266,514));
      
      sidePanel.add(startButton);
      sidePanel.add(stopButton);*/
      
      contentPane = new JPanel(new FlowLayout());
      contentPane.add(clockPanel);
      //contentPane.add(sidePanel);

        // main frame
      mainFrame = new JFrame("Digital Clock");
      mainFrame.setContentPane(contentPane);
      mainFrame.setSize(762,257);
      mainFrame.pack();
      mainFrame.setVisible(true);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      t.start();

   }
   
   
   public static void main(String []args){
            
      new DigitalClock();
   }
   
   /*public void actionPerformed(ActionEvent e){
       JButton source = (JButton)e.getSource();
       if(source.getText().equals("Start"))
       {
          t1.start();
          t2.start();
          t3.start();
       }
       if(source.getText().equals("Stop")){
          //t1.stopRunning();
          //t2.stopRunning();
          //t3.stopRunning();
          //System.out.println("Stop");
          MyThread.running= false;
       
       }
   
   
   } */
}
