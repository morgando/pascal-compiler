package ast;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a block of code in pascal. Stores the statements that make up 
 * a block of code as Statement objects.
 * 
 * @author Morgan Douglas
 * @version 03/13/18
 *
 */
public class Block extends Statement
{
    private ArrayList<Statement> stmts;
    
    /**
     * Constructor for Block objects
     * 
     * @param stmts a ArrayList containing a Statement object representing
     * each statement in the block of code for which the Block object is being
     * created
     */
    public Block(ArrayList<Statement> stmts)
    {
        this.stmts = stmts;
    }
    
    /**
     * @return ArrayList of Statements associated with Block
     */
    public ArrayList<Statement> getStatements()
    {
        return stmts;
    }
}
