import java.util.*;
import java.io.*;

public class is_bst {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isBinarySearchTree() {
            // Implement correct algorithm here
            if (nodes == 0) return true;
            List<Integer> smallerIndex = new ArrayList<Integer>();
            List<Integer> greaterIndex = new ArrayList<Integer>();
            int i = 0;
            Node node = tree[i];
            isOkNode(node, smallerIndex, greaterIndex);
        }

        boolean isOkNode(Node node, List<Integer> smallerIndex, List<Integer> greaterIndex) {
            List<Integer> newSmallerIndex = new ArrayList<Integer>();
            List<Integer> newGreaterIndex = new ArrayList<Integer>();
            newSmallerIndex.addAll(smallerIndex);
            newGreaterIndex.addAll(greaterIndex);
            int left = node.left;
            if (left != -1) {
                newGreaterIndex.add(node.key);
                if(!isOk(left, smallerIndex, newGreaterIndex)) return false;
            }
            int right = node.right;
            if (right != -1) {
                newSmallerIndex.add(node.key);
                if (!isOk(right, newSmallerIndex, greaterIndex)) return false;
            }
            return true;
        }

        boolean isOk(int index, List<Integer> smallerIndex, List<Integer> greaterIndex) {
            int key = tree[index].key;
            for (int j: smallerIndex) {
                if (j >= key) return false;
            }
            for (int j: greaterIndex) {
                if (j <= key) return false;
            }
            return true;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}