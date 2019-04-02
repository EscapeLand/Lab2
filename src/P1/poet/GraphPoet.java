/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import graph.Graph;

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
    private final List<String> wordList = new ArrayList<String>();
    
    // Abstraction function:
    //   graph is the map made up of the word
    //   word list as the same member of the graph
    // Representation invariant:
    //   both of them are not empty
    // Safety from rep exposure:
    //   all fields are private
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
       // throw new RuntimeException("not implemented");
    	BufferedReader reader = new BufferedReader(new FileReader(corpus));
    	for(String s=reader.readLine();s!=null;s=reader.readLine())
    	{
    		String []temp=s.toLowerCase().split(" ");
    		for(String r:temp)
    		{
    			if(!r.trim().equals(""))
    			{
    				wordList.add(r);
    			}
    		}
    		
    		
    	}
    	reader.close();
    	
    	for(int i=0;i<wordList.size()-1;i++)
		{
			if(graph.add(wordList.get(i)))
			{
				graph.set(wordList.get(i),wordList.get(i+1), 1);
			}
			else
			{
				Integer r=graph.targets(wordList.get(i)).get(wordList.get(i+1));
				if(r==null)  r=0;
				
				graph.set(wordList.get(i), wordList.get(i+1), r+1);
			}
		}
		graph.add(wordList.get(wordList.size()-1));
		
		checkRep();
		
		//graph.toString();
    }
    
    // TODO checkRep
    private void checkRep()
    {
    	assert graph.vertices()!=null;
    	assert wordList != null;
    	
    }
    
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        //throw new RuntimeException("not implemented");
    	String temp[]=input.toLowerCase().split(" ");
    	StringBuilder r=new StringBuilder();
    	int tempWeight=0;
    	String tempString="";
    	
    	for(int i=0;i<temp.length-1;i++)
    	{
    		tempWeight=0;
    		tempString="";
    		Map<String,Integer> m1=graph.targets(temp[i]);
    		if(m1.containsKey(temp[i+1])) 
    		{
    			r.append(temp[i]);
    			r.append(" ");
    			continue;
    			
    		}
    		
    		for(String s:m1.keySet())
    		{
    			Map<String,Integer> m2=graph.targets(s);
    			if(m2.containsKey(temp[i+1]))
    			{
    				int weight=m1.get(s)+m2.get(temp[i+1]);
    				if(weight>tempWeight)
    				{
    					tempWeight=weight;
    					tempString=s;
    				}
    			}
    			
    		}
    		
    		r.append(temp[i]);
    		r.append(' ');
    		if(tempWeight>0)
    		{
    			r.append(tempString);
    			r.append(' ');
    		}
    		
    	}
    	r.append(temp[temp.length-1]);
    	
    	return r.toString();
    	
    }
    
    // TODO toString()
    @Override
    public String toString() {
         return graph.toString();
     }
    
}
