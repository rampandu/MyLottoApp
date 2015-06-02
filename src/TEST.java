
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

 public class TEST
{
     static ArrayList<Object> FinalResults=new ArrayList<>();
static ArrayList<Integer> finalArrayList=new ArrayList<>();
	 static int[] A,B,C,D,E;
	static int a,b,c,d,e;
    public static void main(String[] args) throws Exception
    {
    	//READING ARRAY SIZES
    	int numSize[];
    	numSize=new int[5];
    	
    	for(int t=0;t<5;t++){
      String input = JOptionPane.showInputDialog
      ("Enter choice nums length for  "+(t));
  numSize[t]= Integer.parseInt(input);}
    	a=numSize[0];
    	b=numSize[1];
    	c=numSize[2];
    	d=numSize[3];
    	e=numSize[4];
    	A=new int[a];
    	B=new int[b];
    	C=new int[c];
    	D=new int[d];
    	E=new int[e]; 
  
 // READING VALUES FOR ALL ARRAYS
    for (int p= 0; p <a; p++){
    	String num=	JOptionPane.showInputDialog("Enter FAV num for A "+(p+1));
    	A[p]=Integer.parseInt(num);
    	finalArrayList.add(Integer.parseInt(num));
    }
    for (int p= 0; p <b; p++){
    	String num=	JOptionPane.showInputDialog("Enter FAV num for B "+(p+1));
    	B[p]=Integer.parseInt(num);
    	finalArrayList.add(Integer.parseInt(num));
    }
    for (int p= 0; p <c; p++){
    	String num=	JOptionPane.showInputDialog("Enter FAV num for C "+(p+1));
    	C[p]=Integer.parseInt(num);
    	finalArrayList.add(Integer.parseInt(num));
    }
    for (int p= 0; p <d; p++){
    	String num=	JOptionPane.showInputDialog("Enter FAV num for D "+(p+1));
    	D[p]=Integer.parseInt(num);
    	finalArrayList.add(Integer.parseInt(num));
    }
    for (int p= 0; p <e; p++){
    	String num=	JOptionPane.showInputDialog("Enter FAV num for E "+(p+1));
    	E[p]=Integer.parseInt(num);
    	finalArrayList.add(Integer.parseInt(num));
    }
    
    // CONVERT ARRAYLIST TO ARRAY
   Object[] fullArray=finalArrayList.toArray();
   int[] convertedArray=new int[fullArray.length];
   for(int temp=0;temp<fullArray.length;temp++){
	   convertedArray[temp]=(int)fullArray[temp];
   }
   Arrays.sort(convertedArray);
    // SORTING ALL ARRAYS
    Arrays.sort(A);
    Arrays.sort(B);
    Arrays.sort(C);
    Arrays.sort(D);
    Arrays.sort(E);
    for(int g=0;g<convertedArray.length;g++)
    	System.out.println(convertedArray[g]);
//   generateCombinations(convertedArray, 5);
   
    // GENERATING FINAL NUMS COMBINATIONS
    
    
    int i,j,k,l,m;
        	
    for(i=0;i<a;i++){
    	for(j=0;j<b;j++){
    		for(k=0;k<c;k++){
    			for(l=0;l<d;l++){
    				for(m=0;m<e;m++){
    					int[] genArray=new int[5];
    					genArray[0]=A[i];
    					genArray[1]=B[j];
    					genArray[2]=C[k];
    					genArray[3]=D[l];
    					genArray[4]=E[m];
    				
    					FinalResults.add(genArray);
    				}
    			}
    		}
    	}
    }
   displayResult(); 
    }
    
    public static void displayResult(){
    	System.out.println("\n The Final Results are: \n");
    	int[] result1=new int[5];
    	for(int a=0;a<FinalResults.size();a++){
    		result1=new int[5];
    		result1=(int[]) FinalResults.get(a);
    		
    		 System.out.println(result1[0]+" "+result1[1]+" "+result1[2]+" "+result1[3]+" "+result1[4]+"  Result: "+a); 
    	}
    }
    
      
    public static void generateCombinations(int[]  fullArray1, int K){
   	 
        int N = fullArray1.length;
 
if(K > N){
    System.out.println("Invalid input, K > N");
    return;
}

int combination[] = fullArray1;
int r = 0;      
int index = 0;
 
while(r >= 0){
    if(index <= (N + (r - K))){
            combination[r] = index;
             
        if(r == K-1){

           addToFinalList(combination);
            index++;                
        }
        else{
            index = combination[r]+1;
            r++;                                        
        }
    }
    else{
        r--;
        if(r > 0)
            index = combination[r]+1;
        else
            index = combination[0]+1;   
    }           
}
}
	private static void addToFinalList(int[] combination) {
		FinalResults.add(combination);
		printResults();
	}
	private static void printResults() {
	   
    	System.out.println("\n The Final Results are: \n");
    	int[] res;
    	for(int a=0;a<FinalResults.size();a++){
    		res=new int[5];
    		res=(int[]) FinalResults.get(a);
    		
    		 System.out.println(res[0]+" "+res[1]+" "+res[2]+" "+res[3]+" "+res[4]+" "+"  Result: "+a); 
    	
    }
	}
 }
 
 