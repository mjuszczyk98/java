package game;

import java.io.FileReader;
import java.util.Scanner;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

enum Player {
	Empty(0),
	Player(1),
	Computer(2);
	private int value;
	
	public int getValue() {
		return this.value;
	}
	
	private Player(int value) 
    { 
        this.value = value;
    } 
}

public class Game {
	static {
		System.loadLibrary("native");
	}
	
	private static final int COLUMNS = 5;
	private static final int ROWS = 5;
	private static final int FIELDS = COLUMNS * ROWS;
	private static final int TO_WIN = 4;
	
	private boolean workOnC = false;
	
	private int[] gameBoard;
	private Scanner scanner;
	int moves = 0;
	Invocable invocable;

	public static void main(String[] args) throws Exception {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval(new FileReader("script.js"));
		
		new Game((Invocable) engine).start();

	}
	
	public Game(Invocable invocable) {
		gameBoard = new int[FIELDS];
		for(int i = 0; i < FIELDS; i++) {
			gameBoard[i] = Player.Empty.getValue();
		}
		scanner = new Scanner(System.in);
		this.invocable = invocable;
	}
	
	private native int nextMove(int[] gameBoard);
	
	public void start() throws Exception {
		Player player = Player.Player;
		while(true) {
			int move = 0;
			if(moves == FIELDS) {
				write();
				System.out.println("Duel");
				break;
			}
			if(player == Player.Player) {
				write();
				System.out.print(player + "'s move: ");
				move = scanner.nextInt();
				if(move == -1) {
					workOnC = !workOnC;
					continue;
				}
				if(!move(player, move)) {
					continue;
				}
			} else {
				if(workOnC) {
					move(player, nextMove(gameBoard));
				} else {
					double tmp = (Double)(invocable.invokeFunction("nextMove", gameBoard));
					move = (int) tmp;
					move(player, move);
				}
			}
			if(won(player, move)) {
				write();
				System.out.println(player + " won");
				break;
			}
			player = player == Player.Player ? Player.Computer : Player.Player;
		}
	}
	
	private boolean move(Player player, int move) {
		if(move > FIELDS -1 || move < 0)
			return false;
		if(gameBoard[move] != Player.Empty.getValue()) {
			return false;
		}
		gameBoard[move] = player.getValue();
		moves++;
		return true;
	}
	
	private boolean won(Player player, int move) {
		int column = (move) % COLUMNS;
		int counter = 0;
		for(int i = 0; i < COLUMNS; i++) {
			if(gameBoard[column + i * COLUMNS] == player.getValue()) {
				counter++;
				if(counter >= TO_WIN) {
					return true;
				}
			}
			else {
				counter = 0;
			}
		}
		
		int row = (int)(move / ROWS);
		counter = 0;
		
		for(int i = 0; i < ROWS; i++) {
			if(gameBoard[row * COLUMNS + i] == player.getValue()) {
				counter++;
				if(counter == TO_WIN) {
					return true;
				}
			}
			else {
				counter = 0;
			}
		}
		
		int tmpCol = column;
		int tmpRow = row;
		
		if(tmpCol < tmpRow) {
			tmpRow -= tmpCol;
			tmpCol = 0;
		} else {
			tmpCol -= tmpRow;
			tmpRow = 0;
		}
		
		if(Math.abs(tmpCol - tmpRow) <= COLUMNS - TO_WIN) {
			counter = 0;
			while(tmpCol < COLUMNS && tmpRow < ROWS) {
				if(gameBoard[tmpRow * COLUMNS + tmpCol] == player.getValue()) {
					counter++;
					if(counter == TO_WIN) {
						return true;
					}
				}
				else {
					counter = 0;
				}
				tmpCol--;
				tmpRow++;
			}
		}
		
		tmpCol = COLUMNS - column - 1;
		tmpRow = row;
		
		int tmp = Math.min(tmpCol, tmpRow);
		
		tmpCol = column + tmp;
		tmpRow -= tmp;
		
		if(Math.abs(tmpCol - tmpRow) >= TO_WIN - 1) {
			counter = 0;
			while(tmpCol >= 0 && tmpRow < ROWS) {
				if(gameBoard[tmpRow * COLUMNS + tmpCol] == player.getValue()) {
					if(++counter == TO_WIN) {
						return true;
					}
				}
				else {
					counter = 0;
				}
				tmpCol--;
				tmpRow++;
			}
		}
		return false;
	}

	private void write() {
		for(int i = 0; i < ROWS; i++) {
			String line = "";
			for(int j = 0; j < COLUMNS; j++) {
				line += gameBoard[i * COLUMNS + j] == 0 ? "? " : gameBoard[i * COLUMNS + j] == 1 ? "X " : "O ";
			}
			System.out.println(line);
		}
	}
}
