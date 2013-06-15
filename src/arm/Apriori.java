package arm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;
import arm.PrefixTree;

public class Apriori{
	private FrequentSet L1;
	private String[] alphabet;
	private String alpha;
	private int minSupport;
	private CandidateSet Ck;
	private FrequentSet Lk;
	
	public Apriori(){
		L1= new FrequentSet(1);
		Ck=new CandidateSet();
		Lk=new FrequentSet();
		minSupport=2;
	}
	public void aprioriProcess(){
		int k=0;
		do
		{
			k++;
			pruneJoin(k);	
			if(k>1) checkSupport(k);
			System.out.println(k + " Frequent Item Sets: Count");
			for(ItemSet I:Lk.getF()){
				System.out.println(I + " " + I.count());
			}
			
		}while(Ck.size()>0);
	}
	
	public void pruneJoin(int n){
		if(n==1){
			this.getL1();
			Lk=L1;
			sortAlphabet();
		}
		else
			if(n>1){ 
				String temp=new String("");
				Ck = new CandidateSet();
				for(ItemSet I:Lk.getF()){
					for(String st:alphabet){
							if(Integer.parseInt(st)>I.lastMember()){
								for(String items:I.membersOnly()){
									temp+=items;
									temp+=" ";
								}								
								temp+=st;
					  		    ItemSet Iprime=new ItemSet(temp);
								temp="";
					  		   	int k=n-1;
								ArrayList<ItemSet> subsets= ItemSet.powerset(Iprime, k);
								if(!isSubset(Lk, subsets)){
									break;
								}
								else{
									 Ck.add(Iprime);	
								}					  	
							}							
					}
				}
			}
	}
	
	public void checkSupport(int n){
		Lk= new FrequentSet();
		for(ItemSet I:Ck.getC()){
			I.setCount(0);
		}
		File file = new File("transactions.txt");
	    BufferedReader reader = null;
       try {
           reader = new BufferedReader(new FileReader(file));
           String trans = null; 
           while ((trans = reader.readLine()) != null) {  
	    	   Vector<String> CT=new Vector<String>();
	    	   CT=subset(Ck, trans);
	    	   //System.out.println(CT);
	    	   ItemSet temp=new ItemSet();
	    	   for(int i=0;i<CT.size();i++){
	    		   //System.out.println("CT " + CT.get(i));
	    		   if(!CT.get(i).isEmpty()){
	    		    temp.addMember(CT.get(i));
	    		    //System.out.println("temp= " + temp );
	    		   }
	    	   }
	    	   for(ItemSet itemset: Ck.getC()){
				   if(itemset.isSubset(temp)){
					   Ck.incrItemSet(itemset);
				   }
			   }   
           }
           for(ItemSet itemset:Ck.getC()){
        	   if(itemset.count()>=minSupport){
        		   Lk.add(itemset);
        	   }
           }
        }  
	    catch (FileNotFoundException e) {
           e.printStackTrace();
		}
	    catch (IOException e) {
		   e.printStackTrace();
		}
	    finally{
	           try {
	               if (reader != null) {
	                   reader.close();
	               }
	           }
	           catch (IOException e) {
	               e.printStackTrace();
	           }
	    }
	}
	
	private Vector<String> subset(CandidateSet C, String T){
		Vector<String> Y= new Vector<String>();
		PrefixTree trie = new PrefixTree(C.size());
		for(ItemSet itemset:C.getC()){
			for(String str:itemset.membersOnly()){
				trie.addNumbers(str);
			}
		}	
		Y=doSubset("",toVector(T), trie);
		return Y;
	}
	
	private Vector<String> doSubset(String node, Vector<String> T, PrefixTree trie){
		Vector<String> Y= new Vector<String>();
	
		int vSize=T.size();
		
		for(int i=0;i<vSize;i++)
    	{
    		Vector<String> tempVt=new Vector<String>();
    		String I;
    		if(i<T.size())
    		{
    			tempVt.clear();
    			for(int in=0;in<T.size();in++)
	    			tempVt.add((String) T.elementAt(in));
	    		if(node==""){
	    			I= T.elementAt(i);
	    		}
	    		else{
	    			I=node+" "+T.elementAt(i);	
	    		}
    			if(trie.containsPrefixNumber(I))
	    		{
	    			T.remove(0);
	    			Vector<String> temp=new Vector<String>();
	    			temp=doSubset(I,T,trie);
	    			for(int in=0;in<temp.size();in++)		    				
	    				Y.add(temp.elementAt(in));
	    			Y.add(I);
	    			T.clear();
	    			for(int in=0;in<tempVt.size();in++)
		    			T.add(tempVt.elementAt(in));
		    		
	    		}
    		}
    	}
		return Y;
	}
	
	private boolean isSubset(FrequentSet L, ArrayList<ItemSet> ps){
		boolean result=false;
		PrefixTree trie = new PrefixTree(L.size());
  	  		for(ItemSet itemset:L.getF()){
  	  			for(String str:itemset.membersOnly()){
				trie.addNumbers(str);
  	  			}
  	  		}
			for(ItemSet IS:ps){
				for(String st:IS.membersOnly()){
					if(trie.containsNumbers(st))
		    		result= true;
		    	else 
		    		result= false;	
				}	
			}
		
		return result;    	 
	}
	
	private void sortAlphabet(){
		String[] str=alpha.split(" ");
		int[] num=new int[str.length];
		for(int i=0; i< str.length;i++){
			num[i]=Integer.parseInt(str[i]);
		}
		Arrays.sort(num);
		for(int i=0; i< str.length;i++){
			str[i]=Integer.toString(num[i]);
		}
		alphabet=str;
	}
	
	public void getL1(){
		CandidateSet C1=new CandidateSet(1);
		File file = new File("transactions.txt");
	    BufferedReader reader = null;
	    int key=1;
	       try {
	           reader = new BufferedReader(new FileReader(file));
	           String text = null; alpha= new String("");
	           while ((text = reader.readLine()) != null) {  
	        	   if(key==1){
	    			   String[] strary=text.split(" ");
	    			   for (String st:strary){
	    				   ItemSet I=new ItemSet(st);
	    				   I.incrCount();
	    				   C1.add(I);
	    				   alpha+=(st);
	    				   alpha+=" ";
	    			   }
	    		   }
	    		   else
	    		   if(key>1){
	    			   String[] strary=text.split(" ");
	    			   for(String st:strary){
	    				   ItemSet I=new ItemSet(st);
	    				   if(C1.contains(I)){
	    					   C1.incrItemSet(I);  
	    				   }
	    				   else
	    				   {
	    					   I.incrCount();
	    					   C1.add(I);
	    					   alpha+=st;
	    					   alpha+=" ";
	    				   }
	    			   }
	    		   }  
	        	 key++;
	           }
	           Vector<ItemSet> candidates = new Vector<ItemSet>();
	           candidates=C1.getC();
	           Ck=C1;
	           for(int count=0;count<candidates.size();count++){  
	        	   if(candidates.get(count).count()>=minSupport){
	        		   L1.add(candidates.get(count));
	        	   }
	           }
	       } 
	       catch (FileNotFoundException e) {
	           e.printStackTrace();
	       } 
	       catch (IOException e) {
	           e.printStackTrace();
	       }
	       finally {
	           try {
	               if (reader != null) {
	                   reader.close();
	               }
	           }
	           catch (IOException e) {
	               e.printStackTrace();
	           }
	       }
	}
	
	  private  Vector<String> toVector(String st)
	    {
	    	Vector<String> vt=new Vector<String>();
	    	StringTokenizer sToken=new StringTokenizer(st);
	    	while(sToken.hasMoreTokens())vt.add(sToken.nextToken());
	    	    	
	    	return vt;
	    }
	
}