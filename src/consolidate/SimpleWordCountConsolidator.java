package consolidate;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import match.RadicalFinder;

public class SimpleWordCountConsolidator implements WordCountConsolidator {

	Map<String,WordCountConsolidationEntry> _consolidationMap = new HashMap<String, WordCountConsolidationEntry>();
	
	
	public Map<String, WordCountConsolidationEntry> getResult() {
		
		return _consolidationMap;
	}

	
	public void process(Map<String, Integer> wordCountResult, RadicalFinder radicalFinder)
	{
		for(Entry<String,Integer> entry : wordCountResult.entrySet() )
		{
			String radical = radicalFinder.getRadical(entry.getKey());
			if(_consolidationMap.containsKey(radical) )
			{
				_consolidationMap.get(radical).add(entry.getKey(), entry.getValue());
			}
			else
			{
				WordCountConsolidationEntry consolidationEntry = new WordCountConsolidationEntry();
				consolidationEntry.add(entry.getKey(), entry.getValue());
				_consolidationMap.put(radical, consolidationEntry);
			}
		}

	}

}
