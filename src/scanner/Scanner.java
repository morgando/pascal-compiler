package scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * A Scanner is responsible for reading an input stream, one character at a
 * time, and separating this input into tokens representing lexemes. These
 * tokens are then later further analyzed in the compilation process
 *
 * @author Morgan Douglas
 * @version 01/31/18
 * @assistance FindAuthor lab resources
 * @assistance freepascal.org
 */

public class Scanner 
{
    private LineNumberReader in;                        //to parses document
    
    private String currentChar;                         //represents current character scanner is examining
    
    private boolean endOfFile;                          //true if end of file has been reached--false otherwise
    
    private ArrayList<String> keywords;                 //used to store common pascal keywords

    public static enum TOKEN_TYPE {                     //types of tokens that may be found in pascal code
        NUMBER, KEYWORD, IDENTIFIER, OPERAND, MISC
    };

    // stores all possible operands
    public final String[] OPERANDS = { "=", "+", "-", "*", "/", "%", "(", ")", ":", ">", "<", ".", ";"};

    /**
     * @param in
     *            is the reader object supplied by the program constructing this
     *            Scanner object.
     * @throws IOException
     */
    public Scanner(LineNumberReader in) throws IOException {
        keywords = new ArrayList<String>();
        addKeywords();

        this.in = in;
        endOfFile = false;
        getNextChar();
    }

    /**
     * Adds common pascal keywords to the keywords arrayList. Keywords taken
     * from freepascal.org's Turbo Pascal reserved words page.
     * 
     * @throws IOException
     */
    public void addKeywords() throws IOException {
        File pascalKeywords = new File("Pascal_Keywords.txt");
        @SuppressWarnings("resource")
        BufferedReader keyReader = new BufferedReader(new FileReader(pascalKeywords));

        while (keyReader.ready()) {
            String str = keyReader.readLine();
            String key = str.substring(0, str.length() - 2);

            keywords.add(key);
        }
    }

    /**
     * The getNextChar method attempts to get the next character from the input
     * stream. It sets the endOfFile flag true if the end of file is reached on
     * the input stream. Otherwise, it reads the next character from the stream
     * and converts it to a Java String object. postcondition: The input stream
     * is advanced one character if it is not at end of file and the currentChar
     * instance field is set to the String representation of the character read
     * from the input stream. The flag endOfFile is set true if the input stream
     * is exhausted.
     */
    private void getNextChar() {
        try {
            int inp = in.read();
            if (inp == -1)
                endOfFile = true;
            else
                currentChar = "" + (char) inp;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * moves forward in the file if str is equivalent to currentChar
     * 
     * @param str
     *            the String representing the scanner's current position in the
     *            file.
     */
    private void eat(String str) {
        if (str.equals(currentChar)) {
            getNextChar();
        }
    }

    /**
     * @return true if end of file has not been reached--false otherwise
     */
    public boolean hasNextToken() {
        return !endOfFile;
    }

    /**
     * Identifies and returns the next token in the token stream
     * 
     * @return next token in token stream
     * @throws ScanErrorException
     *             thrown if currentChar doesn't fit in to any expected category
     *             of token
     * @throws IOException
     */
    public Token nextToken() throws ScanErrorException, IOException, UnknownTokenException {
       
            while (!endOfFile && isWS(currentChar)) // ignores white space
                eat(currentChar);
            if (endOfFile) {
                return new Token(TOKEN_TYPE.MISC, "."); // indicates that scanner
                // has reached the end of
                // file
            }
            if (currentChar.equals("/")) // determines whether scanner has run
                                         // into an
            { // in-line comment--if so, treats it as white space
                in.mark(1);
                String str = "" + (char) in.read();

                if (str.equals("/")) // moves scanner to next line if comment
                                     // found
                {
                    in.readLine();
                    currentChar = "" + (char) in.read();

                    return nextToken();
                } else {
                    in.reset(); // resets to previous position if no comment
                                // found
                }
            }

            if (isLetter(currentChar)) // determines and returns token depending
                                       // on
            {
                String str = scanIdentifier().getValue(); // the type of currentChar
                if (keywords.indexOf(str) != -1) 
                    return new Token(TOKEN_TYPE.KEYWORD, str);
                else
                    return new Token(TOKEN_TYPE.IDENTIFIER, str);

            } else if (isDigit(currentChar))
                return scanNumber();
            else if (isOperand(currentChar))
                return scanOperand();
            else
                throw new ScanErrorException("--" + currentChar + "--"); // throws exception if 
                                                           // currentChar can't be recognized
        } 
            

    /**
     * @return String representation of a number constructed from sequential
     *         digits
     */
    private Token scanNumber() {
        String val = currentChar;

        eat(currentChar);

        while (!endOfFile && (!isWS(currentChar) && (isDigit(currentChar)))) {
            val += currentChar;
            eat(currentChar);
        }

        return new Token(TOKEN_TYPE.NUMBER, val);
    }

    /**
     * @return the identifier token
     */
    private Token scanIdentifier() {
        String val = currentChar;
        eat(currentChar);
        while ((!endOfFile && (!isWS(currentChar))) && (isDigit(currentChar) || (isLetter(currentChar)))) {
            val += currentChar;
            eat(currentChar);
        }
        return new Token(TOKEN_TYPE.IDENTIFIER, val);
    }

    /**
     * Returns operand lexeme. Since all operands are one character long, there
     * is no need to check for exceptions since the lexeme is merely being
     * returned immediately.
     * 
     * @return the operand token
     * @exception UnknownTokenException
     */
    private Token scanOperand() throws UnknownTokenException
    {
        String val = currentChar;
        eat(currentChar);
        String val2 = currentChar;
        
        if(val2.equals("="))
        {
            eat(val2);
            if(val.equals(":"))
            {
                return new Token(TOKEN_TYPE.OPERAND, val+val2+"");
            }
            else if(val.equals(">"))
            {
                return new Token(TOKEN_TYPE.OPERAND, val+val2+"");
            }
            else if(val.equalsIgnoreCase("<"))
            {
                return new Token(TOKEN_TYPE.OPERAND, val+val2+"");
            }
            else
                throw new UnknownTokenException(val+val2+"");
        }
        else
            return new Token(TOKEN_TYPE.OPERAND, val);
    }

    /**
     * @param str
     *            the String being examined
     * @return true if str is a letter--false otherwise
     */
    public boolean isLetter(String str) {
        if (str.compareTo("A") >= 0 && str.compareTo("Z") <= 0)
            return true;
        else
            return str.compareTo("a") >= 0 && str.compareTo("z") <= 0;
    }

    /**
     * @param str the String being examined
     * @return true if str is a digit--false otherwise
     */
    private boolean isDigit(String str) {
        return str.compareTo("0") >= 0 && str.compareTo("9") <= 0;
    }

    /**
     * @param str the String being examined
     * @return true if str is an operand--false otherwise
     */
    private boolean isOperand(String str) {
        for (int i = 0; i < OPERANDS.length; i++) {
            if (OPERANDS[i].equals(str))
                return true;
        }
        return false;
    }

    /**
     * Determines whether a character is whitespace.
     * @param str the String being examined
     * @return true if str is a white space--false otherwise
     */
    private boolean isWS(String str) {
        return (str.equals(" ") || str.equals("\t") || str.equals("\r") 
                || str.equals("\n"));
    }
}
