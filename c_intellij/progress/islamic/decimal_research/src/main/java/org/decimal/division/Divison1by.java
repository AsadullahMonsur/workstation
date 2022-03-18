package org.decimal.division;

import java.text.DecimalFormat;

public class Divison1by {
    private double output;

    public Divison1by() {
      output = 0.0;
    }

    public double divide(double divider){
        output = 1.0 / divider;
        return output;
    }
}
