import javax.swing.*;
import java.util.*;
import java.applet.*;
import java.awt.event.ActionListener;
import objectdraw.*;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Image;
public class Game extends JFrame
{
    public Game()
    {
        begin();
    }
    public VisibleImage background;
    public Image school;    
    
    private void begin()
    {
     
     add(new Board());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setTitle("Facing Fear");
        setResizable(false);
        setVisible(true);       
        
    }
    /*
    public void begin()
    {
      new Game();
    }
*/

     public static void main(String[] args) {
      Game game = new Game();
    
    }
    
    
    
}
