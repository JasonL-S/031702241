import java.util.*;
import java.io.*;

public class Sudoku {
	public static int rank=3;		
	public static String inputFileName;	
	public static String outputFileName;
	public static int m,n;
	public static int k=12;
	public static int shudu[][]=new int[k][k];

	 public static int square(int a,int b)		
	 {
		 int q=0;
		 if (rank==4)
			 q=(a-1)/2*2+(b-1)/2+1;
		 else if (rank==9)
			 q=(a-1)/3*3+(b-1)/3+1;
		 else if (rank==6)
		 {
			 if (a<=2&&b<=3) q=1;
			 else if (a<=2&&b>=4) q=2;
			 else if (a>=3&&a<=4&&b<=3) q=3;
			 else if (a>=3&&a<=4&&b>=4) q=4;
			 else if (a>=5&&b<=3) q=5;
			 else if (a>=5&&b>=4) q=6;
		 }  
		 else if(rank==8)
		 {
			 if (a<=4&&b<=2) q=1;
			 else if (a<=4&&b>=3&&b<=4) q=2;
			 else if (a<=4&&b>=5&&b<=6) q=3;
			 else if (a<=4&&b>=7) q=4;
			 else if (a>=5&&b<=2) q=5;
			 else if (a>=5&&b>=3&&b<=4) q=6;
			 else if (a>=5&&b>=5&&b<=6) q=7;
			 else if (a>=5&&b>=7) q=8;
		 }
		return q;
	 }
	
	 public static boolean dfs(int x,int y,int[][]shudu,int[][]line,int[][]row,int[][]square)		 
	 {
		 if(x==rank&&y==rank)					
			 return true;
		 int flag=0;
		 for(;x<=rank;x++)						
		 {
			 flag=0;
			 for(;y<=rank;y++)
				 if (shudu[x][y]==0)
				 {
					 flag=1;
					 break;
				 }
			 if(flag==1) 
				 break;
			 else y=1;
		 }										
	
		int t=0;
		 for (int s=1;s<=rank;s++)									
		 {	
			 t=square(x,y);											
			 if(rank==3||rank==5||rank==7) square[t][s]=0;
			 if(line[x][s]==0 && row[y][s]==0 && square[t][s]==0)	
			 {
				 line[x][s]=1;										
				 row [y][s]=1;
				 shudu[x][y]=s;
				 if(rank!=3||rank!=5||rank!=7)						
					 square[t][s]=1;			
				 
				 if(dfs(x,y,shudu,line,row,square)==true)									
					 return true;
				 
				 line[x][s]=0;										
				 row [y][s]=0;	
				 shudu[x][y]=0;
				 if(rank!=3||rank!=5||rank!=7)
					 square[t][s]=0;
			 }
		 }
		 return false;
	 }
	
	 
	 static void loadArgs (String[] args)
		{	
			if(args.length > 0 && args != null) {
				
				for (int i=0;i<args.length;i++) {
				switch(args[i]) {
				case"-i":
					inputFileName = args[++i];
					break;
				case"-o":
					outputFileName = args[++i];
					break;
				case"-m":
					m = Integer.valueOf(args[++i]);
					break;
				case"-n":
					n = Integer.valueOf(args[++i]);
					break;
				default:
					break;
				}
				}
			}
		}
	 	
	 public static void main(String [] args) {			 
		loadArgs(args);
		File src = new File(inputFileName);
		File dest = new File(outputFileName);
		rank = m;
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(src));
			writer = new BufferedWriter(new FileWriter(dest));
			String linenum  = null;
			int p=1,q=1,r=0;
			while((linenum=reader.readLine())!=null) {
				if(!linenum.equals(""))
				{	
					String[] inputstring = linenum.split("  ");
					for(q=1;q<=m;q++)
					{
						shudu[p][q]=Integer.parseInt(inputstring[q-1]);
					}
					p++;r++;
				}
				
				if(r==m) {	
				int [][] line   = new  int[k][k];
				int [][] row    = new  int[k][k];
				int [][] square = new  int[k][k];
				int t=0;int key=0;
					for (int i=1;i<=rank;i++)
						for(int j=1;j<=rank;j++)
						{	key=shudu[i][j];
							line[i][key]=1;						
							row[j][key]=1;		
							if(rank!=3||rank!=5||rank!=7)
							{
								t=square(i,j);
								square[t][key]=1;
							}
						}
		
				dfs(1, 1,shudu,line,row,square);
				for (int i=1;i<=rank;i++)
				{				
					for(int j=1;j<=rank;j++)
					{
						writer.write(shudu[i][j]+" ");	 
					}
						writer.write("\r\n");		 			
				}
				writer.write("\r\n");
				p=1;r=0;
				}
		}	
			writer.flush();
			reader.close();
			writer.close();
	 }catch(IOException r) {
		 r.printStackTrace();
	 }
}
}
	 
	
