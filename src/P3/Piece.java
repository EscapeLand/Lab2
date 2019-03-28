import java.rmi.UnexpectedException;

public class Piece {
	public enum Type{
		rock, horse, queen, king, bishop, soldier
		, piece
	}
	
	private Position pos;
	public final Player owner;
	public final Type type;
	
	public Piece(Type type, Player owner){
		this.type = type;
		this.owner = owner;
		pos = new Position(-1, -1);
	}
	
	public void setPos(Position pos){
		this.pos = pos;
	}
	
	public Position getPos(){
		return pos;
	}
	
	public static Piece copy(Piece origin){
		if(origin == null) return null;
		Piece newp = new Piece(origin.type, origin.owner);
		newp.setPos(origin.getPos());
		return newp;
	}
	
	public static Type cast_Type(String name) throws IllegalArgumentException{
		return Enum.valueOf(Piece.Type.class, name);
	}
	
	@Override
	public String toString(){
		final String black = "♜♞♛♚♝♟●";
		final String white = "♖♘♕♔♗♙○";
		return String.valueOf(owner.black? black.charAt(type.ordinal()): white.charAt(type.ordinal()));
	}
}
