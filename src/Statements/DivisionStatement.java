package Statements;

import Excpetions.DivisionByZeroException;
import Variables.FloatNumber;
import Variables.IntegerNumber;
import Variables.Variable;

public class DivisionStatement extends Expresion {

    public DivisionStatement(String command) {
        super(command);
    }

    /**
     * calculates the <b>division</b> of two operands
     * @return  a variable that contains division of two operands
     */

    @Override
    public Variable run() {
        Variable variable = null ;
        Variable operand1 = Expresion.getOperand(command.split("[ ]+")[0].trim()) ;
        Variable operand2 = Expresion.getOperand(command.split("[ ]+")[2].trim()) ;
        try {
            if (operand1 instanceof IntegerNumber && operand2 instanceof IntegerNumber && ((IntegerNumber) operand1).getValue()% ((IntegerNumber) operand2).getValue() == 0) {
                variable = new IntegerNumber("returnValue", ((IntegerNumber) operand1).getValue() / ((IntegerNumber) operand2).getValue());
            }else if(operand1 instanceof IntegerNumber && operand2 instanceof IntegerNumber && ((IntegerNumber) operand1).getValue()% ((IntegerNumber) operand2).getValue() != 0) {
                variable = new FloatNumber("returnValue",((IntegerNumber) operand1).getValue()/((IntegerNumber) operand2).getValue()) ;
            }else if (operand1 instanceof IntegerNumber && operand2 instanceof FloatNumber) {
                variable = new FloatNumber("returnValue", ((IntegerNumber) operand1).getValue() / ((FloatNumber) operand2).getValue());
            } else if (operand1 instanceof FloatNumber && operand2 instanceof IntegerNumber) {
                variable = new FloatNumber("returnValue", ((FloatNumber) operand1).getValue() / ((IntegerNumber) operand2).getValue());
            } else if (operand1 instanceof FloatNumber && operand2 instanceof FloatNumber) {
                variable = new FloatNumber("returnValue", ((FloatNumber) operand1).getValue() / ((FloatNumber) operand2).getValue());
            }
        }catch (ArithmeticException e){
            throw new DivisionByZeroException("division by zero") ;
        }
        return variable ;
    }
}
