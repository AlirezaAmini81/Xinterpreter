package Statements;

/**
 * a superclass for all statements that consists of <b>Simple statement</b> and
 * <b>Compound statement</b> .
 */

public abstract class Statement implements Runnable{

    /**
     * compares a string to all statement's patterns that defined and
     * returns a suitable statement according to this comparision .
     *
     * @param statement the string to compare to all statements' patterns
     * @return          a <b>simple statement</b> according to comparision
     */

    public static Statement statementDetector(String statement){
        Statement command = null ;
        if (match(statement,VariableDefineStatement.patterns)){
            command = new VariableDefineStatement(statement);
        }else if(match(statement,PrintStatement.patterns)){
            command = new PrintStatement(statement);
        }else if(match(statement,AssignmentStatement.patterns)){
            command = new AssignmentStatement(statement);
        }else if(match(statement,LoopFor.patterns)){
            command = new LoopFor(null) ;
        }else if(match(statement,IfStatement.patterns)){
            command = new IfStatement(null);
        }else if(match(statement,WhileLoop.patterns)){
            command = new WhileLoop(null);
        }else if(match(statement,MultiAssignmentStatement.patterns)){
            command = new MultiAssignmentStatement(statement) ;
        }

        return command ;
    }

    /**
     * compares a <b>string</b> to <b>all patterns of a statement</b> and
     * and return  <tt>true</tt> if string is match to one of the patterns .
     *
     * @param statement the string to compare to patterns
     * @param patterns  the array of patterns of a statements
     * @return          <tt>true</tt> if string is match to one of the patterns
     */

    public  static boolean match(String statement , String [] patterns){
        boolean check = false ;
        for (int i = 0 ; i <= patterns.length-1 ; i++){
            if (statement.matches(patterns[i])){
                check = true ;
                break;
            }
        }
        return check ;
    }
}
