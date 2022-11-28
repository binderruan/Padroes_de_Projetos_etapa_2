package flyweight;

import java.util.ArrayList;
import java.util.List;

public class Vitrine implements MyRoupa{
    private String nomeVitrine;
    private List<MyRoupa> Roupas;

    public Vitrine(String nomeVitrine){
        this.nomeVitrine=nomeVitrine;
        this.Roupas = new ArrayList<MyRoupa>();
    }

    public void add(MyRoupa i){
        Roupas.add(i);
    }

    public String get(){
       String result = new String();
       for(MyRoupa ing:Roupas){
           result = result.concat(ing.get()+"\n");
       }
       return result;
    }

    public String getNomeVitrine() {
        return nomeVitrine;
    }

    public void setNomeVitrine(String nomeVitrine) {
        this.nomeVitrine = nomeVitrine;
    }
}
