package Statements;

public abstract class LogicalExpresion extends SimpleStatement {

    public LogicalExpresion(String command) {
        super(command);
    }

    public static boolean getCondition(String condition){
        boolean check = false ;
        if (condition.contains("==")){
            if (new Equality(condition).run().getValue() == 1){
                check = true ;
            }
        }else if(condition.contains("!=")){
            if (new NonEquality(condition).run().getValue() == 1){
                check = true ;
            }
        }else if(condition.contains("<=")){
            if (new LessThanOrEqual(condition).run().getValue() == 1){
                check = true ;
            }
        }else if(condition.contains(">=")){
            if (new GreaterThanOrEqual(condition).run().getValue() == 1){
                check = true ;
            }
        }else if(condition.contains("<")){
            if (new LessThan(condition).run().getValue() == 1){
                check = true ;
            }
        }else if(condition.contains(">")){
            if (new GreaterThan(condition).run().getValue() == 1){
                check = true ;
            }
        }

        return check ;
    }
}
