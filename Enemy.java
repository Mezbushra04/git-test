import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
    private int x, y, width, height, dx, dy;

    public Enemy(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dx = (int)(Math.random() * 3) - 1;  // Déplacement aléatoire horizontal (-1, 0 ou 1)
        this.dy = (int)(Math.random() * 3) - 1;  // Déplacement aléatoire vertical (-1, 0 ou 1)
    }

    // Méthode pour déplacer l'ennemi aléatoirement
    public void move() {
        x += dx;  // Déplacement horizontal
        y += dy;  // Déplacement vertical

        // Empêcher les ennemis de sortir des limites de l'écran
        if (x < 0 || x > 570) {
            dx = -dx;
        }
        if (y < 0 || y > 370) {
            dy = -dy;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);  // Couleur des ennemis en rouge
        // Dessiner l'ennemi comme un arc (similaire à Pac-Man)
        g.fillArc(x, y, width, height, 45, 270);  // L'arc représente Pac-Man (45 à 270 degrés)
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);  // Retourner les dimensions pour les collisions
    }
}
