package Statements;

import Excpetions.SyntaxErrorException;
import Variables.IntegerNumber;
import Variables.Variable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * a subclass for compound statements that support conditional statement with this structure :
 * <p><b>if </b><i>condition</i></p>
 * <p><i>statements</i></p>
 * <p><b>end if</b></p>
 */

public class IfStatement extends CompoundStatement {

    public final static String [] patterns = {"if[ ]+[-]?[A-Za-z0-9$_.]+[ ]+==[ ]+[-]?[A-Za-z0-9$_.]+[ ]*" , "if[ ]+[-]?[A-Za-z0-9$_.]+[ ]+!=[ ]+[-]?[A-Za-z0-9$_.]+[ ]*" ,
            "if[ ]+[-]?[A-Za-z0-9$_.]+[ ]+>=[ ]+[-]?[A-Za-z0-9$_.]+[ ]*" , "if[ ]+[-]?[A-Za-z0-9$_.]+[ ]+<=[ ]+[-]?[A-Za-z0-9$_.]+[ ]*" , "if[ ]+[-]?[A-Za-z0-9$_.]+[ ]+<[ ]+[-]?[A-Za-z0-9$_.]+[ ]*" , "if[ ]+[-]?[A-Za-z0-9$_.]+[ ]+>[ ]+[-]?[A-Za-z0-9$_.]+[ ]*"} ;

    public IfStatement(String[] commands) {
        super(commands);
    }

    public static String [] ifDetector(int startLine , ArrayList<String> statements){
        ArrayList<String> ifStatements = new ArrayList<>() ;
        ifStatements.add(statements.get(startLine));
        int forCount = 1 , endCount = 0 ;
        for (int i = startLine+1 ; i <= statements.size()-1 ; i++){
            if (Statement.match(statements.get(i),IfStatement.patterns)){
                forCount ++ ;
            }
            if(statements.get(i).matches("end if[ ]*")){
                endCount ++ ;
            }
            ifStatements.add(statements.get(i));
            if (forCount == endCount){
                break;
            }else{
                if (i == statements.size()-1){
                    throw new SyntaxErrorException("There's a if statement with out end");
                }
            }
        }
        String [] commands = new String[ifStatements.size()];
        commands = ifStatements.toArray(commands) ;
        return commands ;
    }

    @Override
    public Variable run() {
        if (LogicalExpresion.getCondition(commands[0])){
            this.excuteCommands();
        }
        return null;
    }


}
