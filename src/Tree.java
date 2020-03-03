import java.util.*;

public class Tree{
	private Node root;
	private LinkedHashMap alphabet;
	private ArrayList<Node> forests = new ArrayList<Node>();

	public Tree(LinkedHashMap alphabet){
		this.alphabet = alphabet;
	}

	public Tree(Node root, LinkedHashMap alphabet, ArrayList<Node> forests){
		this.root = root;
		this.alphabet = alphabet;
		this.forests = forests;
	}

	public void initial_tree(){
		ArrayList<Integer> keys = new ArrayList<Integer>(this.alphabet.keySet());

		for (int key : keys){
			Node n = new Node(key, (int)(this.alphabet.get(key)));
			this.forests.add(n);
		}
	}

	public void general_tree(){
		if (this.forests.size() == 0){
			this.initial_tree();
		}

		while (this.forests.size() > 1){
			Node t1_forest = this.forests.get(0);//min
			Node t2_forest = this.forests.get(1);//max

			for (Node forest : this.forests){
				if (forest.get_freq() < t1_forest.get_freq()){
					t1_forest = forest;
				}
			};
			this.forests.remove(t1_forest);

			for (Node forest : this.forests){
				if (forest.get_freq() > t2_forest.get_freq() && t1_forest.get_freq() <= forest.get_freq()){
					t2_forest = forest;
				}
			};
			this.forests.remove(t2_forest);

			int freq = t1_forest.get_freq() + t2_forest.get_freq();
			Node t_node = new Node(freq, t1_forest, t2_forest);

			t1_forest.set_parent(t_node);
			t2_forest.set_parent(t_node);

			this.forests.add(t_node);

			System.out.println(t_node.get_left_child());

		}

	}
}

