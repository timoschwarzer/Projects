import javax.swing.*;
import java.awt.*;
import javax.swing.*;

public class Maze extends JFrame{

    static Path[][] maze;

    Maze(){
        setTitle("Maze");
        setSize(1000,1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g){
        super.paint(g);
        g.translate(50,60);

        int factor, size = 100;

        GenMaze mazeA = new GenMaze(size);
        mazeA.generateMaze();
        maze = mazeA.getMazeArray();

        factor = 800 / size;

        if(factor < 4){
            factor = 4;
        }

        Color color = Color.BLACK;


        for(int row = 1; row < maze.length - 1; row++ ) {
            for (int col = 1; col < maze[row].length - 1; col++) {

                g.setColor(color);

                if(maze[row][col].hasWallN()){ // - top
                    g.drawLine((col * factor) + 1 , row * factor, ((col + 1) * factor) - 1, row * factor);
                }

                if(maze[row][col].hasWallS()){ // - bottom
                    g.drawLine((col * factor) + 1 , (row + 1) * factor, ((col + 1) * factor) - 1, ((row + 1)* factor));

                }

                if(maze[row][col].hasWallE()){ // - right
                    g.drawLine( (col + 1) * factor, (row * factor) + 1, (col + 1) * factor, ((row + 1)* factor) - 1);
                }

                if(maze[row][col].hasWallW()){ // - left
                    g.drawLine(col * factor, (row * factor) + 1, col * factor, ((row + 1)* factor) - 1);
                }

            }

            // 1: (  xf + 1, yf)    -> ( (x+1)f - 1, yf ) - top
            // 2: (  xf, yf + 1)    -> ( (x+1)f, (y+1)f - 1 ) - left
            // 3: (  xf + 1,(y+1)f) -> ( (x+1)f - 1, (y+1)f ) - bottom
            // 4: ( (x+1)f, yf + 1) -> ( (x+1)f, (y+1)f - 1 ) - right
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Maze maze = new Maze();
                maze.setVisible(true);
            }
        });
    }

    public static int printArr(Path[][]maze){
        int count = 0;
        for(int i = 1; i < maze.length - 1; i++){
            for(int j = 1; j < maze[i].length - 1; j++){

                if(maze[i][j].hasWallN() && maze[i][j].hasWallS() && maze[i][j].hasWallE() && maze[i][j].hasWallW()){
                    count++;
                    System.out.println("(" + maze[i][j].getX() + "," + maze[i][j].getY() + ")");
                }


            }
        }
        return count;


    }
}
