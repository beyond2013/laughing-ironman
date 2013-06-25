package arm;
import java.io.*;
import java.util.*;

// String prefix tree.
public class PrefixTree extends AbstractCollection<String> implements Set<String> {
    
     public static int N;//=26;
    /**
    Reads the given file, treating it as a dictionary containing one word per line. 
    Returns a trie (prefix tree) collection of all words in the dictionary.
     * @param i 
    
    */
    public PrefixTree(int i)
    {
    	//PrefixTree.N=i==0?ABC:i;
    	PrefixTree.N=i;
    	
    }
   
    public static Trie fromFile(String filename) {
        Trie trie = new Trie();
        try {
            Scanner in = new Scanner(new File(filename));
            while (in.hasNext()) {
                String word = in.next();
                if (word.length() >= 4)
                    trie.add(word);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return trie;
    }
    //----------------
  
    //-----------------

    
    // Adds the following word to the prefix tree.
    public boolean add(String word) {
        TrieNode node = this.walk(word, true);
        if (!node.wholeWord) {
            node.wholeWord = true;
            this.size++;
        }
        return true;
    }
    //--------------
    public boolean addNumbers(String word) 
    {
        TrieNode node = this.newWalk(word, true);
        if (!node.wholeWord) {
            node.wholeWord = true;
            this.size++;
        }
        return true;
    }
    // Returns whether the given string represents a prefix or complete word
    // in this tree.
    public boolean containsPrefix(String prefix) {
        return this.walk(prefix, false) != null;
    }
    //------------
    public boolean containsPrefixNumber(String prefix) {
        return this.newWalk(prefix, false) != null;
    }
    // Required by Java Collection interface.
    public boolean contains(Object word) {
        TrieNode node = this.walk((String) word, false);
        return node != null && node.wholeWord;
    }
  //--------------
    public boolean containsNumbers(Object word) {
        TrieNode node = this.newWalk((String) word, false);
        return node != null && node.wholeWord;
    }
    // helper method used by add and contains
    private TrieNode walk(String word, boolean create) {
        if (word == null) {
            throw new NullPointerException();
        }

        TrieNode node = this.root;

        for (char ch : word.toLowerCase().toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                if (create) {
                    node.children[index] = new TrieNode();
                } else {
                    return null;
                }
            }
            node = node.children[index];
        }
        
        return node;
    }
    ////***************************
    private TrieNode newWalk(String word, boolean create) {
        if (word == null) {
            throw new NullPointerException();
        }

        TrieNode node = this.root;
        StringTokenizer str=new StringTokenizer(word);

        while(str.hasMoreTokens()){
        	int index = Integer.parseInt(str.nextToken());
            if (node.children[index] == null) {
                if (create) {
                    node.children[index] = new TrieNode();
                } else {
                    return null;
                }
            }
            node = node.children[index];
        }
        
        return node;
    }
    // Required by Java Collection interface.
    public Iterator<String> iterator() {
        throw new UnsupportedOperationException();
    }
    
    //--------------
    private  String removeSpace(String str)
    {
    	String st="";
    	StringTokenizer token=new StringTokenizer(str);
    	while(token.hasMoreTokens())st+=token.nextToken();    	
    	return st;
    }
    // Returns the number of words in this tree.
    public int size() {
        return this.size;
    }
    
    // Represents a single node of a prefix tree.
    private class TrieNode {
     //   private int N;
    	final int x=N+2;
    
		public TrieNode[] children = new TrieNode[256];
        
        // set to true if this node represents the end of a word
        public boolean wholeWord = false;
    }
    private TrieNode root = new TrieNode();
    private int size = 0;
    private static  int ABC = 'z' - 'a' + 1;
}