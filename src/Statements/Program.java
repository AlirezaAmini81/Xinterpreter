package Statements;

import Excpetions.*;
import Run.Graphic;
import Variables.Variable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Program {
    private String path ;

    private static Program program ;

    private ArrayList<Variable> variables = new ArrayList<>();
    private ArrayList<String> statements = new ArrayList<>();

    private Program(){}

    public synchronized static Program getProgram(){
        if (program == null){
            program = new Program();
        }
        return program ;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * reads file line by line and save them in <b>statements</b>
     *
     */

    private void readFile(){
        statements.clear();
        try (BufferedReader input = new BufferedReader(new FileReader(new File(path)))){
            while(true){
                String line = input.readLine();
                if (line == null){
                    break;
                }
                statements.add(line);
            }
        }catch(IOException e){
            System.out.println("File not Found");
            Graphic.getGraphic().addText("File not Found");
        }
    }

    public void runProgram(){
        long time1 = System.currentTimeMillis() ;
        variables.clear();
        readFile();
        int i = 0 , startCount = 0;
        try {
            for (i = 0; i <= statements.size() - 1; i++) {
                if (Statement.statementDetector(statements.get(i)) instanceof VariableDefineStatement) {
                    if (getStart() > i)
                        variables.add(new VariableDefineStatement(statements.get(i)).run());
                    else {
                        if (getStart() != -1)
                            throw new SyntaxErrorException("Variable defining can't be after \"%%\"");
                        else
                            throw new SyntaxErrorException("After variable defining should be \"%%\"");
                    }
                } else if (Statement.statementDetector(statements.get(i)) instanceof PrintStatement) {
                    if (getStart() < i && getStart() != -1)
                        new PrintStatement(statements.get(i)).run();
                    else
                        throw new SyntaxErrorException("Print statement can't be before \"%%\"");
                } else if (statements.get(i).matches("%%[ ]*")) {
                    startCount++;
                    if (startCount == 2) {
                        throw new SyntaxErrorException("each program can have only a \"%%\"");
                    }
                    continue;
                } else if (Statement.statementDetector(statements.get(i)) instanceof LoopFor) {
                    if (getStart() < i && getStart() != -1) {
                        new LoopFor(LoopFor.forDetector(i, statements)).run();
                        i += LoopFor.forDetector(i, statements).length - 1;
                    } else {
                        throw new SyntaxErrorException("Loop for statement can't be before \"%%\"");
                    }
                } else if (Statement.statementDetector(statements.get(i)) instanceof AssignmentStatement) {
                    if (getStart() < i && getStart() != -1)
                        new AssignmentStatement(statements.get(i)).run();
                    else
                        throw new SyntaxErrorException("Assignment statement can't be before \"%%\"");
                } else if(Statement.statementDetector(statements.get(i)) instanceof MultiAssignmentStatement){
                    if (getStart() < i && getStart() != -1)
                        new MultiAssignmentStatement(statements.get(i)).run();
                    else
                        throw new SyntaxErrorException("Multi Assignment statement can't be before \"%%\"");

                } else if (Statement.statementDetector(statements.get(i)) instanceof IfStatement){
                     if (getStart() < i && getStart() != -1){
                         new IfStatement(IfStatement.ifDetector(i, statements)).run() ;
                         i += IfStatement.ifDetector(i , statements).length -1 ;
                     } else {
                         throw new SyntaxErrorException("If statement can't be before \"%%\"");
                     }
                } else if (Statement.statementDetector(statements.get(i)) instanceof WhileLoop){
                    if (getStart() < i && getStart() != -1){
                        new WhileLoop(WhileLoop.whileDetector(i, statements)).run() ;
                        i += WhileLoop.whileDetector(i , statements).length -1 ;
                    } else {
                        throw new SyntaxErrorException("While loop can't be before \"%%\"");
                    }

                } else if(statements.get(i).matches("[ ]*")){
                    continue;
                }else{
                    throw new SyntaxErrorException("Illegal statement");
                }
            }
        }catch(InvalidVariableNameException | NotFoundVariableException | SyntaxErrorException | VaribleReaptationException | DivisionByZeroException e){
            System.out.println(exceptionReporter(i+1 , e));
            Graphic.getGraphic().addText(exceptionReporter(i+1 , e));
        }finally {
            long time2 = System.currentTimeMillis();
            double totalTime = (time2-time1)/1000.0 ;
            Graphic.getGraphic().addText("\nTime : " + totalTime + " Seconds\n");
        }
    }

    private String exceptionReporter(int line , Exception e){
        return String.format("At line %d : %s",line,e.getMessage());
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public ArrayList<String> getStatements() {
        return statements;
    }

    private int getStart(){
        int index = -1 ;
        for (int i = 0 ; i <= statements.size()-1 ; i++){
            if (statements.get(i).matches("%%[ ]*")){
                index = i ;
                break;
            }
        }
        return index ;
    }


}
