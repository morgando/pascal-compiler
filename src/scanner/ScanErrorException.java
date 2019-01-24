package scanner;
/**
 * Error thrown if a character is not recognized by the scanner.
 * 
 * @author Morgan Douglas
 * @version 01/31/18
 *
 */
public class ScanErrorException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    /**
     * prints error message
     * 
     * @param c the String that has not been recognized by program
     */
    public ScanErrorException(String c)
    {
        System.out.println("character " + c + " not recognizable by program");
    }
    
}
