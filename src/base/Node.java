package base;

/*
*   Klasa Node tworzy na plaszcyznie obiety Node (wezly).
*
*   autor:  Zofia Zub
*   indeks: 259114
*   data:   15.12.2021r.
*/

import java.awt.*;
import static java.lang.Math.pow;


public class Node {

    private int x;
    private int y;
    private int r;
    private Color color;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.r = 8;
        this.color = Color.black;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public boolean isCursorOverNode(int x_cursor, int y_cursor){
        return pow(x-x_cursor, 2)+pow(y-y_cursor, 2) <= r*r;
    }

    public void drawNodes(Graphics node){
        node.setColor(color);
        node.fillOval(x-r,y-r,2*r,2*r);
        node.setColor(color);
        node.drawOval(x-r,y-r,2*r,2*r);
    }

    public static void moveNode(int x, int y, Node node){
        node.setX(node.getX()+x);
        node.setY(node.getY()+y);
    }

    @Override
    public String toString(){
        return "(" + x + ";" + y + ";" + r + ")";
    }
}
