/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
	
    // Testing strategy
    //   TODO
	private final String path;
    public GraphPoetTest(){
    	String dir = System.getProperty("user.dir");
    	path = dir.replaceFirst("src\\\\(P\\d)", "test\\\\$1");
    }
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void testGraphPoet_ctor() {
    	try {
    		File fs = new File(path+"/poet/seven-words.txt");
        	GraphPoet gp = new GraphPoet(fs);
        	//System.out.print(gp.toString());
    	}
    	catch(IOException ex){
    		ex.printStackTrace();
    	}
    	catch(RuntimeException ex) {
    		ex.printStackTrace();
    		return;
    	}
    	
    }

    @Test
	public void testPoem(){
    	try{
			File fs = new File(path+"/poet/seven-words.txt");
			GraphPoet gp = new GraphPoet(fs);
			//System.out.print(gp.toString());
			assertEquals("seek to explore strange new life and exciting synergies!", gp.poem("Seek to explore new and exciting synergies!"));
		}
    	catch(IOException ex){
    		ex.printStackTrace();

		}
	}
    
}
