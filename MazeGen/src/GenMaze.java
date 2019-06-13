import java.util.Stack;

public class GenMaze {

    private Path[][] maze;
    private int size;

    GenMaze(int size){

        this.size = size;
        this.maze = makeArray();
    }

    public Path[][] getMazeArray(){
        return this.maze;
    }

    public void generateMaze(){

        Stack<Path> stack = new Stack<>();

        Path start = new Path(1,1);
        Path current, top, bottom, left, right;

        int x, y;
        double random;

        stack.push(start);

        while(!(stack.isEmpty())){

            random = Math.random();

            current = stack.peek();
            current.setMarked();

            x = current.getX();
            y = current.getY();

            top    = this.maze[x - 1][y];
            bottom = this.maze[x + 1][y];
            left   = this.maze[x][y - 1];
            right  = this.maze[x][y + 1];

            if(random < 0.25 && top.isNotBorder() && top.isNotMarked()){
                stack.push(top);
                top.setMarked();
                current.removeWallN(top);

            }else if(random < 0.50 && bottom.isNotBorder()&& bottom.isNotMarked()){
                stack.push(bottom);
                bottom.setMarked();
                current.removeWallS(bottom);

            }else if(random < 0.75 && left.isNotBorder()&& left.isNotMarked()){
                stack.push(left);
                left.setMarked();
                current.removeWallW(left);

            }else if(right.isNotBorder()&& right.isNotMarked()){
                stack.push(right);
                right.setMarked();
                current.removeWallE(right);

            }else{
                stack.pop();
            }


            //info(current, top, bottom, left, right);

        }
        //getInfo(maze);
    }

    public Path[][] makeArray(){

        Path[][] temp = new Path[this.size + 2][this.size + 2];

        Path path;

        for(int row = 0; row < temp.length; row++){
            for(int col = 0; col < temp[row].length; col ++){
                path = new Path();
                temp[row][col] = path;
            }
        }

        for(int row  = 1; row < temp.length - 1; row++){
            for(int col = 1; col < temp[0].length - 1; col ++){
                path = new Path(row, col);
                temp[row][col] = path;
            }
        }

        return temp;
    }

    public void getInfo(Path[][] temp){
        for(int row  = 1; row < temp.length - 1; row++) {
            for (int col = 1; col < temp[0].length - 1; col++) {

                Path x = temp[row][col];
                if(x.hasWallW() && x.hasWallS() && x.hasWallE()&& x.hasWallN()){
                    System.out.println("(" + row + "," + col + ")");
                }

            }

        }
    }

    public void info(Path center, Path top, Path bottom, Path left, Path right){
        System.out.println("(1,1): " + center.hasWallN() + center.hasWallE() + center.hasWallS() + center.hasWallW());
        System.out.println("(" + top.getX() + "," + top.getY() + ")" + top.hasWallN() + top.hasWallE() + top.hasWallS() + top.hasWallW());
        System.out.println("(" + bottom.getX() + "," + bottom.getY() + ")" + bottom.hasWallN() + bottom.hasWallE() + bottom.hasWallS() + bottom.hasWallW());
        System.out.println("(" + left.getX() + "," + left.getY() + ")" + left.hasWallN() + left.hasWallE() + left.hasWallS() + left.hasWallW());
        System.out.println("(" + right.getX() + "," + right.getY() + ")" + right.hasWallN() + right.hasWallE() + right.hasWallS() + right.hasWallW());
        System.out.println();

    }
}


