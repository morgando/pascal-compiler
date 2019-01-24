package scanner;
import java.io.*;
/**
 * Contains the main method which passes the text of the tester file to the scanner, printing out the
 * Tokens that the scanner returns.
 * 
 * @author Morgan Douglas
 * @version 01/31/18
 */
public class ScannerTester 
{
    public static void main(String[] args) throws IOException, ScanErrorException
    {
        File file = new File("scannerTestAdvanced.txt"); //creates new file--replace name with that of tester document

        LineNumberReader reader = new LineNumberReader(new FileReader(file));
        
        Scanner scan = new Scanner(reader);
        
        while(scan.hasNextToken())                  //prints token stream
        {
            System.out.println(scan.nextToken());
        }
    }
}
