import java.util.*;

public class Node{
	private char label;//letter
	private int freq;
	private Node left_child;
	private Node right_child;
	private Node parent;

	public Node(char label, int freq){
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

	public char get_label(){
		return this.label;
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

	public static Comparator<Node> sort_forests = new Comparator<Node>() {

		public int compare(Node n1, Node n2) {

		   int freq1 = n1.get_freq();
		   int freq2 = n2.get_freq();

		   /*For ascending order*/
		   return freq1-freq2;
		}
	};

	public String deep_path(String path, char charac, ArrayList<Node> checked_forests){
		//System.out.println("Searched char : " + charac + "   Binary path : " + path);
		if (this.label == charac){
			return path;
		}

		else{
			if (this.left_child != null && !checked_forests.contains(this.left_child)){
				checked_forests.add(this.left_child);
				return this.left_child.deep_path(path + "0", charac, checked_forests);
			}
			else if (this.right_child != null && !checked_forests.contains(this.right_child)){
				checked_forests.add(this.right_child);
				return this.right_child.deep_path(path + "1", charac, checked_forests);
			}
			else if (this.parent != null){
				String new_path = path.substring(0, path.length()-1); //We delete the last value
				return this.parent.deep_path(new_path, charac, checked_forests);
			}
			else{
				return "Character not found";
			}
		}
	}

	public String deep_path_decompression(String path, String search_path, ArrayList<Node> checked_forests){
	//	System.out.println(path + " " + search_path);
	//	System.out.println(path.equals(search_path));

		if (path.equals(search_path)){
			return String.valueOf(this.get_label());
		}

		else{
			if (this.left_child != null && !checked_forests.contains(this.left_child)){
				checked_forests.add(this.left_child);
				return this.left_child.deep_path_decompression(path + "0", search_path, checked_forests);
			}
			else if (this.right_child != null && !checked_forests.contains(this.right_child)){
				checked_forests.add(this.right_child);
				return this.right_child.deep_path_decompression(path + "1", search_path, checked_forests);
			}
			else if (this.parent != null){
				String new_path = path.substring(0, path.length()-1); //We delete the last value
				return this.parent.deep_path_decompression(new_path, search_path, checked_forests);
			}
			else{
				return "Character not found";
			}
		}
	}
}
