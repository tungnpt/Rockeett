import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet {
    public Vector2D position;
    public Vector2D velocity;
    public BufferedImage image;
    public double range = 0.0;

    public Bullet(){
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        try {
            this.image = ImageIO.read(new File("resources/images/circle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Bullet(Vector2D position, Vector2D velocity){
        this.position = position.copy();
        this.velocity = velocity.copy().multiply(2f);
        try {
            this.image = ImageIO.read(new File("resources/images/circle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void fire(){
        this.position.addUp(this.velocity);
        //Tầm bắn được tính bằng Pytago
        range += Math.sqrt(Math.pow(this.velocity.x,2) + Math.pow(this.velocity.y, 2));
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, (int)this.position.x, (int)this.position.y, 7, 7, null);
    }
}
