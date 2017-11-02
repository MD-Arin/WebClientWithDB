package webclientwithdb;

import net.webservicex.*;

public class WebClientWithDB {
    public static void main(String[] args) {
        
        Model.Tables table = new Model.Tables();
        Lengths fromLengthUnit = Lengths.MILES;
        Lengths toLengthUnit = Lengths.KILOMETERS;
        
        
        LengthUnit service = new LengthUnit();
        LengthUnitSoap port = service.getLengthUnitSoap12();
        
        for(int i=10; i<=100; i+=10){
            table.updateKilometers(port.changeLengthUnit(i, fromLengthUnit, toLengthUnit), i);
        }
        
    }
    
}
