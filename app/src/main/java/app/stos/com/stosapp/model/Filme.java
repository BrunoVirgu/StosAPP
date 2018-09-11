package app.stos.com.stosapp.model;

public class Filme {
    private long id;
    private String titulo;
    private String descricao;
    private String capa;
    private String setUrlImagem;

    public String getUrlImagem() {
        return setUrlImagem;
    }

    public void setSetUrlImagem(String setUrlImagem) {
        this.setUrlImagem = setUrlImagem;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
