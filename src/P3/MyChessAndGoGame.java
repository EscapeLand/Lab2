import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MyChessAndGoGame {
	static Game game;
	static List<String> history = new ArrayList<>();
	static String P1, P2;
	static String[] op = new String[]{"add", "move", "remove", "replace"};
	enum cmdEnum{
		retval, type, a, b
	}
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("Player1: ");
		P1 = scan.next();
		System.out.print("Player2: ");
		P2 = scan.next();
		System.out.print("select a game: [1/2]\n1. Chess\n2. Go\n");
		
		Game.Type ty;
		try{
			ty = Game.cast_GameType(scan.nextInt());
			play(ty);
		}
		catch(IllegalArgumentException ex){
			System.out.println(ex.getMessage());
			return;
		}
		
		scan.close();
		
		for(String i: history){
			System.out.println(i);
		}
	}
	
	static void play(Game.Type type){
		JFrame wnd = new JFrame(type.toString());
		switch (type){
			case Chess: wnd.setBounds(600, 150, 360, 420); break;
			case Go: wnd.setBounds(400, 40, 720, 840); break;
		}
		
		wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wnd.setAlwaysOnTop(!wnd.isAlwaysOnTop());
		JPanel pnl = new JPanel();
		wnd.add(pnl);
		JLabel lbl = new JLabel();
		lbl.setFont(new Font(null, Font.PLAIN, 22));
		pnl.add(lbl);
		
		wnd.setVisible(true);
		Scanner scan = new Scanner(System.in);
		game = new Game(type, P1, P2);
		Player[] P = game.getPlayer();
		menu(type);
		
		for(int i = 0;; i = 1 - i) {
			game.setCurrent(P[i].toString());
			lbl.setText(game.toString());
			
			String cmd = scan.nextLine();
			if(cmd.equalsIgnoreCase("end")) break;
			
			EnumMap<cmdEnum, Object> r = parseCmd(cmd);
			if(r.isEmpty()){
				i = 1 - i;
				continue;
			}
			
			boolean flag = false;
			Piece.Type ty = (Piece.Type) r.get(cmdEnum.type);
			Position a = ((Position) r.get(cmdEnum.a)), b = ((Position) r.get(cmdEnum.b));
			
			switch(((Integer) r.get(cmdEnum.retval))){
				case 0:         //skip
					flag = true;
					break;
				case 1:         //add
					flag = P[i].addPiece(new Piece(ty, P[i]), a);
					break;
				case 2:         //move
					flag = P[i].movePiece(a, b);
					break;
				case 3:         //remove
					flag = P[i].removePiece(a);
					break;
				case 4:
					flag = P[i].replacePiece(a, b);
					break;
				case -1:
					System.out.println("Wrong operation: " + cmd);
					i = 1 - i;
					continue;
			}
			
			if(flag){
				history.add(P[i].toString() + ": " + cmd);
			}
			else {
				history.add(P[i].toString() + ": " + cmd);
				System.out.println("cannot " + op[((Integer) r.get(cmdEnum.retval)) - 1] + " the piece, try again. ");
				i = 1 - i;
			}
		}
		
		wnd.dispose();
	}
	
	static EnumMap<cmdEnum, Object> parseCmd(String cmd) throws NumberFormatException{
		List<String> org = new ArrayList<>(Arrays.asList(cmd.split("[,\\s]")));
		org.removeIf(x -> x.isEmpty());
		String[] ops = org.toArray(new String[org.size()]);
		EnumMap<cmdEnum, Object> ret = new EnumMap<cmdEnum, Object>(cmdEnum.class);
		int r = -1;
		
		if(ops.length == 0) return ret;
		switch(ops[0]){
			case "1": case "add": r = 1; break;
			case "2": case "move": r = 2; break;
			case "remove": r= 3; break;
			case "replace": r = 4; break;
			case "0": case "skip": r = 0; break;
			default: ret.put(cmdEnum.retval, r); return ret;
		}
		
		switch(ops.length){
			case 1:
				if(r != 0) r = -1;
				break;
			case 3:
				if(r != 3) r = -1;
				else ret.put(cmdEnum.a, new Position(Integer.valueOf(ops[1]), Integer.valueOf(ops[2])));
				break;
			case 4:
				if(r != 1) r = -1;
				else try{
					ret.put(cmdEnum.type, Piece.cast_Type(ops[1]));
					ret.put(cmdEnum.a, new Position(Integer.valueOf(ops[2]), Integer.valueOf(ops[3])));
				}
				catch (IllegalArgumentException ex){
					r = -1;
				}
				break;
			case 5:
				if(r == 2 || r == 4){
					ret.put(cmdEnum.a, new Position(Integer.valueOf(ops[1]), Integer.valueOf(ops[2])));
					ret.put(cmdEnum.b, new Position(Integer.valueOf(ops[3]), Integer.valueOf(ops[4])));
				}
				else r = -1;
				break;
		}
		
		ret.put(cmdEnum.retval, r);
		return ret;
	}
	
	static void menu(Game.Type ty){
		switch (ty){
			case Go: System.out.print("1. add Type P0\n2. move P1, P2\n3. replace P1, P2\n0. skip");
			break;
			case Chess: System.out.print("1. add Type P0\n2. move P1, P2\n3. remove P0\n0. skip");
			break;
		}
		System.out.print("\n\n\n");
	}
	
}
