package consolidate;

import java.util.HashSet;
import java.util.Set;

public class WordCountConsolidationEntry
{
	int _total = 0;
	Set<String> _words = new HashSet<String>();
	
	void add(String word, int count)
	{
		if(!_words.contains(word))
		{
			_total += count;
			_words.add(word);
		}
	}
	
	public int getTotal()
	{
		return _total;
	}
	
	public Set<String> getConsideredWords()
	{
		return _words;
	}
}
