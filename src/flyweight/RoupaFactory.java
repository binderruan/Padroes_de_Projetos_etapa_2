package flyweight;

import java.util.HashMap;
import java.util.Map;

public class RoupaFactory {
    private static Map<String, SingleRoupa> roupas;

    private static void init(){
        roupas = new HashMap<String, SingleRoupa>();
        roupas.put("Calca", new SingleRoupa("Calca",2));
        roupas.put("Camiseta",new SingleRoupa("Camiseta",3));
        roupas.put("Calcao",new SingleRoupa("Calcao", 1));
    }

    public static SingleRoupa get(String name){
        if(roupas==null)
            init();
        return roupas.get(name);
    }
}
