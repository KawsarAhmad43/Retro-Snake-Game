
package javagames;

import java.awt.Color;
import java.awt.Graphics;


public class Body {
    private int xClr, yClr, width, height;
    public Body(int xClr, int yClr, int tileSize){
        this.xClr=xClr;
        this.yClr=yClr;
        width=tileSize;
        height=tileSize;
        
    }
   public void tick(){
       
   } 
   public void draw(Graphics g){
      g.setColor(Color.GREEN);
      g.fillRect(xClr*width, yClr*height,width,height);
      
   }
   public int getxColor(){
       return xClr;
   }
   public void setxColor(int xClr){
       this.xClr=xClr;
   }
   
   public int getyColor(){
       return yClr;
   }
    public void setyColor(int yClr){
       this.xClr=yClr;
   }
}
