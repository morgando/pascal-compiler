package ast;
/**
 * Writeln contains information about pascal writeln statements
 * 
 * @author Morgan Douglas
 * @version 03/12/18
 */
public class Writeln extends Statement
{
    private Expression exp;
    
    /**
     * Constructs a Writeln object
     * 
     * @param exp the Expression contained in the writeln statement
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }
    /**
     * @return Expression within Writeln Statement
     */
    public Expression getExpression()
    {
        return exp;
    }
}
