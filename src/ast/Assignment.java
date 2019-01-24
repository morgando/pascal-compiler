package ast;
/**
 * Stores variables and the values to which they have been assigned
 * intermediately (before they have been inputed into Environment Map)
 * 
 * @author Morgan Douglas
 * @version 04/08/2018
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;
    
    /**
     * Constructor for Assignment objects
     * 
     * @param var the variable getting assigned a value
     * @param exp the Expression denoting the value of the variable
     */
    public Assignment(String var, Expression exp)
    {
        this.var=var;
        this.exp=exp;
    }
    /**
     * @return Expression assigned to variable
     */
    public Expression getExpression()
    {
        return exp;
    }
    /**
     * @return name of variable
     */
    public String getVariable()
    {
        return var;
    }
}
