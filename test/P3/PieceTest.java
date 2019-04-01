import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PieceTest {
	
	@Test
	public void testPiece(){
		Player zzs = new Player(true, "zzs");
		Piece newp = new Piece(Piece.Type.queen, zzs);
		
		assertEquals(zzs, newp.owner);
		assertEquals(new Position(-1, -1), newp.getPos());
		
		newp.setPos(new Position(2, 1));
		assertEquals(new Position(2, 1), newp.getPos());
	}
}
