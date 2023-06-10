package Statements;

import Variables.FloatNumber;
import Variables.IntegerNumber;
import Variables.Variable;

public class SubtractionStatement extends Expresion {

    public SubtractionStatement(String command) {
        super(command);
    }

    /**
     * calculates the <b>subtraction</b> of two operands
     * @return  a variable that contains subtraction of two operands
     */

    @Override
    public Variable run() {
        Variable variable = null ;
        Variable operand1 = Expresion.getOperand(command.split("[ ]+")[0].trim()) ;
        Variable operand2 = Expresion.getOperand(command.split("[ ]+")[2].trim()) ;
        if (operand1 instanceof IntegerNumber && operand2 instanceof IntegerNumber){
            variable = new IntegerNumber("returnValue",((IntegerNumber) operand1).getValue() - ((IntegerNumber) operand2).getValue()) ;
        }else if(operand1 instanceof IntegerNumber && operand2 instanceof FloatNumber){
            variable = new FloatNumber("returnValue",((IntegerNumber) operand1).getValue() - ((FloatNumber) operand2).getValue()) ;
        }else if(operand1 instanceof FloatNumber && operand2 instanceof IntegerNumber){
            variable = new FloatNumber("returnValue",((FloatNumber) operand1).getValue() - ((IntegerNumber) operand2).getValue()) ;
        }else if(operand1 instanceof FloatNumber && operand2 instanceof FloatNumber){
            variable = new FloatNumber("returnValue",((FloatNumber) operand1).getValue() - ((FloatNumber) operand2).getValue()) ;
        }
        return variable ;
    }
}
