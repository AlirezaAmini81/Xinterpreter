package Statements;

import Excpetions.NotFoundVariableException;
import Run.Graphic;
import Variables.FloatNumber;
import Variables.IntegerNumber;
import Variables.Variable;

/**
 * a subclass for compound statements that support print statement with this structure :
 * <b>print </b><i>parameter</i>
 */

public class PrintStatement extends SimpleStatement {
    public final static String [] patterns = {"print[ ]+[-]?[0-9]+[ ]*" , "print[ ]+[-]?[0-9]+[.]{1}[0-9]+[ ]*" , "print[ ]+[A-Za-z0-9_$]+[ ]*"};

    public PrintStatement(String command) {
        super(command);
    }

    @Override
    public Variable run() {
        int length = 0 ;
        if (command.matches("print[ ]+[-]?[0-9]+[ ]*")){
            System.out.println(command.split("print")[1].trim());
            Graphic.getGraphic().addText(command.split("print")[1].trim());
            length = command.split("print")[1].trim().length();
        }else if(command.matches("print[ ]+[-]?[0-9]+[.]{1}[0-9]+[ ]*")){
            System.out.println(command.split("print")[1].trim());
            Graphic.getGraphic().addText(command.split("print")[1].trim());
            length = command.split("print")[1].trim().length() ;
        }else if (command.matches("print[ ]+[A-Za-z0-9_$]+[ ]*")){
            if (Variable.searchVariable(command.split("print")[1].trim()) instanceof IntegerNumber){
                System.out.println(((IntegerNumber)Variable.searchVariable(command.split("print")[1].trim())).getValue());
                Graphic.getGraphic().addText(String.valueOf(((IntegerNumber)Variable.searchVariable(command.split("print")[1].trim())).getValue()));
                length = String.valueOf(((IntegerNumber)Variable.searchVariable(command.split("print")[1].trim())).getValue()).length();
            }else if (Variable.searchVariable(command.split("print")[1].trim()) instanceof FloatNumber){
                System.out.println(((FloatNumber)Variable.searchVariable(command.split("print")[1].trim())).getValue());
                Graphic.getGraphic().addText(String.valueOf(((FloatNumber)Variable.searchVariable(command.split("print")[1].trim())).getValue()));
                length =  String.valueOf(((FloatNumber)Variable.searchVariable(command.split("print")[1].trim())).getValue()).length();
            }else{
                throw new NotFoundVariableException("Variable \"" + command.split("print")[1].trim() + "\" hasn't been defined");
            }
        }
        return new IntegerNumber("returnValue",length);
    }
}
