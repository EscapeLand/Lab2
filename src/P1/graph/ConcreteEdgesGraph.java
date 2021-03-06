/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices) = vertices of the graph.
	//   AF(edges) = edges of the graph.
    // Representation invariant:
    //   for each edge, weight >= 0 and from != to.
    // Safety from rep exposure:
    //   all field private.
    
    public boolean checkRep(){
    	for(Edge<L> i: edges) i.checkRep();
        return true;
    }
    
    @Override public boolean add(L vertex) {
        return vertices.add(vertex);
    }
    
    @Override public int set(L source, L target, int weight) {
        for(Edge<L> i: edges) {
        	if(i.from.equals(source) && i.to.equals(target)) return i.setWeight(weight);
        }
        
        Edge<L> e = new Edge<>(source, target);
        e.setWeight(weight);
        edges.add(e);
        return 0;
    }
    
    @Override public boolean remove(L vertex) {
        if(vertices.remove(vertex)) return edges.removeIf(x -> x.from.equals(vertex) || x.to.equals(vertex));
        else return false;
    }
    
    @Override public Set<L> vertices() {
        return new HashSet<>(vertices);
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> r = new HashMap<>();
        for(Edge<L> i: edges) {
        	if(i.to.equals(target)) r.put(i.from, i.getWeight());
        }
        
        return r;
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	Map<L, Integer> r = new HashMap<>();
        for(Edge<L> i: edges) {
        	if(i.from.equals(source)) r.put(i.to, i.getWeight());
        }
        
        return r;
    }
    
    @Override
    public String toString(){
    	StringBuilder s = new StringBuilder();
    	for(L i: vertices) {
    		s.append(i.toString()).append(" ->");
    		for(Edge<L> j: edges) {
    			if(j.from.equals(i)) s.append(' ').append(j.to.toString()).append('(').append(j.getWeight()).append(')');
    		}
    		s.append('\n');
    	}
    	
    	return s.toString();
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
final class Edge<L> {
    final L from;
    final L to;
    private int weight = 0;
    
    // Abstraction function:
    //   AF(from, to, weight) = an edge from 'from' to 'to', with weight 'weight'.
	//   int weight: weight of the edge.
    // Representation invariant:
    //   weight >= 0 and from != to.
    // Safety from rep exposure:
    //  type L is a immutable, safety from exposure; other fields as private.
    
    Edge(L from, L to){
    	this.from = from;
    	this.to = to;
    }
    
    boolean checkRep() {
		assert !from.equals(to);
		assert weight >= 0;
		
		return true;
    }
    
    int setWeight(int newval){
    	int prev = weight;
    	weight = newval;
    	return prev;
    }
    
    int getWeight() {
    	return weight;
    }
    
    @Override
    public String toString() {
    	return from.toString() + " -> " + to.toString() + '(' + weight + ')';
    }
    
}
