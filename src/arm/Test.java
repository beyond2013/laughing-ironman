package arm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
public class Test {
	public static void main(String[] args){
		ItemSet<Item> A = new ItemSet<Item>();
		ItemSet<Item> B = new ItemSet<Item>();
		Item I1= new Item("1");
		Item I2= new Item("2");
		Item I3= new Item("3");
		Item I4= new Item("4");
		
		A.addMember(I1);
		A.addMember(I2);
		A.addMember(I3);
		A.addMember(I4);
		B.addMember(I2);
		B.addMember(I1);
		B.addMember(I4);
		
		System.out.println("Item Set A: " + A);
		
		System.out.println("ItemSet A.size: " + A.size());
		System.out.println("ItemSet A.count: " + A.count());
		//System.out.println("A.powerSet(A, 2): " + A.powerSet(A, 3).toString());
		
		System.out.println("Item Set B: " + B);
		
		System.out.println("ItemSet.size B: " + B.size());
		System.out.println("ItemSet.count B: " + B.count());
		/*
		System.out.println("A.isSubset(B): " + A.isSubset(B));
		System.out.println("A.equals(B): " + A.equals(B));
		System.out.println("B.equals(A): " + B.equals(A));
		B.addMember(I3);
		
		System.out.println("Item Set B: " + B);
		
		System.out.println("ItemSet B.size: " + B.size());
		System.out.println("ItemSet B.count: " + B.count());
		
		System.out.println("A.isSubset(B): " + A.isSubset(B));
		System.out.println("A.equals(B): " + A.equals(B));
		System.out.println("B.equals(A): " + B.equals(A));
		System.out.println("Iterating over items of B");
		Iterator<Item> itr = B.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}*/
		
		//Item other1=new Item("2");
		
		//System.out.println(other1.compareTo(A.lastMember()));
		/*I2.addMember("4");
		I2.addMember("3");
		I2.addMember("2");
		I2.addMember("1");

		
		System.out.println("Item Set: " + I2);
		System.out.println("ItemSet.size: " + I2.size());
		System.out.println("ItemSet.count: " + I2.count());*/
		
		/*TreeSet<String>  names = new TreeSet<String>();
		TreeSet<String>  names2 = new TreeSet<String>();
		names.add("Imran");
		names.add("Irfan");
		names.add("Javed");
		names.add("Junaid");
		
		names2.add("Imran");
		names2.add("Irfan");	
		
		System.out.println(names.containsAll(names2));
		System.out.println(names.subSet(names.first(), true, names.last(), true));
		System.out.println(names2.subSet(names.first(), true, names.last(), false));*/
		
		TrieST<ItemSet> trie = new TrieST<ItemSet>();
		ArrayList<ItemSet> ps = new ArrayList<ItemSet>();
		ps=A.powerSet(2);
		System.out.println("A.powerSet(2)" + ps.toString());
		String key=""; 
		for (ItemSet<Item> itemset:ps){
			trie.put(itemset.to_String(), itemset);
		}
		ItemSet C= new ItemSet();
		C.addMember(new Item("1"));
		C.addMember(new Item("4"));
		ItemSet D= new ItemSet();
		D.addMember(new Item("2"));
		D.addMember(new Item("5"));
		System.out.println("trie.get(C.to_String())" + trie.get(C.to_String()));
		System.out.println("trie.contains(C.to_String()): " + trie.contains(C.to_String()));
		System.out.println("trie.get(D.to_String())" + trie.get(D.to_String()));
		
	}
}
