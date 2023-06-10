package Statements;

import Excpetions.SyntaxErrorException;
import Variables.FloatNumber;
import Variables.IntegerNumber;
import Variables.Variable;

/**
 * a subclass of <b>simple statement</b> that assigns a value to another by
 * syntax :  <i>value1 = value2</i>
 */

public class AssignmentStatement extends SimpleStatement {

    public static final String [] patterns = {"[A-Za-z0-9_$]+[ ]+=[ ]+[-]?[0-9]+[ ]*" , "[A-Za-z0-9_$]+[ ]+=[ ]+[-]?[0-9]+[.]{1}[0-9]+[ ]*" ,
            "[A-Za-z0-9_$]+[ ]+=[ ]+[A-Za-z0-9_$]+[ ]*" , "[A-Za-z0-9_$]+[ ]+=[ ]+[A-Za-z0-9_$.]+[ ]+[-|+|/|*|%]{1}[ ]+[A-Za-z0-9_$.]+[ ]*" ,
    "[A-Za-z0-9_$]+[ ]+=[ ]+print[ ]+[-]?[0-9]+[ ]*" , "[A-Za-z0-9_$]+[ ]+=[ ]+print[ ]+[-]?[0-9]+[.]{1}[0-9]+[ ]*" , "[A-Za-z0-9_$]+[ ]+=[ ]+print[ ]+[A-Za-z0-9_$]+[ ]*"};

    private Variable leftOperand ;
    private String rightOperand ;

    public AssignmentStatement(String command) {
        super(command);
        leftOperand = Variable.searchVariable(command.split("=")[0].trim()) ;
        rightOperand = command.split("=")[1].trim() ;
    }

    @Override
    public Variable run() {
        Variable variable = null;
        if (rightOperand.matches("[ ]*[-]?[0-9]+[ ]*") && !rightOperand.startsWith("0")){
            if (leftOperand instanceof IntegerNumber){
                ((IntegerNumber) leftOperand).setValue(Integer.parseInt(rightOperand.trim()));
                variable = new IntegerNumber("returnValue",Integer.parseInt(rightOperand.trim()));
            }else if(leftOperand instanceof FloatNumber){
                ((FloatNumber) leftOperand).setValue(Integer.parseInt(rightOperand.trim()));
                variable = new IntegerNumber("returnValue",Integer.parseInt(rightOperand.trim()));
            }
        }else if(rightOperand.matches("[ ]*[-]?[0-9]+[.]{1}[0-9]+[ ]*")){
            if (leftOperand instanceof IntegerNumber){
                throw new SyntaxErrorException("Unmatchable variable format in assignment : \"" + rightOperand + "\" is a float number and \"" + leftOperand.getName() + "\" is an integer number ");
            }else if(leftOperand instanceof FloatNumber){
                ((FloatNumber) leftOperand).setValue(Float.parseFloat(rightOperand.trim()));
                variable = new FloatNumber("returnValue",Float.parseFloat(rightOperand.trim())) ;
            }
        }else if(rightOperand.matches("[ ]*[A-Za-z0-9_$]+[ ]*") || rightOperand.matches("[A-Za-z0-9_$.]+[ ]+[-|+|/|*|%]{1}[ ]+[A-Za-z0-9_$.]+[ ]*") && !rightOperand.startsWith("0")){
            Variable rightVariable = null;
            if (rightOperand.matches("[ ]*[A-Za-z0-9_$]+[ ]*")){
                rightVariable = Variable.searchVariable(rightOperand.trim()) ;
                variable = assign(rightVariable) ;
            }else if(rightOperand.matches("[ ]*[A-Za-z0-9_$.]+[ ]+[-|+|/|*|%]{1}[ ]+[A-Za-z0-9_$.]+[ ]*")){
                rightVariable = Expresion.expresionDetector(rightOperand.trim()).run() ;
                variable = assign(rightVariable);
            }
        }else if(rightOperand.trim().matches(PrintStatement.patterns[0]) || rightOperand.trim().matches(PrintStatement.patterns[1]) || rightOperand.trim().matches(PrintStatement.patterns[2])){
            Variable rightVariable = (new PrintStatement(rightOperand.trim())).run() ;
            variable = assign(rightVariable) ;
        }
        return variable;
    }

    /**
     * checks and assigns <b>right variable</b> to <b>left variable</b>
     * @param rightVariable  The variable that will be assigned
     * @return               The variable that was assigned
     */
    private Variable assign(Variable rightVariable){
        Variable variable = null ;
        if (leftOperand instanceof IntegerNumber && rightVariable instanceof IntegerNumber){
            ((IntegerNumber) leftOperand).setValue(((IntegerNumber) rightVariable).getValue());
            variable = new IntegerNumber("returnValue",((IntegerNumber) rightVariable).getValue()) ;
        }else if(leftOperand instanceof IntegerNumber && rightVariable instanceof FloatNumber){
            throw new SyntaxErrorException("Unmatchable variable format in assignment : \"" + rightOperand + "\" is a float number and \"" + leftOperand.getName() + "\" is an integer number ");
        }else if(leftOperand instanceof FloatNumber && rightVariable instanceof IntegerNumber){
            ((FloatNumber) leftOperand).setValue(((IntegerNumber) rightVariable).getValue());
            variable = new IntegerNumber("returnValue",((IntegerNumber) rightVariable).getValue()) ;
        }else if(leftOperand instanceof FloatNumber && rightVariable instanceof FloatNumber){
            ((FloatNumber) leftOperand).setValue(((FloatNumber) rightVariable).getValue());
            variable = new FloatNumber("returnValue",((FloatNumber) rightVariable).getValue()) ;
        }
        return variable ;
    }
}
