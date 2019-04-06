public class Game {
	public enum Type{
		Go, Chess
	}
	
	private Board board;
	private Player P1, P2;
	private String current;
	
	Game(Type type, String p1, String p2){
		P1 = new Player(false, p1);
		P2 = new Player(true, p2);
		board = new Board(type, P1, P2);
		P1.changeBoard(board);
		P2.changeBoard(board);
	}
	
	Player[] getPlayer(){
		assert P1 != null && P2 != null;
		return new Player[]{P1, P2};
	}
	
	@Override
	public String toString(){
		return "<html><table border = \"1\"><caption>Current: " + current + "\n</caption>" +
				board.toString() + "</table></html>";
	}
	
	void setCurrent(String name){
		current = name;
	}
	
	static Game.Type cast_GameType(int type) throws IllegalArgumentException{
		switch(type){
			case 1: return Type.Chess;
			case 2: return Type.Go;
			default: throw new IllegalArgumentException(String.valueOf(type));
		}
	}
}
