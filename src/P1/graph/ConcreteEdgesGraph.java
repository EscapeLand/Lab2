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
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    
    // TODO checkRep
    
    @Override public boolean add(L vertex) {
        return vertices.add(vertex);
    }
    
    @Override public int set(L source, L target, int weight) {
        for(Edge<L> i: edges) {
        	if(i.from.equals(source) && i.to.equals(target)) return i.setWeight(weight);
        	
        }
        
        Edge<L> e = new Edge<L>(source, target);
        e.setWeight(weight);
        edges.add(e);
        return 0;
    }
    
    @Override public boolean remove(L vertex) {
        if(vertices.remove(vertex)) edges.removeIf(x -> x.from.equals(vertex) || x.to.equals(vertex));
        else return false;
        
        return true;
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
    		s.append(i.toString()).append(" -> ");
    		for(Edge<L> j: edges) {
    			if(j.from.equals(i)) s.append(j.to.toString()).append(' ');
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
class Edge<L> {
    
    // TODO fields
    public final L from;
    public final L to;
    private int weight = 0;
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    public Edge(L from, L to){
    	this.from = from;
    	this.to = to;
    }
    // TODO checkRep
    
    // TODO methods
    public int setWeight(int newval){
    	int prev = weight;
    	weight = newval;
    	return prev;
    }
    public int getWeight() {
    	return weight;
    }
    // TODO toString()
    @Override
    public String toString() {
    	return from.toString() + " -> " + to.toString();
    }
    
}
