package ast;
/**
 * The Variable class represents variables, with a String value containing the name of the
 * variable.
 * 
 * Variable values assigned in the HashMap that is passed to the Parser constructor.
 * 
 * @author Morgan Douglas
 * @version 03/13/18
 *
 */
public class Variable extends Expression
{
    private String name;
    
    /**
     * Constructor for Variable objects
     * @param name the name to be assigned to the Variable object
     */
    public Variable(String name)
    {
        this.name = name;
    }
    /**
     * @return name of Variable
     */
    public String getName()
    {
        return name;
    }
}
