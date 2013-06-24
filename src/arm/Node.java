package arm;
 
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


public class Node {
 int value;
 boolean marker; 
 Collection<Node> child;
 
 public Node(){
	  child = new LinkedList<Node>();
	  marker = false;
	  value=0;
	 } 
 public Node(int v){
	  child = new LinkedList<Node>();
	  marker = false;
	  value = v;
	 }
 
 public String PrefixPaths(){
	 String st= new String();
	 ListIterator<Node> litr = (ListIterator<Node>) this.child.iterator();
	 while (litr.hasPrevious()){
		 st+=litr.previous();
	 }
	 return st;
 }

 public Node subNode(int val){
	  if(child!=null){
	   for(Node eachChild:child){
	    if(eachChild.value == val){
	     return eachChild;
	    }
	   }
	  }
	  return null;
	 }
 public String toString(){
	 String res="";
	 Iterator<Node> itr = this.child.iterator();
	 while(itr.hasNext()){
		 res+=itr.next().value;
		 res+=" ";
	 }
	 return res;
 }
}