import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Maze extends JFrame {
    private MazePanel mazePanel = new MazePanel(true);

    private Maze() {
        setTitle("Maze");
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(mazePanel);
        mazePanel.setVisible(true);
        mazePanel.generateMaze();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Maze maze = new Maze();
            maze.setVisible(true);
        });
    }

    public static int printArr(Path[][] maze) {
        int count = 0;
        for (int i = 1; i < maze.length - 1; i++) {
            for (int j = 1; j < maze[i].length - 1; j++) {

                if (maze[i][j].hasWallN() && maze[i][j].hasWallS() && maze[i][j].hasWallE() && maze[i][j].hasWallW()) {
                    count++;
                    System.out.println("(" + maze[i][j].getRow() + "," + maze[i][j].getCol() + ")");
                }


            }
        }
        return count;


    }
}
