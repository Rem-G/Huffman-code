import java.util.*;

public class Tree{
	private LinkedHashMap alphabet;
	private ArrayList<Node> forests = new ArrayList<Node>();

	public Tree(LinkedHashMap alphabet){
		this.alphabet = alphabet;
	}

	public void initial_tree(){
		ArrayList<Character> keys = new ArrayList<Character>(this.alphabet.keySet());

		System.out.println(this.alphabet);

		for (char key : keys){
			Node n = new Node(key, (int)(this.alphabet.get(key)));
			this.forests.add(n);
		}
	}

	public Node general_tree(){
		this.initial_tree();

		while (this.forests.size() > 1){
			Node t1_forest = this.forests.get(0);//min
			Node t2_forest = this.forests.get(0);//max

			for (Node forest : this.forests){
				if (forest.get_freq() < t1_forest.get_freq()){
					t1_forest = forest;
				}
			};

			for (Node forest : this.forests){
				if (forest.get_freq() > t2_forest.get_freq() && t1_forest.get_freq() <= forest.get_freq()){
					t2_forest = forest;
				}
			};

			int freq = t1_forest.get_freq() + t2_forest.get_freq();
			Node t_node = new Node(freq, t1_forest, t2_forest);

			System.out.println(t1_forest.get_freq() + " " + t2_forest.get_freq() + " " + freq);

			t1_forest.set_parent(t_node);
			t2_forest.set_parent(t_node);

			this.forests.add(t_node);

			this.forests.remove(t1_forest);
			this.forests.remove(t2_forest);
		}

		System.out.println(this.forests.size());
		return this.forests.get(0);
	}
}

