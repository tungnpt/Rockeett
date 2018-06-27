import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameCanvas extends JPanel {

    BufferedImage backBuffered;
    Graphics graphics;

    int countStar = 0;
    int countEnemy = 0;

    List<Star> stars;
    ArrayList<Enemy> enemies;

    Background background;

    public Player player = new Player();

    private Random random = new Random();


    public GameCanvas() {
        this.setSize(1024, 600);

        this.setupBackBuffered();

        this.setupCharacter();

        this.setVisible(true);
    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_4BYTE_ABGR);
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupCharacter() {
        this.background = new Background();
        this.stars = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.setupPlayer();
    }

    private void setupPlayer() {
        this.player.position.set(100, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        this.background.render(this.graphics);
        this.stars.forEach(star -> star.render(graphics));
        this.player.render(this.graphics);
        //this.enemy.render(this.graphics);
        this.enemies.forEach(enemy -> enemy.render(graphics));
        this.repaint();
    }

    public void runAll() {
        this.createStar();
        this.createEnemy();
        this.stars.forEach(star -> star.run());
        this.runEnemy();
        this.player.run();
    }

    private void createStar() {
        if (this.countStar == 30) {
            Star star = new Star(
                    1024,
                    this.random.nextInt(600),
                    this.loadImage("resources/images/star.png"),
                    -this.random.nextInt(3) + 1,
                    0
            );

            this.stars.add(star);
            this.countStar = 0;
        } else {
            this.countStar += 1;
        }
    }

    private void createEnemy() {
        if (this.countEnemy == 100) {
            Enemy e = new Enemy();
            e.position.set(random.nextInt(1025), random.nextInt(601));
            e.image = this.loadImage("resources/images/circle.png");
            enemies.add(e);
            this.countEnemy = 0;
        } else {
            this.countEnemy += 1;
        }
    }

    private void runEnemy() {
        for (int i = 0; i < enemies.size(); i++) {
            Vector2D velocity = this.player.position
                    .subtract(this.enemies.get(i).position)
                    .normalize()
                    .multiply(1.5f);
            enemies.get(i).velocity.set(velocity);
            //Cứ mỗi 20 con Enemy lại có 1 con bắn đạn tỏa tròn
            if (i % 20 == 19) {
                enemies.get(i).runSpecial();
            } else {
                enemies.get(i).run();
            }
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}
