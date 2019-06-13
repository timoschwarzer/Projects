import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GenMaze {

    enum Direction {
        TOP,
        LEFT,
        RIGHT,
        BOTTOM
    }

    private Path[][] maze;
    private int size;
    private Stack<Path> stack = new Stack<>();
    private int markedCount = 0;

    GenMaze(int size) {

        this.size = size;
        this.maze = makeArray();
    }

    public Path[][] getMazeArray() {
        return this.maze;
    }

    void generateMaze() {
        startGenerateMaze();

        while (!(stack.isEmpty())) {
            generateMazeStep();
        }
        //getInfo(maze);
    }

    public void startGenerateMaze() {
        Path start = new Path(1, 1);
        maze[1][1] = start;
        stack.push(start);
    }

    public boolean isComplete() {
        return stack.isEmpty(); // markedCount == size * size;
    }

    public void generateMazeStep() {
        System.out.println(stack.size());

        Path current = stack.peek();
        current.setMarked();

        System.out.println("col: " + current.getCol());
        System.out.println("row: " + current.getRow());

        int row = current.getRow();
        int col = current.getCol();

        Path top = this.maze[row - 1][col];
        Path bottom = this.maze[row + 1][col];
        Path left = this.maze[row][col - 1];
        Path right = this.maze[row][col + 1];

        boolean canGoTop = top.isNotBorder() && top.isNotMarked();
        boolean canGoLeft = left.isNotBorder() && left.isNotMarked();
        boolean canGoRight = right.isNotBorder() && right.isNotMarked();
        boolean canGoBottom = bottom.isNotBorder() && bottom.isNotMarked();

        List<Direction> possibleDirections = new ArrayList<>();

        if (canGoTop) {
            possibleDirections.add(Direction.TOP);
        }
        if (canGoLeft) {
            possibleDirections.add(Direction.LEFT);
        }
        if (canGoRight) {
            possibleDirections.add(Direction.RIGHT);
        }
        if (canGoBottom) {
            possibleDirections.add(Direction.BOTTOM);
        }

        if (possibleDirections.isEmpty()) {
            stack.pop();
            return;
        }

        double random = Math.random() * possibleDirections.size();
        Direction direction = possibleDirections.get((int) Math.floor(random));

        switch (direction) {
            case TOP:
                stack.push(top);
                top.setMarked();
                current.removeWallN(top);
                markedCount++;
                break;
            case LEFT:
                stack.push(left);
                left.setMarked();
                current.removeWallW(left);
                markedCount++;
                break;
            case RIGHT:
                stack.push(right);
                right.setMarked();
                current.removeWallE(right);
                markedCount++;
                break;
            case BOTTOM:
                stack.push(bottom);
                bottom.setMarked();
                current.removeWallS(bottom);
                markedCount++;
                break;
        }
    }

    private Path[][] makeArray() {

        Path[][] temp = new Path[this.size + 2][this.size + 2];

        Path path;

        for (int row = 0; row < temp.length; row++) {
            for (int col = 0; col < temp[row].length; col++) {
                path = new Path();
                temp[row][col] = path;
            }
        }

        for (int row = 1; row < temp.length - 1; row++) {
            for (int col = 1; col < temp[0].length - 1; col++) {
                path = new Path(row, col);
                temp[row][col] = path;
            }
        }

        return temp;
    }

    public void getInfo(Path[][] temp) {
        for (int row = 1; row < temp.length - 1; row++) {
            for (int col = 1; col < temp[0].length - 1; col++) {

                Path x = temp[row][col];
                if (x.hasWallW() && x.hasWallS() && x.hasWallE() && x.hasWallN()) {
                    System.out.println("(" + row + "," + col + ")");
                }

            }

        }
    }

    public void info(Path center, Path top, Path bottom, Path left, Path right) {
        System.out.println("(1,1): " + center.hasWallN() + center.hasWallE() + center.hasWallS() + center.hasWallW());
        System.out.println("(" + top.getRow() + "," + top.getCol() + ")" + top.hasWallN() + top.hasWallE() + top.hasWallS() + top.hasWallW());
        System.out.println("(" + bottom.getRow() + "," + bottom.getCol() + ")" + bottom.hasWallN() + bottom.hasWallE() + bottom.hasWallS() + bottom.hasWallW());
        System.out.println("(" + left.getRow() + "," + left.getCol() + ")" + left.hasWallN() + left.hasWallE() + left.hasWallS() + left.hasWallW());
        System.out.println("(" + right.getRow() + "," + right.getCol() + ")" + right.hasWallN() + right.hasWallE() + right.hasWallS() + right.hasWallW());
        System.out.println();

    }
}


