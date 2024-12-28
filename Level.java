import java.util.ArrayList;

public class Level {

    public void loadLevel(int levelNumber, ArrayList<Enemy> enemies, ArrayList<Point> points, ArrayList<SpecialPoint> specialPoints) {
        // Exemple de niveaux avec des ennemis et des points différents
        enemies.clear();
        points.clear();
        specialPoints.clear();

        if (levelNumber == 1) {
            // Niveau 1
            enemies.add(new Enemy(200, 500, 50, 50));
            enemies.add(new Enemy(400, 500, 50, 50));
            enemies.add(new Enemy(600, 500, 50, 50));

            points.add(new Point(50, 100));
            points.add(new Point(150, 100));
            points.add(new Point(250, 100));

            specialPoints.add(new SpecialPoint(300, 150));
        } else if (levelNumber == 2) {
            // Niveau 2 (plus difficile)
            enemies.add(new Enemy(100, 400, 50, 50));
            enemies.add(new Enemy(300, 400, 50, 50));
            enemies.add(new Enemy(500, 400, 50, 50));

            points.add(new Point(50, 200));
            points.add(new Point(150, 200));
            points.add(new Point(250, 200));
            points.add(new Point(350, 200));

            specialPoints.add(new SpecialPoint(400, 250));
        } else if (levelNumber == 3) {
            // Niveau 3
            enemies.add(new Enemy(100, 300, 50, 50));
            enemies.add(new Enemy(200, 300, 50, 50));
            enemies.add(new Enemy(300, 300, 50, 50));
            enemies.add(new Enemy(400, 300, 50, 50));

            points.add(new Point(50, 50));
            points.add(new Point(150, 50));
            points.add(new Point(250, 50));
            points.add(new Point(350, 50));

            specialPoints.add(new SpecialPoint(500, 150));
        } else {
            // Ajouter plus de niveaux ou afficher un message de fin de jeu si nécessaire
            // Par exemple, un message de victoire lorsque le joueur atteint un certain niveau
            System.out.println("Congratulations! You have completed all the levels.");
        }
    }
}
