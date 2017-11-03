package main.java.webclientwithdb;

import net.webservicex.*;
import main.java.Model.Tables;

public class WebClientWithDB {
    public static void main(String[] args) {
        
        Tables table = new Tables();
        
        LengthUnit service = new LengthUnit();
        LengthUnitSoap port = service.getLengthUnitSoap12();
        
        for(int i=10; i<=100; i+=10){
            table.updateKilometers(port.changeLengthUnit(i, Lengths.MILES, Lengths.KILOMETERS), i);
        }
        
    }
    
}
