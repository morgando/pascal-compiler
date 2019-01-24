package testers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import ast.InvalidRelopException;
import environment.Environment;
import evaluator.Evaluator;
import parser.Parser;
import scanner.ScanErrorException;
import scanner.Scanner;
import scanner.UnknownTokenException;
/**
 * Tests AST Lab. Input proper file name into main method
 * 
 * @author Morgan Douglas
 * @version Apr 8, 2018
 */
public class ASTTester 
{
    public static void main(String[] args) throws ScanErrorException, IOException, UnknownTokenException, InvalidRelopException
    {
        File file = new File("parserTest6.txt"); //input tester document

        LineNumberReader reader = new LineNumberReader(new FileReader(file));
        Scanner scn = new Scanner(reader);

        Environment env = new Environment();
        Parser parseTest = new Parser(scn, env);
        
        Evaluator eval = new Evaluator();

        while(scn.hasNextToken())
        {
            eval.exec(parseTest.parseStatement(), env);
        }
    }

}
