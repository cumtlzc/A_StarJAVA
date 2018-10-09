package aStar;

public class Node {
	public Node(int x, int y,int symble) {
        this.x = x;
        this.y = y;
        this.symble=symble;
    }
	public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int x;
    public int y;
    public int symble;

    public int F;
    public int G;
    public int H;

    public void calcF() {
        this.F = this.G + this.H;
    }


    public Node parent;

}
