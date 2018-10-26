package amc.mb.rsassociations.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface WebEnum {

	String name();

	String getDescription();

	public static abstract class Helper {

		public static List<Map<String, String>> getValueDescriptionMap(Class<? extends WebEnum> enumClass) {
			List<Map<String, String>> result = new ArrayList<>();

			Arrays.asList(enumClass.getEnumConstants()).forEach(entry -> {
				Map<String, String> map = new HashMap<>();
				map.put("value", entry.name());
				map.put("label", ((WebEnum) entry).getDescription());
				result.add(map);
			});

			return result;
		}
	}
}
