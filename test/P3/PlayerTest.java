import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
	
	private Player zzs = new Player(true, "zzs");
	private Player zxh = new Player(false, "zxh");
	
	@Test
	public void testPlayer(){
		Player zzs = new Player(true, "zzs");
		Player zxh = new Player(false, "zxh");
		
		assertTrue(zzs.black);
		assertFalse(zxh.black);
		
		assertEquals("zzs", zzs.name);
		assertEquals("zxh", zxh.name);
		
		assertEquals("zzs(black)", zzs.toString());
		assertEquals("zxh(white)", zxh.toString());
	}
	
	@Test
	public void testAdd(){
		Board b = new Board(Game.Type.Chess, zzs, zxh);
		zzs.changeBoard(b);
		zxh.changeBoard(b);
		
		assertFalse(zzs.addPiece(new Piece(Piece.Type.queen, zzs), new Position(-1, -1)));
		assertFalse(zzs.addPiece(new Piece(Piece.Type.piece, zzs), new Position(0, 0)));
		assertFalse(zzs.addPiece(new Piece(Piece.Type.queen, zzs), new Position(0, 0)));
		assertTrue(zzs.addPiece(new Piece(Piece.Type.queen, zzs), new Position(2, 0)));
	}
	
	@Test
	public void testMove(){
		Board b = new Board(Game.Type.Chess, zzs, zxh);
		zzs.changeBoard(b);
		zxh.changeBoard(b);
		
		zzs.addPiece(new Piece(Piece.Type.queen, zzs), new Position(2, 0));
		zxh.addPiece(new Piece(Piece.Type.king, zxh), new Position(7, 7));
		assertTrue(zzs.movePiece(new Position(2, 0), new Position(2, 1)));
		assertNull(b.at(new Position(2, 0)));
		assertFalse(zzs.movePiece(new Position(-1, -1), new Position(1,1)));
		assertFalse(zzs.movePiece(new Position(7,0), new Position(3, 0)));
		assertFalse(zzs.movePiece(new Position(2, 1), new Position(2, 1)));
		assertFalse(zzs.movePiece(new Position(2, 1), new Position(7, 0)));
		assertFalse(zzs.movePiece(new Position(7, 7),  new Position(7, 6)));
	}
	
	@Test
	public void testReplace(){
		Board b = new Board(Game.Type.Chess, zzs, zxh);
		zzs.changeBoard(b);
		zzs.changeBoard(b);
		
		assertTrue(zzs.replacePiece(new Position(0, 0), new Position(7, 0)));
		assertNull(b.at(new Position(0 ,0)));
		assertFalse(zzs.replacePiece(new Position(-1, -1), new Position(1,1)));
		assertFalse(zzs.replacePiece(new Position(1,1), new Position(1, 1)));
		assertFalse(zzs.replacePiece(new Position(7, 7), new Position(0, 7)));
		assertFalse(zzs.replacePiece(new Position(7, 0), new Position(3, 0)));
	}
	
	@Test
	public void testRemove(){
		Board b = new Board(Game.Type.Go, zzs, zxh);
		zzs.changeBoard(b);
		zxh.changeBoard(b);
		
		Position _55 = new Position(5, 5);
		assertNull(b.at(_55));
		
		zzs.addPiece(new Piece(Piece.Type.piece, zzs), _55);
		assertNotNull(b.at(_55));
		
		assertFalse(zzs.removePiece(_55));
		assertTrue(zxh.removePiece(_55));
		assertNull(b.at(_55));
		
		assertFalse(zzs.removePiece(_55));
		assertFalse(zzs.removePiece(new Position(-1, -1)));
	}
}
