import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SpecialPoint extends Point {
    private boolean collected;

    public SpecialPoint(int x, int y) {
        super(x, y);
        this.collected = false;
    }

    @Override
    public void draw(Graphics g) {
        if (!collected) {
            g.setColor(Color.GREEN);  // Points spéciaux en vert
            g.fillOval(x, y, 15, 15);  // Dessiner un plus grand cercle pour le point spécial
        }
    }

    public void collect() {
        collected = true;  // Marquer le point spécial comme collecté
    }

    public boolean isCollected() {
        return collected;
    }
}
