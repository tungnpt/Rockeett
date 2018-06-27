import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Enemy {

    public Vector2D position;
    public BufferedImage image;
    public Vector2D velocity;

    public LinkedList<Bullet> bullets = new LinkedList<Bullet>();

    public int count = 0;
    public int countSpecial = 0;

    public Enemy() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
    }

    //Enemy bắn bình thường
    public void run() {
        this.position.addUp(this.velocity);
        if (count == 10) {
            Bullet bullet = new Bullet(this.position, this.velocity);
            this.bullets.add(bullet);
            this.count = 0;
        } else {
            count++;
        }
        //Tầm bắt của mỗi Bullet
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).range >= 300) {
                bullets.remove(i);
            }
        }
        this.bullets.forEach(bullet -> bullet.fire());
    }

    //Enemy bắn đạn tỏa tròn
    public void runSpecial() {
        this.position.addUp(this.velocity);
        if (count == 3) {
            countSpecial++;
            if (countSpecial % 4 == 0) {
                Bullet bullet = new Bullet(this.position, this.velocity);
                this.bullets.add(bullet);
            }
            if (countSpecial % 4 == 1) {
                Bullet bullet = new Bullet(this.position, this.velocity.rotate(90));
                this.bullets.add(bullet);
            }
            if (countSpecial % 4 == 2) {
                Bullet bullet = new Bullet(this.position, this.velocity.rotate(180));
                this.bullets.add(bullet);
            }
            if (countSpecial % 4 == 3) {
                Bullet bullet = new Bullet(this.position, this.velocity.rotate(270));
                this.bullets.add(bullet);
            }
            if (countSpecial >= 4) {
                countSpecial = 0;
            }
            count = 0;
        } else {
            count++;
        }
        //Tầm bắn của mỗi Bullet
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).range >= 300) {
                bullets.remove(i);
            }
        }
        this.bullets.forEach(bullet -> bullet.fire());
    }


    public void render(Graphics graphics) {
        graphics.drawImage(this.image, (int) this.position.x, (int) this.position.y, 20, 20, null);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).render(graphics);
        }
    }


}
