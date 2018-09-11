package app.stos.com.stosapp.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.stos.com.stosapp.R;
import app.stos.com.stosapp.model.Filme;
import app.stos.com.stosapp.presenter.ServicoDownload;
import app.stos.com.stosapp.model.Permissoes;
import app.stos.com.stosapp.model.Sessao;

public class SplashActivity extends Activity implements Runnable {
    ArrayList<Filme> filmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        isOnline();
        String permissions[] = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };
        boolean ok = Permissoes.validate(this, 0, permissions);

        if (ok) {
            Handler handler = new Handler();
            handler.postDelayed(this, 5000);
        }
    }

    private void isOnline() {
        if(ServicoDownload.isNetworkAvailable(getApplicationContext()))
        {
            DownloadFilmes newsTask = new DownloadFilmes();
            newsTask.execute();
        }else{
            Toast.makeText(getApplicationContext(), "Sem conexão com a internet", Toast.LENGTH_LONG).show();
        }
    }

    public class DownloadFilmes extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... strings) {
            String xml;
            String urlParameters = "";
            xml = ServicoDownload.excuteGet("http://processo.stos.mobi/app/filme/listar", urlParameters);
            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {
            if(xml.length()>10){
                try {
                    JSONArray jsonArray = new JSONArray(xml);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        adicionarFilme(jsonArray, i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Erro não esperado", Toast.LENGTH_SHORT).show();
                }
                Sessao.instance.setFilmesList(filmes);
            } else{
                Toast.makeText(getApplicationContext(), "Nenhum filme encontrado", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void adicionarFilme(JSONArray jsonArray, int i) throws JSONException {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        Filme filme = new Filme();
        filme.setId(jsonObject.optLong("id"));
        filme.setDescricao(jsonObject.optString("descricao"));
        filme.setTitulo(jsonObject.optString("titulo"));
        filme.setCapa(jsonObject.optString("capa"));
        filme.setSetUrlImagem(jsonObject.optString("url_imagem"));
        filmes.add(filme);
    }

    public void run() {
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "É preciso autorizar as permissões para utilizar o app", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        }
        run();
    }
}

