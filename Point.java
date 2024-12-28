import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Point {
    protected int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);  // Points de couleur bleue
        g.fillOval(x, y, 10, 10);  // Dessiner un petit cercle pour le point
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);  // Retourner la taille du point pour les collisions
    }
}
