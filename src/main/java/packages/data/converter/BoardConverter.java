package packages.data.converter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardConverter {

	private static final Logger LOGGER = LoggerFactory.getLogger(BoardConverter.class);

	public static int[][] convertMapTo2DArray(Map<Integer, List<String>> input, String aliveSymbol) {

		int size = input.keySet().size();

		int[][] result = new int[size][size];

		LOGGER.info("converting request data into Table object");

		int count = 0;
		for (Integer k : input.keySet()) {
			for (int i = 0; i < input.get(k).size(); i++) {
				if (input.get(k).get(i).equals(aliveSymbol))
					result[count][i] = 1;
				else
					result[count][i] = 0;
			}
			count++;
		}

		return result;

	}

	public static Map<Integer, List<String>> convert2DArrayToMap(int[][] input, String aliveSymbol, String deadSymbol) {

		Map<Integer, List<String>> result = new TreeMap<Integer, List<String>>();

		for (int i = 0; i < input.length; i++) {
			List<String> list = new LinkedList<String>();
			for (int j = 0; j < input[i].length; j++) {
				if (input[i][j] == 1)
					list.add(aliveSymbol);
				else
					list.add(deadSymbol);
			}
			result.put(i, list);
		}

		return result;

	}

	public static void displayRawIntBoard(int[][] input) {

		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				System.out.print(input[i][j] + " ");
			}
			System.out.println();
		}

	}

}
