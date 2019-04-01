/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import graph.Graph;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   graph as the keywords graph of the input text file.
    // Representation invariant:
    //   graph.checkRep() == true.
    // Safety from rep exposure:
    //   TODO
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    GraphPoet(File corpus) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(corpus));
        //int line = 0;				//line_number
        
        for(String buf = reader.readLine(); buf!= null; buf = reader.readLine()) {
        	//line++;
	        buf = buf.toLowerCase();
        	String[] list = buf.split(" ");
        	graph.add(list[0]);
        	for(int i = 1; i < list.length; i++) {
        		if(graph.add(list[i])) {
        			graph.set(list[i-1], list[i], 1);
        		}
        		else {
        			Integer r = graph.sources(list[i-1]).get(list[i]);
        			if(r == null) r = 0;
        			
        			graph.set(list[i-1], list[i], r + 1);
        		}
        	}
        }
        
        reader.close();
    }
    
    boolean checkRep(){
	    // TODO checkRep
	    
		return true;
    }
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(@NotNull String input) {
        String[] list = input.toLowerCase().split(" ");
        StringBuilder r = new StringBuilder();
        r.append(list[0]);
        
        for(int i = 1; i < list.length; i++) {
        	int max = 0;
            String max_elm = "";
        	Map<String, Integer> f1 = graph.targets(list[i-1]);
        	if(f1.containsKey(list[i])) continue;
        	for(String j: f1.keySet()) {
        		Map<String, Integer> f2 = graph.targets(j);
        		if(f2.containsKey(list[i])) {
        			int tmp = f1.get(j) + f2.get(list[i]);
        			if(max < tmp) {
        				max = tmp;
        				max_elm = j;
        			}
        		}
        	}
        	
        	r.append(' ');
        	if(max > 0) r.append(max_elm).append(' ');
        	r.append(list[i]);
        }
        
        return r.toString();
    }
    
    @Override
    public String toString() {
    	return graph.toString();
    }
    
}
