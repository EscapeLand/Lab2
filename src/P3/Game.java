public class Game {
	public enum Type{
		Go, Chess
	}
	
	private Board board;
	private Player P1 = null, P2 = null;
	private String current;
	
	public Game(Type type, String p1, String p2){
		P1 = new Player(false, p1);
		P2 = new Player(true, p2);
		board = new Board(type, P1, P2);
		P1.changeBoard(board);
		P2.changeBoard(board);
	}
	
	public Player[] getPlayer(){
		assert P1 != null && P2 != null;
		return new Player[]{P1, P2};
	}
	
	@Override
	public String toString(){
		StringBuilder r = new StringBuilder();
		r.append("<html><table border = \"1\"><caption>Current: ").append(current).append("\n</caption>");
		r.append(board.toString()).append("</table></html>");
		
		return r.toString();
	}
	
	public void setCurrent(String name){
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
