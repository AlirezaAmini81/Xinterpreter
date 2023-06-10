package Variables;

/**
 * a subclass for Variable that <b>integer variables</b> can be defined by ues it
 */

public class IntegerNumber extends Variable{
    private int value ;

    public IntegerNumber(String name , int value) {
        super(name);
        this.value = value ;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
