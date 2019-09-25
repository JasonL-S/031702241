import java.util.*;
import java.io.*;

public class Sudoku {
	public static int rank=3;		//�����Ľ�
	public static String inputFileName;	//CMD����
	public static String outputFileName;
	public static int m,n;
	public static int k=12;
	
	/*
	public static void scanf ()					//���뷽��
	{
		int t=0;
	//	Scanner read = new Scanner (System.in);
		for (int i=1;i<=rank;i++)
			for(int j=1;j<=rank;j++)
			{
				//shudu[i][j]=read.nextInt();
				
				line[i][shudu[i][j]]=1;						//�С��С�������
				row[j][shudu[i][j]]=1;
			
				if(rank!=3||rank!=5||rank!=7)
				{
					t=square(i,j);
					square[t][shudu[i][j]]=1;
				}
			}
	}
*/
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
	
	 public static boolean dfs(int x,int y,int[][]shudu,int[][]line,int[][]row,int[][]square)		//���� �����������+����
	 {
		 if(x==rank&&y==rank)					//���һ������ ����
			 return true;
		 int flag=0;
		 for(;x<=rank;x++)						//�ж��Ƿ�Ϊ�����
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
		 }										//�жϽ���
	
		int t=0;
		 for (int s=1;s<=rank;s++)									//ѭ��9������ ��һ�ų� s=��ǰɸѡ������
		 {	
			 t=square(x,y);											//t=����
			 if(rank==3||rank==5||rank==7) square[t][s]=0;
			 if(line[x][s]==0 && row[y][s]==0 && square[t][s]==0)	//�С��С����Ƿ���Ϲ���
			 {
				 line[x][s]=1;										//�С��С������
				 row [y][s]=1;
				 shudu[x][y]=s;
				 if(rank!=3||rank!=5||rank!=7)						//3��5��7�ײ���Ҫ�жϹ�
					 square[t][s]=1;			
				 
				 if(dfs(x,y,shudu,line,row,square)==true)									//DFSǶ��  =false����
					 return true;
				 
				 line[x][s]=0;										//�С��С���ȡ�����
				 row [y][s]=0;	
				 shudu[x][y]=0;
				 if(rank!=3||rank!=5||rank!=7)
					 square[t][s]=0;
			 }
		 }
		 return false;
	 }
	
	/* public static void print ()					//�������
	{
		for (int i=1;i<=rank;i++)
		{				
			for(int j=1;j<=rank;j++)
			{
				System.out.print(shudu[i][j]+" ");	
			}
				System.out.println();				//����
		}
		System.out.println();
	}
	*/
	 
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
			/*else {
				System.out.println("δ�������");
				System.exit(1);
			}*/
		}
	 
	 
	 
	 
	 
	 public static void main(String [] args) {			 
		loadArgs(args);
		File src = new File("E:/Java/project/031702241/src/input.txt");
		rank = m;
		int shudu[][]=new int[k][k];
		 
		try {
			FileReader file = new FileReader(src);
			BufferedReader buff = new BufferedReader(new FileReader(src));
			PrintWriter out = new PrintWriter ("E:/Java/project/031702241/src/output1.txt");
			String linenum  = null;
			
			int p=0,q=0,r=0;
			while((linenum=buff.readLine())!=null) {
				if(!linenum.equals(""))
				{
					
					String[] inputstring = linenum.split("");
					for(q=0;q<m;q++)
						shudu[p][q]=Integer.parseInt(inputstring[q]);
					p++;r++;
				}
				
				if(r==m) {
				//sudoku.scanf();
				int [][] line   = new  int[k][k];
				int [][] row    = new  int[k][k];
				int [][] square = new  int[k][k];
				
				int t=0;
					for (int i=1;i<=rank;i++)
						for(int j=1;j<=rank;j++)
						{
							line[i][shudu[i][j]]=1;						//�С��С�������
							row[j][shudu[i][j]]=1;
						
							if(rank!=3||rank!=5||rank!=7)
							{
								t=square(i,j);
								square[t][shudu[i][j]]=1;
							}
						}
				
				
				dfs(1, 1,shudu,line,row,square);
				//sudoku.print();
				for (int i=1;i<=rank;i++)
				{				
					for(int j=1;j<=rank;j++)
					{
						 out.print(shudu[i][j]+" ");	
					}
						 out.println();				
				}
				 out.println();
				
				p=0;r=0;
				}
			
		}	
		out.flush();
		file.close();
		buff.close();
		out.close();
		
	 }catch(IOException r) {
		 r.printStackTrace();
	 }
}

}
	 
	
