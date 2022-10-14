package packages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.jupiter.api.Test;

import packages.calculations.GameOfLifeRequestProcessor;
import packages.data.converter.BoardConverter;
import packages.data.model.Board;
import packages.service.jaxb.GetTableStateRequest;
import packages.service.jaxb.MapAdapter;

public class GameBoardTests extends TestBase {

	@Test
	public void testJAXBMarshalling() throws JAXBException {

		JAXBContext jc = JAXBContext.newInstance(GetTableStateRequest.class);

		GetTableStateRequest getTableStateRequest = new GetTableStateRequest();

		getTableStateRequest.setSteps(5);
		getTableStateRequest.setTableMap(create3By3Map());

		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setAdapter(new MapAdapter());
		marshaller.marshal(getTableStateRequest, System.out);

	}

	@Test
	public void testTableConverter() throws JAXBException {

		int[][] rawBoard = BoardConverter.convertMapTo2DArray(create3By3Map(), ALIVE);

		Board board = new Board();
		board.setRawBoard(rawBoard);
		System.out.println(board.toString(DEAD, ALIVE));

		BoardConverter.displayRawIntBoard(rawBoard);

	}

	@Test
	public void CountAliveNeigbours_GivesCorrectValue_With1235Neighbours() throws JAXBException {

		// given
		int[][] rawBoard = BoardConverter.convertMapTo2DArray(create3By3Map(), ALIVE);

		int neighbors_1 = 0;
		int neighbors_2 = 0;
		int neighbors_3 = 0;
		int neighbors_5 = 0;

		// BoardConverter.displayRawIntBoard(rawBoard);

		// when
		neighbors_1 = GameOfLifeRequestProcessor.countAliveNeigbours(rawBoard, 0, 1);
		neighbors_2 = GameOfLifeRequestProcessor.countAliveNeigbours(rawBoard, 2, 2);
		neighbors_3 = GameOfLifeRequestProcessor.countAliveNeigbours(rawBoard, 1, 2);
		neighbors_5 = GameOfLifeRequestProcessor.countAliveNeigbours(rawBoard, 1, 1);

		// then
		assertEquals(1, neighbors_1);
		assertEquals(2, neighbors_2);
		assertEquals(3, neighbors_3);
		assertEquals(5, neighbors_5);

	}

	@Test
	public void CalculateNextGen_CalculatesCorrectState_After1Step() throws JAXBException {

		// given
		int[][] rawBoard = BoardConverter.convertMapTo2DArray(createNByNMapOfDead(10), ALIVE);

		rawBoard[4][4] = 1;
		rawBoard[5][4] = 1;
		rawBoard[6][4] = 1;

		// when
		int[][] nextGen = GameOfLifeRequestProcessor.calculateNextGen(rawBoard);

		// then
		assertEquals(1, nextGen[5][3]);
		assertEquals(1, nextGen[5][4]);
		assertEquals(1, nextGen[5][5]);

	}

	@Test
	public void CalculateFutureNSteps_CalculatesCorrectState_After2Steps() throws JAXBException {

		// given
		int[][] rawBoard = BoardConverter.convertMapTo2DArray(createNByNMapOfDead(10), ALIVE);

		rawBoard[4][4] = 1;
		rawBoard[5][4] = 1;
		rawBoard[6][4] = 1;

		// when
		int[][] nextGen = GameOfLifeRequestProcessor.calculateFutureNSteps(rawBoard, 2);

		// then
		assertEquals(1, nextGen[4][4]);
		assertEquals(1, nextGen[5][4]);
		assertEquals(1, nextGen[6][4]);

	}
	
}
