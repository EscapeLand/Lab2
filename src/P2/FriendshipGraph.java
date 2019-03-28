import graph.*;

import java.util.*;

public class FriendshipGraph{
    private Graph<Person> graph = Graph.empty();
    private List<Person> visit = new ArrayList<>();
    
    public boolean addVertex(Person v){
    	Set<Person> s = graph.vertices();
    	for(Person i: s) if(i.name.equals(v.name)) return false;
    	
        return graph.add(v);
    }

    public boolean addEdge(Person a, Person b){
	    Set<Person> s = graph.vertices();
		if(s.contains(a) && s.contains(b))
	        return graph.set(a, b, 1) == 0;
		else return false;
    }

    public int getDistance(Person a, Person b){
	    if(a.equals(b)) return 0;
	    int n = graph.vertices().size();
	    visit.add(a);
        int min = n + 1;
        Map<Person, Integer> t = graph.targets(a);
        for(Map.Entry<Person, Integer> i: t.entrySet()){
        	if(visit.contains(i.getKey())) continue;
            int tmp = getDistance(i.getKey(), b);
            if(min == -1) min = tmp;
            else if(tmp < min) min = tmp;
        }
        visit.remove(a);
        return min > n || min == -1? -1: min + 1;
    }
}