package Statements;

import Excpetions.SyntaxErrorException;
import Variables.Variable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * a subclass for compound statements that support while statement with this structure :
 * <p><b>whiles </b><i>count</i></p>
 * <p><i>statements</i></p>
 * <p><b>end if</b></p>
 */

public class WhileLoop extends CompoundStatement {

    public final static String [] patterns = {"while[ ]+[-]?[A-Za-z0-9$_.]+[ ]+==[ ]+[-]?[A-Za-z0-9$_.]+[ ]*" , "while[ ]+[-]?[A-Za-z0-9$_.]+[ ]+!=[ ]+[-]?[A-Za-z0-9$_.]+[ ]*" ,
            "while[ ]+[-]?[A-Za-z0-9$_.]+[ ]+>=[ ]+[-]?[A-Za-z0-9$_.]+[ ]*" , "while[ ]+[-]?[A-Za-z0-9$_.]+[ ]+<=[ ]+[-]?[A-Za-z0-9$_.]+[ ]*" , "while[ ]+[-]?[A-Za-z0-9$_.]+[ ]+<[ ]+[-]?[A-Za-z0-9$_.]+[ ]*" , "while[ ]+[-]?[A-Za-z0-9$_.]+[ ]+>[ ]+[-]?[A-Za-z0-9$_.]+[ ]*"} ;

    public WhileLoop(String[] commands) {
        super(commands);
    }

    public static String [] whileDetector(int startLine , ArrayList<String> statements){
        ArrayList<String> whileStatements = new ArrayList<>() ;
        whileStatements.add(statements.get(startLine));
        int whileCount = 1 , endCount = 0 ;
        for (int i = startLine+1 ; i <= statements.size()-1 ; i++){
            if (Statement.match(statements.get(i),WhileLoop.patterns)){
                whileCount ++ ;
            }
            if(statements.get(i).matches("end while[ ]*")){
                endCount ++ ;
            }
            whileStatements.add(statements.get(i));
            if (whileCount == endCount){
                break;
            }else{
                if (i == statements.size()-1){
                    throw new SyntaxErrorException("There's a while loop with out end");
                }
            }
        }
        String [] commands = new String[whileStatements.size()];
        commands = whileStatements.toArray(commands) ;
        return commands ;
    }

    @Override
    public Variable run() {
        while (LogicalExpresion.getCondition(commands[0])){
            this.excuteCommands();
        }
        return null;
    }
}
