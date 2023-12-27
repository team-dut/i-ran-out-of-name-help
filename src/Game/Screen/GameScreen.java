package Game.Screen;

import Engine.UI.Screen;
import Game.Entity.Maze;
import Game.Entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class GameScreen extends Screen {
    private final int currentMapNumber;
    private Maze currentMaze;
    private Image wallImg, keyImg;
    private Player player;

    public GameScreen(int mapNumber) {
        this.currentMapNumber = mapNumber;

        try {
            this.wallImg = ImageIO.read(Files.newInputStream(Paths.get("resources/Wall/wall.png")));
            this.keyImg = ImageIO.read(Files.newInputStream(Paths.get("resources/Key/key.png")));
        } catch (IOException e) {
            //
        }
    }

    public GameScreen() {
        this(0);
    }

    @Override
    public void render(Graphics2D g2d) {
        Maze maze = this.currentMaze;
        int[][] map = maze.getMazeMatrix();

        g2d.setColor(new Color(135, 205, 246));

        // draw maze and key
        for (int i = 0; i < maze.getHeight(); i++)
            for (int j = 0; j < maze.getWidth(); j++) {
                if (map[i][j] == Maze.WALL_CONST) {
                    g2d.drawImage(wallImg, 10 + j * 28, 10 + i * 28, null);
                }

                if (map[i][j] == Maze.KEY_CONST) {
                    g2d.drawImage(keyImg, 10 + j * 28, 10 + i * 28, null);
                }
            }

        int playerX = this.player.getPostPendingX();
        int playerY = this.player.getPostPendingY();

        // I don't get how the addition of the next if prevent the console from flooding errors
        // but since it works, it's ok.
        if (0 <= playerY && playerY < maze.getHeight() && 0 <= playerX && playerX < maze.getWidth())
            if (map[playerY][playerX] != Maze.WALL_CONST) {
                this.player.move();
            } else {
                this.player.revokePending();
            }

        g2d.drawImage(this.player.getAnimImg(), 10 + player.getX() * 28, 10 + player.getY() * 28, null);
    }

    @Override
    public void init() {
        this.setBackground(Color.black);
        this.getParentWindow().setSize(720, 600);

        String mapStr = "";

        // process map file to string.
        try {
            Scanner scn = new Scanner(Files.newInputStream(Paths.get("resources/Map/map" + this.currentMapNumber + ".txt")));
            StringBuilder sb = new StringBuilder();
            String line;
            while (scn.hasNextLine()) {
                line = scn.nextLine();
                sb.append(line).append('\n');
            }
            mapStr = sb.toString();
        } catch (Exception e) {
            System.err.println("Error Reading Map File !");
        }
        if (mapStr.isEmpty()) {
            System.err.println("Map is Empty !");
        }

        this.currentMaze = Maze.setupFromString(mapStr);
        this.player = new Player(this.currentMaze.getPlayerStartX(), this.currentMaze.getPlayerStartY(), "lizard");

        this.player.setAnimType("idle");
        this.player.resetAnimCounter();

        this.addKeyListener(this.player);
    }
}
