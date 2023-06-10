package Statements;

import Variables.FloatNumber;
import Variables.IntegerNumber;
import Variables.Variable;

/**
 * a subclass for logical expression that checks a value is less than another value or not
 */

public class LessThan extends LogicalExpresion {

    public LessThan(String command) {
        super(command);
    }

    @Override
    public IntegerNumber run() {
        IntegerNumber condition = new IntegerNumber("Check",0) ;
        Variable variable1 = Expresion.getOperand(command.split("[ ]+")[1].trim()) ;
        Variable variable2 = Expresion.getOperand(command.split("[ ]+")[3].trim()) ;
        if (variable1 instanceof IntegerNumber && variable2 instanceof IntegerNumber){
            if (((IntegerNumber) variable1).getValue() < ((IntegerNumber) variable2).getValue()){
                condition.setValue(1);
            }
        }else if(variable1 instanceof IntegerNumber && variable2 instanceof FloatNumber){
            if (((IntegerNumber) variable1).getValue() < ((FloatNumber) variable2).getValue()){
                condition.setValue(1);
            }
        }else if(variable1 instanceof FloatNumber && variable2 instanceof IntegerNumber){
            if(((FloatNumber) variable1).getValue() < ((IntegerNumber) variable2).getValue()){
                condition.setValue(1);
            }
        }else if(variable1 instanceof FloatNumber && variable2 instanceof FloatNumber){
            if (((FloatNumber) variable1).getValue() < ((FloatNumber) variable2).getValue()){
                condition.setValue(1);
            }
        }
        return condition ;
    }
}
