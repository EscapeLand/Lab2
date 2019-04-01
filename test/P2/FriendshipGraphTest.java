import org.junit.Test;

import static org.junit.Assert.*;

public class FriendshipGraphTest {
	
	@Test
	public void testAddVertex() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		assertTrue(graph.addVertex(rachel));
		Person ross = new Person("Ross");
		assertTrue(graph.addVertex(ross));
		Person fake = new Person("Rachel");
		assertFalse(graph.addVertex(fake));
	}
	
	@Test
	public void testAddEdge() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ben = new Person("Ben");
		graph.addVertex(rachel);
		assertFalse(graph.addEdge(rachel, ben));
		graph.addVertex(ben);
		assertTrue(graph.addEdge(rachel, ben));
		assertFalse(graph.addEdge(rachel, ben));
	}
	
	@Test
	public void testGetDistance() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		
		assertEquals(1, graph.getDistance(rachel, ross));
		assertEquals(2, graph.getDistance(rachel, ben));
		assertEquals(0, graph.getDistance(rachel, rachel));
		assertEquals(-1, graph.getDistance(rachel, kramer));
	}
	
	@Test
	public void testGetDistance_Ring() {
		FriendshipGraph graph = new FriendshipGraph();
		Person one = new Person("one");
		Person two = new Person("two");
		Person three = new Person("three");
		Person four = new Person("four");
		Person five = new Person("five");
		Person six = new Person("six");
		Person seven = new Person("seven");
		
		graph.addVertex(one);
		graph.addVertex(two);
		graph.addVertex(three);
		graph.addVertex(four);
		graph.addVertex(five);
		graph.addVertex(six);
		graph.addVertex(seven);
		
		graph.addEdge(one, two);
		graph.addEdge(two,three);
		graph.addEdge(three,four);
		graph.addEdge(four, five);
		graph.addEdge(five,two);
		graph.addEdge(four, six);
		
		assertEquals(0, graph.getDistance(two,two), 0.1);
		assertEquals(3, graph.getDistance(one,four), 0.1);
		assertEquals(4, graph.getDistance(one,six), 0.1);
		assertEquals(-1, graph.getDistance(one,seven), 0.1);
	}
}
