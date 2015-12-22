import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Enemy
{
   
    private String craft = "ghost.png";

    private int x;
    private int y;
    private int width;
    private int height;
    private int health;
    private boolean visible;
    private Image image;

    public Enemy(int x, int y)
    {
        
        ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        health = 4;
        this.x = x;
        this.y = y;
     
    }
    public void chase()
    {
        
    }
    public void move() {
        if (x < 0) 
            x = 400;
        x -= 2;
    }
    public void setX(int x1)
    {
        x = x1;
    }
    public void setY(int y1)
    {
        y = y1;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public int rockLanded()
    {
        --health;
        return health;
    }
    
    public int enemyHealth()
    {
        return health;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
