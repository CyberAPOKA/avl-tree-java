package avltree;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

@SuppressWarnings("serial")

public class AVLTreeRenderer extends JFrame {

    static public int CANVAS_HEIGHT = 500;
    static public int CANVAS_WIDTH = 500;

    private int rootY = 10;
    private int NODE_SIZE = 25;
    private int ROW_HEIGHT = 50;
    mxGraph graph = new mxGraph();
    Object parent = graph.getDefaultParent();
    
    public Object drawTree(Node root, int depth, int index) {
        if (root == null) return null;
        
        int myX = (int) ((CANVAS_WIDTH * (index)) / (Math.pow(2, depth - 1) + 1));

        Object rootVertex = graph.insertVertex(parent, null, root.getKey(),
                myX, depth * ROW_HEIGHT + rootY, NODE_SIZE, NODE_SIZE);

        Object rightChildVertex = drawTree(root.getRight(), depth + 1, index * 2);
        if (rightChildVertex != null) {
            graph.insertEdge(parent, null, null, rootVertex, rightChildVertex);
        }
        
        Object leftChildVertex = drawTree(root.getLeft(), depth + 1, index * 2 - 1);
        if (leftChildVertex != null) {
            graph.insertEdge(parent, null, null, rootVertex, leftChildVertex);
        }

        return rootVertex;
    }

    public void update(Node root) {
        graph.getModel().beginUpdate();
        try {

            Object[] cells = graph.getChildCells(parent, true, false);
            graph.removeCells(cells, true);
            drawTree(root, 1, 1);

        } finally {
            graph.getModel().endUpdate();
        }
    }

    public AVLTreeRenderer(Node root) {
        this.update(root);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

}
