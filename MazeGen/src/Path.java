public class Path {

    boolean WallN, WallS, WallW, WallE;
    boolean border = false;
    boolean marked = false;
    private int row = 0;
    private int col = 0;

    Path(int row, int col) {

        this.row = row;
        this.col = col;

        this.WallE = true;
        this.WallN = true;
        this.WallS = true;
        this.WallW = true;
    }

    Path() {
        this.border = true;
    }


    public boolean isNotBorder() {
        return !border;
    }

    public boolean isNotMarked() {
        return !marked;
    }

    public void setMarked() {
        marked = true;
    }

    public boolean hasWallN() {
        return WallN;
    }

    public boolean hasWallS() {
        return WallS;
    }

    public boolean hasWallE() {
        return WallE;
    }

    public boolean hasWallW() {
        return WallW;
    }


    public void removeWallN(Path other) {
        this.WallN = false;
        other.WallS = false;
    }

    public void removeWallS(Path other) {
        this.WallS = false;
        other.WallN = false;
    }

    public void removeWallW(Path other) {
        this.WallW = false;
        other.WallE = false;
    }

    public void removeWallE(Path other) {
        this.WallE = false;
        other.WallW = false;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
