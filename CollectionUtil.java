import java.util.*;

interface Mapper<E,K>{
	K mapperCallback(E element);
}

interface Filter<E>{
	boolean filterCallback(E element);
}

interface Reducer<E,K>{
	K reduceCallback(K pv, E cv);
}

public class CollectionUtil{
	public static<E,K> List<K> map(List<E> list, Mapper<E,K> listMapper){
		List<K> mapped=new ArrayList<K>();
		for(E element : list){
			mapped.add(listMapper.mapperCallback(element));
		}
		return mapped;
	}

	public static<E> List<E> filter(List<E> list, Filter<E> listFilter){
		List<E> filtered=new ArrayList<E>();
		for(E element : list){
			if((boolean)listFilter.filterCallback(element)) filtered.add(element);
		}
		return filtered;
	}

	public static<E,K> K reduce(List<E> list, Reducer<E,K> listReducer,K initialValue){
		K returnValue=null;
		for(E element : list) {
			initialValue=listReducer.reduceCallback(initialValue,element);
		}
		returnValue=initialValue;
		return returnValue;
	}

}