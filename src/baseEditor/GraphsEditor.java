package baseEditor;

/*
 *   Klasa GraphsEditor implementuje główne okno programu
 *   oraz wykonuje działania na grafie.
 *
 *   autor:  Zofia Zub
 *   indeks: 259114
 *   data:   16.12.2021r.
 */


import base.Graph;
import base.Node;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GraphsEditor extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private static final String INTRODUCING =
            "Autor:     Zofia Zub\n"+
            "Indeks:    259114\n"+
            "Data:      15.12.2021r.\n" +
            "Tytul:     Program do edytowania grafow";

    private static final String INSTRUCTION =
            "PROGRAM INSTRUCTION:\n"+
            "RMB -> right mouse button\n"+
            "LMB -> left mouse button\n"+
            "- drag to move all nodes\n"+
            "- use arrow keys to move your graph\n"+
            "- when the mouse cursor is on the node:\n"+
            "   --> press + or - to increase or decrease the size of the node\n"+
            "   --> press RMB to change the node's color or to remove the node\n"+
            "   --> press Y/ G/ R/ W to change the color (yellow, green, red, white) of the node \n"+
            "   --> press Delate to remove the node\n"+
            "   --> drag to move the node\n"+
            "- drag to move all nodes\n"+
            "- press the RMB to add a new node, where the mouse cursor is \n"+
            "";


    public static void main(String[] args) {
        new GraphsEditor();
    }

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuGraph = new JMenu("Graph");
    private JMenu menuHelp = new JMenu("Help");
    private JMenu menuListOf = new JMenu("List of ...");
    private JMenuItem newItem = new JMenuItem("New");
    private JMenuItem removeItem = new JMenuItem("Remove");
    private JMenuItem exitItem = new JMenuItem("EXIT");
    private JMenuItem nodesListItem = new JMenuItem("List of nodes");
    private JMenuItem instructionItem = new JMenuItem("Instruction");
    private JMenuItem introductionItem = new JMenuItem("Introduction");

    private GraphPanel panel = new GraphPanel();

    public GraphsEditor() {
        super(INTRODUCING);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        setContentPane(panel);
        createMenu();
        setVisible(true);
    }


    private void showListOfNodes(Graph graph){
        Node array[] = graph.getNodesList().toArray(new Node[0]);
        StringBuilder amountOfNodes = new StringBuilder("Ilosc wezlow: " + array.length + "\n");
        for (Node node : array){
            amountOfNodes.append(node + " ");
        }
        JOptionPane.showMessageDialog(this, amountOfNodes);
    }

    private void createMenu(){
        newItem.addActionListener(this);
        removeItem.addActionListener(this);
        exitItem.addActionListener(this);
        nodesListItem.addActionListener(this);
        instructionItem.addActionListener(this);
        introductionItem.addActionListener(this);

        menuBar.add(menuGraph);
        menuBar.add(menuListOf);
        menuBar.add(menuHelp);
        setJMenuBar(menuBar);

        menuGraph.setMnemonic(KeyEvent.VK_G);
        menuGraph.add(newItem);
        menuGraph.add(removeItem);
        menuGraph.add(exitItem);

        menuListOf.setMnemonic(KeyEvent.VK_G);
        menuListOf.add(nodesListItem);

        menuHelp.setMnemonic(KeyEvent.VK_G);
        menuHelp.add(instructionItem);
        menuHelp.add(introductionItem);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (newItem.equals(source)) {
            panel.setGraph(new Graph());

        }else if (removeItem.equals(source)){
            panel.setGraph(new Graph());

        } else if (nodesListItem.equals(source)) {
            showListOfNodes(panel.getGraph());

        } else if (instructionItem.equals(source)) {
            JOptionPane.showMessageDialog(this, INSTRUCTION);

        } else if (introductionItem.equals(source)) {
            JOptionPane.showMessageDialog(this, INTRODUCING);

        } else if (exitItem.equals(source)) {
            System.exit(0);
        }
    }
}
