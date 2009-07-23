package filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AndFilterer<T extends Comparable<?>> implements Filterer<T> {

	private Set<Filter<T>> _filters = new HashSet<Filter<T>>(); 
	
	
	public void addFilter(Filter<T> filter) {
		_filters.add(filter);
		
	}

	
	public List<T> filter(List<T> list) {
		List<T> result = new ArrayList<T>();
		for(T comparableObject : list)
		{
			if(filter(comparableObject))
			{
				result.add(comparableObject);
			}
		}
		return result;
		
	}

	
	public void removeFilter(Filter<T> filter) {
		_filters.remove(filter);
		
	}


	
	public boolean filter(T object) {
		boolean include = true;
		for(Filter<T> filter : _filters)
		{
			if(!filter.filter(object))
			{
				include = false;
			}
		}
		return include;
	}

}
