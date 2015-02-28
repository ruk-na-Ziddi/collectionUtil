import java.util.*;

interface Mapper<E,K> {
	K mapperCallback(E element);
}

interface Filter<E> {
	boolean filterCallback(E element);
}

public class CollectionUtil {
	public static<E,K> List<K> map(List<E> list, Mapper<E,K> listMapper) {
		List<K> mapped = new ArrayList<K>();
		for(E number : list){
			mapped.add(listMapper.mapperCallback(number));
		}
		return mapped;
	}

	public static<E> List<E> filter(List<E> list, Filter<E> listFilter) {
		List<E> filtered = new ArrayList<E>();
		for(E number : list){
			if((boolean)listFilter.filterCallback(number)) filtered.add(number);
		}
		return filtered;
	}

}