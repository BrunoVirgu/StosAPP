package app.stos.com.stosapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sessao {
    public static final Sessao instance = new Sessao();
    private final Map<String, Object> values = new HashMap<>();

    private void setValor(String chave, Object valor){
        values.put(chave, valor);
    }

    public void setFilmesList(ArrayList<Filme> filmesList) {
        setValor("sessao.FilmeList", filmesList);
    }

    public ArrayList<Filme> getFilmesList() {
        return (ArrayList<Filme>) values.get("sessao.FilmeList");
    }

    public Filme getFilmeSelecionado(){
        return (Filme) values.get("sessao.FilmeSelecionado");
    }

    public void setFilmeSelecionado(Filme filmeSelecionado){
        setValor("sessao.FilmeSelecionado", filmeSelecionado);
    }

}
