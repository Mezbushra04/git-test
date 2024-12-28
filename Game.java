import javax.swing.JPanel;  // Pour la classe JPanel
import javax.swing.JFrame;  // Pour la classe JFrame
import javax.swing.Timer;   // Pour utiliser Timer (pour le compte à rebours)
import java.awt.Graphics;   // Pour les méthodes de dessin sur le composant
import java.awt.Color;      // Pour utiliser les couleurs
import java.awt.event.KeyEvent;  // Pour gérer les événements du clavier
import java.awt.event.KeyListener;  // Pour écouter les événements du clavier
import java.awt.event.ActionEvent;  // Pour gérer les actions du Timer
import java.util.ArrayList;   // Pour gérer les listes des ennemis, murs, etc.
import java.awt.Dimension;  // Pour définir la dimension du jeu
import java.awt.Rectangle;  // Pour la gestion des zones de collision

public class Game extends JPanel implements KeyListener {

    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Wall> walls;
    private ArrayList<Point> points;
    private ArrayList<SpecialPoint> specialPoints;
    private boolean gameOver;
    private int lives;
    private int level;
    private Timer timer;  // Pour le compte à rebours
    private int timeLeft;  // Temps restant dans le niveau en secondes

    public Game() {
        this.setPreferredSize(new Dimension(600, 400));  // Dimension du jeu
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);

        player = new Player(100, 300, 30, 30);  // Position du joueur
        enemies = new ArrayList<>();
        walls = new ArrayList<>();
        points = new ArrayList<>();
        specialPoints = new ArrayList<>();
        gameOver = false;
        lives = 3;  // Le joueur commence avec 3 vies
        level = 1;
        timeLeft = 60;  // 60 secondes pour chaque niveau

        // Créer des ennemis
        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy(50 + i * 100, 200, 30, 30)); // Position initiale des ennemis
        }

        // Créer des points
        for (int i = 0; i < 10; i++) {
            points.add(new Point(50 + i * 60, 100)); // Position des points à manger
        }

        // Créer des points spéciaux (bonus)
        specialPoints.add(new SpecialPoint(300, 200)); // Exemple de point spécial

        loadNewLevel();  // Charger le niveau au démarrage

        // Timer pour le compte à rebours
        timer = new Timer(1000, (ActionEvent e) -> {
            if (timeLeft > 0) {
                timeLeft--;
            } else {
                gameOver = true;  // Si le temps est écoulé, la partie est terminée
            }
        });
        timer.start();
    }

    private void loadNewLevel() {
        // Créer des murs spécifiques à chaque niveau pour former un labyrinthe
        walls.clear();  // Vider la liste des murs avant de charger un nouveau niveau

        if (level == 1) {
            // Créer des murs pour le niveau 1
            walls.add(new Wall(100, 50, 400, 10));  // Mur horizontal
            walls.add(new Wall(100, 100, 10, 200));  // Mur vertical
            walls.add(new Wall(200, 200, 200, 10));  // Mur horizontal
            walls.add(new Wall(400, 100, 10, 150));  // Mur vertical
        } else if (level == 2) {
            // Créer des murs pour le niveau 2
            walls.add(new Wall(50, 50, 10, 300));  // Mur vertical
            walls.add(new Wall(150, 50, 10, 200));  // Mur vertical
            walls.add(new Wall(200, 100, 300, 10));  // Mur horizontal
            walls.add(new Wall(100, 250, 200, 10));  // Mur horizontal
        }

        // Ajouter un point bonus ou des points supplémentaires dans ce niveau
        points.add(new Point(550, 300));  // Exemple de point bonus
        specialPoints.add(new SpecialPoint(450, 150)); // Ajouter un point spécial
    }

    public void update() {
        if (!gameOver) {
            player.update();  // Mettre à jour la position du joueur

            // Déplacer les ennemis de manière aléatoire
            for (Enemy enemy : enemies) {
                enemy.move();  // Déplacement aléatoire des ennemis
            }

            // Vérifier les collisions avec les ennemis
            for (Enemy enemy : enemies) {
                if (player.getBounds().intersects(enemy.getBounds())) {
                    lives--;  // Perdre une vie si collision avec un ennemi
                    player.resetPosition();  // Réinitialiser la position du joueur

                    if (lives <= 0) {
                        gameOver = true;  // Fin du jeu si plus de vies
                    }
                    break;
                }
            }

            // Vérifier la collision avec les murs (obstacles)
            for (Wall wall : walls) {
                if (player.getBounds().intersects(wall.getBounds())) {
                    player.resetPosition();  // Réinitialiser si collision avec un mur
                }
            }

            // Vérifier si le joueur mange un point
            for (int i = 0; i < points.size(); i++) {
                if (player.getBounds().intersects(points.get(i).getBounds())) {
                    points.remove(i);  // Supprimer le point collecté
                    i--;
                }
            }

            // Vérifier si le joueur mange un point spécial
            for (int i = 0; i < specialPoints.size(); i++) {
                if (player.getBounds().intersects(specialPoints.get(i).getBounds())) {
                    specialPoints.remove(i);  // Supprimer le point spécial collecté
                    timeLeft += 5;  // Ajouter 5 secondes au temps restant
                    i--;
                }
            }

            // Passer au niveau suivant si tous les points sont collectés
            if (points.isEmpty()) {
                level++;
                timeLeft = 60;  // Réinitialiser le temps
                loadNewLevel();  // Charger un nouveau niveau
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!gameOver) {
            // Dessiner le joueur
            player.draw(g);

            // Dessiner les ennemis
            for (Enemy enemy : enemies) {
                enemy.draw(g);
            }

            // Dessiner les murs (labyrinthe)
            for (Wall wall : walls) {
                wall.draw(g);
            }

            // Dessiner les points à collecter
            for (Point point : points) {
                point.draw(g);
            }

            // Dessiner les points spéciaux (bonus)
            for (SpecialPoint specialPoint : specialPoints) {
                specialPoint.draw(g);
            }

            // Afficher les vies restantes, le niveau et le temps restant
            g.setColor(Color.WHITE);
            g.drawString("Lives: " + lives, 10, 10);
            g.drawString("Level: " + level, 10, 30);
            g.drawString("Time: " + timeLeft, 500, 10);
        } else {
            // Afficher le message de Game Over
            g.setColor(Color.WHITE);
            g.drawString("Game Over", 250, 200);  // Afficher "Game Over"
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);  // Gérer les touches du clavier
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);  // Gérer les touches du clavier
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Labyrinth Game");
        Game game = new Game();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(game);
        frame.pack();
        frame.setVisible(true);

        // Jeu en boucle
        while (!game.gameOver) {
            game.update();
            game.repaint();

            try {
                Thread.sleep(100);  // Contrôler la vitesse du jeu
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
