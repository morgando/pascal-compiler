package parser;
import java.io.IOException;


import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import scanner.ScanErrorException;
import scanner.Scanner;
import scanner.Token;
import scanner.UnknownTokenException;
import ast.*;
import ast.Number;

import environment.*;

import evaluator.*;
/**
 * Responsible for parsing pascal code
 * 
 * @author Morgan Douglas
 * @version Mar 14, 2018
 */
public class Parser 
{
    private Scanner scn;
    private Token currentToken;

    /**
     * Creates objects of type Parser
     * @param s A scanner object
     * @throws ScanErrorException
     * @throws IOException
     * @throws UnknownTokenException 
     * @throws InvalidRelopException 
     */
    public Parser(Scanner s, Environment env) throws ScanErrorException, IOException, UnknownTokenException, InvalidRelopException
    {
        scn=s;
        currentToken = scn.nextToken();
 
    }
    /**
     * 
     * @param expected
     * @throws ScanErrorException
     * @throws IOException
     * @throws UnknownTokenException 
     */
    public void eat(String expected) throws ScanErrorException, IOException, UnknownTokenException
    {
        if(currentToken.getValue().equals(expected))
            currentToken=scn.nextToken();
        else
            throw new IllegalArgumentException();
    }
    /**
     * Parses a pascal statement. A pascal statement can either begin with Writeln(exp),
     * or it can start with BEGIN, consist of a series of statements, and end with END;.
     * 
     * @throws ScanErrorException
     * @throws IOException
     * @throws UnknownTokenException 
     */
    public Statement parseStatement() throws ScanErrorException, IOException, UnknownTokenException
    {
        if(currentToken.getValue().equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            Expression exp = parseExpression();
            eat(")");
            eat(";");
            
            return new Writeln(exp);
        }
        else if(currentToken.getValue().equals("BEGIN"))
        {
  
            eat("BEGIN");
            ArrayList<Statement> statements = new ArrayList<Statement>();
            while(!currentToken.getValue().equals("END"))
            {

                statements.add(parseStatement());
            }
            eat("END");
            eat(";");
   
            return new Block(statements);
      
        }
        else if(currentToken.getValue().equals("IF"))
        {
            eat("IF");
            Condition cond = parseCondition();
            eat("THEN");
            Statement stmt = parseStatement();
            If i;
            if(currentToken.getValue().equals("ELSE"))
            {
                eat("ELSE");
                Statement stmt2 = parseStatement();
                
                i = new If(cond, stmt, stmt2);
            }
            else
            {
                i = new If(cond, stmt, null);
            }

            return i;
        }
        else if(currentToken.getValue().equals("WHILE"))
        {
            eat("WHILE");
            Condition cond = parseCondition();
            eat("DO");
            
            Statement stmt = parseStatement();
 
            While whi = new While(cond, stmt);
            
            return whi;
        }
        else
        {
            String var = currentToken.getValue();
    
            eat(var);
            
            eat(currentToken.getValue());

            Expression exp = parseExpression();
          
            
            Assignment assign = new Assignment(var, exp);
 
            eat(";");
    
            return assign;
            
        }
   
    }
    /**
     * 
     * @return condition at the current location in the input buffer
     * @throws ScanErrorException
     * @throws IOException
     * @throws UnknownTokenException 
     */
    public Condition parseCondition() throws ScanErrorException, IOException, UnknownTokenException
    {
        Expression exp1 = parseExpression();
        String relop = currentToken.getValue();
        eat(currentToken.getValue());
        Expression exp2 = parseExpression();
        
        return new Condition(exp1, relop, exp2); 
    }
    /**
     * 
     * @param numNegs a variable that will track the number of negatives attached to a
     * factor in order to return a value of the right sign. 
     * @return the value of the factor at the current location in the input buffer
     * @precondition numNegs must be zero
     * @throws ScanErrorException
     * @throws IOException
     * @throws UnknownTokenException 
     */
    public Expression parseFactor() throws ScanErrorException, IOException, UnknownTokenException
    {
        if(currentToken.getValue().equals("("))
        {
            eat("(");
            return parseExpression();
        }
        else if(currentToken.getValue().equals("-"))
        {
 
            return new BinOp("*", new Number(-1), parseFactor());
        }
        else if(scn.isLetter(currentToken.getValue()))
        {
            return new Variable(currentToken.getValue());
        }
        else
            return new Number((int) (Integer.parseInt(currentToken.getValue())));
    }
    /**
     * 
     * @return the Term at the current location of the input buffer
     * 
     * @throws ScanErrorException
     * @throws IOException
     * @throws UnknownTokenException 
     */
    public Expression parseTerm() throws ScanErrorException, IOException, UnknownTokenException
    {

        Expression term = parseFactor();

        eat(currentToken.getValue());

 
        Expression factor2;
        
        if(currentToken.getValue().equals("*") || currentToken.getValue().equals("/"))
        {
            String op = currentToken.getValue();
            eat(currentToken.getValue());
            if(currentToken.getValue().equals("("))
            {
                
                factor2=parseTerm();
            }
            else
            {
                factor2 = parseFactor();
                eat(currentToken.getValue());
            }
            Expression bin = new BinOp(op, term, factor2);
            return bin;
        }

        return term;     
    }
    
    /**
     * 
     * @return the value resulting from the expression at the current location of
     * the buffer being evaluated.
     * 
     * @throws ScanErrorException
     * @throws IOException
     * @throws UnknownTokenException 
     */
    public Expression parseExpression() throws ScanErrorException, IOException, UnknownTokenException
    {
        Expression num= parseTerm();

        if(currentToken.getValue().equals("+") || currentToken.getValue().equals("-"))
        {

            String op = currentToken.getValue();
            eat(currentToken.getValue());

            Expression num2 = parseTerm();

            

            Expression bin = new BinOp(op, num, num2);

            
            return bin;
        }
 
        return num;  
    }
}
