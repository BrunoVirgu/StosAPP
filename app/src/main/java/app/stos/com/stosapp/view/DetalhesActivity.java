package app.stos.com.stosapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.stos.com.stosapp.R;
import app.stos.com.stosapp.model.Filme;
import app.stos.com.stosapp.model.Sessao;

public class DetalhesActivity extends AppCompatActivity {
    private TextView nome, descricao;
    private ImageView imagem;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes2);
        setUpItemsView();
        setUpInformations();
    }

    protected void setUpItemsView(){
        this.nome = findViewById(R.id.nome);
        this.descricao = findViewById(R.id.descricao);
        this.imagem = findViewById(R.id.imageView);
        this.scrollView = findViewById(R.id.scroll);
    }

    private void setUpInformations(){
        Filme filme = Sessao.instance.getFilmeSelecionado();
        nome.setText(filme.getTitulo());
        descricao.setText(filme.getDescricao());
        String urlImagem;
        if(filme.getUrlImagem().substring(0,1).equals("h")){
            urlImagem = filme.getUrlImagem();
        }else {
            urlImagem = "http:\\/\\/"+ filme.getUrlImagem();
        }
        Picasso.get()
                .load(urlImagem)
                .resize(768, 1024)
                .into(imagem);

    }
}
