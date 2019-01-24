package ast;
/**
 * Stores expressions and relop associated with a Condition in Pascal
 * 
 * @author Morgan Douglas
 * @version Mar 19, 2018
 */
public class Condition 
{
    private Expression exp1;
    private String relop;
    private Expression exp2;
    
    
    /**
     * Constructor for Condition objects
     * 
     * @param exp1 expression before the relational operator
     * @param relop the relational operator between the two expressions
     * @param exp2 expression after the relational operator
     */
    public Condition(Expression exp1, String relop, Expression exp2)
    {
        this.exp1=exp1;
        this.relop=relop;
        this.exp2=exp2;
    }
    /**
     * @return expression to the left of relop
     */
    public Expression getExp1()
    {
        return exp1;
    }
    /**
     * @return expression to the right of relop
     */
    public Expression getExp2()
    {
        return exp2;
    }
    /**
     * @return relational operator between exp1 and exp2
     */
    public String getRelop()
    {
        return relop;
    }
   
    


}
