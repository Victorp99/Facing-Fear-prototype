import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class PowerUp
{
   
   
   private int x;
   private int y;  
   private int width;
   private int height;
   private Image image;
   private boolean visible;
   
    public PowerUp(int x,int y)
    {      
     ImageIcon ii = new ImageIcon("pbj.png");
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;    
        
     this.x = x;
     this.y = y;
    }
    public int getX()
    {
        return x;
    }
     public int getY()
    {
        return y;
    }
    
    public Image getImage() {
        return image;
    }   
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
