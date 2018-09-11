package app.stos.com.stosapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import app.stos.com.stosapp.R;
import app.stos.com.stosapp.model.Filme;
import app.stos.com.stosapp.model.Sessao;

public class FilmeListFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Filme> listaFilmes;
    private ActionMode actionMode;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filmes_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        listaFilmes = Sessao.instance.getFilmesList();
        recyclerView.setAdapter(new FilmesAdapter(getContext(), listaFilmes, onClickTitulo()));
        return view;
    }

    private FilmesAdapter.TituloOnClickListener onClickTitulo() {
        return new FilmesAdapter.TituloOnClickListener() {
            @Override
            public void onClickTitulo(FilmesAdapter.FilmesViewHolder holder, int indexFilme) {
                Filme filme = listaFilmes.get(indexFilme);
                if (actionMode == null){
                    detalharFilme(filme);
                }
        }
    };
}

    private void detalharFilme(Filme filme) {
        Toast.makeText(getContext(), filme.getTitulo(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), DetalhesActivity.class);
        Sessao.instance.setFilmeSelecionado(filme);
        startActivity(intent);
    }
    }
