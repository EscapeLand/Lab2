
import java.util.*;
import graph.*;




public class FriendshipGraph {
	
	private Graph<Person> g=Graph.empty();
	//private List<Person> visit = new ArrayList<Person>();
	int i=0;
	public boolean addVertex(Person name)
	{
		if(g.vertices().contains(name))
		{
			return false;
		}
		else
		{
			g.add(name);
			name.setNum(i++);
			
		}
		return true;
		
	}
	
	public boolean addEdge(Person name1, Person name2)
	{
		if(g.vertices().contains(name1) &&g.vertices().contains(name2))  
			{
				g.set(name1, name2, 1);
				return true;
			}
		
		return false;
	}
	
	public  int getDistance(Person A,Person B)
	{
		
		Queue<Integer> queue = new LinkedList<Integer>();
		if(!g.vertices().contains(A) && !g.vertices().contains(B))
		{
			
			return -1;
		}
		if(A.getName().equals(B.getName()))
		{
			return 0;
		}
		int length=g.vertices().size();
		int level[]=new int[length];
		int i=A.getNum();
		int j;
		int distance=1;
		boolean[] visit =new boolean[length];
		visit[i]=true;level[i]=0;queue.add(i);
		
		while(!queue.isEmpty())
		{
			i=queue.remove().intValue();
			for(j=0;j<length;j++)
			{
				if(g.targets(getPerson(i)).containsKey(getPerson(j))  && !visit[j])
				{
					if(j==B.getNum())
					{
						Arrays.parallelSort(level);
                        distance = level[length - 1] + 1;
                        //checkRep();
                        return distance;
					}
					else
					{
						 queue.add(j);
	                     level[j] = level[i] + 1;
	                     visit[j] = true;
					}
				}
			}
		}
		
		return -1;
		
		
	}
	private Person getPerson(int m) {
        for (Person p : g.vertices()) {
            if (m == p.getNum()) {
                return p;
            }
        }
        return new Person(null);
    }
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		 FriendshipGraph graph = new FriendshipGraph();
	        Person rachel = new Person("Rachel");
	        Person ross = new Person("Ross");
	        Person ben = new Person("Ben");
	        Person kramer = new Person("Kramer");
	        graph.addVertex(rachel);
	        graph.addVertex(ross);
	        graph.addVertex(ben);
	        graph.addVertex(kramer);
	        graph.addEdge(rachel, ross);
	        graph.addEdge(ross, rachel);
	        graph.addEdge(ross, ben);
	        graph.addEdge(ben, ross);
	        System.out.println(graph.getDistance(rachel, ross));
	        // should print 1
	        System.out.println(graph.getDistance(rachel, ben));
	        // should print 2
	        System.out.println(graph.getDistance(rachel, rachel));
	        // should print 0
	        System.out.println(graph.getDistance(rachel, kramer));
	        // should print -1
		
		
	}
}