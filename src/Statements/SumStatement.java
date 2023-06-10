package Statements;

import Variables.FloatNumber;
import Variables.IntegerNumber;
import Variables.Variable;

public class SumStatement extends Expresion {

    public SumStatement(String command) {
        super(command);
    }

    /**
     * calculates the <b>sum</b> of two operands
     * @return  a variable that contains  sum of two operands
     */

    @Override
    public Variable run() {
        Variable variable = null ;
        Variable operand1 = Expresion.getOperand(firstOperand) ;
        Variable operand2 = Expresion.getOperand(secondOperand) ;
        if (operand1 instanceof IntegerNumber && operand2 instanceof IntegerNumber){
            variable = new IntegerNumber("returnValue",((IntegerNumber) operand1).getValue() + ((IntegerNumber) operand2).getValue()) ;
        }else if(operand1 instanceof IntegerNumber && operand2 instanceof FloatNumber){
            variable = new FloatNumber("returnValue",((IntegerNumber) operand1).getValue() + ((FloatNumber) operand2).getValue()) ;
        }else if(operand1 instanceof FloatNumber && operand2 instanceof IntegerNumber){
            variable = new FloatNumber("returnValue",((FloatNumber) operand1).getValue() + ((IntegerNumber) operand2).getValue()) ;
        }else if(operand1 instanceof FloatNumber && operand2 instanceof FloatNumber){
            variable = new FloatNumber("returnValue",((FloatNumber) operand1).getValue() + ((FloatNumber) operand2).getValue()) ;
        }
        return variable ;
    }
}
