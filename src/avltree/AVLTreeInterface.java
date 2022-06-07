package avltree;

import core.InputReader;
import javax.swing.JFrame;

public class AVLTreeInterface {

    private AVLTree tree;
    private AVLTreeRenderer treeViewer;
    
    public AVLTreeInterface() {
        tree = new AVLTree();
        treeViewer = new AVLTreeRenderer(tree.getRoot());
        JFrame frame = treeViewer;
        frame.setSize(AVLTreeRenderer.CANVAS_WIDTH, AVLTreeRenderer.CANVAS_HEIGHT);
        treeViewer.setVisible(true);
    }
    
    public void accept() {
      
        boolean running = true;
        while (running) {
            String line = InputReader.readLineWithMessage("\nComando >> ");
            
            if (line.toUpperCase().equals("X")) {
                treeViewer.dispose();
                break;
            } else if (line.toUpperCase().equals("E")) {
                System.out.print("Pre-ordem: ");
                tree.printPreOrder();
                System.out.print("\nPos-ordem: ");
                tree.printPostOrder();
                System.out.print("\nEm ordem: ");
                tree.printInOrder();
                System.out.print("\nPor nível: ");
                tree.printLevelOrder();
                continue;
            }
            String[] args = line.split(" ");
            if (args.length != 2) {
                System.out.println("operação inválida!");
                continue;
            }
            int key;
            try {
                key = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("operação inválida!");
                continue;
            }
            String op = args[0].toUpperCase();
            switch(op) {
                case "I": 
                    tree.insert(key);
                    treeViewer.update(tree.getRoot());
                    break;
                case "R": 
                    tree.remove(key);
                    treeViewer.update(tree.getRoot());
                    break;
                case "B":
                    if (tree.search(key) != null) {
                        System.out.println("Chave encontrada!");
                    } else {
                        System.out.println("Chave não encontrada!");
                    }
                    break;
                default:
                    System.out.println("Comando não existe");
                    break;
            }
        }
    }
    
}
