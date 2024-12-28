import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class TiledMapLoader {
    private int[][] mapData;  // Données de la carte
    private ArrayList<Wall> walls;  // Murs de la carte
    private ArrayList<Point> points;  // Points collectables
    private ArrayList<SpecialPoint> specialPoints;  // Points spéciaux collectables

    public TiledMapLoader() {
        walls = new ArrayList<>();
        points = new ArrayList<>();
        specialPoints = new ArrayList<>();
    }

    public void loadMap(String mapFile) {
        try {
            // Charger le fichier TMX
            File inputFile = new File(mapFile);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Element mapElement = (Element) doc.getElementsByTagName("map").item(0);
            int width = Integer.parseInt(mapElement.getAttribute("width"));
            int height = Integer.parseInt(mapElement.getAttribute("height"));
            int tileWidth = Integer.parseInt(mapElement.getAttribute("tilewidth"));
            int tileHeight = Integer.parseInt(mapElement.getAttribute("tileheight"));

            mapData = new int[height][width];
            NodeList layers = doc.getElementsByTagName("layer");
            Node layerNode = layers.item(0);
            Element layerElement = (Element) layerNode;
            NodeList data = layerElement.getElementsByTagName("data");

            String tileData = data.item(0).getTextContent().trim();
            String[] tilesArray = tileData.split(",");
            int index = 0;

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    mapData[i][j] = Integer.parseInt(tilesArray[index].trim());
                    index++;
                }
            }

            // Créer des objets en fonction des données de la carte (murs, points, etc.)
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int tileId = mapData[i][j] - 1;  // Les IDs commencent à 1
                    if (tileId == 1) {  // ID 1 pour les murs
                        walls.add(new Wall(j * tileWidth, i * tileHeight, tileWidth, tileHeight));
                    } else if (tileId == 2) {  // ID 2 pour les points
                        points.add(new Point(j * tileWidth, i * tileHeight));
                    } else if (tileId == 3) {  // ID 3 pour les points spéciaux
                        specialPoints.add(new SpecialPoint(j * tileWidth, i * tileHeight));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Dessiner la carte en arrière-plan
    public void drawMap(Graphics g, int tileWidth, int tileHeight) {
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[i].length; j++) {
                int tileId = mapData[i][j] - 1;  // Correction d'index (les IDs commencent à 1)
                if (tileId == 0) {
                    continue;  // Si c'est un "vide", on passe
                }
                // Dessiner le fond de la carte (par exemple un simple carré pour chaque tile)
                g.setColor(Color.LIGHT_GRAY);  // Utilisez la couleur ou l'image des tuiles ici
                g.fillRect(j * tileWidth, i * tileHeight, tileWidth, tileHeight);  // Dessiner chaque tuile
            }
        }

        // Dessiner les murs, points et points spéciaux après la carte
        for (Wall wall : walls) {
            wall.draw(g);
        }
        for (Point point : points) {
            point.draw(g);
        }
        for (SpecialPoint specialPoint : specialPoints) {
            specialPoint.draw(g);
        }
    }

    // Getters pour les objets interactifs
    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public ArrayList<SpecialPoint> getSpecialPoints() {
        return specialPoints;
    }
}
