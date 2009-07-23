package consolidate;

import java.util.Map;

import match.RadicalFinder;

public interface WordCountConsolidator 
{
	public void process(Map<String,Integer> wordCountResult, RadicalFinder radicalFinder);
	
	public Map<String,WordCountConsolidationEntry> getResult();

}
