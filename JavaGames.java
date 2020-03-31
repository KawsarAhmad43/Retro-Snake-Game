package javagames;

import javax.swing.JFrame;



public class JavaGames  {
    public JavaGames(){
        JFrame frame= new JFrame();
        GamePanel gamepanel=new GamePanel();
        
        frame.add(gamepanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Retro Snake");
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        
    }
    


    public static void main(String[] args) {
      new JavaGames();
    }
    
}
