package arm;


import java.util.Vector;

public class CandidateSet  {
	private Vector<ItemSet> C;
	private int k;
	private int size;
	public CandidateSet(){
		C= new Vector<ItemSet>();
		k=0;
		size=0;
	}
	
	public CandidateSet(int ind){
		C= new Vector<ItemSet>();
		k=ind;
		size=0;
	}
	
	public void add(ItemSet s){
		C.add(s);
		size++;
	}
	public void setk(int val){
		k=val;
	}
	
	public int size(){
		return size;
	}
	public Vector<ItemSet> getC(){
		return C;
	}
	public int getk(){
		return k;
	}
	
	public boolean contains(ItemSet I){
	boolean result=false;
	for(ItemSet i:C){
		//if(i.size()==I.size()){
			if(i.equals(I)){
				result=true;
			}
		//}
	}
	return result;
	}
	
	public void incrItemSet(ItemSet arg){
		for(int i=0;i<C.size();i++){
			if(C.get(i).equals(arg)){
				C.get(i).incrCount();
			}
		}
		
	}
	public ItemSet[] membersOnly(){
		ItemSet[] memb= new ItemSet[C.size()];
		for(int i=0;i<C.size();i++){
			memb[i]=C.get(i);
		}
		return memb;
	}
	public ItemSet membAt(int arg){
		ItemSet result=C.get(arg);
		return result;
	}
	public void removeMembAt(int arg){
		if(arg<C.size()){
			C.remove(arg);
			this.size--;	
		}
		
	}
	public String toString(){
	String result=new String("");
	result="Candidate " + this.getk() + " Itemsets"; 
	result+="\n";
	result+="{";
	for(int count=0;count<this.size;count++){
		result+=this.C.get(count).toString();
		/*result+=": ";
		result+=this.C.get(count).count();*/
		if(count+1<this.size){
		  result+=", ";
		}
	}
	result+="}";
	
	return result;
	}
}
