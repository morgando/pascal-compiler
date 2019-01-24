package ast;
/**
 * Stores and accesses information relating to a single IF THEN statement.
 * 
 * @author Morgan Douglas
 * @version Mar 19, 2018
 */
public class If extends Statement
{
    private Condition con;
    private Statement stmt;
    private Statement elseStmt;
    
    /**
     * Constructor for If objects
     * 
     * @param con Condition that must be met for stmt to execute
     * @param stmt the Statement that executes when con is met
     */
    public If(Condition con, Statement stmt, Statement elseStmt) 
    {
        this.con=con;
        this.stmt=stmt;
        this.elseStmt=elseStmt;
    }
    
    /**
     * @return conditional portion of if then
     */
    public Condition getCondition()
    {
        return con;
    }
    
    /**
     * @return Statement to be executed if conditional is met
     */
    public Statement getStatement()
    {
        return stmt;
    }
    
    /**
     * @return Statement to be executed if conditional is not met
     */
    public Statement getElseStatement()
    {
        return elseStmt;
    }
}
