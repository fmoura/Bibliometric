package filter;

import java.util.Set;


public class ExcludeFilter<C extends Comparable<?>> implements Filter<C>
{
	
	private Set<C> _excludeList;
	
	public ExcludeFilter(Set<C> excludeList) {
		_excludeList = excludeList;
	}

	@Override
	public boolean filter(C object) {
		
		return !_excludeList.contains(object);
	}

}
