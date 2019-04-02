/**
 * 
 */

/**
 * @author Shinelon
 *
 */

public class Person {
	private String name;
	private int num;
	public Person(String name) 
	{
		this.name = name;
	}
	public void setNum(int i)
	{
		this.num=i;
	}
	
	public String getName()
	{
		return name;
	}
	public int getNum() 
	{
		return num;
	}
	 public boolean sameName(String name)
	 {
	    if(this.name.equals(name))
	    {
	            return true;
	     }
	        return false;
	 }
	
}