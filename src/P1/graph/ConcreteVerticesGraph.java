/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.*;
import java.util.Map.Entry;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices) = list of the vertices of the graph.
    // Representation invariant:
    //   for each Vertex in vertices, Vertex do not connect to itself; Vertex.weight >= 0.
    // Safety from rep exposure:
    //   all field private.
    
    private Vertex<L> findVertex(L Label) {
    	for(Vertex<L> i: vertices) if(i.getLabel().equals(Label)) return i;
    	return null;
    }
    public boolean checkRep(){
    	for(Vertex<L> i: vertices) i.checkRep();
    	return true;
    }
    
    @Override public boolean add(L vertex) {
        if(findVertex(vertex) != null) return false;
        
        return vertices.add(new Vertex<>(vertex));
    }
    
    @Override public int set(L source, L target, int weight) {
    	assert source != target;
    	
    	Vertex<L> s = findVertex(source);
    	if(s == null) {
    		s = new Vertex<>(source);
    		vertices.add(s);
    	}
    	
    	Vertex<L> t = findVertex(target);
    	if(t == null) {
    		t = new Vertex<>(target);
    		vertices.add(t);
    	}
	
	    int r = s.setEdge(t, weight);
    	return r == -1 ? 0 : r;
    }
    
    @Override public boolean remove(L vertex) {
    	Vertex<L> v = findVertex(vertex);
    	if(v == null) return false;
    	
        for(Vertex<L> i: vertices) i.removeEdge(v);
        return vertices.remove(v);
    }
    
    @Override public Set<L> vertices() {
        Set<L> r = new HashSet<>(vertices.size());
        for(Vertex<L> i: vertices) r.add(i.getLabel());
        
        return r;
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	Map<L, Integer> r = new HashMap<>();
        Vertex<L> v = findVertex(target);
        if(v == null) return r;
        
        for(Vertex<L> i: vertices) {
        	Integer tmp = i.get(v);
        	if(tmp != null) r.put(i.getLabel(), tmp);
        }
        
        return r;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Vertex<L> v = findVertex(source);
        if(v == null) return new HashMap<>();
        
        return v.getMap();
    }
    
    @Override
    public String toString() {
    	StringBuilder r = new StringBuilder();
    	for(Vertex<L> i: vertices) r.append(i.toString()).append('\n');
    	
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
    private L label;
    
    // Abstraction function:
    //   AF(to) = a map that records end point and the weight to it.
    // Representation invariant:
    //   for each i in to, i.value >= 0.
	//   for each i in to, i.key != label.
    // Safety from rep exposure:
    //   Map to is mutable, and private. label as private.
    
    Vertex(L Label){
    	label = Label;
    }
    
    boolean checkRep(){
    	for(Entry<Vertex<L>, Integer> i: to.entrySet()){
    		assert i.getKey() != label;
    		assert i.getValue() >= 0;
	    }
    	return true;
    }
    
    int setEdge(Vertex<L> target, int weight) {
    	if(target.getLabel() == label) return -1;
    	if(weight == 0) return to.remove(target);
    	else{
    		Integer r = to.put(target, weight);
    		if(r == null) return 0;
    		else return r;
	    }
    }
    int removeEdge(Vertex<L> target) {
    	Integer r = to.remove(target);
    	if(r == null) return 0;
    	else return r;
    }
    Integer get(Vertex<L> v) {
    	return to.get(v);
    }
    
    L getLabel(){
    	return label;
    }
    
    void setLabel(L newLabel){
        label =  newLabel;
    }
    
    Map<L, Integer> getMap() {
    	Map<L, Integer> r = new HashMap<>();
    	for(Entry<Vertex<L>, Integer> i: to.entrySet()) {
    		r.put(i.getKey().label, i.getValue());
    	}
    	return r;
    }
    
    @Override
    public String toString() {
    	StringBuilder s = new StringBuilder();
    	s.append(label).append(" ->");
    	for(Entry<Vertex<L>, Integer> i: to.entrySet()) {
    		s.append(' ').append(i.getKey().label.toString());
    		s.append("(").append(i.getValue()).append(")");
    	}
    	return s.toString();
    }
}
