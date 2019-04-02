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
    public void toStringTest()
    {
    	Graph<String> r = emptyInstance();
    	r.add("A");
    	r.add("B");
    	r.add("C");
    	r.set("A", "B", 10);
    	assertEquals(-1,r.set("A", "B", -10));
    	assertEquals("A--(10)-->B\n[A, B, C]",r.toString());
    	r.set("B", "A", 10);
    	assertEquals("A--(10)-->B\nB--(10)-->A\n[A, B, C]",r.toString());
    	
    	
    	r.set("B", "A", 0);
    	assertEquals("A--(10)-->B\n[A, B, C]",r.toString());
    	assertEquals(false,r.remove("D"));
    	assertEquals(true,r.remove("A"));
    	
    	assertEquals("[B, C]",r.toString());
    	r.remove("B");
    	r.remove("C");
    	assertEquals("[]",r.toString());
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
    // TODO tests for ConcreteEdgesGraph.toString()
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
    
    // TODO tests for operations of Edge
    @Test
    public void edgeTest()
    {
    	Edge<String> a = new Edge<>("A", "B");
    	Edge<String> b = new Edge<>("B", "A");
    	assertTrue(a.checkRep());
	    assertTrue(b.checkRep());
	    
	    assertEquals(0, a.setWeight(2));
    	b.setWeight(1);
    	assertTrue(a.checkRep());
	    assertTrue(b.checkRep());
	    
    	assertEquals(2, a.getWeight());
    	assertEquals(1, b.getWeight());
    	
    	assertEquals("A--(2)-->B", a.toString());
    	assertEquals("B--(1)-->A", b.toString());
    }
    
}
