import java.util.*;

interface Mapper<E,K> {
	K mapperCallback(E number);
}

public class CollectionUtil {
	public static<E,K> List<K> map(List<E> list, Mapper<E,K> listMapper) {
		List<K> mapped = new ArrayList<K>();
		for(E number : list){
			mapped.add(listMapper.mapperCallback(number));
		}
		return mapped;
	}
	
}