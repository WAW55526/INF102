package INF102.h21.triplicate;

import java.util.Collections;
import java.util.List;

/**
 * Sorts the list and looks for 3 adjacent objects
 * 
 * @author willi
 *
 * @param <T>
 */
public class MyFantasticTriplicateFinder<T extends Comparable<T>> implements ITriplicate<T> {

	@Override
	public T findTriplicate(List<T> list) {
		Collections.sort(list);
		for(int i = 2; i < list.size(); i++) {
			if(list.get(i).equals(list.get(i-1)) && list.get(i).equals(list.get(i-2))) {
				return list.get(i);
			}
		}
		return null;
	}

}
