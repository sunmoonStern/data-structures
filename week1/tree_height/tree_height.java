import java.util.*;
import java.io.*;

public class tree_height {

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

	class Node {
		int index;
		List<Integer> children;

		Node(int index) {
			this.index = index;
			this.children = new ArrayList<Integer>();
		}

		public void addChildren(int node) {
			this.children.add(node);
		}

		public List<Integer> getChildren() {
			return this.children;
		}
	}

	public class TreeHeight {
		int n;
		int parent[];
		int root;
		Node[] nodes;

		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
                        // Replace this code with a faster implementation
			int maxHeight = 0;
			for (int vertex = 0; vertex < n; vertex++) {
				int height = 0;
				for (int i = vertex; i != -1; i = parent[i])
					height++;
				maxHeight = Math.max(maxHeight, height);
			}
			return maxHeight;
		}

		int computeHight2() {
			nodes = new Node[n];
			for (int i = 0; i < n; i++) {
				if (parent[i] == -1) {
					root = i;
					break;
				}
			}
			for (int i = 0; i < n; i++) {
				nodes[i] = new Node(i);
			}
			for (int i = 0; i < n; i++) {
				if (parent[i] != -1) {
					 nodes[parent[i]].addChildren(i);
				}
			}
			return getHeight(nodes[root]);
		}

		int getHeight(Node node) {
			if (node.children.size() == 0)
				return 1;
			int height = -1;
			for (int c: node.getChildren()) {
				int currentHeight = getHeight(nodes[c]);
				if (currentHeight > height) height = currentHeight;
			}
			return 1 + height;
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHight2()); // System.out.println(tree.computeHeight());
	}
}
