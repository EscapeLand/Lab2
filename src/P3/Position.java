

public class Position {
	public final int x, y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object x){
		if(this == x) return true;
		else if(x == null) return false;
		else if(this.getClass() != x.getClass()) return false;
		
		Position that = (Position) x;
		return that.x == this.x && that.y == this.y;
	}
	
	@Override
	public String toString(){
		return "( " + x + ", " + y + ") ";
	}
}
