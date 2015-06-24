/*
 * Knapsack
 * Lee, Juhyun
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;


public class Knapsack {

	public static void main(String[] args) throws IOException {
		
		int items = 0;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				
		while(true){
			System.out.println("enter the number of items :");
			try{
				items = (new Integer ((String)in.readLine())).intValue();
			}//end of try
			catch(NumberFormatException e){
				System.out.println("input is not a number");
			}//end of catch
			if (items == 0)
				System.out.println(" you cant have 0 items");
			else
				break;
		}//end of while input items
		
		//asks for number of items to add to program.
		
		
		String[] iName = new String[items];
		//string array to store items name.
		double[] iweig = new double[items];
		//double array to store items weight.
		
		
		double maxwei = 0;
		while(true){
			System.out.println("enter the number of maximum weight");
			try{
				maxwei = (new Double ((String)in.readLine())).doubleValue();
			}//end of try
			catch(NumberFormatException e){
				System.out.println("input is not a number");
			}//end of catch
			if (maxwei == 0)
				System.out.println(" you cant have 0 max weight");
			else
				break;
		}//end of while input max weight
		
		//asks for maximum weight limit.
		
		
		for(int i= 0; i < items; i++){
			System.out.println("enter the name of the item");
			iName[i] = in.readLine();
			System.out.println("enter the weight of " + iName[i]);
			try{
				iweig[i] = (new Double ((String)in.readLine())).doubleValue();
			}//end of try
			catch(NumberFormatException e){
				System.out.println("input is not a number");
			}//end of catch
		}//end of for loop
		
		//asks for its name and weight of individual items.
		
		
	    int numRows = (int)Math.pow(2, items);
	    boolean[][] bools = new boolean[numRows][items];
	    for(int i = 0;i<bools.length;i++)
	    {
	        for(int j = 0; j < bools[i].length; j++)
	        {
	            int val = bools.length * j + i;
	            int ret = (1 & (val >>> j));
	            bools[i][j] = ret != 0;
	        }//end of inner for loop
	    }//end of outter for loop
	    
	    // this will generate a boolean 2d array that is size of item number squared as row.
	    // array will reflect on the power set of the input.
	    // the bool array will decide if item will be taken or not taken.
	    
	    
	    String temp = new String("");
	    double tempw = 0.0;
	    String[][] solution = new String[numRows][2];
	    
	    for(int i = 0; i < numRows; i++){
	    	
	    	temp = " ";
	    	tempw = 0.00;
	    	
	    	for(int j = 0; j < items; j++){
	    		if(bools[i][j] == true){
	    			temp = temp + iName[j] + " ,";
	    			tempw = tempw + iweig[j];
	    		}//end of if

	    	solution[i][0] = Double.toString(tempw);
	    	solution[i][1] = temp;
	    		
	    	}//end of inner for loop
	    }//end of outter for loop
	    
	    // solution array will store every possible combination of items
	    // along with the added weights of the items choosen.
	    
	    
	    Arrays.sort(solution, new Comparator<String[]>(){
	    	public int compare(final String[] a,final String[] b){
	    		final String t1 = a[0];
	    		final String t2 = b[0];
	    		return Double.valueOf(t1).compareTo(Double.valueOf(t2));
	    	} //end of compare
	    }); // end of array sort
	    
	    // this will use sort method included in array
	    // to sort the array in acending order of combined weight.
	    
	   
	   for(final String[] s : solution){
	    	System.out.println(s[0] + " " + s[1]);
	    }//end of for
	   
	   // prints powerset and max weight of the items to console.
	   
	   int match = 0;
	   
	   while(Double.parseDouble(solution[match][0]) < maxwei){
		   match = match +1;
	   } // keep incre match by 1 until maximum is found.
	   
	   System.out.println();
	   
	   String answer;
	   
	   if(match == 0 || (Double.parseDouble(solution[match][0])) > maxwei){
		   answer = "no possible solution available.";
	   } //end of if
	   else
		   answer = new String ("single solution weight is " + solution[match][0] + " and items being "+ solution[match][1]);
	   
	   System.out.println(answer);
	   // console print answer
	   
	   BufferedWriter out = new BufferedWriter(new FileWriter("result.txt"));
	   out.write(answer); //write single answer
	   out.close(); //closes written file
	   
	   // this will write single available solution to the txt file.
	   
	}//end of main
}//end of knapsack
