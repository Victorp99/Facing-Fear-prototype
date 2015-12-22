import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import java.applet.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.*;

public class Board extends JPanel implements ActionListener
{
    private Timer timer;
    private FacingFear boy;
    private ArrayList enemies;
    private ArrayList powerups;
    private boolean ingame;
    private int B_WIDTH;
    private int B_HEIGHT;
    
    private AudioClip healthUp;
    private AudioClip hitAt;
    private AudioClip enemyHit;
    private AudioClip boyDead;
    //positions of enemies 
 // make random
  int x1 = (int) (Math.random() * 1000);
  int y1 = (int) (Math.random() * 800);
  int x2 = (int) (Math.random() * 1000);
  int y2 = (int) (Math.random() * 600);
  int x3 = (int) (Math.random() * 1000);
  int y3 = (int) (Math.random() * 800);
  int x4 = (int) (Math.random() * 1000);
  int y4 = (int) (Math.random() * 600);
  int x5 = (int) (Math.random() * 1000);
  int y5 = (int) (Math.random() * 600);
  int x6 = (int) (Math.random() * 1000);
  int y6 = (int) (Math.random() * 800);
  int x7 = (int) (Math.random() * 1000);
  int y7 = (int) (Math.random() * 900);
  int x8 = (int) (Math.random() * 1000);
  int y8 = (int) (Math.random() * 1000);
  /**
   * this is for positoning of the enemies along with the powerups in a random fashion
   */   
  private int[][] pos = 
      { 
        {x1, y1}, {x2, y2}, {x3, y3},
        {x4, y4}, {x5, y5}, {x6, y6},
        {x7, y7},{x8, y8}       
      };
    
    public Board()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("javtile.png"));
        
        add(background);
        setDoubleBuffered(true);
        ingame = true;

        boy = new FacingFear();
        
        initEnemies();
        initPowerUp();

        timer = new Timer(5, this);
        timer.start();
    }
    
     public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();   
    }

    public void initEnemies() {
        enemies = new ArrayList();

        for (int i=0; i<pos.length; i++ ) {
            enemies.add(new Enemy(pos[i][0], pos[i][1]));
        }
    }
     public void initPowerUp() {
        powerups = new ArrayList();        
        for (int i=0; i<3; i++ ) {
            powerups.add(new PowerUp(pos[i][0], pos[i][1]));
        }
    }
     
    
    public void paint(Graphics g) {
       super.paint(g);
       Graphics2D g2d = (Graphics2D)g;

        if (ingame) {
            
            if (boy.isVisible())
                g2d.drawImage(boy.getImage(), boy.getX(), boy.getY(),
                              this);
            
                             

            ArrayList ms = boy.getRocks();
            //checks direction
            
            if(boy.checkXDirection() == -1)
            {
                g2d.drawImage(boy.getImage(), boy.getX(), boy.getY(),
                              this);
            }
            if(boy.checkYDirection() == -1)
            {
                g2d.drawImage(boy.getImage(), boy.getX(), boy.getY(),
                              this);
            }
            /**
             * These are for drawing the images onto the board
             */
            for (int i = 0; i < ms.size(); i++) {
                Rock m = (Rock)ms.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }

            for (int i = 0; i < enemies.size(); i++) {
                Enemy a = (Enemy)enemies.get(i);                
                if (a.isVisible())
                    g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);                   
            }
            for (int i = 0; i < powerups.size(); i++) {
                PowerUp p = (PowerUp)powerups.get(i);               
                if (p.isVisible()==true)
                    g2d.drawImage(p.getImage(), p.getX(), p.getY(), this);                   
            }
          
           
            g2d.setColor(Color.BLACK);
            g2d.drawString("Monsters left: " + enemies.size(), 5, 15);
            g2d.drawString("Health left: " + boy.avatarHealth(), 355, 15);
            g2d.setFont(new Font("Helvetica", Font.BOLD, 215)); 
        }
        if (ingame == false)
            {
               if (boy.avatarHealth() == 0)
               {
                   g2d.drawString("YOU LOSE.", 355, 25);  
                   g2d.setFont(new Font("Helvetica", Font.BOLD, 1));
                   
               }
               else if (enemies.size() == 0)
               {
                   g2d.drawString("You defeated your fears!", 355, 25);  
                   g2d.setFont(new Font("Helvetica", Font.BOLD, 1));
               }
           // String msg = "Game Over";
           // Font small = new Font("Helvetica", Font.BOLD, 14);
           // FontMetrics metr = this.getFontMetrics(small);

          //  g2d.setColor(Color.BLACK);
          //  g2d.setFont(small);
          //  g2d.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
            //             B_HEIGHT / 2);
             }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    public void actionPerformed(ActionEvent e) {
         if (enemies.size()==0) {
            ingame = false;
        }
        //gets all the rocks
        ArrayList ms = boy.getRocks();
        //manage each rock
        //take out for loop and have a method in the rock class that deals
        //with the movement. maybe put move method wihtin it  
        for (int i = 0; i < ms.size(); i++) {
            Rock m = (Rock) ms.get(i);
            if (m.isVisible()) 
            {
                m.flying();
                
            }
            else ms.remove(i);
        }
        
        for (int i = 0; i < enemies.size(); i++) 
        {
            Enemy a = (Enemy) enemies.get(i);
            if (a.isVisible()) 
            {
                int XorY = (int) (Math.random() * 2);

                //if (XorY == 0) 
                //{

                    if (a.getX() > boy.getX()) {

                        a.setX(a.getX() - 1);

                    } 
                    
                    else
                        {
                            if (a.getX() < boy.getX()) 
                            {
                                a.setX(a.getX() + 1);
                            }
                        }
                   // } 
               // else {   // therefore XorY == 1
                if (a.getY() > boy.getY()) 
                    {
                        a.setY(a.getY() - 1);
                    }

                else {

                    if (a.getY() < boy.getY()) 
                      {
                        a.setY(a.getY() + 1);                                
                       }
                     }
               //}   

                //a.move();
            }
            else
            {
            enemies.remove(i);
            
           }         
        }      
       

        boy.move();
        checkCollisions();
        repaint();  
    }
    
    public void checkCollisions() {

        Rectangle r3 = boy.getBounds();

        for (int j = 0; j<enemies.size(); j++) 
        {
            Enemy a = (Enemy) enemies.get(j);
            Rectangle r2 = a.getBounds();

            if (r3.intersects(r2)) {
               try
               {
                       URL url = new URL("file:///D:/G&D/CryofFear/Java/fear/hitSound.wav");
                       hitAt = Applet.newAudioClip(url);
                       hitAt.play();
                       boy.avatarHit();
                       //a.setVisible(false);
                       if(a.getX() > boy.getX())
                       {
                           a.setX(a.getX() + 30);
                        }
                    else
                    {
                        a.setX(a.getX() - 30);
                    }
                    //a.setY(a.getY() + 20);
                    
                    if(boy.avatarHealth() == 0)
                    { 
                    try
                        {                     
                            URL url1 = new URL("http://themushroomkingdom.net/sounds/wav/sm64/sm64_mario_hurt.wav");
                            boyDead = Applet.newAudioClip(url1);
                            boyDead.play();
                            boy.setVisible(false);
                            ingame = false;
                            /**
                             * We can delete this to make sounds of the enemies attackling player even after ending
                             */
                            a.setVisible(false);
                        }
                        catch(MalformedURLException e)
                        {
                            System.err.println("Caught Url exception: " + e.getMessage());
                        }
                   
                     }
               }
               catch(MalformedURLException e)
                        {
                            System.err.println("Caught Url exception: " + e.getMessage());
                        }
            }
        }
        for (int j = 0; j<powerups.size(); j++) {
            PowerUp p = (PowerUp) powerups.get(j);
            Rectangle r0 = p.getBounds();

            if (r3.intersects(r0)) {
                try
                {
                    URL url = new URL("file:///D:/G&D/CryofFear/Java/fear/powerUp.wav");
                    healthUp = Applet.newAudioClip(url);
                    healthUp.play();
                    boy.avatarIncreaseHealth();
                    //a.setVisible(false);
                    p.setVisible(false); 
                    powerups.remove(j);
                }
               catch(MalformedURLException e)
               {
                   System.err.println("Caught Url exception: " + e.getMessage());
               }
                
            }
        }
       
        ArrayList ms = boy.getRocks();

        for (int i = 0; i < ms.size(); i++) {
            Rock m = (Rock) ms.get(i);

            Rectangle r1 = m.getBounds();

            for (int j = 0; j<enemies.size(); j++) {
                Enemy a = (Enemy) enemies.get(j);                
                Rectangle r2 = a.getBounds();

                if (r1.intersects(r2)) {
                     try
                {
                    URL url = new URL("file:///D:/G&D/CryofFear/Java/fear/rockNoise.wav");
                    enemyHit = Applet.newAudioClip(url);
                    enemyHit.play();
                    m.setVisible(false);
                    a.rockLanded();
                    if(a.enemyHealth() == 0)
                    {
                        a.setVisible(false);                     
                       
                    }
                    
                }
               catch(MalformedURLException e)
               {
                   System.err.println("Caught Url exception: " + e.getMessage());
               }
                    
                    
                }
            }
        }
        
    }



    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            boy.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            boy.keyPressed(e);
        }

}
}
