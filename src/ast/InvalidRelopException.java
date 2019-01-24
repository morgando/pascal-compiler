package ast;
/**
 * Thrown when conditionals contain unrecognized relational operators.
 * 
 * @author Morgan Douglas
 * @version Mar 22, 2018
 */
public class InvalidRelopException extends Throwable
{
    /**
     * Prints an error message specifying the String that was unrecognized.
     * 
     * @param str the unrecognized relop
     */
    public InvalidRelopException(String str)
    {
        System.out.println(str + " is not a valid relop");
    }
}
