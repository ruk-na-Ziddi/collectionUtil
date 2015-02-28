import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

class AddTwoMapper implements Mapper<Integer,Integer> {
	public Integer mapperCallback(Integer ele){
		return ele+2;
	}
}

public class CollectionUtilTest{
	@Test
	public void map_returns_an_Integer_List_after_Sqauring_on_the_given_list(){
		Mapper<Integer,Integer> listMapper = new AddTwoMapper();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Integer> expected = new ArrayList<Integer>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		List<Integer> mapped = CollectionUtil.<Integer,Integer>map(numbers,listMapper);
		assertEquals(3,(int)mapped.get(0));
		assertEquals(4,(int)mapped.get(1));
		assertEquals(5,(int)mapped.get(2));
	}
}