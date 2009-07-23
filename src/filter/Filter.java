package filter;

public interface Filter<C extends Comparable<?>>
{
	public boolean filter(C object);
	
}
