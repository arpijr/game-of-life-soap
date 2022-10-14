package packages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestBase {

	protected static final String DEAD = "0";
	protected static final String ALIVE = "X";

	protected Map<Integer, List<String>> create3By3Map() {

		Map<Integer, List<String>> resultMap = new TreeMap<>();

		List<String> list_1 = new ArrayList<>();

		list_1.add("0");
		list_1.add("X");
		list_1.add("0");

		List<String> list_2 = new ArrayList<>();

		list_2.add("0");
		list_2.add("0");
		list_2.add("X");

		List<String> list_3 = new ArrayList<>();

		list_3.add("X");
		list_3.add("X");
		list_3.add("X");

		resultMap.put(1, list_1);
		resultMap.put(2, list_2);
		resultMap.put(3, list_3);

		return resultMap;

	}

	protected Map<Integer, List<String>> createNByNMapOfDead(int N) {

		Map<Integer, List<String>> resultMap = new TreeMap<>();

		for (int i = 0; i < N; i++) {
			List<String> tmpList = new ArrayList<String>();
			for (int j = 0; j < N; j++)
				tmpList.add(DEAD);
			resultMap.put(i, tmpList);

		}

		return resultMap;

	}

}
