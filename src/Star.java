import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Star {

    public int x;
    public int y;
    public BufferedImage image;
    public int velocityX;
    public int velocityY;

    public Star(int x, int y, BufferedImage image, int velocityX, int velocityY) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public Star() {
    }

    public void run() {
        this.x += this.velocityX;
        this.y += this.velocityY;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, this.x, this.y, 5, 5, null);
    }
}
