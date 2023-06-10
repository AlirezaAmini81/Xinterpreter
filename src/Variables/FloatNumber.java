package Variables;

/**
 * a subclass for Variable that <b>float variables</b> can be defined by ues it
 */

public class FloatNumber extends Variable {
    private float value ;

    public FloatNumber(String name, float value) {
        super(name);
        this.value = value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
