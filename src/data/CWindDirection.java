package data;

/**
 *
 * @author georgiosaslanidis
 */
public class CWindDirection {
    
    String sDirection;
    
    CArray<String> sCompas;
            
    public CWindDirection(){
    
        sDirection = new String();
        
        sCompas = new CArray<>(8);
        sCompas.appendItem("North");
        sCompas.appendItem("Northeast");
        sCompas.appendItem("East");
        sCompas.appendItem("Southeast");
        sCompas.appendItem("South");
        sCompas.appendItem("Southwest");
        sCompas.appendItem("West");
        sCompas.appendItem("Northwest");
    }
    
    public String getDirection(long p_lDegrees){
        
        long lDegrees = p_lDegrees;

        int nPosition = (int) ((lDegrees%360)/45);
        sDirection = sCompas.getItem(nPosition);
        
        return sDirection;
    }
}

