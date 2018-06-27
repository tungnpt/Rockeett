import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Player {
    public Vector2D position;
    private List<Vector2D> vertices;
    private Polygon polygon;
    public Vector2D velocity;
    public double angle = 0.0;
    public LinkedList<Bullet> bullets = new LinkedList<Bullet>();

    public int count = 0;


    public Player() {
        this.position = new Vector2D();
        this.vertices = Arrays.asList(
                new Vector2D(),
                new Vector2D(0, 16),
                new Vector2D(20, 8)
        );
        this.polygon = new Polygon();
        this.velocity = new Vector2D(3.5f, 0);
    }

    public void run() {
        this.position.addUp(this.velocity);
        if (count == 10) {
            Bullet bullet = new Bullet(this.position, this.velocity);
            this.bullets.add(bullet);
            this.count = 0;
        } else {
            count++;
        }
        this.bullets.forEach(bullet -> bullet.fire());
    }

    public void render(Graphics graphics) {
        this.polygon.reset();

        Vector2D center = this.vertices
                .stream()
                .reduce(new Vector2D(), (v1, v2) -> v1.add(v2))
                .multiply(1 / this.vertices.size())
                .rotate(this.angle);

        Vector2D translation = this.position.subtract(center);

        this.vertices
                .stream()
                .map(vertex -> vertex.rotate(angle))
                .map(vertex -> vertex.add(translation))
                .forEach(vertex -> polygon.addPoint((int) vertex.x, (int) vertex.y));

        graphics.setColor(Color.RED);
        graphics.fillPolygon(this.polygon);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).render(graphics);
        }
    }
}
