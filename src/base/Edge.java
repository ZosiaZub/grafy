package base;
/*
 *   Klasa Edge tworzy na plaszcyznie obiety Edge (krawedzie).
 *
 *   autor:  Zofia Zub
 *   indeks: 259114
 *   data:   15.12.2021r.
 */

import java.awt.*;

public class Edge {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;

    public Edge(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = Color.black;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void drawEdges(Graphics edge){
        edge.setColor(color);
        edge.drawLine(getX1(),getY1(), getX2(),getY2());
    }

    public static void moveEdge(int x, int y, Edge edge){
        edge.setX1(edge.getX1()+x);
        edge.setY1(edge.getY1()+y);
        edge.setX2(edge.getX2()+x);
        edge.setY2(edge.getY2()+y);
    }

    public float aRownanieProstej (int x1, int y1, int x2, int y2){
        return (y2-y1)/(x2-x1);
    }

    public float bRownanieProstej (int x1, int y1, int x2, int y2){
        return y1-aRownanieProstej(x1,y1, x2, y2)*x1;
    }

    public int minXY(int xy1, int xy2){
        if (xy1< xy2) return xy1;
        else return xy2;
    }
    public int maxXY(int xy1, int xy2){
        if (xy1< xy2) return xy2;
        else return xy1;
    }

    public boolean isCursorOverEdge(int x_pointer, int y_pointer){
        boolean answer = false;
        if (minXY(x1, x2) < x_pointer && x_pointer < maxXY(x1, x2) && minXY(y1, y2) < y_pointer && y_pointer< maxXY(y1, y2)) {
            if (y_pointer == aRownanieProstej(x1, y1, x2, y2) * x_pointer + bRownanieProstej(x1, y1, x2, y2)) {
                answer = true;
            } else answer = false;
        }
        return answer;
    }
}
