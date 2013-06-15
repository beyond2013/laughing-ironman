package arm;


public class Tokenizer {

	public static void main(String args[]){
		String str= new String("اسپیشلسٹ عمران خان کی جلد صحت یابی کیلئے پرامید ہی...");
		String[] strary= str.split(" ");
		int i;
		for(i=0;i< strary.length-1; i++){
				System.out.println("(" + (i +1) + ", " + (i + 2) + "):" + strary[i] + " " +strary[i+1]);
			
		}
	}
}
