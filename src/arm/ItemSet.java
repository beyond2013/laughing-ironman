package arm;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;
@SuppressWarnings("hiding")
public class ItemSet<Item> implements Iterable<Item> {

	/*
	 * HashSet is much faster than TreeSet 
	 * (constant-time versus log-time for most operations
	 *  like add, remove and contains) but offers no ordering 
	 *  guarantees like TreeSet.
	 */
	private int size;
	// placed outside constructorZ to avoid null pointer exception
	private TreeSet<Item> members=new TreeSet<Item>();
	private int count;
	
	public ItemSet(){
		size=0;
		count=0;
	}

	public ItemSet(Item i){
		this.members.add(i);
		size++;
	}
	//copy constructor 
	public ItemSet(final ItemSet<Item> other){
		this.size = other.size;
		this.members.addAll(other.members);
		this.count=0;
	}
	public void addMember(Item i){
		this.members.add(i);
		size++;
	}
	
	
	public void setCount(int arg){
		count=arg;
	}
	public int count(){
		return this.count;
	}
	public void incrCount(){
		this.count++;
	}
	
	
	public int size(){
		return this.size;
	}
	public String to_String(){
		String itemset=new String("");
		int counter=0;
		for(Item i:this.members){
			itemset+=i.toString();
			counter++;
			if(counter<members.size()){
				itemset+=" ";
			}	
		}
		return itemset;
	}
	public String toString(){
		String itemset=new String("");
		int counter=0;
		itemset="{";
		for(Item i:this.members){
			itemset+=i.toString();
			counter++;
			if(counter<members.size()){
				itemset+=", ";
			}	
		}
		itemset+="}";
		return itemset;
	}
 
	public Item lastMember(){
		 Item last =this.members.last();
		 return last;
	}
	
	public boolean isSubset(ItemSet<Item> item){
		boolean result=false;
		if(item.members.containsAll(this.members)){
			result=true;
		}
		return result;
	}
	   /**
  * Returns the power set from the given set by using a binary counter
  * Example: S = {a,b,c}
  * P(S) = {[], [c], [b], [b, c], [a], [a, c], [a, b], [a, b, c]}
  * @param set String[]
  * @return ArrayList
  * I have altered the method, the outer for loop has been initialized with 1 
  * rather than 0, to exclude the empty set from the power set, similarly to exclude
  * the set itself from the power set the conditional test reads i < powerElements-1
  * rather than i < powerElements 
  * the parameter size will allow the method to further reduce the power set to include
  * only the subsets of a specific length
  */
@SuppressWarnings({ "rawtypes", "unchecked" })
public ArrayList<ItemSet> powerSet(int size) {
    ArrayList<ItemSet> subsets= new ArrayList<ItemSet>();
	   ArrayList<Item> set=new ArrayList<Item>();  
	   Iterator<Item> itr= this.members.iterator();
	   while(itr.hasNext()){
		   set.add(itr.next());
		   count++;
	   }
	   
	   //create the empty power set
    LinkedHashSet power = new LinkedHashSet();
  
    //get the number of elements in the set
    int elements = this.size;
  
    //the number of members of a power set is 2^n
    int powerElements = (int) Math.pow(2,elements);
  
    //run a binary counter for the number of power elements
    for (int i = 1; i < powerElements-1; i++) {
      
        //convert the binary number to a string containing n digits
        String binary = intToBinary(i, elements);
      
        //create a new set
        LinkedHashSet innerSet = new LinkedHashSet();
      
        //convert each digit in the current binary number to the corresponding element
         //in the given set
        for (int j = 0; j < binary.length(); j++) {
            if (binary.charAt(j) == '1')
                innerSet.add(set.get(j));
            	//innerSet.add();
        }
      
        //add the new set to the power set
        power.add(innerSet);
      
    }
    
    Iterator<HashSet> it=power.iterator();
    while(it.hasNext()){
	       	HashSet hs=it.next();
	       	if(hs.size()==size){
	       		Object[] ary = hs.toArray();
	       		ItemSet i= new ItemSet();
	       		for(Object o: ary){
	       			i.addMember(o);	
	       		}
	       		subsets.add(i);
	        }
    }
   return subsets;
}

/**
  * Converts the given integer to a String representing a binary number
  * with the specified number of digits
  * For example when using 4 digits the binary 1 is 0001
  * @param binary int
  * @param digits int
  * @return String
  */
public static String intToBinary(int binary, int digits) {
    String temp = Integer.toBinaryString(binary);
    int foundDigits = temp.length();
    String returner = temp;
    for (int i = foundDigits; i < digits; i++) {
        returner = "0" + returner;
    }
    return returner;
} 
	@SuppressWarnings("unchecked")
	public boolean equals(Object other){
		boolean result=false;
		if(other==null) return false;
		if(other.getClass()!=this.getClass()) return false;
		if(other==this) return true;
		ItemSet<Item> i= (ItemSet<Item>) other;
		if(this.isSubset(i) && i.isSubset(this)){
			result=true;
		}
		return result;
	}

	public int hashCode(){
		int result=3;
		for(Item i: this.members){
			result +=i.hashCode();
		}
		return result;
	}
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return this.members.iterator();
	}

}
