import java.awt.Image;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
public class Rock
{
    //maybe have shooting based off of wasd, set animations then also 
    private int x, y;
    private Image image;
    boolean visible;
    private int width, height;

    private final int BOARD_WIDTH = 1000;
    private final int BOARD_HEIGHT = 600;
    private final int ROCK_SPEED = 3;
    private int thrown; // used to check what direction previously thrown
    // 1= right , 2 = left, 3 = up, 4 = down
    public Rock(int x, int y, int fx, int fy)
    {
      ImageIcon ii =
            new ImageIcon(this.getClass().getResource("stone.png"));
        image = ii.getImage();
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
        this.x = x;
        this.y = y;
        if(fx == 1) //right
        {
            thrown = 1;
        }
        if(fx == -1) // left
        {
            thrown = 2;
        }
        if(fy == -1)//up
        {
            thrown = 3;
        }
        if(fy == 1) // down
        {
            thrown = 4;
        }
        
    }
    
     public Image getImage() {
        return image;
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

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }    
    public void flying()
    {
        if(thrown == 1)
        {
          
                x+= ROCK_SPEED;
            if (x > BOARD_WIDTH)
            {
               visible = false;
            }
            
        }
        if(thrown == 2)
        {
            
                x-= ROCK_SPEED;
            if (x < 0)
            {
               visible = false;
            }
            
        }
        if(thrown == 3)
        {
             y -= ROCK_SPEED;
            if (y <= 0)
            {
                visible = false;
            }
        }
        if(thrown == 4)
        {
             y += ROCK_SPEED;
            if (y > BOARD_HEIGHT)
            {
                visible = false;
            }
        }
    }
   

  
}
