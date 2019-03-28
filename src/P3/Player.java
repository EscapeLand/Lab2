public class Player implements Action{
	public final String name;
	private Board board;
	public final boolean black;
	
	public Player(boolean black, String Name){
		this.black = black;
		name = Name;
	}
	
	public void changeBoard(Board newb){
		board = newb;
	}
	
	public boolean isMyPiece(Piece a) { return a.owner == this; }
	
	@Override
	public boolean movePiece(Position from, Position to){
		if(from.equals(to)) return false;
		
		Piece a = board.at(from);
		Piece b = board.at(to);
		
		if(a == null || b != null || !isMyPiece(a)) return false;
		return null == board.place(board.place(null, from), to);
	}
	
	@Override
	public boolean addPiece(Piece a, Position to){
		if(a.type == Piece.Type.piece && board.getType() == Game.Type.Chess) return false;
		if(a.type != Piece.Type.piece && board.getType() == Game.Type.Go) return false;
		if(!isMyPiece(a)) return false;
		if(board.inPool(a.getPos())) return false;
		if(!board.inPool(to)) return false;
		if(board.at(to) != null) return false;
		
		return null == board.place(a, to);
	}
	
	@Override
	public boolean removePiece(Position where){
		if(board.getType() != Game.Type.Go) return false;
		Piece r = board.at(where);
		if(r == null) return false;
		if(isMyPiece(r)) return false;
		
		return null != board.place(null, where);
	}
	
	@Override
	public boolean replacePiece(Position from, Position to){
		if(board.getType() != Game.Type.Chess) return false;
		if(from.equals(to)) return false;
		Piece a, b;
		a = board.at(from); b = board.at(to);
		if(a == null || b == null) return false;
		if(!isMyPiece(a) || isMyPiece(b)) return false;
		
		a = board.place(null, from);
		return null != board.place(a, to);
	}
	
	@Override
	public String toString(){
		return name + "(" +  (black ? "black": "white") + ")";
	}
}
