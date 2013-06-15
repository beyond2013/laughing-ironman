package arm;

// base class item, where each item is a string 

public class Item implements Comparable<Item>{
	private String item;
	
	//constructor
	public Item(String i){
		item = new String(i);
	}
	
	public String getItem(){
		return item;
	}
	
	public void setItem(String arg){
		item = arg;
	}

	
	public String toString(){
		return item;
	}

	@Override
	public int compareTo(Item arg0) {
		// TODO Auto-generated method stub
		int result=-1;
		result = this.item.compareToIgnoreCase(arg0.getItem());
		return result;
	}
	
	
	public int hashCode(){
		return item.hashCode();
	}
}
