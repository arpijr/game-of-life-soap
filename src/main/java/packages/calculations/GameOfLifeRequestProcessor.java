package packages.calculations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import packages.data.converter.BoardConverter;
import packages.data.model.Board;
import packages.service.jaxb.GetTableStateRequest;
import packages.service.jaxb.GetTableStateResponse;

@Component
public class GameOfLifeRequestProcessor {


	private static final String DEAD = "O";
	private static final String ALIVE = "X";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GameOfLifeRequestProcessor.class);
	
	public GameOfLifeRequestProcessor() {
	}

	private Board calculateSteps(Board board, int steps) {

		Board resultGameBoard = new Board();
		System.out.println("calculateSteps");
		
		resultGameBoard.setRawBoard(calculateFutureNSteps(board.getRawBoard(), steps));
		
		/*
		 * logic goes here
		 */

		return resultGameBoard;

	}

	public static int countAliveNeigbours(int rawBoard[][], int l, int m) {

		int aliveNeighbours = 0;
		int boardSize = rawBoard.length;

		// finding no Of Neighbors that are alive
		aliveNeighbours = 0;
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				if ((l + i >= 0 && l + i < boardSize) && (m + j >= 0 && m + j < boardSize))
					aliveNeighbours += rawBoard[l + i][m + j];

		aliveNeighbours -= rawBoard[l][m];

		return aliveNeighbours;
	}

	public static int[][] calculateNextGen(int rawBoard[][]) {

		int boardSize = rawBoard.length;
		int[][] nextGen = new int[boardSize][boardSize];

			for (int l = 0; l < boardSize; l++) {
				for (int m = 0; m < boardSize; m++) {
					int aliveNeigbours = countAliveNeigbours(rawBoard, l, m);
					// Cell is lonely and dies
					if ((rawBoard[l][m] == 1) && (aliveNeigbours < 2))
						nextGen[l][m] = 0;

					// Cell dies due to over population
					else if ((rawBoard[l][m] == 1) && (aliveNeigbours > 3))
						nextGen[l][m] = 0;

					// A new cell is born
					else if ((rawBoard[l][m] == 0) && (aliveNeigbours == 3))
						nextGen[l][m] = 1;

					// Remains the same
					else
						nextGen[l][m] = rawBoard[l][m];
				}

			}

			LOGGER.info("calculated next generation of grid:");
			BoardConverter.displayRawIntBoard(nextGen);
		return nextGen;
	}
	
	public static int[][] calculateFutureNSteps(int grid [][], int n) {
		
		int boardSize = grid.length;
		int[][] future = new int[boardSize][boardSize];
		
		for(int i = 0; i < n; i++) {
			System.out.println("calculating step");
			future = calculateNextGen(grid);
			grid=future;	
		}
		
		return future;
		
	}

	public GetTableStateResponse processGetTableStateRequest(GetTableStateRequest request) {

		GetTableStateResponse response = new GetTableStateResponse();
		Board resultGameTable = new Board();
		Board gameTable = new Board();

		gameTable.setRawBoard(BoardConverter.convertMapTo2DArray(request.getTableMap(), ALIVE));
		resultGameTable = calculateSteps(gameTable, request.getSteps());
		response.setTableMap(BoardConverter.convert2DArrayToMap(resultGameTable.getRawBoard(), ALIVE, DEAD));

		return response;
	}

}
