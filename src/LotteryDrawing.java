 import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

 public class LotteryDrawing
{
	static ArrayList<Object> AllPossiblities=new ArrayList<>();
     static ArrayList<Object> AllRecentResults=new ArrayList<>();
     static ArrayList<Object> FinalResults=new ArrayList<>();
     static int k;
     static int res=0;
    public static void main(String[] args)
    {
    String input = JOptionPane.showInputDialog
          ("How many numbers do you need to draw?");
      k = Integer.parseInt(input);

     String  input1 = JOptionPane.showInputDialog
          ("What is the highest number you can draw?");
       int n = Integer.parseInt(input1);
       
       String lucky=JOptionPane.showInputDialog("how many ucky numbers you have???");
	 	int lky=Integer.parseInt(lucky);
       
       int[] luckynumbers = new int[lky];
       int[] numbersCopy=new int[lky];
       int[] recentdraw=new int[k];
       int[] result; 
       int m=0;

       
      
      for (int d = 0; d < lky; d++){
   	 String luckynum=	JOptionPane.showInputDialog("Enter your "+(d+1)+"Lukcky number of "+lky);
  	 	luckynumbers[d]=Integer.parseInt(luckynum);
   	 	

      }

       
      String input3 = JOptionPane.showInputDialog
    	          ("enter no. of recent draws");
    	       int draws = Integer.parseInt(input3);
    	       
       
       for(int z=0;z<draws;z++){
       for(int y=0;y<recentdraw.length;y++){
    	   String input4 = JOptionPane.showInputDialog
    		          ("enter recent draw result number "+(y+1)+"for draw "+(z+1));
    	   recentdraw[y]=Integer.parseInt(input4);
       }
       AllRecentResults.add(recentdraw);
       }
          
       
      
       
      for(m=0;m<1000;m++){   	
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
//       else if(k==5)
//    	   System.out.println(result[0]+" "+result[1]+" "+result[2]+" "+result[3]+" "+result[4]+"  possible: "+res);         
      
       else
    	   System.out.println("Please check no. of numbers to be selected");
    	   
    	   res++;
      }
            getresult();
            displayResult();
          System.exit(0);
    }
    
    public static void getresult(){
   	int[] drawFromPossible=new int[k];
    	int[] drawFromRecent=new int[k];
    	for(int i=0;i<AllPossiblities.size();i++){
    		drawFromPossible=(int[]) AllPossiblities.get(i);
    		int count=0;
    		for(int j=0;j<AllRecentResults.size();j++){
    			drawFromRecent=(int[]) AllRecentResults.get(j);
    			
    			
    			for(int x=0;x<k;x++){
    				for(int y=0;y<k;y++){
    				if(drawFromPossible[x]==drawFromRecent[y])	
    					count++;
    				}
    			}
    			
    			}
    		if(count<2)
				FinalResults.add(drawFromPossible);
    	}
    	    }  
    
    public static void displayResult(){
    	System.out.println("\n The Final Results are: \n");
    	int[] drawFromFinal=new int[k];
    	for(int a=0;a<FinalResults.size();a++){
    		drawFromFinal=new int[k];
    		drawFromFinal=(int[]) FinalResults.get(a);
    		
    		 System.out.println(drawFromFinal[0]+" "+drawFromFinal[1]+" "+drawFromFinal[2]+" "+drawFromFinal[3]+" "+drawFromFinal[4]+" "+drawFromFinal[5]+"  Result: "+a); 
    	}
    }   
    
 }
 
 