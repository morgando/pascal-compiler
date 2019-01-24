package scanner;

public class UnknownTokenException extends Throwable
{
    public UnknownTokenException(String tok)
    {
        System.out.println("token " + tok + " not recognized by program");
    }
}
