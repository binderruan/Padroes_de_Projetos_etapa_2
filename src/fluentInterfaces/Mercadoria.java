package fluentInterfaces;

public class Mercadoria {
    private String nome;
    private String descricao;
    private Double quantidade;

    public Mercadoria named(String nome){
        this.nome = nome;
        return this;
    }

    public Mercadoria described(String descricao){
        this.descricao = descricao;
        return this;
    }

    public Mercadoria theAmount(Double quantidade){
        this.quantidade = quantidade;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getQuantidade() {
        return quantidade;
    }
}
