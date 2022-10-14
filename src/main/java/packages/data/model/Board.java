package packages.data.model;

import org.springframework.stereotype.Component;

@Component
public class Board {

	private int[][] rawBoard;

	public Board() {
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < rawBoard.length; i++) { // this equals to the row in our matrix.
			for (int j = 0; j < rawBoard[i].length; j++) { // this equals to the column in each row.
				sb.append(rawBoard[i][j] + " ");
			}
			sb.append("\n"); // change line on console as row comes to end in the matrix.

		}

		return sb.toString();

	}

	public String toString(String dead, String alive) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < rawBoard.length; i++) { // this equals to the row in our matrix.
			for (int j = 0; j < rawBoard[i].length; j++) { // this equals to the column in each row.
				if (rawBoard[i][j] == 1)
					sb.append(alive + " ");
				else
					sb.append(dead + " ");
			}
			sb.append("\n"); // change line on console as row comes to end in the matrix.

		}

		return sb.toString();

	}

	public int[][] getRawBoard() {
		return rawBoard;
	}

	public void setRawBoard(int[][] rawBoard) {
		this.rawBoard = rawBoard;
	}

	
	
}
