
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


 public class TEST
{
     static ArrayList<Object> FinalResults=new ArrayList<>();
static ArrayList<Integer> finalArrayList=new ArrayList<>();
static ArrayList<Object> FilteredResults=new ArrayList<>();

static ArrayList<Object> AllRecentResults=new ArrayList<>();

	 static int[] A,B,C,D,E,F,G;
	static int a,b,c,d,e,f,g;
	static int est;
    public static void main(String[] args) throws Exception
    {
    	generatePossibleCombinations();
    AllRecentResults=readFromXL();
       filterResults();
       getResult();
       displayResult();
    }
    
   
    	private static void generatePossibleCombinations() {
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
       	
       	A=new int[a];
       	B=new int[b];
       	C=new int[c];
       	D=new int[d];
       	E=new int[e];    	
       	F=new int[f];
       	G=new int[g];
       	
     
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
           
       // CONVERT ARRAYLIST TO ARRA
      Object[] fullArray=finalArrayList.toArray();
      int[] convertedArray=new int[fullArray.length];
      for(int temp=0;temp<fullArray.length;temp++){
   	   convertedArray[temp]=(int)fullArray[temp];
      }
     
       // SORTING ALL ARRAYS
      Arrays.sort(convertedArray);
//       Arrays.sort(A);
//       Arrays.sort(B);
//       Arrays.sort(C);
//       Arrays.sort(D);
//       Arrays.sort(E);
//       Arrays.sort(F);
//       Arrays.sort(G);
       
       // PRINT ARRAY
       for(int g=0;g<convertedArray.length;g++)
       	System.out.println(convertedArray[g]+"\t");
      
       // GENERATING FINAL NUMS COMBINATIONS        
       int i,j,k,l,m,n,q;
       int[] genArray=new int[est];
       for(i=0;i<a;i++){
       	for(j=0;j<b;j++){
       		for(k=0;k<c;k++){
       			for(l=0;l<d;l++){
       				for(m=0;m<e;m++){
       					for(n=0;n<f;n++){
       						for(q=0;q<g;q++){
       					genArray=new int[est];
      					
       					genArray[0]=A[i];
       					genArray[1]=B[j];
       					genArray[2]=C[k];
       					genArray[3]=D[l];
       					genArray[4]=E[m];						
       					genArray[5]=F[n];
       					genArray[6]=G[q];
       					FinalResults.add(genArray);
       					 }
       					}
       				}
       			}
       		}
       	}
       }		
	}


		public static void getResult(){
    	   	int[] drawFromPossible=new int[est];
    	    	int[] drawFromRecent=new int[est];
    	    	int x,y;
    	    	int xTest,yTest;
    	    	for(int i=0;i<FinalResults.size();i++){
    	    		drawFromPossible=(int[])FinalResults.get(i);
    	    		int count=0;
    	    		for(int j=0;j<AllRecentResults.size();j++){
    	    			
    	    			drawFromRecent=(int[]) AllRecentResults.get(j);    			
    	  			
    	    			for(x=0;x<est;x++){
    	    				
    	    				for(y=0;y<est;y++){
    	    					xTest=drawFromPossible[x];
    	    					yTest=drawFromRecent[y];
    	    				if(drawFromPossible[x]==drawFromRecent[y])	
    	    					count++;
    	    				}
    	    				
    	    			}
    	    			
    	    			}
    	    		
    	    		if(count<=5)
    					FilteredResults.add(drawFromPossible);
    				drawFromPossible=new int[est];
    				
    	    	}
    	    	    }  
		
	

	private static ArrayList<Object> readFromXL()throws Exception {
            String filename = "recentresults.xls";
       int[] sheetData;
       ArrayList<Object> RecentResultsList=new ArrayList<>();
       FileInputStream fis = null;
      try {
          fis = new FileInputStream(filename);
          HSSFWorkbook workbook = new HSSFWorkbook(fis);
          HSSFSheet sheet = workbook.getSheetAt(0);
          Iterator<Row> rows = sheet.rowIterator();
        
          while (rows.hasNext()) {
        	  sheetData=new int[est];
              HSSFRow row = (HSSFRow) rows.next();
              Iterator<Cell> cells = row.cellIterator();
               int c=0;
               
                 while(cells.hasNext() && c<est ) {
              	Cell  cell=(HSSFCell) cells.next();
              	cell.setCellType(Cell.CELL_TYPE_NUMERIC);          	
              	sheetData[c]=(int)cell.getNumericCellValue();
              	c++;
              	          }
                 Arrays.sort(sheetData);
                 if(RecentResultsList.size()<=5)
    RecentResultsList.add(sheetData);
          }
      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          if (fis != null) {
              fis.close();
          }
      }
    return RecentResultsList;
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
		   		if(checkcount>=4){   //4
		   			FinalResults.remove(tempRes);
//		   		FinalResults.remove(origRes);
		   		}
		   		}
}
	}

	public static void displayResult(){
    	System.out.println("\n The Final Results are: \n");
    	int[] result1=new int[est];
    	for(int a=0;a<FilteredResults.size();a++){
    		result1=new int[est];
    		result1=(int[]) FilteredResults.get(a);
    		if(est==6)
    		 System.out.println(result1[0]+" "+result1[1]+" "+result1[2]+" "+result1[3]+" "+result1[4]+" "+result1[5]+" "+"  Result: "+(a+1)); 
    		else if(est==7)
    		System.out.println(result1[0]+" "+result1[1]+" "+result1[2]+" "+result1[3]+" "+result1[4]+" "+result1[5]+" "+result1[6]+"  Result: "+(a+1)); 
    	}
    }
    
      
    
 }
 
 