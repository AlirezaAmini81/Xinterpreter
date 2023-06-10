package Statements;

import Variables.FloatNumber;
import Variables.IntegerNumber;
import Variables.Variable;

public abstract class Expresion extends SimpleStatement {

    protected String firstOperand = command.split("[ ]+")[0].trim() ;
    protected String secondOperand = command.split("[ ]+")[2].trim() ;

    public Expresion(String command) {
        super(command.trim());
    }

    /**
     * checks a string as a statement for containing "+-/*" and return
     * a <b>SumStatement</b> or a <b>SubtractionStatement</b> or a <b>ProductionStatement</b>
     * or a <b>DivisionStatement</b>
     *
     * @param statement the string to check for containing +-/*
     * @return          the suitable expression according to check for containing +-/*
     */

    public static Expresion expresionDetector(String statement){
        Expresion expresion = null ;
        if (statement.contains("+")){
            expresion = new SumStatement(statement) ;
        }else if(statement.contains("-")){
            expresion = new SubtractionStatement(statement);
        }else if(statement.contains("*")){
            expresion = new ProductionStatement(statement);
        }else if(statement.contains("/")){
            expresion = new DivisionStatement(statement) ;
        }else if(statement.contains("%")){
            expresion = new ModStatement(statement);
        }
        return expresion ;
    }

    /**
     * checks a string as a operand is a <b>integer literal</b> or a <b>float literal</b>
     * or a <b>variable</b>
     *
     * @param operand the string as a operand to compare to patterns
     * @return        a variable according to result of comparision
     */

    public static Variable getOperand(String operand){
        Variable variable = null ;
        if (operand.matches("[ ]*[-]?[0-9]+[ ]*")){
            variable = new IntegerNumber("operand",Integer.parseInt(operand));
        }else if(operand.matches("[ ]*[-]?[0-9]+.[0-9]+[ ]*")){
            variable = new FloatNumber("operand",Float.parseFloat(operand));
        }else if(operand.matches("[ ]*[A-Za-z0-9_$]+[ ]*")){
            variable = Variable.searchVariable(operand) ;
        }
        return variable ;
    }

}
