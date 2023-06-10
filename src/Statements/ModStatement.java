package Statements;

import Excpetions.SyntaxErrorException;
import Variables.FloatNumber;
import Variables.IntegerNumber;
import Variables.Variable;

public class ModStatement extends Expresion {

    public ModStatement(String command) {
        super(command);
    }

    /**
     * calculates the <b>remainder</b> of division of two operands
     * @return  a variable that contains remainder of two operands
     */

    @Override
    public Variable run() {
        Variable variable = null ;
        Variable operand1 = Expresion.getOperand(firstOperand) ;
        Variable operand2 = Expresion.getOperand(secondOperand) ;
        if (operand1 instanceof IntegerNumber && operand2 instanceof IntegerNumber){
            variable = new IntegerNumber("returnValue",((IntegerNumber) operand1).getValue() % ((IntegerNumber) operand2).getValue()) ;
        }else{
            throw new SyntaxErrorException("Both of 2 operands should be integer");
        }
        return variable ;
    }
}
