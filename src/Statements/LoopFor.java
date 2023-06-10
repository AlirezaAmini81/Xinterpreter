package Statements;

import Excpetions.SyntaxErrorException;
import Variables.FloatNumber;
import Variables.IntegerNumber;
import Variables.Variable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * a subclass for compound statements that support for statement with this structure :
 * <p><b>for </b><i>count</i></p>
 * <p><i>statements</i></p>
 * <p><b>end if</b></p>
 */

public class LoopFor extends CompoundStatement {
    public final static String [] patterns = {"for[ ]+[0-9]+[ ]*","for[ ]+print[ ]+[-]?[0-9]+[ ]*" , "for[ ]+print[ ]+[-]?[0-9]+[.]{1}[0-9]+[ ]*" , "for[ ]+print[ ]+[A-Za-z0-9_$]+[ ]*" , "for[ ]+[A-Za-z0-9$_]+[ ]*" ,
    "for[ ]+[A-Za-z0-9_$.]+[ ]+[-|+|/|*]{1}[ ]+[A-Za-z0-9_$.]+[ ]*" , "for[ ]+[A-Za-z0-9_$]+[ ]+=[ ]+[-]?[0-9]+[ ]*" , "for[ ]+[A-Za-z0-9_$]+[ ]+=[ ]+[-]?[0-9]+[.]{1}[0-9]+[ ]*" ,
            "for[ ]+[A-Za-z0-9_$]+[ ]+=[ ]+[A-Za-z0-9_$]+[ ]*" , "for[ ]+[A-Za-z0-9_$]+[ ]+=[ ]+[A-Za-z0-9_$.]+[ ]+[-|+|/|*]{1}[ ]+[A-Za-z0-9_$.]+[ ]*"};

    private int repeat ;

    public LoopFor(String[] commands) {
        super(commands);
    }

    public static String [] forDetector(int startLine , ArrayList<String> statements){
        ArrayList<String> forStatements = new ArrayList<>() ;
        forStatements.add(statements.get(startLine));
        int forCount = 1 , endCount = 0 ;
        for (int i = startLine+1 ; i <= statements.size()-1 ; i++){
            if (Statement.match(statements.get(i),LoopFor.patterns)){
                forCount ++ ;
            }
            if(statements.get(i).matches("end for[ ]*")){
                endCount ++ ;
            }
            forStatements.add(statements.get(i));
            if (forCount == endCount){
                break;
            }else{
                if (i == statements.size()-1){
                    throw new SyntaxErrorException("There's a loop for with out end");
                }
            }
        }
        String [] commands = new String[forStatements.size()];
        commands = forStatements.toArray(commands) ;
        return commands ;
    }

    @Override
    public Variable run() {
        repeat = getRepeat(commands[0]);
        for (int i = 1 ; i <= repeat ; i++){
            this.excuteCommands();
        }

        return null;
    }

    /**
     * finds the number of repeat of a for loop
     *
     * @param command the string as the count of repeat
     * @return        rhe integer as number of repeat
     */

    private int getRepeat(String command) {
        int repeatCount = 0;
        if (command.matches("for[ ]+[0-9]+[ ]*")) {
            repeatCount = Integer.parseInt(command.split("[ ]+")[1].trim());
        }else if (command.matches("for[ ]+[A-Za-z0-9$_]+[ ]*")){
            if (Variable.searchVariable(command.split("[ ]+")[1].trim()) instanceof IntegerNumber){
                repeatCount = ((IntegerNumber) Variable.searchVariable(command.split("[ ]+")[1].trim())).getValue() ;
            }else{
                throw new SyntaxErrorException("Float number can't be used as loop counter");
            }
        }else if(command.matches("for[ ]+print[ ]+[-]?[0-9]+") || command.matches("for[ ]+print[ ]+[-]?[0-9]+[.]{1}[0-9]+") ||
                command.matches("for[ ]+print[ ]+[A-Za-z0-9_$]+")){
            Variable variable = new PrintStatement(command.split("for")[1].trim()).run() ;
            repeatCount = ((IntegerNumber)variable).getValue();
        }else if(command.matches("for[ ]+[A-Za-z0-9_$.]+[ ]+[-|+|/|*]{1}[ ]+[A-Za-z0-9_$.]+")){
            Variable variable = Expresion.expresionDetector(command.split("for")[1].trim()).run() ;
            if (variable instanceof IntegerNumber){
                repeatCount = ((IntegerNumber) variable).getValue() ;
            }else{
                throw new SyntaxErrorException("Float number can be repeat count of a loop");
            }
        }else if(command.matches("for[ ]+[A-Za-z0-9_$]+[ ]+=[ ]+[-]?[0-9]+") || command.matches("for[ ]+[A-Za-z0-9_$]+[ ]+=[ ]+[-]?[0-9]+[.]{1}[0-9]+") ||
                 command.matches("for[ ]+[A-Za-z0-9_$]+[ ]+=[ ]+[A-Za-z0-9_$]+") ||
                command.matches("for[ ]+[A-Za-z0-9_$]+[ ]+=[ ]+[A-Za-z0-9_$.]+[ ]+[-|+|/|*]{1}[ ]+[A-Za-z0-9_$.]+")){
            Variable variable = new AssignmentStatement(command.split("for")[1].trim()).run() ;
            if (variable instanceof IntegerNumber){
                repeatCount = ((IntegerNumber) variable).getValue() ;
            }else{
                throw new SyntaxErrorException("Float number can be repeat count of a loop");
            }
        }
        return repeatCount ;
    }
}
