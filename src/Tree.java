import java.util.*;

public class Tree{
	private Map alphabet;
	private ArrayList<Node> forests = new ArrayList<Node>();

	public Tree(Map alphabet){
		this.alphabet = alphabet;
	}

	public void initial_tree(){
		ArrayList<Character> keys = new ArrayList<Character>(this.alphabet.keySet());

		for (char key : keys){
			Node n = new Node(key, (int)(this.alphabet.get(key)));
			this.forests.add(n);
		}
	}

	public Node general_tree(){
		this.initial_tree();

		while (this.forests.size() > 1){
			//this.forests is sorted
			Node t1_forest = this.forests.get(0);//min
			Node t2_forest = this.forests.get(1);//min2

			//System.out.println(t1_forest.get_freq() + " " + t2_forest.get_freq() + " " + (t1_forest.get_freq() + t2_forest.get_freq()));

			this.forests.remove(t1_forest);
			this.forests.remove(t2_forest);

			Node t_node = new Node(t1_forest.get_freq() + t2_forest.get_freq(), t1_forest, t2_forest);
			t1_forest.set_parent(t_node);
			t2_forest.set_parent(t_node);

			this.forests.add(t_node);

			Collections.sort(this.forests, Node.sort_forests);
		}
		return this.forests.get(0);
	}
}

