
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

 public class TEST
{
     static ArrayList<Object> FinalResults=new ArrayList<>();
static ArrayList<Integer> finalArrayList=new ArrayList<>();
	 static int[] A,B,C,D,E,F,G,H,I;
	static int a,b,c,d,e,f,g,h,i;
	static int est;
    public static void main(String[] args) throws Exception
    {
    	//READING ARRAY SIZES
    	
    	 String input1 = JOptionPane.showInputDialog
    		      ("WELCOME !!! Enter size of guess numbers... ");
    	est=Integer.parseInt(input1);
    	int numSize[];
    	numSize=new int[est];
    	for(int t=0;t<est;t++){
      String input = JOptionPane.showInputDialog
      ("Enter choice nums length for  "+(t+1));
  numSize[t]= Integer.parseInt(input);}
    	a=numSize[0];
    	b=numSize[1];
    	c=numSize[2];
    	d=numSize[3];
    	e=numSize[4];
    	f=numSize[5];
    	g=numSize[6];
    	h=numSize[7];
    	i=numSize[8];
    	
    	A=new int[a];
    	B=new int[b];
    	C=new int[c];
    	D=new int[d];
    	E=new int[e];    	
    	F=new int[f];
    	G=new int[g];
    	H=new int[h];
    	I=new int[i];
    	
  
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
    
    for (int p= 0; p <f; p++){
    	String num=	JOptionPane.showInputDialog("Enter FAV num for F "+(p+1));
    	F[p]=Integer.parseInt(num);
    	finalArrayList.add(Integer.parseInt(num));
    }
    
    for (int p= 0; p <g; p++){
    	String num=	JOptionPane.showInputDialog("Enter FAV num for G "+(p+1));
    	G[p]=Integer.parseInt(num);
    	finalArrayList.add(Integer.parseInt(num));
    }
    
    for (int p= 0; p <h; p++){
    	String num=	JOptionPane.showInputDialog("Enter FAV num for H "+(p+1));
    	H[p]=Integer.parseInt(num);
    	finalArrayList.add(Integer.parseInt(num));
    }
    
    for (int p= 0; p <i; p++){
    	String num=	JOptionPane.showInputDialog("Enter FAV num for I "+(p+1));
    	I[p]=Integer.parseInt(num);
    	finalArrayList.add(Integer.parseInt(num));
    }
        
    // CONVERT ARRAYLIST TO ARRAY
   Object[] fullArray=finalArrayList.toArray();
   int[] convertedArray=new int[fullArray.length];
   for(int temp=0;temp<fullArray.length;temp++){
	   convertedArray[temp]=(int)fullArray[temp];
   }
  
    // SORTING ALL ARRAYS
   Arrays.sort(convertedArray);
//    Arrays.sort(A);
//    Arrays.sort(B);
//    Arrays.sort(C);
//    Arrays.sort(D);
//    Arrays.sort(E);
//    Arrays.sort(F);
//    Arrays.sort(G);
    
    // PRINT ARRAY
    for(int g=0;g<convertedArray.length;g++)
    	System.out.println(convertedArray[g]+"\t");
   
    // GENERATING FINAL NUMS COMBINATIONS        
    int i,j,k,l,m,n,q,r,t;
    int[] genArray=new int[est];
    for(i=0;i<a;i++){
    	for(j=0;j<b;j++){
    		for(k=0;k<c;k++){
    			for(l=0;l<d;l++){
    				for(m=0;m<e;m++){
    					for(n=0;n<f;n++){
    						for(q=0;q<g;q++){
    							for(r=0;r<h;r++){
    	    						for(t=0;t<i;t++){
    					genArray=new int[est];
   					
    					genArray[0]=A[i];
    					genArray[1]=B[j];
    					genArray[2]=C[k];
    					genArray[3]=D[l];
    					genArray[4]=E[m];						
    					genArray[5]=F[n];
    					genArray[6]=G[q];
    					genArray[7]=H[r];
    					genArray[8]=I[t];
    					FinalResults.add(genArray);
    	    						}
    	    						}
    					 }
    					}
    				}
    			}
    		}
    	}
    }
    filterResults();
    displayResult();
    }
    
    private static void filterResults() {
for(int q=0;q<(FinalResults.size()-1);q++){
	int[] origRes=new int[est];
	origRes=(int[])FinalResults.get(q);
	for(int s=1;s<FinalResults.size();s++){
		int[] tempRes=new int[est];
		tempRes=(int[])FinalResults.get(s);
		int	checkcount=0,h,l;
		   		for(h=0;h<origRes.length;h++){
		   		for(l=0;l<tempRes.length;l++){
		    			if(origRes[h]==tempRes[l])
		    				checkcount++;
		   		}
		   }
		   		if(checkcount>=3){   //4
		   			FinalResults.remove(tempRes);
//		   		FinalResults.remove(origRes);
		   		}
		   		}
}
	}

	public static void displayResult(){
    	System.out.println("\n The Final Results are: \n");
    	int[] result1=new int[est];
    	for(int a=0;a<FinalResults.size();a++){
    		result1=new int[est];
    		result1=(int[]) FinalResults.get(a);
    		if(est==6)
    		 System.out.println(result1[0]+" "+result1[1]+" "+result1[2]+" "+result1[3]+" "+result1[4]+" "+result1[5]+" "+"  Result: "+(a+1)); 
    		else if(est==7)
    		System.out.println(result1[0]+" "+result1[1]+" "+result1[2]+" "+result1[3]+" "+result1[4]+" "+result1[5]+" "+result1[6]+"  Result: "+(a+1));
    		
    		else if(est==9)
        		System.out.println(result1[0]+" "+result1[1]+" "+result1[2]+" "+result1[3]+" "+result1[4]+" "+result1[5]+" "+result1[6]+" "+result1[7]+" "+result1[8]+" Result: "+(a+1));
    	}
    }
    
      
    
 }
 
 