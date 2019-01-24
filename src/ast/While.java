package ast;
/**
 * Stores the condition and statement associated with a While loop in Pascal
 * @author Morgan Douglas
 * @version Mar 19, 2018
 */
public class While extends Statement
{
    private Condition cond;
    private Statement stmt;
    
    /**
     * 
     * @param cond Condition that must be met to continue while loop
     * @param stmt Statement executed each time condition is met
     */
    public While(Condition cond, Statement stmt)
    {
        this.cond=cond;
        this.stmt=stmt;
    }
    /**
     * @return Condition associated with while loop
     */
    public Condition getCondition()
    {
        return cond;
    }
    /**
     * @return Statement associated with While loop
     */
    public Statement getStatement()
    {
        return stmt;
    }
}
