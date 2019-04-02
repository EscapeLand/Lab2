/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO contain all of the relations of the map
    // Representation invariant:
    //   TODO for each Vertex in vertices, Vertex do not connect to itself; 
    //        Vertex.weight >= 0.
    // Safety from rep exposure:
    //   TODO All fields are private
    
    // TODO constructor
    
    // TODO checkRep
    private boolean checkRep()
    {
    	for(Vertex<L> i:vertices)
    	{
    		for(Entry<L, Integer> j:i.getMap().entrySet())
    		{
    			if(i.getVertex().equals(j.getKey()))
    			{
    				return false;
    			}
    		}
    	}
    	
    	return true;
    }
    /**
     * Is vertex in vertices before
     * 
     * @param vertex label for a vertex which we want to find
     * @return True if vertex isn't exist before or false
     */
    public boolean findVertex(L vertex)
    {
    	for(Vertex<L> i:vertices)
    	if(i.getVertex().equals(vertex)) return true;
    	
    	return false;
    }
    /**
     * Add a vertex in vertices
     * 
     * @param vertex label for a new vertex
     * @return True if adding is successful or false
     */
    @Override public boolean add(L vertex) {
        //throw new RuntimeException("not implemented");
    	if(!findVertex(vertex)) return vertices.add(new Vertex<L>(vertex));
    	return false;
    }
    /**
     * Add a new edge into the map,if the source exist in vertices before,add an edge in it's map;
     * else add the source in the map and do it again;
     * 
     * @param source label of the source vertex
     * @param target label of the target vertex
     * @param weight of the edge
     * @return the previous weight of the edge, or zero if there were no such edge,or -1 when weight<0
     *
     */
    @Override public int set(L source, L target, int weight)
    {
        //throw new RuntimeException("not implemented");
    	assert !source.equals(target);
    	int temp=0;
    	if(weight<0)
    	{
    		checkRep();
    		return temp=-1;
    	}
    	if(findVertex(source))
    	{
    		for(Vertex<L> i:vertices)
    	    if(i.getVertex().equals(source))  i.setEdge(target, weight);
    		
    	}
    	else
    	{
    		vertices.add(new Vertex<L>(source));
    		for(Vertex<L> i:vertices)
        	if(i.getVertex().equals(source)) temp= i.setEdge(target, weight);
    		
    	}
    	checkRep();
    	return temp;
    }
    /**
     * Remove vertex from vertices and remove edges which have the vertex
     * 
     * @param vertex which wanted to be removed
     * @return true if remove successful or false
     */
    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
    	
    		if(findVertex(vertex))
    		{
    			
    			
    			for(Vertex<L> i: vertices) 
    			{
    				i.removeEdge(vertex);
    				
    			}
    			for(int i=0;i<vertices.size();i++)
    		    	if(vertices.get(i).getVertex().equals(vertex)) vertices.remove(i);
    			checkRep();
    	        return true;
    		}
    		checkRep();
    		return false;
    	
    }
    /**
     * Get a set of all vertices
     * 
     * @return a hash set made up of all vertices
     */
    @Override public Set<L> vertices() {
        //throw new RuntimeException("not implemented");
    	Set<L> r = new HashSet<>(vertices.size());
        for(Vertex<L> i: vertices) r.add(i.getVertex());
        
        return r;
    }
    /**
     * Get the map of source and weight which edge use target as target 
     * 
     * @return a map 
     */
    @Override public Map<L, Integer> sources(L target) {
        //throw new RuntimeException("not implemented");
    	Map<L, Integer> r = new HashMap<>();
    	for(Vertex<L> i:vertices)
    	{
    		for(Entry<L, Integer> j: i.getMap().entrySet())
    		{
    			if(j.getKey().equals(target))
    			{
    				r.put(i.getVertex(), j.getValue());
    				break;
    			}
    		}
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
    	for(Vertex<L> i:vertices)
        	if(i.getVertex().equals(source)) return i.getMap();
    	
    	return null;
    }
    
    // TODO toString()
    @Override
    public String toString() 
    {
    	StringBuffer s = new StringBuffer();
    	for(Vertex<L> i: vertices) s.append(i.toString() + '\n');
    	
    	return s.toString();
    }
    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
    // TODO fields
	private  Map<L, Integer> target_v = new HashMap<>();
	private  L vertex;
    // Abstraction function:
    // 	 AF(vertex)={v must in vertices}
	//	 AF(target_v)={target_v.key must in vertices}
    // Representation invariant:
    //   for each i in target-v, i.value >= 0.
	//   for each i in target_v, i.key != vertex.
    // Safety from rep exposure:
    //   All fields are private
    
    // TODO constructor
	public Vertex(L v) 
	{
        // TODO Auto-generated constructor stub
        this.vertex = v;
        this.target_v = new HashMap<L, Integer>();
    }

    
    // TODO checkRep
	public boolean checkRep()
	{
    	for(Entry<L, Integer> i: target_v.entrySet())
    	{
    		assert i.getKey() != vertex;
    		assert i.getValue() >= 0;
	    }
    	return true;
    }
    
    // TODO methods
	public int setEdge(L target, int weight)
	{
		if(target!=null)
		{
	    	if(weight == 0) return this.removeEdge(target);
	    	else
	    	{
	    		int w=this.removeEdge(target);
	    		target_v.put(target, weight);
	    		return w;
		    }
		}
		return 0;
	}
	
	public int removeEdge(L target) 
	{
		if (getMap() != null && getMap().containsKey(target) && target != null) {
            return getMap().remove(target);
        } else {
            return 0;
        }
    }
    
	public L getVertex() 
	{
        return this.vertex;
    }

    public Map<L, Integer> getMap() 
    {
        return this.target_v;
    }
    // TODO toString()
    
    @Override 
    public String toString() 
    {
    	StringBuilder s=new StringBuilder ();
    	s.append(vertex+": ");
    	for(Entry<L, Integer> i: target_v.entrySet())
    	{
    		s.append(i.getKey()+"("+i.getValue()+")"+"/");
    	}
    	
    	return s.toString();
    }
    
}
