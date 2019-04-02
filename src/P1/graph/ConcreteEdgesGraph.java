/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices) = {vertices[i] | 0 <= i < vertices.length()}
    //   AF(edges) = {edges[i] | 0 <= i < edges.length()}
    //   AF(edges) = {edges[i] | edges[i].source,edges[i].target must in vertices}
    // Representation invariant:
    //   edges contains no repeated edge
    //   edges' points must in vertices
    // Safety from rep exposure:
    //   All fields are private final
    
    // TODO constructor
    
    // TODO checkRep
    public boolean checkRep()
    {
    	for(Edge<L> i: edges) i.checkRep();
        return true;
    }
    
    /**
     * Add a vertex in vertices
     * 
     * @param vertex label for a new vertex
     * @return True if adding is successful or false
     */
    @Override public boolean add(L vertex) {
        //throw new RuntimeException("not implemented");
    	return vertices.add(vertex);
    }
    
    /**
     * Add, change, or remove a weighted directed edge in this graph.
     * If weight nonzero,add an edge or update the weight of that edge;
     * also add vertices if the vertices dosen't have these
     * else remove the edge if it exists
     * 
     * @param source label of the source vertex
     * @param target label of the target vertex
     * @param weight of the edge
     * @return the previous weight of the edge, or zero if there were no such edge,or -1 when weight<0
     */
    @Override public int set(L source, L target, int weight) {
        //throw new RuntimeException("not implemented");
    	if(weight>0)
    	{
    		for(Edge<L> i: edges)
    		{
    			if(i.source.equals(source) && i.target.equals(target))
    				return i.setWeight(weight);
    		}
    		Edge<L> e = new Edge<L>(source, target);
            e.setWeight(weight);
            edges.add(e);
            checkRep();
            return 0;
    	}
    	else if(weight==0)
    	{
    		for(Edge<L> i: edges)
    		{
    			if(i.source.equals(source) && i.target.equals(target))
    			{
    				int w=i.setWeight(weight);
    				edges.remove(i);
    				checkRep();
    				return w;
    			}
    		}
    	}
    	checkRep();
		return -1;
    }
    /**
     * Remove vertex from vertices and remove edges which have the vertex
     * 
     * @param vertex which wanted to be removed
     * @return true if remove successful or false
     */
    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
    	if(vertices.remove(vertex))
    	{
    		 //Edge<L> r;
    		for(int i=0;i<edges.size();i++)
    		{
    			if(edges.get(i).source.equals(vertex) || edges.get(i).target.equals(vertex))
    				edges.remove(i);
    		}
    		checkRep();
    		return true;
    	}
    	else return false;
    }
    /**
     * Get a set of all vertices
     * 
     * @return a hash set made up of all vertices
     */
    @Override public Set<L> vertices() {
        //throw new RuntimeException("not implemented");
    	return new HashSet<>(vertices);
    }
    /**
     * Get the map of source and weight which edge use target as target 
     * 
     * @return a map 
     */
    @Override public Map<L, Integer> sources(L target) {
        //throw new RuntimeException("not implemented");
    	Map<L, Integer> r = new HashMap<>();
    	for(Edge<L> i: edges)
    	{
        	if(i.target.equals(target)) r.put(i.source, i.getWeight());
        }
    	return r;
    	
    }
    /**
     * Get the map of source and weight which edge use source as source
     * 
     * @return a map
     */
    @Override public Map<L, Integer> targets(L source) {
        //throw new RuntimeException("not implemented");
    	Map<L, Integer> r = new HashMap<>();
    	for(Edge<L> i: edges)
    	{
        	if(i.source.equals(source)) r.put(i.target, i.getWeight());
        }
    	return r;
    }
    
    // TODO toString()
    public String toString()
    {
    	String s=new String();
    	for (Edge<L> i : edges)
    	{
    		s=s+i.toString()+"\n";
    	}
    	s=s+vertices;
    	return s;
    }
    
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
	// TODO fields
	public final L source;
	public final L target;
	private int weight = 0;
	
	
    
    // Abstraction function:
    //  AF(source_v,target_v,weight)=source_v--weight-->target
    // Representation invariant:
    //  source!=target
	//  weight>=0
    // Safety from rep exposure:
    //  source and target are final
	//  weight are private
    
    // TODO constructor
	public Edge(L source, L target)
	{
    	this.source = source;
    	this.target = target;
    }
    // TODO checkRep
	public boolean checkRep() 
	{
		assert !source.equals(target);
		assert weight >= 0;
		
		return true;
    }
    // TODO methods
	public int setWeight(int neWeight)
	{
    	int prev = weight;
    	weight = neWeight;
    	return prev;
    }
	public int getWeight() 
	{
    	return weight;
    }
    // TODO toString()
	public String toString() {
    	return  source.toString() + "--"+ '(' + weight + ')'+ "-->" + target.toString() ;
    }
    
}
