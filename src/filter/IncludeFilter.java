package filter;

import java.util.Set;


public class IncludeFilter<C extends Comparable<?>> implements Filter<C> {

	private Set<C> _includeSet;

	public IncludeFilter(Set<C> includeSet)
	{
		_includeSet = includeSet;
	}
	
	public boolean filter(C object)
	{
		return _includeSet.contains(object);
	}

}
