package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ListeDetail extends HashMap implements Serializable {

    private Map<String,PlanteDetail> plante = new HashMap<>();

    public void setMap(HashMap m){
        plante = m;
    }
}
