package baseEditor;

/*
 *   Klasa GraphPanel implementuje podstawowe działania edytora grafu.
 *
 *   autor:  Zofia Zub
 *   indeks: 259114
 *   data:   16.12.2021r.
 */


import base.Edge;
import base.Graph;
import base.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GraphPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    private static final long serialVersionUID = 1L;

    private Graph graph = new Graph();

    private int mouseX = 0;
    private int mouseY = 0;
    private boolean leftMouseButton = false;
    private boolean rightMouseButton = false;
    protected int mouseCursor = Cursor.DEFAULT_CURSOR;

    private Node nodeUnderCursor = null;
    private Edge edgeUnderCursor = null;

    private int mousePreviousCordX = 0;
    private int mousePreviousCordY = 0;

    public GraphPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        setFocusable(true);
        requestFocus();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e);
                if (e.getButton() == 3) {
                    showPopUpMenuNode(e);
                } else if (e.getButton() == 1) {
                    showPopUpMenuEdge(e);
                    if (mousePreviousCordX != 0 && mousePreviousCordY != 0) {
                        Edge edge = new Edge(mousePreviousCordX, mousePreviousCordY, e.getX(), e.getY());
                        mousePreviousCordX = 0;
                        mousePreviousCordY = 0;
                        graph.addEdge(edge);
                        showPopUpMenuEdge(e, edge);
                        repaint();
                    }
                }
            }
        });
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }


    private Node isItNode(int x_cursor, int y_cursor) {
        for (Node node : graph.getNodesList()) {
            if (node.isCursorOverNode(x_cursor, y_cursor))
                return node;
        }
        return null;
    }

    private Node isItNodeEvent(java.awt.event.MouseEvent event) {
        return isItNode(event.getX(), event.getY());
    }


    private Edge isItEdge(int x_cursor, int y_cursor) {
        if (edgeUnderCursor.isCursorOverEdge(x_cursor, y_cursor) == true) {
            return edgeUnderCursor;
        }
        return null;
    }

    private Edge isItEdgeEvent(java.awt.event.MouseEvent event) {
        return isItEdge(event.getX(), event.getY());
    }


    private void setCursorEvent(java.awt.event.MouseEvent event) {

        if (isItNode(event.getX(), event.getY()) != null) {
            nodeUnderCursor = isItNodeEvent(event);

            if (nodeUnderCursor != null) {
                mouseCursor = Cursor.HAND_CURSOR;
            } else if (leftMouseButton) {
                mouseCursor = Cursor.MOVE_CURSOR;
            } else {
                mouseCursor = Cursor.DEFAULT_CURSOR;
            }

        } else if (isItEdge(event.getX(), event.getY()) != null) {
            edgeUnderCursor = isItEdgeEvent(event);

            if (edgeUnderCursor != null) {
                mouseCursor = Cursor.HAND_CURSOR;
            } else if (leftMouseButton) {
                mouseCursor = Cursor.MOVE_CURSOR;
            } else {
                mouseCursor = Cursor.DEFAULT_CURSOR;
            }

        }

        setCursor(Cursor.getPredefinedCursor(mouseCursor));
        mouseX = event.getX();
        mouseY = event.getY();
    }

    private void setCursorObject() {
        if (isItNode(mouseX, mouseY) != null) {
            nodeUnderCursor = isItNode(mouseX, mouseY);

            if (nodeUnderCursor != null) {
                mouseCursor = Cursor.HAND_CURSOR;
            } else if (leftMouseButton) {
                mouseCursor = Cursor.MOVE_CURSOR;
            } else {
                mouseCursor = Cursor.DEFAULT_CURSOR;
            }
            setCursor(Cursor.getPredefinedCursor(mouseCursor));

        } else if (isItEdge(mouseX, mouseY) != null) {
            edgeUnderCursor = isItEdge(mouseX, mouseY);

            if (nodeUnderCursor != null) {
                mouseCursor = Cursor.HAND_CURSOR;
            } else if (leftMouseButton) {
                mouseCursor = Cursor.MOVE_CURSOR;
            } else {
                mouseCursor = Cursor.DEFAULT_CURSOR;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (graph == null) return;
        graph.draw(g);

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int step = 1;
        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                graph.moveAllNodes(-step, 0);
                break;

            case KeyEvent.VK_RIGHT:
                graph.moveAllNodes(step, 0);
                break;

            case KeyEvent.VK_DOWN:
                graph.moveAllNodes(0, -step);
                break;

            case KeyEvent.VK_UP:
                graph.moveAllNodes(0, step);
                break;

            case KeyEvent.VK_DELETE:
                if (nodeUnderCursor != null) {
                    graph.removeNode(nodeUnderCursor);
                    nodeUnderCursor = null;
                } else {
                    if (edgeUnderCursor != null) {
                        graph.removeEdge(edgeUnderCursor);
                        edgeUnderCursor = null;
                    }
                }
                break;
        }
        repaint();
        setCursorObject();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        if (e.getButton() == 1) leftMouseButton = true;
        if (e.getButton() == 3) rightMouseButton = true;
        setCursorEvent(e);
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        if (leftMouseButton) {
            if (nodeUnderCursor != null) {
                nodeUnderCursor.moveNode(e.getX() - mouseX, e.getY() - mouseY, nodeUnderCursor);
            } else {
                graph.moveAllNodes(e.getX() - mouseX, e.getY() - mouseY);
                graph.moveAllEdges(e.getX() - mouseX, e.getY() - mouseY);
            }
        }
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        setCursorEvent(e);
    }


    public void showPopUpMenuNode(java.awt.event.MouseEvent event) {
        JMenuItem menuItem;

        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("New node");

        menuItem.addActionListener((action) -> {
            Node node = new Node(event.getX(), event.getY());
            graph.addNode(node);
            this.showPopUpMenuNode(event, node);
            repaint();
        });

        popup.add(menuItem);
        popup.show(event.getComponent(), event.getX(), event.getY());
    }

    public void showPopUpMenuNode(java.awt.event.MouseEvent event, Node node) {
        JMenuItem menuItem;

        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("Change the color");

        menuItem.addActionListener((a) -> {
            Color newColor = JColorChooser.showDialog(
                    this,
                    "Choose Background Color",
                    node.getColor());
            if (newColor != null) {
                node.setColor(newColor);
            }
            repaint();
        });

        popup.add(menuItem);
        menuItem = new JMenuItem("Remove this node");

        menuItem.addActionListener((action) -> {
            graph.removeNode(node);
            repaint();
        });


        popup.add(menuItem);
        popup.show(event.getComponent(), event.getX(), event.getY());
    }

    public void showPopUpMenuEdge(java.awt.event.MouseEvent event) {
        JMenuItem menuItem;

        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("New edge");

        menuItem.addActionListener((action) -> {
            mousePreviousCordX = event.getX();
            mousePreviousCordY = event.getY();
            repaint();
        });

        popup.add(menuItem);
        popup.show(event.getComponent(), event.getX(), event.getY());
    }

    public void showPopUpMenuEdge(java.awt.event.MouseEvent event, Edge edge) {
        JMenuItem menuItem = new JMenuItem("Remove this edge");

        JPopupMenu popup = new JPopupMenu();
        popup.add(menuItem);

        menuItem.addActionListener((action) -> {
            graph.removeEdge(edge);
            repaint();
        });

        popup.add(menuItem);
        popup.show(event.getComponent(), event.getX(), event.getY());
    }
}
