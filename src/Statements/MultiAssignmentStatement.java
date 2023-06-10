package Statements;

import Variables.Variable;

/**
 * a subclass of <b>simple statement</b> that assigns more than 2 value
 * <i>value1 = value2 = value3 = ... </i>
 */

public class MultiAssignmentStatement extends SimpleStatement {

    public static final String [] patterns = {"[[A-Za-z0-9$_]+[ ]+=[ ]+]{2,}[-]?[0-9]+[ ]*" , "[[A-Za-z0-9$_]+[ ]+=[ ]+]{2,}[-]?[0-9]+[.]{1}[0-9]+[ ]* " ,
    "[[A-Za-z0-9$_]+[ ]+=[ ]+]{2,}[A-Za-z0-9_$]+[ ]*" , "[[A-Za-z0-9$_]+[ ]+=[ ]+]{2,}[A-Za-z0-9_$.]+[ ]+[-|+|/|*|%]{1}[ ]+[A-Za-z0-9_$.]+[ ]*" , "[[A-Za-z0-9$_]+[ ]+=[ ]+]{2,}print[ ]+[-]?[0-9]+[ ]*" ,
    "[[A-Za-z0-9$_]+[ ]+=[ ]+]{2,}print[ ]+[-]?[0-9]+[.]{1}[0-9]+[ ]*" , "[[A-Za-z0-9$_]+[ ]+=[ ]+]{2,}print[ ]+[A-Za-z0-9_$]+[ ]*"};

    private String [] operands ;

    public MultiAssignmentStatement(String command) {
        super(command);
        operands = command.trim().split("=") ;
    }

    @Override
    public Variable run() {
        Variable variable = null ;
        for (int i = operands.length-1 ; i >= 1 ; i--){
            variable = new AssignmentStatement(String.format("%s=%s",operands[i-1],operands[i]).trim()).run();
        }
        return variable;
    }
}
