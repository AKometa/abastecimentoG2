package com.example.cadastrocartola;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class editar extends AppCompatActivity {

    private EditText nome_do_time, ano_de_fundacao, km;
    private Button salvar_time;
    private String acao;
    private int idProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_cadastro_time);

        nome_do_time = (EditText) findViewById(R.id.nome_do_time);
        km = (EditText) findViewById(R.id.km);
        ano_de_fundacao = (EditText) findViewById(R.id.ano_de_fundacao);

        salvar_time = (Button) findViewById(R.id.salvar_time);

        acao = getIntent().getExtras().getString("acao");
        if ( acao.equals("editar")){
            idProduto = getIntent().getExtras().getInt("idProduto");
            carregarFormulario(idProduto);
        }

        salvar_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    private void salvar(){
        String nome = nome_do_time.getText().toString();
        String km2 = km.getText().toString();
        String qtd = ano_de_fundacao.getText().toString();

        if( nome.isEmpty() ){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon( android.R.drawable.ic_dialog_alert);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Você deve informar o nome do produto.");
            alerta.setPositiveButton("OK", null);
            alerta.show();
        }else {
            Time p = new Time();
            p.setKm( km2 );
            p.setNome( nome );
            if( qtd.isEmpty() ){
                p.setAno( 0 );
            }else {
                p.setAno( Integer.valueOf( qtd ) );
            }

            if( acao.equals("inserir")) {
                TimeDAO.inserir(this, p);
            }
            if( acao.equals("editar")) {
                p.setId( idProduto );
                TimeDAO.editar(this, p);
            }

            this.finish();
        }
    }

    private void carregarFormulario(int idProduto){
        Time prod = TimeDAO.getProdutoById(this, idProduto);
        nome_do_time.setText( prod.getNome() );
        km.setText( prod.getKm() );
        ano_de_fundacao.setText( String.valueOf( prod.getAno() ) );

    }

}