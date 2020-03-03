import java.util.*;

public class Tree{
	private Node root;
	private LinkedHashMap alphabet;
	private ArrayList<Node> nodes = new ArrayList<Node>();

	public Tree(LinkedHashMap alphabet){
		this.alphabet = alphabet;
	}

	public Tree(Node root, LinkedHashMap alphabet, ArrayList<Node> nodes){
		this.root = root;
		this.alphabet = alphabet;
		this.nodes = nodes;
	}

	public void initial_tree(){
		ArrayList<Integer> keys = new ArrayList<Integer>(this.alphabet.keySet());

		for (int key : keys){
			Node n = new Node(key, (int)(this.alphabet.get(key)));
			this.nodes.add(n);
		}
	}

	public Node t_node(Node t1, Node t2){
		int avg = (int)((t1.get_freq()+t2.get_freq())/2);

		int distance = Math.abs(t1.get_freq() - avg);
		Node avg_node = this.nodes.get(0);

		for (Node node : this.nodes){
			int current_distance = Math.abs(node.get_freq() - avg);

			if (current_distance < distance){
				distance = current_distance;
				avg_node = node;
			}
		}
		return avg_node;
	}

	public void general_tree(){
		if (this.nodes.size() == 0){
			this.initial_tree();
		}

		Node t1_node = this.nodes.get(0);//min
		Node t2_node = this.nodes.get(0);//max

		for (Node node : this.nodes){
			if (node.get_freq() > t2_node.get_freq()){
				t2_node = node;
			};

			if (node.get_freq() < t1_node.get_freq()){
				t1_node = node;
			}
		}

		Node t_node = this.t_node(t1_node, t2_node);

		System.out.println(t1_node.get_freq());
		System.out.println(t_node.get_freq());
		System.out.println(t2_node.get_freq());
	}
}

