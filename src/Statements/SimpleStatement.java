package Statements;

import java.util.ArrayList;

/**
 * a subclass for <b>Statement</b> that statement with <b>only a line of command</b>
 * can be defined by use it .
 */

public abstract class SimpleStatement extends Statement{
    protected String command ;

    public SimpleStatement(String command) {
        this.command = command;
    }

}
