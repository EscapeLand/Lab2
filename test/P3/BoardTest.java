import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
	private Board b;
	private Player zzs = new Player(true, "zzs");
	private Player zxh = new Player(false, "zxh");
	
	@Test
	public void testBoard(){
		 b = new Board(Game.Type.Chess, zzs, zxh);
		 assertNotNull(b.at(new Position(0, 0)));
		 assertNull(b.at(new Position(3, 0)));
		 assertNull(b.at(new Position(-1, -1)));
	}
	
	@Test
	public void testPlace(){
		b = new Board(Game.Type.Go, zzs, zxh);
		
		assertNull(b.place(new Piece(Piece.Type.piece, zzs), new Position(5, 5)));
		assertNotNull(b.place(new Piece(Piece.Type.piece, zxh), new Position(5, 5)));
		
		assertNull(b.place(new Piece(Piece.Type.piece, zzs), new Position(-1, -1)));
	}
	
	@Test
	public void testInPool(){
		b = new Board(Game.Type.Chess, zzs, zxh);
		
		assertTrue(b.inPool(new Position(0, 0)));
		assertTrue(b.inPool(new Position(7, 7)));
		assertFalse(b.inPool(new Position(-1, -1)));
		assertFalse(b.inPool(new Position(8, 8)));
		
		b = new Board(Game.Type.Go, zzs, zxh);
		
		assertTrue(b.inPool(new Position(0, 0)));
		assertTrue(b.inPool(new Position(17, 17)));
		assertFalse(b.inPool(new Position(-1, -1)));
		assertFalse(b.inPool(new Position(18, 18)));
	}
}
