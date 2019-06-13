import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MazePanel extends JPanel {

    private static Path[][] maze;
    private final int size = 50;

    public MazePanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.translate(50, 60);

        int factor = 800 / size;

        if (factor < 4) {
            factor = 4;
        }

        Color color = Color.BLACK;

        if (maze == null) {
            return;
        }

        for (int row = 1; row < maze.length - 1; row++) {
            for (int col = 1; col < maze[row].length - 1; col++) {

                g.setColor(color);

                if (maze[row][col].hasWallN()) { // - top
                    g.drawLine((col * factor) + 1, row * factor, ((col + 1) * factor) - 1, row * factor);
                }

                if (maze[row][col].hasWallS()) { // - bottom
                    g.drawLine((col * factor) + 1, (row + 1) * factor, ((col + 1) * factor) - 1, ((row + 1) * factor));

                }

                if (maze[row][col].hasWallE()) { // - right
                    g.drawLine((col + 1) * factor, (row * factor) + 1, (col + 1) * factor, ((row + 1) * factor) - 1);
                }

                if (maze[row][col].hasWallW()) { // - left
                    g.drawLine(col * factor, (row * factor) + 1, col * factor, ((row + 1) * factor) - 1);
                }

                if (maze[row][col].isNotMarked()) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(col * factor + 1, row * factor + 1, factor - 1, factor - 1);
                }
            }

            // 1: (  xf + 1, yf)    -> ( (x+1)f - 1, yf ) - top
            // 2: (  xf, yf + 1)    -> ( (x+1)f, (y+1)f - 1 ) - left
            // 3: (  xf + 1,(y+1)f) -> ( (x+1)f - 1, (y+1)f ) - bottom
            // 4: ( (x+1)f, yf + 1) -> ( (x+1)f, (y+1)f - 1 ) - right
        }
    }

    public void generateMaze() {
        GenMaze mazeA = new GenMaze(size);

        mazeA.startGenerateMaze();
        Timer timer = new Timer(1, (ActionListener) actionEvent -> {
            if (!mazeA.isComplete()) {
                mazeA.generateMazeStep();
                maze = mazeA.getMazeArray();

                revalidate();
                repaint();
            }
        });
        timer.start();
    }
}
