import java.applet.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class FacingFear
{

    /**
     * Constructor for objects of class FacingFear
     */
    private String boy = "StayAttack1.png"; //right
    private String up = "up.png";
    private String left = "left.png"; //dont  have a left pick yet
    private String down = "down.png";
    
    private int dx;
    private int dy;
    private int x;
    private int y;
     private int width;
    private int height;
    private boolean visible;
    private int health;
    private int fx; // -1 = facing left , 1 = facing right, set to 0 when going up or down
    private int fy; // -1 = facing up, 1 = facing down, set to 0 when going left or right
    //have another method that checks these values and changes the picture of the avatar
    //use these variables to decide which direction the player is shooting in
    private Image image;
    private ArrayList rocks;
//think of how we can tell the story in the game without cutscenes
    private final int BOY_SIZE = 50;

    public FacingFear()
    {
        // initialise instance variables
        ImageIcon ii = new ImageIcon(this.getClass().getResource(boy));
        image = ii.getImage();
         width = image.getWidth(null);
        height = image.getHeight(null);
         rocks = new ArrayList();
         visible = true;
         health = 10;
        x = 40;
        y = 60;
        fx = 1; // start facing right
        fy = 0; 
    }
    
     public void move() {
        x += dx;
        y += dy;
        
        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
    public ArrayList getRocks() {
        return rocks;
    }
    
    public int avatarHit()
    {
        --health;
        return health;
    }
    
    public int avatarHealth()
    {
        return health;
    }
   /**
    * player powerup
    */
    public double avatarIncreaseHealth()
    {   
        
        health = health + 3;
        return health;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
     public boolean isVisible() {
        return visible;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }



    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }
        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
            fx = -1;
            fy = 0;
           
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
            fx = 1;
            fy = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
            fy = -1;
            fx = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
            fy = 1;
            fx = 0;
        }
        //call method to change image of avatar
        checkXDirection();
        checkYDirection();
        
    }
    //check directions
    public int checkXDirection()
    {
        if(fx == -1)
        {
           ImageIcon ii = new ImageIcon(this.getClass().getResource(left));
            image = ii.getImage();
            return -1; // left
        }
        else if(fx == 1)
        {
            ImageIcon ii = new ImageIcon(this.getClass().getResource(boy));
            image = ii.getImage();
            return 1; //right
        }
        else
        {
            return 0; //up or down
        }
    }
    public int checkYDirection()
    {
        if(fy == -1)
        {
            ImageIcon ii = new ImageIcon(this.getClass().getResource(up));
            image = ii.getImage();
            return -1; //up
        }
        else if(fy == 1)
        {
            ImageIcon ii = new ImageIcon(this.getClass().getResource(down));
            image = ii.getImage();
            return 1; // down
        }
        else
        {
            return 0; // left or right
        }
    }
     public void fire() {
        rocks.add(new Rock(x + width, y + height/2, checkXDirection(), checkYDirection()));
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }


}
