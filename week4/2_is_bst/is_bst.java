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
            List<Integer> smallerKeys = new ArrayList<Integer>();
            List<Integer> greaterKeys = new ArrayList<Integer>();
            int i = 0;
            Node node = tree[i];
            return isOkNode(node, smallerKeys, greaterKeys);
        }

        boolean isOkNode(Node node, List<Integer> smallerKeys, List<Integer> greaterKeys) {
            boolean result1 = true;
            boolean result2 = true;
            int left = node.left;
            if (left != -1) {
                List<Integer> newGreaterKeys = new ArrayList<Integer>();
                newGreaterKeys.addAll(greaterKeys);
                newGreaterKeys.add(node.key);
                if(!isOk(tree[left], smallerKeys, newGreaterKeys)) return false;
                result1 = isOkNode(tree[left], smallerKeys, newGreaterKeys);
            }
            int right = node.right;
            if (right != -1) {
                List<Integer> newSmallerKeys = new ArrayList<Integer>();
                newSmallerKeys.addAll(smallerKeys);
                newSmallerKeys.add(node.key);
                if (!isOk(tree[right], newSmallerKeys, greaterKeys)) return false;
                result2 = isOkNode(tree[right], newSmallerKeys, greaterKeys);
            }
            return result1 && result2;
        }

        boolean isOk(Node node, List<Integer> smallerIndex, List<Integer> greaterIndex) {
            int key = node.key;
//            System.out.println("Checking for index = " + index + " key = " + key);
//            System.out.println("smaller index  = " + smallerIndex);
//            System.out.println("greater index = " + greaterIndex);
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