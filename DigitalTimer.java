import java.awt.*;  
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

class MyThread implements Runnable{
    String str;
    int n;
    public static volatile boolean running = false;
    MyThread(String str,int n){
        this.str = str;
        this.n = n;
    }
    /*public void stopRunning(){
         running = false;
    }*/
    public void run(){
       running=true;
       synchronized(this){
           for(int i=0;running;i++)
           {
               //System.out.println(str+(i%60));
               if(n==1){
                     DigitalTimer.displaySec.setText(":       " + i%60);
                     
               }
               else if(n==60)
                  DigitalTimer.displayMin.setText(":       " + i%60 );
               else
                  DigitalTimer.displayHour.setText("       " + i%24);
               try{
           
                  Thread.currentThread().sleep(n*1000);
               }
               catch(InterruptedException e){
                  System.out.println(e);
                  running = false;
               }
           }
       }
    
    }
}
class DigitalTimer implements ActionListener{

    private JFrame mainFrame;    // main frame
    private JPanel contentPane;  // content pane used to hold all the panes
    private JPanel clockPanel;    // clock panel
    private JPanel sidePanel;    // score board panel
    
    public static JLabel displaySec,displayMin,displayHour;
    private JButton startButton,stopButton,restartButton;
    
    MyThread mt1;
    MyThread mt2;
    MyThread mt3;
    /*Thread t1;
    Thread t2;
    Thread t3;*/
    
   private DigitalTimer(){
      mt1 = new MyThread("Sec",1);
      mt2 = new MyThread("Min",60);
      mt3 = new MyThread("Hour",3600);
      
      /*t1 = new Thread(mt1);
      t2 = new Thread(mt2);
      t3 = new Thread(mt3);*/
      
      displaySec = new JLabel();
      displaySec.setForeground(Color.BLUE);
      displaySec.setFont(new Font("Serif", Font.PLAIN, 40));
      
      
      
      displayMin = new JLabel();
      displayMin.setForeground(Color.BLUE);
      displayMin.setFont(new Font("Serif", Font.PLAIN, 40));

      displayHour = new JLabel();
      displayHour.setForeground(Color.BLUE);
      displayHour.setFont(new Font("Serif", Font.PLAIN, 40));
      
      startButton = new JButton("Start");
      startButton.setPreferredSize(new Dimension(126, 126));
      startButton.setFont(new Font("Serif", Font.PLAIN, 40));
      startButton.addActionListener(this);

      stopButton = new JButton("Stop");
      stopButton.setPreferredSize(new Dimension(126, 126));
      stopButton.setFont(new Font("Serif", Font.PLAIN, 40));
      stopButton.addActionListener(this);
      
      restartButton = new JButton("Restart");
      restartButton.setPreferredSize(new Dimension(126, 126));
      restartButton.setFont(new Font("Serif", Font.PLAIN, 40));
      restartButton.addActionListener(this);
      
      clockPanel =new JPanel(new GridLayout(1,3));
      //clockPanel.setLayout(new GridBagLayout(1,3));
      clockPanel.setPreferredSize(new Dimension(762, 514));
      
      
      clockPanel.add(displayHour);
      clockPanel.add(displayMin);
      clockPanel.add(displaySec);
      
      sidePanel = new JPanel(new GridLayout(3,1));
      sidePanel.setPreferredSize(new Dimension(266,514));
      
      sidePanel.add(startButton);
      sidePanel.add(stopButton);
      sidePanel.add(restartButton);
      
      contentPane = new JPanel(new FlowLayout());
      contentPane.add(clockPanel);
      contentPane.add(sidePanel);

        // main frame
      mainFrame = new JFrame("Digital Timer");
      mainFrame.setContentPane(contentPane);
      mainFrame.setSize(1028,762);
      mainFrame.pack();
      mainFrame.setVisible(true);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
   
   }
   
   
   public static void main(String []args){
            
      new DigitalTimer();
   }
   
   public void actionPerformed(ActionEvent e){
       JButton source = (JButton)e.getSource();
       if(source.getText().equals("Start"))
       {
          Thread t1 = new Thread(mt1);
          Thread t2 = new Thread(mt2);
          Thread t3 = new Thread(mt3);
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
       if(source.getText().equals("Restart")){
          //t1.stopRunning();
          //t2.stopRunning();
          //t3.stopRunning();
          //System.out.println("Stop");
          MyThread.running= false;
          displaySec.setText(":       " + 00);
          displayMin.setText(":       " + 00);
          displayHour.setText("       " + 00);
          
       
       }
   
   
   }
}
