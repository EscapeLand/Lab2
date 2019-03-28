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
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    private Vertex<L> findVertex(L Label) {
    	for(Vertex<L> i: vertices) if(i.label.equals(Label)) return i;
    	return null;
    }
    // TODO checkRep
    
    @Override public boolean add(L vertex) {
        if(findVertex(vertex) != null) return false;
        
        return vertices.add(new Vertex<L>(vertex));
    }
    
    @Override public int set(L source, L target, int weight) {
    	assert source != target;
    	
    	Vertex<L> s = findVertex(source);
    	if(s == null) {
    		s = new Vertex<L>(source);
    		vertices.add(s);
    	}
    	
    	Vertex<L> t = findVertex(target);
    	if(t == null) {
    		t = new Vertex<L>(target);
    		vertices.add(t);
    	}
    	
    	return s.setEdge(t, weight);
    }
    
    @Override public boolean remove(L vertex) {
    	Vertex<L> v = findVertex(vertex);
    	if(v == null) return true;
    	
        for(Vertex<L> i: vertices) i.removeEdge(v);
        return vertices.remove(v);
    }
    
    @Override public Set<L> vertices() {
        Set<L> r = new HashSet<>(vertices.size());
        for(Vertex<L> i: vertices) r.add(i.label);
        
        return r;
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	Map<L, Integer> r = new HashMap<>();
        Vertex<L> v = findVertex(target);
        if(v == null) return r;
        
        for(Vertex<L> i: vertices) {
        	Integer tmp = i.get(v);
        	if(tmp != null) r.put(i.label, tmp);
        }
        
        return r;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Vertex<L> v = findVertex(source);
        if(v == null) return new HashMap<L, Integer>();
        
        return v.getMap();
    }
    
    // TODO toString()
    @Override
    public String toString() {
    	StringBuffer r = new StringBuffer();
    	for(Vertex<L> i: vertices) r.append(i.toString() + '\n');
    	
    	return r.toString();
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
    private final Map<Vertex<L>, Integer> to = new HashMap<>();
    public final L label;
    // TODO fields
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    Vertex(L Label){
    	label = Label;
    }
    // TODO checkRep
    
    // TODO methods
    public int setEdge(Vertex<L> target, int weight) {
    	if(weight == 0) return to.remove(target);
    	else return to.put(target, weight);
    }
    public int removeEdge(Vertex<L> target) {
    	return to.remove(target);
    }
    public Integer get(Vertex<L> v) {
    	return to.get(v);
    }
    public Map<L, Integer> getMap() {
    	Map<L, Integer> r = new HashMap<>();
    	for(Entry<Vertex<L>, Integer> i: to.entrySet()) {
    		r.put(i.getKey().label, i.getValue());
    	}
    	return r;
    }
    // TODO toString()
    @Override
    public String toString() {
    	StringBuilder s = new StringBuilder();
    	s.append(label + " ->");
    	for(Entry<Vertex<L>, Integer> i: to.entrySet()) {
    		s.append('\t' + i.getKey().label.toString());
    		s.append("(" + i.getValue() + ")");
    	}
    	return s.toString();
    }
}
