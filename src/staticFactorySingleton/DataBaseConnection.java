package staticFactorySingleton;

public class DataBaseConnection {
    private String url;
    private String user;
    private String senha;
    private static DataBaseConnection instance;

    private DataBaseConnection(String url, String user, String senha){
        this.url = url;
        this.user = user;
        this.senha = senha;
    }

    private DataBaseConnection(String user, String senha){
        this.url = "UrlPadr�o";
        this.user = user;
        this.senha = senha;
    }

    private DataBaseConnection(){
        this.url = "UrlPadr�o";
        this.user = "UserPadr�o";
        this.senha = "SenhaPadr�o";
    }

    public static DataBaseConnection newConnection(String url, String user, String senha){
        if(!valida(url) || !valida(user) || !valida(senha))
            return null;

        if(instance!=null){
            instance.setUrl(url);
            instance.setUser(user);
            instance.setSenha(senha);
            return instance;
        }
        
        DataBaseConnection novaInstancia = new DataBaseConnection(url, user, senha);
        instance = novaInstancia;
        return instance;
    }

    public static DataBaseConnection newUserConnection(String user, String senha){
        //valida��o
        if(!valida(user) || !valida(senha))
            return null;

        if(instance!=null){
            instance.setUser(user);
            instance.setSenha(senha);
            return instance;
        }
        //Criando nova Instancia para o usuario caso nao exista
        DataBaseConnection novaInstancia = new DataBaseConnection(user,senha);
        instance = novaInstancia;
        return instance;
    }

    public static DataBaseConnection newUrlConnection(String url){
        if(!valida(url))
            return null;

        if(instance!=null){
            instance.setUrl(url);
            return instance;
        }
        //Criando nova Instancia para o usuario caso nao exista
        DataBaseConnection novaInstancia = new DataBaseConnection();
        novaInstancia.setUrl(url);
        instance = novaInstancia;
        return instance;
    }

    public static DataBaseConnection newSenhaConnection(String senha){
        //valida��o
        if(!valida(senha))
            return null;

        if(instance!=null){
            instance.setSenha(senha);
            return instance;
        }
        //Criando nova Instancia caso nao tenha sido inicializada
        DataBaseConnection novaInstancia = new DataBaseConnection();
        novaInstancia.setSenha(senha);
        instance = novaInstancia;
        return instance;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private static boolean valida(String content){
        return !content.contains(" ");
    }
}