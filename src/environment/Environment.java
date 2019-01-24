package environment;

import java.util.HashMap;
import java.util.Map;
/**
 * Environment class keeps track variables, containing methods to get and set variable
 * values.
 * 
 * @author Morgan Douglas
 * @version Mar 19, 2018
 */
public class Environment 
{
    private Map<String, Integer> vars;  //stores variable names as keys and values as values
    
    /**
     * Constructor for Environment objects
     */
    public Environment()
    {
        vars = new HashMap<String, Integer>();  //instantiates vars
    }
    
    /**
     * @param variable name of variable to which value is to be attached
     * @param value the value of variable
     * 
     * @postcondition variable and value have been put into vars, as key and value,
     * respectively
     */
    public void setVariable(String variable, int value)
    {
        vars.put(variable, value);
    }
    
    /**
     * @param variable the name of the variable who's value is being retrieved
     * @return Integer value associated with variable name
     */
    public Integer getVariable(String variable)
    {
        return vars.get(variable);
    }

}
