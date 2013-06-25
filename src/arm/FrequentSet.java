package arm;

import java.util.Vector;

public class FrequentSet {

		private Vector<ItemSet> F;
		private int k;
		private int size;
		public FrequentSet(){
			F= new Vector<ItemSet>();
			k=0;
			size=0;
		}
		public FrequentSet(int ind){
			F= new Vector<ItemSet>();
			k=ind;
			size=0;
		}
		
		public void add(ItemSet<Item> s){
			F.add(s);
			size++;
		}
		public void setk(int val){
			k=val;
		}
		
		public int size(){
			return size;
		}
		public Vector<ItemSet> getF(){
			return F;
		}
		public int getk(){
			return k;
		}
		public boolean contains(ItemSet I){
			boolean result=false;
			for(ItemSet i:F){
				if(i.size()==I.size()){
					if(i.equals(I)){
						result=true;
					}
				}
			}
			return result;
			}
		public String to_String(){
			String result=new String("");
			for(int count=0;count<this.size;count++){
				result+=this.F.get(count).toString();	
				if(count+1<this.size){
					  result+=", ";
				}
			}
			return result;
			}
		
		public String toString(){
			String result=new String("");
			result="Frequent " + this.getk() + " Itemsets"; 
			result+="\n";
			result+="{";
			for(int count=0;count<this.size;count++){
				result+=this.F.get(count).toString();
				result+=": ";
				result+=this.F.get(count).count();	
				if(count+1<this.size){
					  result+=", ";
				}
			}
			result+="}";
			return result;
			}
}
