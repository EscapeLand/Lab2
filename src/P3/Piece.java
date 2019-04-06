public class Piece {
	public enum Type{
		rock, horse, queen, king, bishop, soldier
		, piece
	}
	
	private Position pos;
	final Player owner;
	final Type type;
	
	Piece(Type type, Player owner){
		this.type = type;
		this.owner = owner;
		pos = new Position(-1, -1);
	}
	
	void setPos(Position pos){
		this.pos = pos;
	}
	
	Position getPos(){
		return pos;
	}
	
	static Piece copy(Piece origin){
		if(origin == null) return null;
		Piece newp = new Piece(origin.type, origin.owner);
		newp.setPos(origin.getPos());
		return newp;
	}
	
	static Type cast_Type(String name) throws IllegalArgumentException{
		return Enum.valueOf(Piece.Type.class, name);
	}
	
	@Override
	public String toString(){
		final String black = "♜♞♛♚♝♟●";
		final String white = "♖♘♕♔♗♙○";
		return String.valueOf(owner.black? black.charAt(type.ordinal()): white.charAt(type.ordinal()));
	}
}
