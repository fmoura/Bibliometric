package filter;

import java.util.List;

public interface Filterer<T extends Comparable<?>> 
{
	void addFilter(Filter<T> filter);
	
	void removeFilter(Filter<T> filter);
	
	boolean filter(T object);
	
	List<T> filter(List<T> list);
	
}
