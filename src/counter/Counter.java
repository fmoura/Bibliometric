package counter;

import java.util.HashMap;
import java.util.Set;

public class Counter<C extends Comparable<?>> {
	
	HashMap<C, Integer> _countMap = new HashMap<C, Integer>();
	
	public void includeInCount(C word)
	{
		if(_countMap.containsKey(word))
		{
			_countMap.put(word, _countMap.get(word)+1);
		}
		else
		{
			_countMap.put(word, 1);
		}
	}
	
	public int getTotal(String word)
	{
		return _countMap.get(word);
	}
	
	public Set<C> getIncludedObjects()
	{
		return _countMap.keySet();
	}
	
	public HashMap<C, Integer> getResult()
	{
		return _countMap;
	}

}
