package P1;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**ʵ��ʵ�ֲ���
 * 1) main()������������� isLegalMagicSquare()����
 * 2)isLegalMagicSquare()����:�����ļ���Ϣ���ж��Ƿ�����Ҫ��
 * @author yuyu
 */

public class MagicSquare {	
public static void main(String[] args) throws IOException {
	 MagicSquare t =new  MagicSquare();
	 boolean answer;
	 for(int i=1;i<=5;i++) {
		 String fileName="src/P1/txt/"+String.valueOf(i)+".txt";//һ�η�������ļ�
		 answer=t.isLegalMagicSquare(fileName);
		 System.out.println(answer);
		 
	 }
	int n=5;
	boolean a =t.generateMagicSquare(n);
	System.out.println("�������ļ��жϽ��"+a);
}

//�ж��Ƿ����������ĺ���
public boolean isLegalMagicSquare(String fileName) throws IOException {
	//�÷ָ����š�\t�������ַ����飬��ת��������
	 File file = new File(fileName);
	 int length=0,number=0;
	 int initLength=0;
     BufferedReader reader = null;
     ArrayList<String[]> list= new ArrayList();//��δ֪��ѧ��
     try {
         reader = new BufferedReader(new FileReader(file));
         String tempString = null;
         while ((tempString = reader.readLine()) != null) {
             String[] a= tempString.split("\t");//�á�,���ָ���
             initLength=a.length;//����ÿ�еĳ���׼����
             length+=a.length;
             number++;//�����еĸ���
             if(length!=initLength*number) {
            	 return false;//�и�������ȣ�ֱ�ӷ���false
             }
             list.add(a);
             //System.out.println(list); 
         }
         reader.close();
         //ֻ��ÿ�еĸ�������ȣ��Ž��롰�У�=�С��ж�
         if(number!=initLength) {
        	 return false;//���в���ȣ�ֱ�ӷ���false
         }
         //��ȫ���ϵĿ�ʼ����
         String[][] test = (String [][])list.toArray(new String[0][0]);
         //ArrayList<int[]> relist= new ArrayList();//��δ֪��ѧ��
         //int[][] retest = (int [][])relist.toArray(new int[0][0]);
         //��Stringת����int�������ж��ǲ���������
         int[][] retest = new int [initLength][initLength];
         for(int i=0;i<initLength;i++) {
        	 for(int j=0;j<initLength;j++) {
        		//�ж��ǲ�������,����1.00�ᱻ�г�С��
        			if(test[i][j].indexOf(".")>-1) {
        				//System.out.println("С��");
        				return false;
        			}
        			//��Stringת����int
        			retest[i][j]=Integer.parseInt(test[i][j]);
        			if(retest[i][j]<=0) {
        				return false;
        			}
        	 }
         }//����������֣�ֻʣ�������һ���������Ƿ�����������
         //��ȡ����׼�����
         int initsum=0,sum=0;
         //�ж��ҶԽ���
         for(int i=0;i<initLength;i++) {
        	 initsum+=retest[i][i];
         }
         //System.out.println(initsum);��׼�ͼ�����ȷ
         //�ж���Խ�����
         for(int i=0;i<initLength;i++) {
        	 	 sum+=retest[i][initLength-1-i];//����ÿһ�����
         }
         if(sum!=initsum) {
			 return false;//ÿ�е�ֵ�����
		 }
         sum=0;
         //�ж�ÿ�е�ֵ�Ƿ����
         for(int i=0;i<initLength;i++) {
        	 for(int j=0;j<initLength;j++) {
        		 sum+=retest[i][j];//����ÿһ�����
        	 }
        	 if(sum!=initsum*(i+1)) {
    			 return false;//ÿ�е�ֵ�����
    		 }
         }
         sum=0;
         //�ж�ÿ��
         for(int i=0;i<initLength;i++) {
        	 for(int j=0;j<initLength;j++) {
        		 sum+=retest[j][i];//����ÿһ�����
        	 }
        	 if(sum!=initsum*(i+1)) {
    			 return false;//ÿ�е�ֵ�����
    		 }
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
     return true;//��ȫ��ȷ����
}

//
public boolean generateMagicSquare(int n) throws EOFException{
	//�׳��쳣
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
		 //���
		 for(i=0;i<n;i++) {
			 for(j=0;j<n;j++)
				 System.out.print(magic[i][j]+"\t");
			 System.out.println();
		 }
		 //д���ĵ�
		 FileWriter fw = null;
		 String fileName="src/P1/txt/"+String.valueOf(6)+".txt";
	  		try {
	  		//����ļ����ڣ���׷�����ݣ�����ļ������ڣ��򴴽��ļ�
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
	  		//�ж��Ƿ�����square��������
	  		MagicSquare t =new  MagicSquare();
	  		boolean answer=t.isLegalMagicSquare(fileName);
	  		//System.out.println("�жϽ��"+answer);
	}catch(Exception e) {
		System.out.println("����");
		return false;
	}
	return true;
}

}