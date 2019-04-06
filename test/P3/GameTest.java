import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class GameTest {
	private Game game;
	
	@Test
	public void testGame(){
		game = new Game(Game.Type.Chess, "zzs", "zxh");
		
		assertArrayEquals(new Player[]{new Player(false, "zzs"), new Player(true, "zxh")},
				game.getPlayer());
	}
}
