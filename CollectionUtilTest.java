import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

class AddTwoMapper implements Mapper<Integer,Integer>{
	public Integer mapperCallback(Integer number){
		return number+2;
	}
}

class AddABCMapper implements Mapper<String,String>{
	public String mapperCallback(String string){
		return string+"ABC";
	}
}

class EvenFilter implements Filter<Integer>{
	public boolean filterCallback(Integer number){
		return number%2==0;
	}
}

class AddAllReducer implements Reducer<Integer,Integer>{
	public Integer reduceCallback(Integer pv,Integer cv){
		return pv+cv;
	}
}

class AddAllCharsReducer implements Reducer<Character,String>{
	public String reduceCallback(String pv,Character cv){
		return pv+(""+cv);
	}
}

public class CollectionUtilTest{
	@Test
	public void map_returns_an_Integer_List_of_each_element_increment_by_2(){
		Mapper<Integer,Integer> listMapper=new AddTwoMapper();
		List<Integer> numbers=new ArrayList<Integer>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		List<Integer> mapped=CollectionUtil.<Integer,Integer>map(numbers,listMapper);
		assertEquals(3,(int)mapped.get(0));
		assertEquals(4,(int)mapped.get(1));
		assertEquals(5,(int)mapped.get(2));
	}

	@Test
	public void map_returns_an_Integer_List_of_each_element_added_ABC_to_it(){
		Mapper<String,String> listMapper=new AddABCMapper();
		List<String> strings=new ArrayList<String>();
		strings.add("abc");
		strings.add("def");
		strings.add("ghi");
		List<String> mapped=CollectionUtil.<String,String>map(strings,listMapper);
		assertEquals("abcABC",(String)mapped.get(0));
		assertEquals("defABC",(String)mapped.get(1));
		assertEquals("ghiABC",(String)mapped.get(2));
	}

	@Test
	public void filter_returns_an_Integer_List_of_even_elements(){
		Filter<Integer> listFilter=new EvenFilter();
		List<Integer> numbers=new ArrayList<Integer>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		List<Integer> filtered=CollectionUtil.<Integer>filter(numbers,listFilter);
		assertEquals(2,(int)filtered.get(0));
		assertEquals(4,(int)filtered.get(1));
	}

	@Test
	public void reduce_gives_added_value_of_all_elements_of_list(){
		Reducer<Integer,Integer>listReducer=new AddAllReducer();
		List<Integer> numbers=new ArrayList<Integer>();
		Integer initialValue=0;
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		Integer sumOfAllEle=CollectionUtil.<Integer,Integer>reduce(numbers,listReducer,initialValue);
		assertEquals(15,(int)sumOfAllEle);
	}

	@Test
	public void reduce_gives_string_by_adding_all_characters_of_list(){
		Reducer<Character,String>listReducer=new AddAllCharsReducer();
		List<Character> characters=new ArrayList<Character>();
		String initialValue="";
		characters.add('a');
		characters.add('b');
		characters.add('c');
		String stringOfAllEle=CollectionUtil.<Character,String>reduce(characters,listReducer,initialValue);
		assertEquals("abc",(String)stringOfAllEle);
	}
}