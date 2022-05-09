package com.example.testedeinterface;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private EditText campoNome;
    private TextInputEditText campoEmail;
    private TextView checkBoxResultado;
    private TextView editTextResultado;
    private TextView radiobuttonResultado;
    private CheckBox checkEconomia, checkEsportes , checkEntretenimento;
    private ProgressBar progressBar;
    private TextView campoIdade, campoIdadeResultado;
    private SeekBar seekBar;

    //RadioGroup
    private RadioButton sexoMasc;
    private RadioButton sexoFem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            //ATRIBUINDO IDs/////////////
            //Textos
            campoNome   = findViewById(R.id.editNome);
            campoEmail  = findViewById(R.id.editEmail);
            editTextResultado = findViewById(R.id.textResultadoDados);
            checkBoxResultado = findViewById(R.id.textResultadoCategoria);
            radiobuttonResultado = findViewById(R.id.textResultadoSexo);
            campoIdade = findViewById(R.id.textIdadeProgresso);
            campoIdadeResultado = findViewById(R.id.textIdadeResultado);

            //Checkbox
            checkEconomia       = findViewById(R.id.checkEconomia);
            checkEsportes       = findViewById(R.id.checkEsportes);
            checkEntretenimento = findViewById(R.id.checkEntretenimento);

            //RadioGroup
            sexoMasc = findViewById(R.id.radioButtonM);
            sexoFem = findViewById(R.id.radioButtonF);

            //Progress Bar
            progressBar = findViewById(R.id.progressBar);

            //Seek Bar
            seekBar = findViewById(R.id.seekBar);


        //LISTENER SEEKBAR ///////////////////////
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                campoIdade.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });///////////////////////////////////////////

    }   ///////////////////////////////


    //METODO CHECKBOX/////////////////
        public void checkbox(){

            String texto = "Categorias Selecionadas: \n";
            String categSelec = "";

            //Validando CheckBox
            if ( checkEconomia.isChecked() ){
                categSelec = checkEconomia.getText().toString() + " ";
            }
            if ( checkEsportes.isChecked() ){
                categSelec = categSelec + checkEsportes.getText().toString()+ " ";
            }
            if ( checkEntretenimento.isChecked() ){
                categSelec = categSelec + checkEntretenimento.getText().toString() + " ";
            }

            //Settando texto de Resultado
            checkBoxResultado.setText(texto + categSelec);
        };
    //////////////////////////////


    //METODO RADIOBUTTON///////////////////////
        public void radiobutton(){

            String texto = "Sexo selecionado: ";
            String sexoSelec = "";

            //Validando RadioButton
            if (sexoMasc.isChecked()){
                sexoSelec = sexoMasc.getText().toString();

            }else if (sexoFem.isChecked()){
                sexoSelec = sexoFem.getText().toString();
            }

            //Settando texto de Resultado
            radiobuttonResultado.setText(texto + sexoSelec );
        }
    /////////////////////////////////////////////

    //METODO ENVIAR /////////////////
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void enviar(View view){

        checkbox();
        radiobutton();
        abrirToast(textoDadosSalvos);
        loadProgressBar();

        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        editTextResultado.setText( "Nome: " + nome +  "\nEmail: " + email);
        campoIdadeResultado.setText("Idade: " + seekBar.getProgress());
    }
    /////////////////////////////////

    //METODO TOAST PADRÃO//////////////////////////
    String textoDadosSalvos = "Parabéns! Seus dados foram salvos";
    String textoDadosApagados = "Os dados foram apagados!!";
        public void abrirToast(String txt){
            Toast.makeText(
                    getApplicationContext(),                    //context
                    txt,                           //msg
                    Toast.LENGTH_LONG                           //duração
                    ).show();
        }
    ////////////////////////////////////////////////

    //METODO ALERT DIALOG//////////////////////////
        public void abrirDialog (View view){

            //INSTANCIAR ALERTDIALOG
            AlertDialog.Builder dialog = new AlertDialog.Builder( this);

            //CONFIGURAR TITULO E MENSAGEM
            dialog.setTitle( "Limpar dados" );
            dialog.setMessage("Você tem certeza que gostaria de apagar os dados informados?");

            //CONFIGURAR CANCELAMENTO
            dialog.setCancelable(true);

            //AÇÕES PARA SIM E NÃO
            dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    limpar();
                    abrirToast(textoDadosApagados);
                }
            });
            dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.create();
            dialog.show();
        }
        /////////////////////////////////////////////////////////////////////////////////////

    //METODO LIMPAR ///////////////////////
    public void limpar() {
        campoNome.setText("");
        campoEmail.setText("");
        checkBoxResultado.setText("");
        editTextResultado.setText("");
        radiobuttonResultado.setText("");
        checkEsportes.setChecked(false);
        checkEconomia.setChecked(false);
        checkEntretenimento.setChecked(false);
        sexoMasc.setChecked(false);
        sexoFem.setChecked(false);
        progressBar.setProgress(0);
        seekBar.setProgress(0);
        campoIdade.setText("");
        campoIdadeResultado.setText("");

    }
    /////////////////////////////////////////

    //METODO DE CARREGAR PROGRESS BAR///////////////
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void loadProgressBar() {
        int progresso = 0;
        int progressoFinal = 100;
        progressBar.setMax(progressoFinal);
        while (progresso < progressoFinal) {
            progresso = progresso + 1;
            progressBar.setProgress(progresso, true);
        }
    }
    ////////////////////////////////////////////////
}