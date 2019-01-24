package scanner;
/**
 * Token objects represent different lexemes that may be found in pascal code. 
 * Each object has a type (KEYWORD, OPERAND, IDENTIFIER, NUMBER, or MISC) and
 * a String value.
 * 
 * @author Morgan Douglas
 * @version 01/31/18
 * @assistance FindAuthor lab resources
 */
public class Token
{
    // instance variables - replace the example below with your own
    private String value;
    private Scanner.TOKEN_TYPE type;

    /**
     * Constructor for objects of class Token
     */
    public Token(Scanner.TOKEN_TYPE typ, String str)
    {
        type = typ;
        value = str;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public Scanner.TOKEN_TYPE getType()
    {
       return type;
    }
    public String getValue()
    {
        return value;
    }
    public String toString()
    {
        return "type: " + type + " // value: " + value;
    }
    public int hashCode()
    {
        return value.hashCode()*3;
    }
    public boolean equals(Token j)
    {
        if(type!=j.getType())
            return false;
            else
            return hashCode()==j.hashCode();
    }
}
