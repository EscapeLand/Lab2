/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    private final String path;
    public GraphPoetTest(){
    	String dir = System.getProperty("user.dir");
    	path = dir.replaceFirst("src\\\\(P\\d)", "test\\\\$1");
    }
    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGraphPoet_ctor() {
    	System.out.println(path);
    	try {
    		File fs = new File(path + "/poet/seven-words.txt");
        	GraphPoet gp = new GraphPoet(fs);
        	//System.out.print(gp.toString());
    	}
    	catch(IOException | RuntimeException ex){
    		ex.printStackTrace();
    	}
	
    }

    @Test
	public void testPoem(){
    	try{
			File fs = new File(path + "/poet/seven-words.txt");
			GraphPoet gp = new GraphPoet(fs);
			assertEquals("upon the place", gp.poem("Upon place"));
		}
    	catch(IOException ex){
    		ex.printStackTrace();

		}
	}
	
	@Test
	public void testPoem_emmm(){
		try{
			File fs = new File(path + "/poet/emmm.txt");
			GraphPoet gp = new GraphPoet(fs);
			assertEquals("Seek to explore strange new life and exciting synergies!".toLowerCase(), gp.poem("Seek to explore new and exciting synergies!"));
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
}
