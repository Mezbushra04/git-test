import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player {
    private int x, y, width, height;
    private int dx, dy;

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        dx = 0;
        dy = 0;
    }

    // Méthode pour mettre à jour la position du joueur
    public void update() {
        x += dx;
        y += dy;
    }

    // Méthode pour dessiner le joueur sur l'écran
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillArc(x, y, width, height, 45, 270);
    }

    // Méthodes pour obtenir les coordonnées du joueur
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Gérer les touches du clavier
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -5;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 5;
        }
        if (key == KeyEvent.VK_UP) {
            dy = -5;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 5;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    public void resetPosition() {
        x = 100;
        y = 300;
    }
}
