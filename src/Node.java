public class Node{
	private int label;//letter
	private int freq;
	private Node left;
	private Node right;
	private Node parent;

	public Node(int label, int freq){
		//If the node is a letter, it's a leaf
		this.label = label;
		this.freq = freq;
	}

	public Node(int freq, Node left, Node right){
		this.freq = freq;
		this.left = left;
		this.right = right;
	}

	public int get_freq(){
		return this.freq;
	}

}