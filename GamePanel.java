
package javagames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable, KeyListener {
    private static final long serailVersionUID = 1l;
    public static final int width=1000,height=1000;
    private Thread thread;
    private boolean running;
    private boolean right=true,left=false,up=false,down=false;
    private Body b;
    private ArrayList<Body> snake;
    
    private Apple apple;
    private ArrayList<Apple>apples;
    private Random r;
    public int p=0;
    
    private int xClr=10,yClr=10,size=5;
    private int ticks=0;
    
   public GamePanel(){
       setFocusable(true);
       setPreferredSize(new Dimension(width,height));
       addKeyListener(this);
       snake = new ArrayList<Body>();
       apples = new ArrayList<Apple>();
       
       r = new Random();
       start();
   }
   
   
   
   public void start(){
       running =true;
       thread=new Thread(this);
       thread.start();
   }
   
   public void stop(){
      running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   public void tick(){
      
     if(snake.size()==0){
         b= new Body(xClr,yClr,10);
         snake.add(b);
     } 
     ticks++;
     if(ticks>250000){
         if(right)xClr++;
         if(left)xClr--;
         if(up)yClr--;
         if(down)yClr++;
         
         ticks=0;
         b=new Body(xClr,yClr,10);
         snake.add(b);
         if(snake.size()>size){
             snake.remove(0);
         }
       }
     if(apples.size()==0){
         int xClr=r.nextInt(49);
         int yClr=r.nextInt(49);
         apple = new Apple(xClr, yClr,10);
         apples.add(apple);
     }
     for(int i=0;i<apples.size();i++){
         if(xClr==apples.get(i).getxColor() && yClr==apples.get(i).getyColor()){
             size++;
             apples.remove(i);
             i++;
             p++;
         }
     }
    
     if(xClr < 0 || xClr > 96 || yClr < 0 || yClr>96){
        System.out.println("Game Over!");
        System.out.println(p);
         stop();
     }
   }
   
   public void paint(Graphics g){
       g.clearRect(0, 0, width, height);
       g.setColor(Color.BLACK);
       g.fillRect(0, 0, width, height);
       for(int i=0;i<width/10;i++)
       {
           g.drawLine(i*10, 0, i*10, height);
       }
       for(int i=0;i<height/10;i++)
       {
           g.drawLine(0,i*10, height, i*10);
       }
       for(int i=0;i<snake.size();i++){
           snake.get(i).draw(g);
       }
       for(int i=0;i<apples.size();i++){
           apples.get(i).draw(g);
       }
   }

    @Override
    public void run() {
        while(running){
            tick();
            repaint();
        }
    }

   

    @Override
    public void keyPressed(KeyEvent e) {
       int key = e.getKeyCode();
       if(key==KeyEvent.VK_RIGHT && !left){
           right=true;
           up=false;
           down=false;
       }
       if(key==KeyEvent.VK_LEFT && !right){
           left=true;
           up=false;
           down=false;
       }
       if(key==KeyEvent.VK_UP && !down){
           up=true;
           left=false;
           right=false;
       }
       if(key==KeyEvent.VK_DOWN && !up){
           down=true;
           left=false;
           right=false;
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
     @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
