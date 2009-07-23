package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import match.RadicalFinder;
import match.SimpleRadicalFinder;

import consolidate.SimpleWordCountConsolidator;
import consolidate.WordCountConsolidationEntry;
import consolidate.WordCountConsolidator;

import counter.Counter;

import filter.AndFilterer;
import filter.ExcludeFilter;
import filter.Filter;

public class MainCountTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String title = "Planning of disposal sites in daressalaam tanzania a decision support system approach";
		String resume = "A system is developed aiming at supporting the planners in several aspects of solid waste management in developing countries The system is tailored especially to planning of disposal sites in daressalaam tanzania based on a decision support system framework emphasis was placed on applicability for users without particular computer knowledge a simple and comprehensible system for solid waste planners to use stressing environmental impact and introduction of subjective judjements as an important element of problem formulation and problem solving the involved decision processes are elaborated the entire system is implemented within a lotus123 spreadsheet environment the developed model illustrates that it is possible to include and emphasize several features that are lacking in many existing commercial computerized models.";
		String citation = "Waste plan the solid waste management planning tool masterplan on solid waste management for daressalaam microcomputer software in municipal solid waste management a review of programs and issues for developing countries technical supplements daressalaam masterplan solid waste planning in tanzania a decision support system for daressalaam operational research-for developing countries a case of transfer of technology an illustrated description of harbinger computerized tools for decision making haste management planning evaluation technologies urban solid waste management in tanzania environmental epidemiology in a market place waste disposal in kairakoo market scavenging of solid wastes in daressalaam tanzania africa's waste dilemma computerized tools for solid wastes decision making process.";
	
		String clearedText = clearSymbols(title);
		
		List<String> wordList = convertToWordList(clearedText);
				
		List<String> filteredList = applyStopList(wordList );
		
		HashMap<String, Integer> countResult = countWords(filteredList);
		
		Map<String, WordCountConsolidationEntry> result = consolidate(countResult);
		
		printOutResult(result);

	}

	private static String clearSymbols(String text) {
		
		Set<Character> symbols = initSymbolsList();
		
		StringBuffer buffer = new StringBuffer(text);
		
		for(int i = 0 ; i < buffer.length() ; i ++)
		{
			if(symbols.contains(buffer.charAt(i)))
			{
				buffer.setCharAt(i, ' ');
			}
		}
		
		return buffer.toString();
	}

	private static Set<Character> initSymbolsList() {
		Set<Character> symbols = new HashSet<Character>();
		symbols.add('!');
		symbols.add('?');
		symbols.add('~');
		symbols.add('|');
		symbols.add('.');
		symbols.add(',');
		symbols.add(';');
		//symbols.add('\'');
		symbols.add('\"');
		symbols.add('\\');
		symbols.add('/');
		symbols.add(':');
		symbols.add('!');
		symbols.add('(');
		symbols.add(')');
		symbols.add('[');
		symbols.add(']');
		symbols.add('{');
		symbols.add('}');
		return symbols;
	}

	private static Map<String, WordCountConsolidationEntry> consolidate(HashMap<String, Integer> countResult) {
		
		RadicalFinder radicalFinder = initRadicalFinder();
		
		WordCountConsolidator consolidator = new SimpleWordCountConsolidator();

		consolidator.process(countResult, radicalFinder);
		
		Map<String,WordCountConsolidationEntry> result = consolidator.getResult();
		return result;
	}

	private static HashMap<String, Integer> countWords(List<String> filteredList) {
		Counter<String> counter = new Counter<String>();
		
		for(String word : filteredList)
		{
			counter.includeInCount(word);
		}
		
		HashMap<String, Integer>countResult = counter.getResult();
		return countResult;
	}

	private static List<String> applyStopList(List<String> wordList) {
		
		Set<String> stopList = initStopList();
		
		Filter<String> excludeFilter = new ExcludeFilter<String>(stopList);
		
		AndFilterer<String> filterer = new AndFilterer<String>();
		filterer.addFilter(excludeFilter);
		
		List<String> filteredList = filterer.filter(wordList);
		return filteredList;
	}

	private static RadicalFinder initRadicalFinder() {
		Set<String> prefixesSet = new HashSet<String>();
		prefixesSet.add("pre");
		prefixesSet.add("pos");
		prefixesSet.add("un");
		Set<String> infixesSet = new HashSet<String>();
		Set<String> sufixesSet = new HashSet<String>();
		sufixesSet.add("en");
		sufixesSet.add("s");
		sufixesSet.add("ing");
		sufixesSet.add("ed");
		sufixesSet.add("´s");
		RadicalFinder radicalFinder = new SimpleRadicalFinder(prefixesSet,infixesSet,sufixesSet);
		return radicalFinder;
	}

	private static Set<String> initStopList() {
		Set<String> stopList = new HashSet<String>();
		stopList.add("a");
		stopList.add("an");
		stopList.add("the");
		stopList.add("for");
		stopList.add("by");
		stopList.add("then");
		stopList.add("if");
		stopList.add("of");
		stopList.add("as");
		stopList.add("in");
		stopList.add("to");
		stopList.add("and");
		stopList.add("or");
		stopList.add("on");
		return stopList;
	}

	private static void printOutResult(
			Map<String, WordCountConsolidationEntry> result) {
		for(String radical : result.keySet())
		{
			WordCountConsolidationEntry entry = result.get(radical);
			System.out.println("Radical : "+radical);
			System.out.println("Total: "+entry.getTotal());
			System.out.print("Considered words: ");
			for(String word : entry.getConsideredWords())
			{
				System.out.print(word+"; ");
			}
			System.out.print("\n");
			
		}
	}

	private static List<String> convertToWordList(String text) {
		List<String> wordList = new ArrayList<String>();
		
		StringTokenizer tokenizer = new StringTokenizer(text," ");
		while(tokenizer.hasMoreTokens())
		{
			String word = tokenizer.nextToken();
			wordList.add(word.toLowerCase());
		}
		return wordList;
	}
	
	

}
