package Statements;

import Variables.Variable;

public interface Runnable {

    /**
     * each excutable statement should implement <b>Runnable</b> and override this
     * method to have suitable behaviour
     * @return  The variable according to behavior
     */
    Variable run() ;
}
