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
     static int k;
     static int res=0;
     static int lky;
     static int[] luckynumbers = new int[lky];
     static int[] numbersCopy=new int[lky];
// static    int[] recentdraw=new int[k];
     static int[] result; 
     static int m=0;  
     static String lucky;
    public static void main(String[] args) throws Exception
    {
    String input = JOptionPane.showInputDialog
          ("How many numbers do you need to draw?");
      k = Integer.parseInt(input);

//     String  input1 = JOptionPane.showInputDialog
//          ("What is the highest number you can draw?");
//       int n = Integer.parseInt(input1);
       
       lucky=JOptionPane.showInputDialog("how many lucky numbers you have???");
	 	 lky=Integer.parseInt(lucky);     
           
      
//      for (int d = 0; d < lky; d++){
//   	 String luckynum=	JOptionPane.showInputDialog("Enter your "+(d+1)+"Lukcky number of "+lky);
//  	 	luckynumbers[d]=Integer.parseInt(luckynum);
//   	 	
//
//      }
       luckynumbers=ReadLuckyNumFromXL();

       
//      String input3 = JOptionPane.showInputDialog
//    	          ("enter no. of recent draws");
//    	       int draws = Integer.parseInt(input3);
    	AllRecentResults=ReadRecentResultsFromXL();
       
//       for(int z=0;z<draws;z++){
//       for(int y=0;y<recentdraw.length;y++){
//    	   String input4 = JOptionPane.showInputDialog
//    		          ("enter recent draw result number "+(y+1)+"for draw "+(z+1));
//    	   recentdraw[y]=Integer.parseInt(input4);
//       }
//       AllRecentResults.add(recentdraw);
//       }
                 
     getAllPossibles();
            getresult();
            displayResult();
//            WriteFinalResultsToExcel();
          System.exit(0);
    }
    
    private static void getAllPossibles() {
    	 for(m=0;m<300;m++){   	
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
    	       if(k==6)
    	          System.out.println(result[0]+" "+result[1]+" "+result[2]+" "+result[3]+" "+result[4]+" "+result[5]+"  possible: "+res);         
//    	       else if(k==5)
//    	    	   System.out.println(result[0]+" "+result[1]+" "+result[2]+" "+result[3]+" "+result[4]+"  possible: "+res);         
    	      
    	       else
    	    	   System.out.println("Please check no. of numbers to be selected");    	   
    	    	   res++;
    	      }
		
	}

	public static void getresult(){
   	int[] drawFromPossible=new int[k];
    	int[] drawFromRecent=new int[k];
    	int x,y;
    	int xTest,yTest;
    	for(int i=0;i<AllPossiblities.size();i++){
    		drawFromPossible=(int[]) AllPossiblities.get(i);
    		int count=0;
    		for(int j=0;j<AllRecentResults.size();j++){
    			drawFromRecent=(int[]) AllRecentResults.get(j);    			
  			 count=0;
    			for(x=0;x<k;x++){
    				
    				for(y=0;y<k;y++){
    					xTest=drawFromPossible[x];
    					yTest=drawFromRecent[y];
    				if(drawFromPossible[x]==drawFromRecent[y])	
    					count++;
    				}
    				
    			}
    			
    			}
    		
    		if(count==1)
				FinalResults.add(drawFromPossible);
			drawFromPossible=new int[k];
    	}
    	    }  
    
    public static void displayResult(){
    	System.out.println("\n The Final Results are: \n");
    	int[] drawFromFinal=new int[k];
    	for(int a=0;a<FinalResults.size();a++){
    		drawFromFinal=new int[k];
    		drawFromFinal=(int[]) FinalResults.get(a);
    		
    		 System.out.println(drawFromFinal[0]+" "+drawFromFinal[1]+" "+drawFromFinal[2]+" "+drawFromFinal[3]+" "+drawFromFinal[4]+" "+drawFromFinal[5]+" "+drawFromFinal[6]+"  Result: "+a); 
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
 
 