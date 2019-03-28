public class Board {
	private Piece[][] pool;
	private Game.Type type;
	
	private void clear(int n){
		pool = new Piece[n][n];
	}
	
	public Board(Game.Type type, Player p1, Player p2){
		this.type = type;
		switch(type) {
			case Go:
				clear(18);
				break;
			case Chess:
				clear(8);
				initChess(p1, p2);
				break;
		}
	}
	
	public boolean inPool(Position where){
		int n = pool.length;
		return where.x >= 0 && where.y >= 0 &&
				where.y < n && where.x < n;
	}
	
	public Piece at(Position where){
		if(inPool(where)) return Piece.copy(pool[where.x][where.y]);
		else return null;
	}
	
	public Piece place(Piece a, Position where){
		Piece r;
		if(a != null) a.setPos(new Position(where.x, where.y));
		
		if(inPool(where)){
			r = pool[where.x][where.y];
			if(r != null) r.setPos(new Position(-1, -1));
		}
		else r = null;
		
		pool[where.x][where.y] = a;
		return r;
	}
	
	private void initChess(Player white, Player black){
		Piece.Type[] order = {Piece.Type.rock, Piece.Type.horse, Piece.Type.bishop, Piece.Type.king,
				Piece.Type.queen, Piece.Type.bishop, Piece.Type.horse, Piece.Type.rock};
		
		for(int i = 0; i < 8; i++){
			pool[0][i] = new Piece(order[i], white);
			pool[1][i] = new Piece(Piece.Type.soldier, white);
			
			pool[7][i] = new Piece(order[i], black);
			pool[6][i] = new Piece(Piece.Type.soldier, black);
			
			pool[0][i].setPos(new Position(0, i));
			pool[1][i].setPos(new Position(1, i));
			pool[6][i].setPos(new Position(6, i));
			pool[7][i].setPos(new Position(7, i));
		}
	}
	
	public Game.Type getType(){
		return type;
	}
	
	@Override
	public String toString(){
		int n = pool.length;
		StringBuilder r = new StringBuilder();
		//r.append("<colgroup><col span=\"" + n + "\" width=\"250\"></colgroup>");
		for (int i = n - 1; i >= 0; i--) {
			r.append("<tr>");
			for (Piece piece : pool[i]) {
				r.append("<td>");
				if(piece == null) r.append("&emsp");
				else r.append(piece.toString());
				r.append("</td>");
			}
			r.append("</tr>");
		}
		
		return r.toString();
	}
}
