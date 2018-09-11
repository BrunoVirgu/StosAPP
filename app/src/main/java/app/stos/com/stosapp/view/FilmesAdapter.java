package app.stos.com.stosapp.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.stos.com.stosapp.R;
import app.stos.com.stosapp.model.Filme;

public class FilmesAdapter extends RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder> {
    private final List<Filme> filmes;
    private final Context context;
    private final TituloOnClickListener onClickListener;
    public interface TituloOnClickListener {
        void onClickTitulo(FilmesViewHolder holder, int indexFilme);
    }

    public FilmesAdapter(Context context, List<Filme> filmes, TituloOnClickListener onClickListener) {
        this.context = context;
        this.filmes = filmes;
        this.onClickListener = onClickListener;
    }

    @Override
    public FilmesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_filme, viewGroup, false);
        FilmesViewHolder holder = new FilmesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final FilmesViewHolder holder, final int position) {
        Filme filme = filmes.get(position);
        String urlImagem;
        if(filme.getUrlImagem().substring(0,1).equals("h")){
            urlImagem = filme.getUrlImagem();
        }else {
            urlImagem = "http:\\/\\/"+ filme.getUrlImagem();
        }
        Picasso.get()
                .load(urlImagem)
                .resize(400, 550)
                .into(holder.imageView);
        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickTitulo(holder, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return this.filmes != null ? this.filmes.size() : 0;
    }

    public static class FilmesViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public View view;
        CardView cardView;
        public FilmesViewHolder(View view) {
            super(view);
            this.view = view;
            imageView = view.findViewById(R.id.img);
            cardView = view.findViewById(R.id.card_view);
        }
    }
}

