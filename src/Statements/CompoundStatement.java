package Statements;

import Excpetions.SyntaxErrorException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * a subclass for <b>Statement</b> that statements with <b>2 or more lines</b>
 * can be defined by use it .
 */

public abstract class CompoundStatement extends Statement {
     protected String [] commands ;

    public CompoundStatement(String[] commands) {
        this.commands = commands;
    }

    public final void setCommands(String[] commands) {
        this.commands = commands;
    }

    public void excuteCommands(){
        for (int j = 1 ; j <= commands.length-2 ; j++){
            if (Statement.statementDetector(commands[j]) instanceof PrintStatement){
                new PrintStatement(commands[j]).run() ;
            }else if(Statement.statementDetector(commands[j]) instanceof AssignmentStatement){
                new AssignmentStatement(commands[j]).run() ;
            }else if(Statement.statementDetector(commands[j]) instanceof LoopFor){
                new LoopFor(LoopFor.forDetector(j,new ArrayList<>(Arrays.asList(commands)))).run() ;
                j += LoopFor.forDetector(j,new ArrayList<>(Arrays.asList(commands))).length-1 ;
            }else if(Statement.statementDetector(commands[j]) instanceof IfStatement){
                new IfStatement(IfStatement.ifDetector(j,new ArrayList<>(Arrays.asList(commands)))).run() ;
                j += IfStatement.ifDetector(j,new ArrayList<>(Arrays.asList(commands))).length-1 ;
            }else if(Statement.statementDetector(commands[j]) instanceof WhileLoop){
                new WhileLoop(WhileLoop.whileDetector(j,new ArrayList<>(Arrays.asList(commands)))).run() ;
                j += WhileLoop.whileDetector(j,new ArrayList<>(Arrays.asList(commands))).length-1 ;
            }else if(Statement.statementDetector(commands[j]) instanceof MultiAssignmentStatement){
                new MultiAssignmentStatement(commands[j]).run()  ;
            }else if(commands[j].matches("[ ]*")){
                continue;
            }else{
                throw new SyntaxErrorException("Illegal statement in while");
            }
        }
    }
}
