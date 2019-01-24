package ast;
/**
 * The BinOp class
 * 
 * @author Morgan Douglas
 * @version 03/13/18
 *
 */
public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;
    
    /**
     * Constructor for BinOp objects
     * @param op operator associated with Expression
     * @param exp1 expression to the left of operator
     * @param exp2 expression to the right of operator
     */
    public BinOp(String op, Expression exp1, Expression exp2)
    {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
    
    /**
     * @return operator associated with Expression
     */
    public String getOp()
    {
        return op;
    }
    /**
     * @return expression to the left of operator
     */
    public Expression getExp1()
    {
        return exp1;
    }
    /**
     * @return expression to the right of operator
     */
    public Expression getExp2()
    {
        return exp2;
    }

    
}
