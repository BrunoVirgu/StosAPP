package app.stos.com.stosapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import app.stos.com.stosapp.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        criarFragment(savedInstanceState);
    }

    private void criarFragment(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            FilmeListFragment frag = new FilmeListFragment();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.container ,frag).commit();
        }
    }


}
