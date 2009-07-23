package match;

import java.util.Set;

public class SimpleRadicalFinder implements RadicalFinder {

	
	private Set<String> _infixesList;
	private Set<String> _sufixesList;
	private Set<String> _prefixesList;
	
	public SimpleRadicalFinder(Set<String> prefixesSet, Set<String> infixesSet, Set<String> sufixesSet) 
	{
		_prefixesList = prefixesSet;
		_infixesList = infixesSet;
		_sufixesList = sufixesSet;
	}

	public String getRadical(String word)
	{
		
		String result = delPrefix(word);
		result = delSufix(result);
		result = delInfix(result);
		
		return result;
	}

	private String delInfix(String word)
	{
		String auxiliar = word.toLowerCase();
		for(String infix : _infixesList)
		{
			if(auxiliar.contains(infix.toLowerCase()))
			{
				auxiliar = auxiliar.replace(infix.toLowerCase(), "");
				break;
			}
		}
		return auxiliar;
	}

	private String delSufix(String word) {
		String auxiliar = word.toLowerCase();
		for(String sufix : _sufixesList)
		{
			if(auxiliar.endsWith(sufix.toLowerCase()))
			{
				auxiliar = auxiliar.substring(0, auxiliar.indexOf(sufix.toLowerCase()));
				break;
			}
		}
		return auxiliar;
	}

	private String delPrefix(String word) {
		String auxiliar = word.toLowerCase();
		for(String prefix : _prefixesList)
		{
			if(auxiliar.endsWith(prefix.toLowerCase()))
			{
				auxiliar = auxiliar.replace(prefix.toLowerCase(), "");
				break;
			}
		}
		return auxiliar;
	}

}
