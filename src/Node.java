public class Node{
	private int label;//letter
	private int freq;
	private Node left_child;
	private Node right_child;
	private Node parent;

	public Node(int label, int freq){
		//If the node is a letter, it's a leaf
		this.label = label;
		this.freq = freq;
	}

	public Node(int freq, Node left_child, Node right_child){
		this.freq = freq;
		this.left_child = left_child;
		this.right_child = right_child;
	}

	public int get_freq(){
		return this.freq;
	}

	public void set_parent(Node parent){
		this.parent = parent;
	}

	public Node get_parent(){
		return this.parent;
	}

	public Node get_left_child(){
		return this.left_child;
	}

	public Node get_right_child(){
		return this.right_child;
	}

}