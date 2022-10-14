package packages.service.jaxb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MapAdapter extends XmlAdapter<packages.service.jaxb.MapAdapter.ListOfEntry, Map<Integer, List<String>>> {
	@Override
	public Map<Integer, List<String>> unmarshal(ListOfEntry loe) throws Exception {
		Map<Integer, List<String>> map = new TreeMap<>();
		for (Entry entry : loe.getList()) {
			map.put(entry.getKey(), entry.getList());
		}
		return map;
	}

	@Override
	public ListOfEntry marshal(Map<Integer, List<String>> map) throws Exception {
		ListOfEntry loe = new ListOfEntry();
		for (Map.Entry<Integer, List<String>> mapEntry : map.entrySet()) {
			Entry entry = new Entry();
			entry.setKey(mapEntry.getKey());
			entry.getList().addAll(mapEntry.getValue());
			loe.getList().add(entry);
		}
		return loe;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ListOfEntry {
		@XmlElement(namespace = "http://arpi.io/game/gol-web-service")
		private List<Entry> list = new ArrayList<>();

		public List<Entry> getList() {
			return list;
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Entry {
		@XmlElement(namespace = "http://arpi.io/game/gol-web-service")
		private Integer key;
		@XmlElement(namespace = "http://arpi.io/game/gol-web-service")
		private List<String> list = new ArrayList<>();

		public Integer getKey() {
			return key;
		}

		public void setKey(Integer value) {
			key = value;
		}

		public List<String> getList() {
			return list;
		}
	}
}