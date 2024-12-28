import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall {
    private int x, y, width, height;

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);  // Couleur des murs (gris)
        g.fillRect(x, y, width, height);  // Dessiner un mur sous forme de rectangle
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);  // Retourner les dimensions pour les collisions
    }
}
