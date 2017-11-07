package main.java.webclientwithdb;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import net.webservicex.*;
import main.java.Model.Tables;

public class WebClientWithDB {
    public static void main(String[] args) {
        
        Tables table = new Tables();
        
        LengthUnit service = new LengthUnit();
        LengthUnitSoap port = service.getLengthUnitSoap12();
        
        for(int i=10; i<=100; i+=10){
            double result = port.changeLengthUnit(i, Lengths.MILES, Lengths.KILOMETERS);
            String formated = NumberFormat.getInstance().format(result);
            table.updateKilometers(formated, i);
        }
        
    }
    
}
