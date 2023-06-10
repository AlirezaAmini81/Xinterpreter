package Variables;

import Excpetions.InvalidVariableNameException;
import Excpetions.NotFoundVariableException;
import Statements.Program;

/**
 * a super class for all variables that contains of float variables and
 * integer variables
 */

public abstract class Variable {
    private String name ;

    public Variable(String name) {
        setName(name);
    }

    public void setName(String name) {
        if (name.matches("[A-Za-z0-9_$]+") && !Character.isDigit(name.charAt(0))){
            this.name = name ;
        }else{
            throw new InvalidVariableNameException("\"" + name + "\" is invalid as the name of a variable") ;
        }
    }

    public String getName() {
        return name;
    }

    /**
     * finds the variable corresponding to the name
     * @param name  the string by which the variable is to be searched
     * @return      if variable has been found , suitable variable will be return
     */

    public static Variable searchVariable(String name){
        Variable variable = null ;
        for (int i = 0; i <= Program.getProgram().getVariables().size()-1; i++){
            if (Program.getProgram().getVariables().get(i).getName().equals(name)){
                variable = Program.getProgram().getVariables().get(i) ;
                break;
            }
        }
        if (variable == null){
            throw new NotFoundVariableException("Variable \"" + name + "\" hasn't been defined");
        }
        return variable ;
    }

    /**
     * checks whether a variable is already defined or not
     * @param name  the string as the variable name to be defined
     * @return      boolean according to result of existence of a variable
     */

    public static boolean isReapted(String name){
        boolean check = false ;
        for (int i = 0 ; i <= Program.getProgram().getVariables().size()-1 ; i++){
            if (Program.getProgram().getVariables().get(i).getName().equals(name)){
                check = true ;
                break;
            }
        }
        return check ;
    }
}
