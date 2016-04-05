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
     static int k;
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
      k = Integer.parseInt(input);
      lucky=JOptionPane.showInputDialog("how many LUCKY numbers you have???");
	 	 lky=Integer.parseInt(lucky);  
	 	 
	 	 exclStr=JOptionPane.showInputDialog("how many EXCLUDE numbers you have???");
	 	 excl=Integer.parseInt(exclStr);  
	 	excludenumbers = new int[excl];
           
      //EXCLUDE NUMS
	 	int d;
      for (d=0;d<excl;d++){
   	 String exclnum=	JOptionPane.showInputDialog("Enter your "+(d+1)+"EXCLUDE number of "+excl);
  	 	excludenumbers[d]=Integer.parseInt(exclnum);
  	 	}
      
       luckynumbers=ReadLuckyNumFromXL();
             getAllPossibles();
             printAllPossibleResults();
             
             AllRecentResults=ReadRecentResultsFromXL();
             printRecentResults();
             
            getresult();
          printResults();
//            filterResults();   //temp
//            filterResults(); 
//            filterResults(); 
           
//        	filterRepeatedNums();
//        	displayResult(FilteredResults);
//        	filterRepeatedNums();
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
        	int[] draw=new int[k];
    	  	for(int s=0;s<AllPossiblities.size();s++){
			draw=(int[])AllPossiblities.get(s);
			count=0;
			for(x=0;x<k;x++){				
				for(y=0;y<excl;y++){
					if(draw[x]==excludenumbers[y]){
						count++;
					}
					
				}
    	}
			if(count>=2)
//				FilteredResults.add(s);
				AllPossiblities.remove(s);
    	  	}
		
	}

	private static void getAllPossibles() {
    	 for(m=0;m<100;m++){   	
    	    	lky=Integer.parseInt(lucky);
    	    	numbersCopy = Arrays.copyOf(luckynumbers, luckynumbers.length);
    	 
    	    	 result=new int[k];
    	      for (int i = 0; i < k; i++){
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
   	int[] drawFromPossible=new int[k];
    	int[] drawFromRecent=new int[k];
    	int x,y,count;
    	for(int i=0;i<AllPossiblities.size();i++){
    		drawFromPossible=new int[k];
    		drawFromPossible=(int[]) AllPossiblities.get(i);
    		
    		for(int j=0;j<AllRecentResults.size();j++){
    			count=0;
    			drawFromRecent=(int[]) AllRecentResults.get(j);    			
  			
    			for(x=0;x<k;x++){    				
    				for(y=0;y<k;y++){
    				if(drawFromPossible[x]==drawFromRecent[y])
    					count++;    				
    				}
    			}
    			
//    			if(count>1 && count<3 && !FinalResults.contains(drawFromPossible))
//    				FinalResults.add(drawFromPossible);
    			if(count>=3)
    				AllPossiblities.remove(j);
    			}
    	}
    	    }  
    
    	public static void displayResult(ArrayList<Object> res){
    	int[] drawFromFinal=new int[k];
    	for(int a=0;a<res.size();a++){
    		drawFromFinal=new int[k];
    		drawFromFinal=(int[]) res.get(a);
    		if(k==6)
  	          System.out.println(drawFromFinal[0]+" "+drawFromFinal[1]+" "+drawFromFinal[2]+" "+drawFromFinal[3]+" "+drawFromFinal[4]+" "+drawFromFinal[5]+"  Result: "+a);         
  	       else if(k==7)
  	    	   System.out.println(drawFromFinal[0]+" "+drawFromFinal[1]+" "+drawFromFinal[2]+" "+drawFromFinal[3]+" "+drawFromFinal[4]+" "+drawFromFinal[5]+" "+drawFromFinal[6]+"  Result: "+a); 
    	}
    }
    
    private static void filterResults() {
    	System.out.println("\n Filtering results EVEN-ODD\n");
    	int[] draw=new int[k];
    	int a;
    	for(a=0;a<AllPossiblities.size();a++){
    		int evenCount=0,oddCount=0;
//    		draw=new int[k];
    		draw=(int[]) AllPossiblities.get(a);
    		
    		for(int b=0;b<k;b++){
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
		int[] draw1=new int[k];
		int[] draw2=new int[k];
		int x,y;
    	int count;
		for(int s=0;s<AllPossiblities.size()-1;s++){
			draw1=(int[])AllPossiblities.get(s);
			count=0;
			for(int p=1;p<AllPossiblities.size();p++){	
				draw2=(int[])AllPossiblities.get(p);
				 count=0;
				 if(s!=(p-1)){
			for(x=0;x<k;x++){				
				for(y=0;y<k;y++){
					if(draw1[x]==draw2[y]){
						count++;
					}
					
				}
				
			}
			 
			}
//				 if(count<=2 && !FilteredResults.contains(draw1)){
				 if(count>=4){
//						FilteredResults.add(draw1);
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
    	  sheetData=new int[k];
          HSSFRow row = (HSSFRow) rows.next();
          Iterator<Cell> cells = row.cellIterator();
           int c=0;
           
             while(cells.hasNext() && c<k ) {
          	Cell  cell=(HSSFCell) cells.next();
          	cell.setCellType(Cell.CELL_TYPE_NUMERIC);          	
          	sheetData[c]=(int)cell.getNumericCellValue();
          	c++;
          	          }
             Arrays.sort(sheetData);
             if(RecentResultsList.size()<=4){
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
 
 