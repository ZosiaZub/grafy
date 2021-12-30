package base;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Graph implements Serializable {
    private static final long serialVersionUID = 1L;


    public List<Node> nodesList;
    public List<Node> getNodesList() {return nodesList;}

    public List<Edge> edgesList;
    public List<Edge> getEdgesList() {return edgesList;}


    public Graph() {
        this.nodesList = new ArrayList<Node>();
        this.edgesList = new ArrayList<Edge>();
    }

    public void addNode(Node node){nodesList.add(node);}
    public void removeNode(Node node){nodesList.remove(node);}

    public void addEdge(Edge edge){edgesList.add(edge);}
    public void removeEdge(Edge edge){edgesList.remove(edge);}


    public void moveAllNodes(int x, int y) {
        for (Node node : getNodesList()) {
            Node.moveNode(x, y, node);
        }
    }

    public void moveAllEdges(int x, int y) {
        for (Edge edge : getEdgesList()) {
            Edge.moveEdge(x, y, edge);
        }
    }

    public void drawGraph (Graphics nodes, Graphics edges){
        for (Node node : nodesList) node.drawNodes(nodes);
        for (Edge edge : edgesList) edge.drawEdges(edges);
    }
    public void draw(Graphics g){
        for(Node node : nodesList) node.drawNodes(g);
        for(Edge edge : edgesList) edge.drawEdges(g);
    }
}
