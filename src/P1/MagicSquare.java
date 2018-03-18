package P1;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**实验实现步骤
 * 1) main()函数：调用五次 isLegalMagicSquare()函数
 * 2)isLegalMagicSquare()函数:读入文件信息，判断是否满足要求
 * @author yuyu
 */

public class MagicSquare {	
public static void main(String[] args) throws IOException {
	 MagicSquare t =new  MagicSquare();
	 boolean answer;
	 for(int i=1;i<=5;i++) {
		 String fileName="src/P1/txt/"+String.valueOf(i)+".txt";//一次访问五个文件
		 answer=t.isLegalMagicSquare(fileName);
		 System.out.println(answer);
		 
	 }
	int n=5;
	boolean a =t.generateMagicSquare(n);
	System.out.println("第六个文件判断结果"+a);
}

//判断是否满足条件的函数
public boolean isLegalMagicSquare(String fileName) throws IOException {
	//用分隔符号“\t”读成字符数组，再转化成数字
	 File file = new File(fileName);
	 int length=0,number=0;
	 int initLength=0;
     BufferedReader reader = null;
     ArrayList<String[]> list= new ArrayList();//有未知个学生
     try {
         reader = new BufferedReader(new FileReader(file));
         String tempString = null;
         while ((tempString = reader.readLine()) != null) {
             String[] a= tempString.split("\t");//用“,”分隔开
             initLength=a.length;//计算每行的出标准个数
             length+=a.length;
             number++;//计算列的个数
             if(length!=initLength*number) {
            	 return false;//行个数不相等，直接返回false
             }
             list.add(a);
             //System.out.println(list); 
         }
         reader.close();
         //只有每行的个数都相等，才进入“行！=列”判断
         if(number!=initLength) {
        	 return false;//行列不相等，直接返回false
         }
         //完全符合的开始计算
         String[][] test = (String [][])list.toArray(new String[0][0]);
         //ArrayList<int[]> relist= new ArrayList();//有未知个学生
         //int[][] retest = (int [][])relist.toArray(new int[0][0]);
         //把String转换成int，并且判断是不是正整数
         int[][] retest = new int [initLength][initLength];
         for(int i=0;i<initLength;i++) {
        	 for(int j=0;j<initLength;j++) {
        		//判断是不是整数,不足1.00会被判成小数
        			if(test[i][j].indexOf(".")>-1) {
        				//System.out.println("小数");
        				return false;
        			}
        			//把String转换成int
        			retest[i][j]=Integer.parseInt(test[i][j]);
        			if(retest[i][j]<=0) {
        				return false;
        			}
        	 }
         }//处理完毕数字，只剩检验最后一个条件：是否满足相加相等
         //提取出标准相加行
         int initsum=0,sum=0;
         //判定右对角线
         for(int i=0;i<initLength;i++) {
        	 initsum+=retest[i][i];
         }
         //System.out.println(initsum);标准和计算正确
         //判定左对角线行
         for(int i=0;i<initLength;i++) {
        	 	 sum+=retest[i][initLength-1-i];//检验每一行相加
         }
         if(sum!=initsum) {
			 return false;//每行的值不相等
		 }
         sum=0;
         //判断每行的值是否相等
         for(int i=0;i<initLength;i++) {
        	 for(int j=0;j<initLength;j++) {
        		 sum+=retest[i][j];//检验每一行相加
        	 }
        	 if(sum!=initsum*(i+1)) {
    			 return false;//每行的值不相等
    		 }
         }
         sum=0;
         //判定每列
         for(int i=0;i<initLength;i++) {
        	 for(int j=0;j<initLength;j++) {
        		 sum+=retest[j][i];//检验每一行相加
        	 }
        	 if(sum!=initsum*(i+1)) {
    			 return false;//每行的值不相等
    		 }
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
     return true;//完全正确返回
}

//
public boolean generateMagicSquare(int n) throws EOFException{
	//抛出异常
	try{
		int magic[][] = new int[n][n];
		 int row = 0,col =n/2,i,j,square = n*n;
		 for(i=1;i<=square;i++) {
			 magic[row][col]=i;
			 if(i%n==0)
				 row++;
			 else {
				 if(row==0)
					 row = n-1;
				 else 
					 row--;
				 if(col==(n-1))
					 col=0;
				 else
					 col++;
			 }
		 }
		 //输出
		 for(i=0;i<n;i++) {
			 for(j=0;j<n;j++)
				 System.out.print(magic[i][j]+"\t");
			 System.out.println();
		 }
		 //写进文档
		 FileWriter fw = null;
		 String fileName="src/P1/txt/"+String.valueOf(6)+".txt";
	  		try {
	  		//如果文件存在，则追加内容；如果文件不存在，则创建文件
	  		File f=new File(fileName);
	  		fw = new FileWriter(f, true);
	  		} catch (IOException e) {
	  		e.printStackTrace();
	  		}
	  		PrintWriter pw = new PrintWriter(fw);
	  		for(i=0;i<n;i++) {
				 for(j=0;j<n;j++) {
					 pw.print(String.format("%d\t",magic[i][j]));
				 }
				 pw.println(String.format("")); 
			 }
	  		pw.flush();
	  		try {
	  		fw.flush();
	  		pw.close();
	  		fw.close();
	  		} catch (IOException e) {
	  		e.printStackTrace();
	  		}
	  		//判断是否满足square矩阵条件
	  		MagicSquare t =new  MagicSquare();
	  		boolean answer=t.isLegalMagicSquare(fileName);
	  		//System.out.println("判断结果"+answer);
	}catch(Exception e) {
		System.out.println("错误");
		return false;
	}
	return true;
}

}