package ast;
/**
 * The Number class represents numbers, storing an int value that can be 
 * manipulated by functions performed in the program.
 * 
 * @author Morgan Douglas
 * @version 03/13/18
 *
 */
public class Number extends Expression
{
    private int value;
    
    /**
     * Constructor for Number objects
     * 
     * @param value the value to be assigned to the Number object
     */
    public Number(int value)
    {
        this.value = value;
    }
    
    /**
     * @return the value of the Number object
     */
    public int getValue()
    {
        return value;
    }
    
    /**
     * @return String representation of Number's int value
     */
    public String toString()
    {
        return value + "";
    }

}
