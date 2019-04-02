import static org.junit.Assert.*;

import org.junit.Test;

public class FriendshipGraphTest {

	@Test
	public void addVertexTest() {
		FriendshipGraph graphTest = new FriendshipGraph();
		Person A = new Person("zxh");
		Person B = new Person("zzs");
		graphTest.addVertex(A);
		assertEquals(true, graphTest.addVertex(B));
		assertEquals(false, graphTest.addVertex(A));
	}
	
	@Test
	public void addEdgeTest() {
		FriendshipGraph graphTest = new FriendshipGraph();
		Person A = new Person("ZXH");
		Person B = new Person("ZZS");
		Person C = new Person("ppq");
		graphTest.addVertex(A);
		graphTest.addVertex(B);
		assertEquals(true, graphTest.addEdge(A, B));
		assertEquals(false, graphTest.addEdge(A, C));
	}
	
	@Test
	public void getDistanceTest() {
		FriendshipGraph graphTest = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graphTest.addVertex(rachel);
		graphTest.addVertex(ross);
		graphTest.addVertex(ben);
		graphTest.addVertex(kramer);
		graphTest.addEdge(rachel, ross);
		graphTest.addEdge(ross, rachel);
		graphTest.addEdge(ross, ben);
		graphTest.addEdge(ben, ross);
		assertEquals(1, graphTest.getDistance(rachel, ross));
		assertEquals(2, graphTest.getDistance(rachel, ben));
		assertEquals(0, graphTest.getDistance(rachel, rachel));
		assertEquals(-1, graphTest.getDistance(rachel, kramer));
	}
}

