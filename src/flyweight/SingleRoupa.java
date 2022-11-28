package flyweight;

public class SingleRoupa implements MyRoupa{
    private final String nome;
    private final int quantidade;

    public SingleRoupa(String nome, int quantidade){
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String get(){
        return "Peça: "+nome+" | Quantidade: "+quantidade;
    }
}
