/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public <L> Graph<L> emptyInstance() {
        return new ConcreteEdgesGraph<L>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
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
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
	
    @Test
    public void testEdge() {
    	Edge<String> a = new Edge<>("A", "B");
    	Edge<String> b = new Edge<>("B", "A");
    	
	    assertTrue(a.checkRep());
	    assertTrue(b.checkRep());
	    
    	a.setWeight(2);
    	b.setWeight(1);
	
	    assertTrue(a.checkRep());
	    assertTrue(b.checkRep());
	    
    	assertEquals(2, a.getWeight());
    	assertEquals(1, b.getWeight());
    	
    	assertEquals("A -> B(2)", a.toString());
    	assertEquals("B -> A(1)", b.toString());
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
