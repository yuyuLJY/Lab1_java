package P3;

import java.util.ArrayList;

/**
 * 要求
 * 1)名字是唯一的，如果重复添加，则提示出错并且退出
 * 2)写清楚每个函数的注释
 * 3)关系的建立是双向的,比如A和B，B和A，只有一边关系，求不出实际距离（或者为0）
 * 
 * 提示：
 * 1)计算两个人的距离：用到广度搜索
 * 
 * 函数：
 * 1)addVertex(Person a) //添加进人物列表
 * 2)addEdge(Person a,Person b)//添加边
 * 3)getDistance(Person a,Person b) //计算两个人的距离
 * */
public class FriendshipGraph {
	ArrayList<Person> vertex = new  ArrayList<>();//存放顶点的String数组
	int N=20;
	int[][] edges = new int[N][N];//邻接矩阵，用来存储边
	int count;//计数边长
	int flag=0;
	/**
	 * 添加进人物列arraylist列表
	 * 并且判断这个名字是否已经被包含，如果包含直接退出*/
	public void addVertex(Person a) {
		if(vertex.contains(a)) {
			System.out.println("重复添加！");
			System.exit(0);
		}
		vertex.add(a);
	}
	/**
	 * 求出人物的顶点坐标，把他们当做是矩阵的坐标
	 * 如果建立出关系，那么对应的edges[i][j]=1
	 * */
	public void addEdge(Person a,Person b){
		int i = vertex.indexOf(a);//求出这个人“标号”对应的数字
		int j = vertex.indexOf(b);
		edges[i][j]=1;//把能连接的赋值为1
	}
	//计算两个人的距离
	public int getDistance(Person a,Person b) {
		//int distance=0;
		int i = vertex.indexOf(a);//求出这个人“标号”对应的数字
		int j = vertex.indexOf(b);
		//只有满足条件才进行长度计算
		count=0;
		flag=0;
		int[] visit1 = new int[vertex.size()];//没有必要再给visit赋值等于0，因为已经自动做了
		int k =DFS(edges,i,j,visit1);//深度优先搜索，找出两个点之间的距离
		return k;
	}
	
	//深度优先搜索
	public int DFS(int[][] edges,int i,int j,int[] visit) {
		if(i==j) {
			return 0;
		}
		//System.out.println("i:"+i+"j:"+j);
		visit[i]=1;//表示这个标号被访问过
		for(int k=0;k<vertex.size();k++) {
			if(edges[i][k]==1 && visit[k]==0) {
				count++;
				if(k==j) {			
					//直接退出循环，返回
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
		graph.addVertex(ben); //重复添加直接退出
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
