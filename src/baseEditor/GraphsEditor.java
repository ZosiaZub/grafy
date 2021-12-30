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
import java.awt.event.*;

public class GraphsEditor extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private static final String INTRODUCING =
            "Author:     Zofia Zub\n" +
                    "Indeks:    259114\n" +
                    "Data:      15.12.2021r.\n" +
                    "Title:     Program do edytowania grafow";

    private static final String INSTRUCTION =
            "PROGRAM INSTRUCTION:\n" +
                    "- RMB --> right mouse button\n" +
                    "- LMB --> left mouse button\n" +
                    "\n" +
                    "- if you want to add a new node follow those steps:\n" +
                    "   > click RMB in the place, where you want to create a new node\n" +
                    "   > click the 'New node' button\n" +
                    "   > click the 'Remove node' button, if you want to remove the new node\n" +
                    "   > in order to change the color, click the 'Change the color' button and choose whatever color you like\n" +
                    "   > drag the node with the LMB to move it\n" +
                    "\n" +
                    "- if you want to add a new edge follow those steps:\n" +
                    "   > click LMB in the place, where you want the edge to start\n" +
                    "   > click the 'New edge' button\n" +
                    "   > click in the place, where you want the edge to end\n" +
                    "   > click the 'Remove edge' button, if you want to remove the new edge\n" +
                    "\n";


    public static void main(String[] args) {
        new GraphsEditor();
    }

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuGraph = new JMenu("Graph");
    private JMenu menuHelp = new JMenu("Help");
    private JMenu menuListOf = new JMenu("List of ...");
    private JMenuItem newItem = new JMenuItem("New");
    private JMenuItem exitItem = new JMenuItem("EXIT");
    private JMenuItem nodesListItem = new JMenuItem("List of nodes");
    private JMenuItem instructionItem = new JMenuItem("Instruction");
    private JMenuItem introductionItem = new JMenuItem("Introduction");

    private GraphPanel panel = new GraphPanel();

    public GraphsEditor() {
        super(INTRODUCING);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setContentPane(panel);
        createMenu();
        setVisible(true);
    }


    private void showListOfNodes(Graph graph) {
        Node array[] = graph.getNodesList().toArray(new Node[0]);
        StringBuilder amountOfNodes = new StringBuilder("Ilosc wezlow: " + array.length + "\n");
        for (Node node : array) {
            amountOfNodes.append(node + " ");
        }
        JOptionPane.showMessageDialog(this, amountOfNodes);
    }

    private void createMenu() {
        newItem.addActionListener(this);
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
