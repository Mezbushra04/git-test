import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Obstacle {
    private int x, y, width, height;

    // Constructeur pour initialiser l'obstacle
    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Méthode pour dessiner l'obstacle
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);  // Obstacle de couleur verte
        g.fillRect(x, y, width, height);  // Dessiner un rectangle pour l'obstacle
    }

    // Retourner un rectangle pour la détection des collisions
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);  // Retourner les dimensions pour les collisions
    }
}
