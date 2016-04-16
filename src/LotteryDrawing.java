 import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
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

 public class LotteryDrawing
{
	static ArrayList<Object> AllPossiblities=new ArrayList<>();
     static ArrayList<Object> AllRecentResults=new ArrayList<>();
     static ArrayList<Object> FinalResults=new ArrayList<>();
     static ArrayList<Object> FinalResultsCopy=new ArrayList<>();
     static ArrayList<Object> FilteredResults=new ArrayList<>();
     static ArrayList<Integer> finalArrayList=new ArrayList<>();
     static int[] A,B,C,D,E,F,G;
 	static int a,b,c,d,e,f,g;

     static int est;
     static int res=0;
     static int lky,excl;
     static int[] luckynumbers = new int[lky];
     static int[] excludenumbers;
     static int[] numbersCopy=new int[lky];
     static int[] result; 
     static int m=0;  
     static String lucky,exclStr;
    public static void main(String[] args) throws Exception
    {
    String input = JOptionPane.showInputDialog
          ("How many numbers do you need to draw?");
      est= Integer.parseInt(input);
//      lucky=JOptionPane.showInputDialog("how many LUCKY numbers you have???");
//	 	 lky=Integer.parseInt(lucky);  
	 	 
	 	 exclStr=JOptionPane.showInputDialog("how many EXCLUDE numbers you have???");
	 	 excl=Integer.parseInt(exclStr);  
	 	excludenumbers = new int[excl];
           
      //EXCLUDE NUMS
	 	int d;
      for (d=0;d<excl;d++){
   	 String exclnum=	JOptionPane.showInputDialog("Enter your "+(d+1)+"EXCLUDE number of "+excl);
  	 	excludenumbers[d]=Integer.parseInt(exclnum);
  	 	}
      
//       luckynumbers=ReadLuckyNumFromXL();
//             getAllPossibles();
       generatePossibleCombinations();
             printAllPossibleResults();
             
             AllRecentResults=ReadRecentResultsFromXL();
             printRecentResults();
             
            getresult();
          printResults();
//            filterResults();   //temp
//            filterResults(); 
//            filterResults(); 
           
        	filterRepeatedNums();
//        	displayResult(FilteredResults);
        	filterRepeatedNums();
//        	filterRepeatedNums();
        	
//        	filterExcludeNums();
//        	filterExcludeNums();
//        	filterExcludeNums();
//        	 getresult();
        	System.out.println("\n FINAL RESULTS are: \n");
            displayResult(AllPossiblities);
//            WriteFinalResultsToExcel();
          System.exit(0);
    }
    
    private static void printResults() {
    	System.out.println("\n Generated RESULTS are: \n");
    	  displayResult(AllPossiblities);		
	}

	private static void printAllPossibleResults() {
    	System.out.println("\n All POSSIBLE Results are: \n");
displayResult(AllPossiblities);		
	}

	private static void printRecentResults() {
    	  System.out.println("\n The RECENT Results are: \n");
    	displayResult(AllRecentResults);	
	}

	private static void filterExcludeNums() {
		 System.out.println("filtering exclude nums");
    	int x,y,count;
        	int[] draw=new int[est];
    	  	for(int s=0;s<AllPossiblities.size();s++){
			draw=(int[])AllPossiblities.get(s);
			count=0;
			for(x=0;x<est;x++){				
				for(y=0;y<excl;y++){
					if(draw[x]==excludenumbers[y]){
						count++;
					}
					
				}
    	}
			if(count>=3){
//				FilteredResults.add(s);
				AllPossiblities.remove(s);
			}
    	  	}
		
	}

	private static void generatePossibleCombinations() {
		//READING ARRAY SIZES
    	
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
   
  
   
         
   // CONVERT ARRAYLIST TO ARRA
  Object[] fullArray=finalArrayList.toArray();
  int[] convertedArray=new int[fullArray.length];
  for(int temp=0;temp<fullArray.length;temp++){
	   convertedArray[temp]=(int)fullArray[temp];
  }
 
   // SORTING ALL ARRAYS
  Arrays.sort(convertedArray);
//   Arrays.sort(A);
//   Arrays.sort(B);
//   Arrays.sort(C);
//   Arrays.sort(D);
//   Arrays.sort(E);
//   Arrays.sort(F);
//   Arrays.sort(G);
   
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
   					
   					genArray=new int[est];
  					
   					genArray[0]=A[i];
   					genArray[1]=B[j];
   					genArray[2]=C[k];
   					genArray[3]=D[l];
   					genArray[4]=E[m];						
   					AllPossiblities.add(genArray);
   					
   				}
   			}
   		}
   	}
   }		
}

	
	private static void getAllPossibles() {
    	 for(m=0;m<100;m++){   	
    	    	lky=Integer.parseInt(lucky);
    	    	numbersCopy = Arrays.copyOf(luckynumbers, luckynumbers.length);
    	 
    	    	 result=new int[est];
    	      for (int i = 0; i < est; i++){
    	          int r = (int)(Math.random() * lky);    	             
    	          result[i] = numbersCopy[r];
    	          numbersCopy[r] = numbersCopy[lky - 1];
    	          lky--;          
    	       }
    	          Arrays.sort(result);
    	       AllPossiblities.add(result);   
    	        }
		
	}

	public static void getresult(){
		System.out.println("filtering recent results");
   	int[] drawFromPossible=new int[est];
    	int[] drawFromRecent=new int[est];
    	int x,y,count;
    	for(int i=0;i<AllPossiblities.size();i++){
    		drawFromPossible=new int[est];
    		drawFromPossible=(int[]) AllPossiblities.get(i);
    		
    		for(int j=0;j<AllRecentResults.size();j++){
    			count=0;
    			drawFromRecent=(int[]) AllRecentResults.get(j);    			
  			
    			for(x=0;x<est;x++){    				
    				for(y=0;y<est;y++){
    				if(drawFromPossible[x]==drawFromRecent[y])
    					count++;    				
    				}
    			}
    			
    			if(count>=2)  //>=2 IMP
    				AllPossiblities.remove(j);
    			}
    	}
    	    }  
    
    	public static void displayResult(ArrayList<Object> res){
    	int[] drawFromFinal=new int[est];
    	for(int a=0;a<res.size();a++){
    		drawFromFinal=new int[est];
    		drawFromFinal=(int[]) res.get(a);
    		Arrays.sort(drawFromFinal);
    		if(est==5)
    			 System.out.println(drawFromFinal[0]+" "+drawFromFinal[1]+" "+drawFromFinal[2]+" "+drawFromFinal[3]+" "+drawFromFinal[4]+"  Result: "+a);      
//  	          System.out.println(drawFromFinal[0]%10+" "+drawFromFinal[1]%10+" "+drawFromFinal[2]%10+" "+drawFromFinal[3]%10+" "+drawFromFinal[4]%10+"  Result: "+a);         
  	       else if(est==7)
  	    	   System.out.println(drawFromFinal[0]+" "+drawFromFinal[1]+" "+drawFromFinal[2]+" "+drawFromFinal[3]+" "+drawFromFinal[4]+" "+drawFromFinal[5]+" "+drawFromFinal[6]+"  Result: "+a); 
    	}
    }
    
    private static void filterResults() {
    	System.out.println("\n Filtering results EVEN-ODD\n");
    	int[] draw=new int[est];
    	int a;
    	for(a=0;a<AllPossiblities.size();a++){
    		int evenCount=0,oddCount=0;
//    		draw=new int[k];
    		draw=(int[]) AllPossiblities.get(a);
    		
    		for(int b=0;b<est;b++){
    			if((draw[b] % 2)==0)
    				evenCount++;
    			else oddCount++;
    		}
//    	if(evenCount<3 || evenCount>5 || oddCount<3 || oddCount>4)
    		if(oddCount<4 || oddCount>5)
    		AllPossiblities.remove(a);
    	}
	}

	private static void filterRepeatedNums() {
		System.out.println("filtering REPEATED nums");
		int[] draw1=new int[est];
		int[] draw2=new int[est];
		int x,y;
    	int count;
		for(int s=0;s<AllPossiblities.size()-1;s++){
			draw1=(int[])AllPossiblities.get(s);
			count=0;
			for(int p=1;p<AllPossiblities.size();p++){	
				draw2=(int[])AllPossiblities.get(p);
				 count=0;
				 if(s!=(p-1)){
			for(x=0;x<est;x++){				
				for(y=0;y<est;y++){
					if(draw1[x]==draw2[y]){
						count++;
					}
					
				}
				
			}
			 
			}
				 if(count>=3){  //>=3 IMP
						AllPossiblities.remove(p);
				 }
			
				
		}	
			}
		}
		
	

	public static int[] ReadLuckyNumFromXL() throws Exception {
              String filename = "luckynumbers.xls";
         int[] sheetData = new int[lky];
         FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            int i=0;
            while (rows.hasNext() ) {
                HSSFRow row = (HSSFRow) rows.next();
                Iterator<Cell> cells = row.cellIterator();
 
                               while (cells.hasNext()&& i<lky) {
                	Cell  cell=(HSSFCell) cells.next();
                	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                	
                	sheetData[i]=(int)cell.getNumericCellValue();
                	i++;
                }
 
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        Arrays.sort(sheetData);
 return sheetData;
            }
    
    
    public static ArrayList<Object> ReadRecentResultsFromXL() throws Exception {
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
             if(RecentResultsList.size()<=20){
RecentResultsList.add(sheetData);
             }
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
    
    
    public static void WriteFinalResultsToExcel(){
   
    	        // TODO Auto-generated method stub
    	        String FileName = "Final-Results.xls";

    	        try 
    	        {
    	            FileInputStream fileInputStream3 = new FileInputStream(FileName);
    	            File outputsheetfile1 = new File(FileName);
    	            if(outputsheetfile1.exists()) 
    	            {
    	                System.out.println("File existed");
    	                try
    	                {
    	                	HSSFWorkbook workbook = new HSSFWorkbook();
    	                    HSSFSheet sheet = workbook.createSheet("Course Pack Resolution Details");
    	                    FileName = outputsheetfile1.getAbsolutePath(); 
    	                    int rownum = 0;
    	                    for (int i = 0; i < FinalResults.size(); i++) {
    	                        int[] intArr = (int[]) FinalResults.get(i);
    	                        HSSFRow row = sheet.createRow(rownum++);

    	                        int cellnum = 0;
    	                        for (int intVal : intArr) {
    	                            Cell cell = row.createCell(cellnum++);
    	                            sheet.autoSizeColumn((short) cellnum);    	                            
    	                                cell.setCellValue(intVal);    	                           
    	                        }
    	                    }
    	                    if (outputsheetfile1.exists()) {
    	                        outputsheetfile1.delete();
    	                    }
    	                    FileOutputStream out =
    	                            new FileOutputStream(outputsheetfile1);
    	                    workbook.write(out);
    	                     fileInputStream3.close();
    	                    }

    	                 
    	            catch (IOException e) 
    	            {
    	                // TODO Auto-generated catch block
    	                e.printStackTrace();
    	            }

    	            }
    	        } catch (FileNotFoundException e) {
    	            // TODO Auto-generated catch block
    	            e.printStackTrace();
    	        }
    	       
    }
    
 }
 
 