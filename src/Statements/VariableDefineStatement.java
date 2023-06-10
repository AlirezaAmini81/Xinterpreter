package Statements;

import Excpetions.VaribleReaptationException;
import Variables.FloatNumber;
import Variables.IntegerNumber;
import Variables.Variable;

/**
 * a subclass for compound statements that support variable defining statement with this structure :
 * <b>variable type </b><i>variable name</i>
 */

public class VariableDefineStatement extends SimpleStatement {
    public static final String [] patterns = {"int[ ]+[A-Za-z0-9_$]+[ ]*","int[ ]+[A-Za-z0-9_$]+[ ]+=[ ]+[-]?[0-9]+[ ]*",
            "float[ ]+[A-Za-z0-9_$]+[ ]*","float[ ]+[A-za-z0-9_$]+[ ]+=[ ]+[-]?[0-9]+[.]?[0-9]+[ ]*"} ;

    public VariableDefineStatement(String command) {
        super(command);
    }

    @Override
    public Variable run() {
        Variable variable = null ;
        if (!Variable.isReapted(command.split("[ ]+")[1])) {
            if (command.matches("int[ ]+[A-Za-z0-9_$]+[ ]*")) {
                variable = new IntegerNumber(command.split("[ ]+")[1], 0);
            } else if (command.matches("int[ ]+[A-Za-z0-9_$]+[ ]+=[ ]+[-]?[0-9]+[ ]*")) {
                variable = new IntegerNumber(command.split("[ ]+")[1], Integer.parseInt(command.split("[ ]+")[3]));
            } else if (command.matches("float[ ]+[A-Za-z0-9_$]+[ ]*")) {
                variable = new FloatNumber(command.split("[ ]+")[1], 0);
            } else if (command.matches("float[ ]+[A-za-z0-9_$]+[ ]+=[ ]+[-]?[0-9]+[.]?[0-9]+[ ]*")) {
                variable = new FloatNumber(command.split("[ ]+")[1], Float.parseFloat(command.split("[ ]+")[3]));
            }
        }else{
            throw new VaribleReaptationException("Variable \"" + command.split("[ ]+")[1] + "\" has been defined") ;
        }
        return variable ;
    }
}
