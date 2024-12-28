import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener {
    private TiledMapLoader mapLoader;
    private Player player;

    public Game() {
        mapLoader = new TiledMapLoader();
        player = new Player(100, 300, 50, 50); // Position initiale du joueur
        mapLoader.loadMap("maps/labyrinthe.tmx"); // Charge la carte depuis le fichier Tiled

        setPreferredSize(new Dimension(800, 600)); // Taille de la fenêtre
        setFocusable(true);
        addKeyListener(this); // Ajouter l'écouteur des événements clavier
    }

    // Méthode pour dessiner la carte et le joueur
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessiner la carte comme arrière-plan
        mapLoader.drawMap(g, 32, 32);  // Supposons que chaque tuile est de 32x32 pixels
        player.draw(g);  // Dessiner le joueur
    }

    // Méthode pour mettre à jour les objets du jeu à chaque frame
    public void update() {
        player.update();  // Mettre à jour la position du joueur
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e); // Gérer les touches enfoncées
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e); // Gérer les touches relâchées
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nous n'avons pas besoin de gérer keyTyped ici
    }

    // Méthode main pour démarrer le jeu
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jeu avec Tiled");
        Game gamePanel = new Game();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Boucle de mise à jour du jeu
        while (true) {
            gamePanel.update();  // Mettre à jour l'état du jeu
            gamePanel.repaint();  // Repeindre l'écran
            try {
                Thread.sleep(16); // Limiter la vitesse de la boucle (environ 60 FPS)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
