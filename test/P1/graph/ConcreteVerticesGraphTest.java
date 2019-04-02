/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public <L> Graph<L> emptyInstance() {
        return new ConcreteVerticesGraph<L>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void toStringTest() {
    	Graph<String> r = emptyInstance();
    	r.add("A");
    	r.add("B");
    	r.add("C");
    	r.set("A", "B", 2);
    	assertEquals(-1,r.set("A", "B", -10));
    	assertEquals("A: B(2)/\nB: \nC: \n", r.toString());
    	r.set("B", "A", 1);
    	assertEquals(true,r.remove("C"));
    	assertEquals("A: B(2)/\nB: A(1)/\n", r.toString());
    	assertEquals(true,r.remove("B"));
    	assertEquals("A: \n", r.toString());
    	assertEquals(false,r.remove("D"));
    	r.remove("A");
    	assertEquals("", r.toString());
    	
    	r.add("A");
    	r.add("B");
    	r.add("C");
    	r.add("D");
    	r.add("E");
    	r.add("F");
    	Set<String> s=new HashSet<String>();
    	s.add("A");
    	s.add("B");
    	s.add("C");
    	s.add("D");
    	s.add("E");
    	s.add("F");
    	assertEquals(s,r.vertices());
    	r.set("B", "A", 10);
    	r.set("C", "A", 5);
    	r.set("F", "A", 6);
    	Map<String,Integer> ms=new HashMap<String,Integer>();
    	ms.put("B", 10);
    	ms.put("C", 5);
    	ms.put("F", 6);
    	assertEquals(ms,r.sources("A"));
    	
    	r.set("A", "B", 10);
    	r.set("A", "C", 5);
    	r.set("A", "F", 6);
    	assertEquals(ms,r.targets("A"));
    }
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    
    // TODO tests for operations of Vertex
    @Test
    public void vertexTest(){
    	Vertex<String> a = new Vertex<>("A");
	    Vertex<String> b = new Vertex<>("B");
	
	    assertTrue(a.checkRep());
	    assertTrue(b.checkRep());
	    
	    assertEquals(0, a.setEdge("B", 10));
	    assertEquals(10, a.setEdge("B", 5));
	    assertTrue(a.checkRep());
		
		Integer r = a.getMap().get("B");
	    assertNotNull(r);
	    assertEquals(5, r.intValue());
    }
    
}
