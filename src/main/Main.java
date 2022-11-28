package main;

import decoratorAndProxy.*;
import staticFactorySingleton.DataBaseConnection;
import fluentInterfaces.*;
import commands.*;
import doubleDispatch.*;
import doubleDispatch.Produto;
import facade.*;
import flyweight.*;

public class Main {

    public static void main(String[] args){

        //Utilizando o padrao Prox e Decorator para buscar, criar e alterar funcioanrios
    	
		System.out.println("------------------- \n PADRAO PROXY E DECORATOR");

		 FuncionarioDAOInterface  funcionarioDAO = new FuncionarioDAODecorator();

	        Funcionario ruan = new Funcionario("Ruan", "Vendedor", 1000.0);

	        //Sempre utilizando try por estar utilizando "Banco de dados" e para ver se consegue pegar alguma exceção

	        System.out.println("Adicionando Ruan e promovendo:");
	        try {
	            //adicionando ruan e depois alterando seu salario
	            funcionarioDAO.addFuncionario(ruan);
	            Funcionario ruanPromovido = new Funcionario(ruan.getNome(), ruan.getCargo(), ruan.getSalario()*1.40);
	            funcionarioDAO.altFuncionario(ruan.getNome(), ruanPromovido);
	        }catch (FuncionarioException ex){
	            System.out.println("Não consegui adicionar/Promover o usuário vazio.");
	            System.out.println(ex.getMessage());
	        }

	        System.out.println("Procurando Funcionario Existente:");
	        try {
	            //Procurando funcionario existente
	            Funcionario ruanDnv = funcionarioDAO.getFuncionario("Ruan");
	            System.out.println(ruanDnv.getNome());
	        }catch (FuncionarioException ex){
	            System.out.println("Nao foi possivel encontar usuario.");
	            System.out.println(ex.getMessage());
	        }

		System.out.println("------------------- \n PADRAO STATIC FACTORY METHOD E SINGLETON");


        System.out.println("Criando instancias e dando print: ");
        DataBaseConnection ruan1 = DataBaseConnection.newConnection("MySQL","ruan","senhaErrada");
        System.out.println("URL: "+ruan1.getUrl()+" | Usuario: "+ruan1.getUser()+" | Senha: "+ruan1.getSenha());

        DataBaseConnection ruan2 = DataBaseConnection.newSenhaConnection("senhaCerta");
        System.out.println("URL: "+ruan2.getUrl()+" | Usuario: "+ruan2.getUser()+" | Senha: "+ruan2.getSenha());

        DataBaseConnection yasmin = DataBaseConnection.newUserConnection("yasmin", "12345678");
        System.out.println("URL: "+yasmin.getUrl()+" | Usuario: "+yasmin.getUser()+" | Senha: "+yasmin.getSenha());

        DataBaseConnection yasmin2 = DataBaseConnection.newUrlConnection("SQLite");
        System.out.println("URL: "+yasmin2.getUrl()+" | Usuario: "+yasmin2.getUser()+" | Senha: "+yasmin2.getSenha());

        System.out.println("\nObjetos apos a realizacao da ultima conexao:");
        System.out.println("URL: "+ruan1.getUrl()+" | Usuario: "+ruan1.getUser()+" | Senha: "+ruan1.getSenha());
        System.out.println("URL: "+ruan2.getUrl()+" | Usuario: "+ruan2.getUser()+" | Senha: "+ruan2.getSenha());
        System.out.println("URL: "+yasmin.getUrl()+" | Usuario: "+yasmin.getUser()+" | Senha: "+yasmin.getSenha());
        System.out.println("URL: "+yasmin2.getUrl()+" | Usuario: "+yasmin2.getUser()+" | Senha: "+yasmin2.getSenha());

        //Utilizando o padrao fluent interface para criar mercadorias
        
		System.out.println("------------------- \n PADRAO FLUENT INTERFACES");
        System.out.println("Criando novas mercadorias:");

        Mercadoria camiseta = new Mercadoria().named("Camiseta").described("Sem estapa ").theAmount(10.0);

        System.out.println("Nome: "+camiseta.getNome()+" | Descricao: "+camiseta.getDescricao()+" | Quantidade: "+camiseta.getQuantidade());

        //Utilizando o padrao commands para criar o carrinho de produtos comprados
		System.out.println("------------------- \n  PADRAO COMMANDS");

        Carrinhoo c = new Carrinhoo();
        ExecutorComando exec = new ExecutorComando();

        ProdutoPronto camisa = new ProdutoPronto("camisa", 50.0);
        ProdutoPronto calca = new ProdutoPronto("calca", 100.0);
        ProdutoPronto blusa = new ProdutoPronto("blusa", 150.0);

        c.setProdutos(exec.adicionar(new InserirProduto(c.getProdutos(), camisa)));
        c.setProdutos(exec.adicionar(new InserirProduto(c.getProdutos(), calca)));
        c.setProdutos(exec.adicionar(new InserirProduto(c.getProdutos(), blusa)));


        printCarrinho(c);
        c.setProdutos(exec.desfazer());
        printCarrinho(c);
        c.setProdutos(exec.desfazer());
        printCarrinho(c);
        c.setProdutos(exec.refazer());
        c.setDesconto(20.0);
        
        printCarrinho(c);

		System.out.println("------------------- \n  PADRAO DOUBLE DISPATCH");

		Produto meia = new ProdutoFisico(5, 20, 7);
		Produto cueca = new ProdutoFisico(10, 20, 7);
		Produto ebook = new ProdutoDigital(50, 5);

		Carrinho cc = new Carrinho();
		cc.addProduto(meia);
		cc.addProduto(cueca);
		cc.addProduto(ebook);

		System.out.println("Preco: " + cc.getTotal());
		System.out.println("Peso: " + cc.getPeso());
	
		System.out.println("------------------- \n  PADRAO FACADE ");

		ReservaProduto reservaproduto = new ReservaProduto();
		reservaproduto.ReservaProdutos();


        //Utilizando o padrao FlyWeight para-
		System.out.println("------------------- \n  PADRAO FLYWEIGHT ");
		   
        //Criando vitrine
        Vitrine vitrine = new Vitrine("Vitrine da loja");
        vitrine.add(RoupaFactory.get("Calca"));
        vitrine.add(RoupaFactory.get("Camiseta"));
        vitrine.add(RoupaFactory.get("Calcao"));

        //Mostrando a receita
        System.out.println("\nVitrine: "+vitrine.getNomeVitrine());
        System.out.println(vitrine.get());	
		
    }

    //Produtos no carrinho
    public static void printCarrinho(Carrinhoo c){
        for(ProdutoPronto p : c.getProdutos()){
            System.out.println("Nome: "+p.getNome()+" | Preco: "+p.getPreco());
        }
        System.out.println("Preco final: "+c.getPrecoTotal()+"\n");
    }
}
