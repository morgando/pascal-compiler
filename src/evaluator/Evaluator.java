package evaluator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;



import ast.*;
import ast.Number;
import environment.*;
import parser.*;

/**
 * Responsible for handling all evaluations that arise during parsing
 * 
 * @author Morgan Douglas
 * @version Mar 19, 2018
 */
public class Evaluator 
{
    /**
     * Determines type of pascal statement, performing proper output
     * required by this type.
     * 
     * @param stmt the statement to be evaluated
     * @param env the environment in which the statement is being evaluated
     * @throws InvalidRelopException 
     */
    public void exec(Statement stmt, Environment env) throws InvalidRelopException
    {

        if(stmt.getClass().getName().equals("ast.Writeln"))
        {
            System.out.println(
                    eval(((Writeln) stmt).getExpression(), env));
        }
        else if(stmt.getClass().getName().equals("ast.Assignment"))
        {
            
            int val = eval(((Assignment)stmt).getExpression(), env);
            
            String var = ((Assignment)stmt).getVariable();
            
            env.setVariable(var, val);   
        }
        else if(stmt.getClass().getName().equals("ast.Block"))
        {
            ArrayList<Statement> stmts = ((Block)stmt).getStatements();
            Iterator<Statement> itr = stmts.iterator();

            while(itr.hasNext())
            {
                Statement nextState = itr.next();
                exec(nextState, env);
            }
        }
        else if(stmt.getClass().getName().equals("ast.If"))
        {
            Condition c = ((If) stmt).getCondition();
            Statement s = ((If) stmt).getStatement();
            Statement s2 = ((If) stmt).getElseStatement();
            Boolean b = evalCon(c, env);
            
            if(b)
                exec(s, env);
            else if(s2!=null)
                exec(s2, env);
        }
        else
        {
            Condition c = ((While) stmt).getCondition();
            Statement s = ((While) stmt).getStatement();

            while(evalCon(c, env))
                exec(s, env);
        }
                
    }
    
    /**
     * Determines type of Expression and evaluates int value accordingly
     * 
     * @param exp the Expression to be evaluated
     * @param env the Environment in which the Expression is being evaluated
     * @return int value of the Expression
     */
    public int eval(Expression exp, Environment env)
    {
        int i;          //declares variable that will store return value
        
        if(exp.getClass().getName().equals("ast.BinOp"))    //evaluation if exp is of
        {                                                   //class BinOp
            String op = ((BinOp) exp).getOp();
            Expression exp1= ((BinOp) exp).getExp1();
            Expression exp2 = ((BinOp) exp).getExp2();
            int exp1Val = eval(exp1, env);
            int exp2Val = eval(exp2, env);
            
            if(op.equals("+"))
                i=exp1Val+exp2Val;
            else if(op.equals("-"))
                i=exp1Val-exp2Val;
            else if(op.equals("*"))
                i=exp1Val*exp2Val;
            else
                i=exp1Val/exp2Val;
        }
        else if(exp.getClass().getName().equals("ast.Number")) //evaluation if exp
        {                                                      //is of class Number
            i = ((Number) exp).getValue();
        }
        else    //evaluation if exp is of class Variable
        {
            i = env.getVariable(((Variable) exp).getName());  
        }
        
        return i;
    }
    
    /**
     * Evaluates conditional expressions
     * 
     * @return boolean resulting from the execution of the relational evaluation
     * @throws InvalidRelopException 
     */
    public Boolean evalCon(Condition c, Environment env) throws InvalidRelopException
    {
        String relop = c.getRelop();
        
        Number exp1 = new Number(eval(c.getExp1(), env));
        Number exp2 = new Number(eval(c.getExp2(), env));
        
        if(relop.equals("="))
            return exp1==exp2;
        else if(relop.equals("<>"))
            return exp1!=exp2;
        else if(relop.equals("<"))
            return exp1.getValue()<exp2.getValue();
        else if(relop.equals(">"))
            return exp1.getValue()>exp2.getValue();
        else if(relop.equals("<="))
            return exp1.getValue()<=exp2.getValue();
        else if(relop.equals(">="))
            return exp1.getValue()>=exp2.getValue();
        else
            throw new InvalidRelopException(relop);
                
    }
    

}
