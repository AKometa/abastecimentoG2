package com.example.cadastrocartola;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends AppCompatActivity{

    private ListView lvTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //
        lvTimes = findViewById(R.id.lvTimes);


        //
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, telaDeCadastroTime.class);
                i.putExtra("acao", "inserir");
                startActivity( i );
            }
        });

        //
        lvTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Time prod = (Time) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(MainActivity.this, telaDeCadastroTime.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idTime", prod.getId() );
                startActivity( intent );
            }
        });

        lvTimes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluir( (Time) adapterView.getItemAtPosition(i)  );
                return true;
            }
        });
        //
    }



    private void excluir(final Time time){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Produto");
        alerta.setMessage("Confirma a exclusão do produto "
                + time.getNome() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TimeDAO.excluir(MainActivity.this, time.getId());
                carregarLista();
            }
        });
        alerta.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        carregarLista();
    }

    private void carregarLista(){
        List<Time> lista = TimeDAO.getProdutos(this);

        if ( lista.size() == 0 ){
            lvTimes.setEnabled( false );
            Time fake = new Time();
            fake.setAno(0);
            fake.setNome("MainActivity Vazia!");
            fake.setKm("MainActivity Vazia!");
            lista.add( fake );
        }else {
            lvTimes.setEnabled( true );
        }

//        ArrayAdapter<Produto> adapter = new ArrayAdapter(
//                this, android.R.layout.simple_list_item_1,
//                lista);

        AdapterTime adapter = new AdapterTime(this, lista);

        lvTimes.setAdapter( adapter );

    }

}