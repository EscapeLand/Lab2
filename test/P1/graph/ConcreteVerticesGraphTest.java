/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import org.junit.Test;

import static org.junit.Assert.*;

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
    
    @Test
    public void testToString() {
    	Graph<String> r = emptyInstance();
    	r.add("A");
    	r.add("B");
    	r.set("A", "B", 2);
    	
    	assertEquals("A -> B(2)\nB ->\n", r.toString());
    	r.set("B", "A", 1);
    	assertEquals("A -> B(2)\nB -> A(1)\n", r.toString());
    }
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    
	@Test
    public void testVertex(){
    	Vertex<String> a = new Vertex<>("A");
	    Vertex<String> b = new Vertex<>("B");
	
	    assertTrue(a.checkRep());
	    assertTrue(b.checkRep());
	    
	    assertEquals(0, a.setEdge(b, 10));
	    assertTrue(a.checkRep());
		
		Integer r = a.getMap().get("B");
	    assertNotNull(r);
	    assertEquals(10, r.intValue());
    }
    
    
	@Test
	public void testRemove(){
		Graph<String> r = emptyInstance();
		r.add("zzs");
		r.add("zxh");
		
		r.set("zzs", "zxh", 10);
		assertTrue(r.remove("zzs"));
		
		assertFalse(r.remove("zzs"));
	}
}
