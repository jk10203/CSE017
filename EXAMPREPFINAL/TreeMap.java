import java.util.Comparator;
import java.util.PrintWriter;
/**
 * Class TreeMap
 * @author Houria Oudghiri
 * Date of creation: May 10, 2022
 * @param <K> Type of the key
 * @param <V> Type of the value
 */
public class TreeMap<K, V> {
	private TreeNode root;
	private Comparator<K> comparator;
	private int size;
	/**
	 * Inner class for the tree nodes
	 * @author Houria Oudghiri
	 *
	 */
	private class TreeNode{
		MapEntry<K,V> value;
		TreeNode left;
		TreeNode right;
		/**
		 * Constructor
		 * @param val map entry to initialize the value of the tree node
		 */
		TreeNode(MapEntry<K,V> val){
			value = new MapEntry<>(val.getKey(), val.getValue());
			left = right = null;
		}
	}
	/**
	 * Constructor to create an empty treemap
	 * @param c Comparator object used to compare the node keys
	 */
	TreeMap(Comparator<K> c){
		root = null;
		comparator = c;
		size = 0; 
	}
	/**
	 * Method size
	 * @return the number of nodes in the tree map
	 */
	public int size() {
		return size; 
	}
	/**
	 * Method isEmpty
	 * @return true if the tree map is empty, false otherwise
	 */
	public boolean isEmpty() {
		return (size == 0); 
	}
	/**
	 * Method clear to remove all the nodes from the tree map
	 */
	public void clear() {
		root = null; 
		size = 0;
	}
	/**
	 * Method get
	 * @param key the value of the key being searched
	 * @return the value of the map entry with its key equal to key
	 */
	public V get(K key) {
		TreeNode node = root;
		while (node != null) {
			if( comparator.compare(key, node.value.getKey()) < 0)
				node = node.left;
			else if (comparator.compare(key, node.value.getKey()) > 0)
				node = node.right;
			else
				return node.value.getValue();
		}
		return null;
	}
	/**
	 * Method contains
	 * @param key the value of the key being searched
	 * @return true if a map entry with the same key is found, false otherwise
	 */
	public boolean contains(K key) {
		if(get(key) != null)
				return true;
		return false;
	}
	/**
	 * Method to add a new map entry
	 * @param key of the new map entry
	 * @param value of the new map entry
	 * @return true if a new map entry was added, 
	 *         false if the map entry already exists in the tree map
	 */
	public boolean add(K key, V value) {
		if (root == null)
			root = new TreeNode(new MapEntry<>(key, value));
		else {
			TreeNode parent, node;
			parent = null; node = root;
			while (node != null) {
				parent = node;
				if(comparator.compare(key, node.value.getKey()) < 0) {
					node = node.left; 
				}
				else if (comparator.compare(key, node.value.getKey()) > 0) {
					node = node.right; 
				}
				else {
					node.value.setValue(value);
					return true;
				}
			}
			if (comparator.compare(key, parent.value.getKey()) < 0)
				parent.left = new TreeNode(new MapEntry<>(key, value));
			else
				parent.right = new TreeNode(new MapEntry<>(key, value));
		}
		size++;
		return true; 
	}
	/**
	 * Method to remove the map entry that matches key
	 * @param key being removed
	 * @return true if an entry map was found and removed
	 *         false otherwise
	 */
	public boolean remove(K key) {
		TreeNode parent, node;
		parent = null; node = root;
		// Find value first
		while (node != null) {
			if (comparator.compare(key,node.value.getKey()) < 0) {
				parent = node;
				node = node.left;
			}
			else if (comparator.compare(key,node.value.getKey()) > 0) {
				parent = node;
				node = node.right;
			}
			else
				break;
		}
		if (node == null)
			return false;

		// Case 1: node has no children
		if(node.left == null && node.right == null){
			if(parent == null){
				root = null;
			}
			else{
				changeChild(parent, node, null);
			}
		}
		//case 2: node has one right child
		else if(node.left == null){
			if (parent == null){
				root = node.right;
			}
			else{
				changeChild(parent, node, node.right);
			}
		}
		//case 2: node has one left child
		else if(node.right == null){
			if (parent == null){
				root = node.left;
			}
			else{
				changeChild(parent, node, node.left);
			}
		}
		//case 3: node has two children
		else {
			TreeNode rightMostParent = node;
			TreeNode rightMost = node.left;
			while (rightMost.right != null) {
				rightMostParent = rightMost;
				rightMost = rightMost.right;
			}
			node.value = rightMost.value;
			changeChild(rightMostParent, rightMost, 
					rightMost.left);
		}
		size--;
		return true;
	}
	/**
	 * Method to change the child of parent from node to newChild
	 * @param parent	parent node
	 * @param node		current child
	 * @param newChild	new child
	 */
	private void changeChild(TreeNode parent,
			TreeNode node, TreeNode newChild){
		if(parent.left == node)
			parent.left = newChild;
		else
			parent.right = newChild;
	}
	/**
	 * Method inorder and its recursive helper method
	 * prints the nodes of the tree using inorder traversal
	 */
	public void inorder() {
		inorder(root);
	}
	File file = new File(filename);
		try {
			PrintWriter writeFile = new PrintWriter(file);
			if (node != null) {
				inorder(node.left);

				writeFile.println(node.value);
				//System.out.print(node.value + " ");
				inorder(node.right);
			}
			writeFile.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Cannot write to files.");
			System.exit(0);
		}

		/*Iterator<Room> iterR = rooms.iterator();
		File rFile = new File(roomFile);
		try {
			PrintWriter write = new PrintWriter(rFile);
			while(iterR.hasNext()) {
				Room r = iterR.next();
				write.println(r);
			}
			write.close();
		}*/
	/**
	 * Method preorder and its recursive helper method
	 * prints the nodes of the tree using preorder traversal
	 */
	public void preorder() {
		preorder(root);
	}
	private void preorder(TreeNode node) {
		if (node != null) {
			System.out.print(node.value + " ");
			preorder(node.left);
			preorder(node.right);
		}
	}
	/**
	 * Method postorder and its recursive helper method
	 * prints the nodes of the tree using postorder traversal
	 */
	public void postorder() {
		postorder(root);
	}
	private void postorder(TreeNode node)  {
		if (node != null) {
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.value + " ");	
		}
	}
	/**
	 * Method inorder: prints the nodes of the tree to 
	 *        filename using inorder traversal
	 */
	public void inorder(String filename){
	}
}


