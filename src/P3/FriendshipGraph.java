package P3;

import java.util.ArrayList;

/**
 * Ҫ��
 * 1)������Ψһ�ģ�����ظ���ӣ�����ʾ�������˳�
 * 2)д���ÿ��������ע��
 * 3)��ϵ�Ľ�����˫���,����A��B��B��A��ֻ��һ�߹�ϵ���󲻳�ʵ�ʾ��루����Ϊ0��
 * 
 * ��ʾ��
 * 1)���������˵ľ��룺�õ��������
 * 
 * ������
 * 1)addVertex(Person a) //��ӽ������б�
 * 2)addEdge(Person a,Person b)//��ӱ�
 * 3)getDistance(Person a,Person b) //���������˵ľ���
 * */
public class FriendshipGraph {
	ArrayList<Person> vertex = new  ArrayList<>();//��Ŷ����String����
	int N=20;
	int[][] edges = new int[N][N];//�ڽӾ��������洢��
	int count;//�����߳�
	int flag=0;
	/**
	 * ��ӽ�������arraylist�б�
	 * �����ж���������Ƿ��Ѿ����������������ֱ���˳�*/
	public void addVertex(Person a) {
		if(vertex.contains(a)) {
			System.out.println("�ظ���ӣ�");
			System.exit(0);
		}
		vertex.add(a);
	}
	/**
	 * �������Ķ������꣬�����ǵ����Ǿ��������
	 * �����������ϵ����ô��Ӧ��edges[i][j]=1
	 * */
	public void addEdge(Person a,Person b){
		int i = vertex.indexOf(a);//�������ˡ���š���Ӧ������
		int j = vertex.indexOf(b);
		edges[i][j]=1;//�������ӵĸ�ֵΪ1
	}
	//���������˵ľ���
	public int getDistance(Person a,Person b) {
		//int distance=0;
		int i = vertex.indexOf(a);//�������ˡ���š���Ӧ������
		int j = vertex.indexOf(b);
		//ֻ�����������Ž��г��ȼ���
		count=0;
		flag=0;
		int[] visit1 = new int[vertex.size()];//û�б�Ҫ�ٸ�visit��ֵ����0����Ϊ�Ѿ��Զ�����
		int k =DFS(edges,i,j,visit1);//��������������ҳ�������֮��ľ���
		return k;
	}
	
	//�����������
	public int DFS(int[][] edges,int i,int j,int[] visit) {
		if(i==j) {
			return 0;
		}
		//System.out.println("i:"+i+"j:"+j);
		visit[i]=1;//��ʾ�����ű����ʹ�
		for(int k=0;k<vertex.size();k++) {
			if(edges[i][k]==1 && visit[k]==0) {
				count++;
				if(k==j) {			
					//ֱ���˳�ѭ��������
					flag=1;
					return count;
				}
				count=DFS(edges,k,j,visit);
			}
		}
		if(flag==0) {
			count=-1;
		}
		return count;
	}
	
	public static void main(String args[]) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross"); 
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(ben); //�ظ����ֱ���˳�
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel,ross));
		System.out.println(graph.getDistance(rachel,ben));
		System.out.println(graph.getDistance(rachel,rachel));
		System.out.println(graph.getDistance(rachel,kramer));
	}
}
