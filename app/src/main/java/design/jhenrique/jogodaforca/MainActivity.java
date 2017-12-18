package design.jhenrique.jogodaforca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String palavras [] = {"MACACO", "CACHORRO", "GATO", "VACA", "COELHO", "CANGURU", "MELANCIA", "ABACAXI", "CARAMBOLA", "TARTARUGA", "VITAMINA", "LARANJA", "BANANA", "ACEROLA", "AVIAO", "CARRO", "APOCALIPSE", "SOL", "LUA", "OCEANO", "PRAIA" };
    String palavra;
    TextView resultado;
    ArrayList<View> listadeBotoes;
    int indiceImagem = 0;
    String letra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado = findViewById(R.id.textResult);
        GridLayout layout = findViewById(R.id.gridBotoes);
        listadeBotoes = layout.getTouchables();
        inicio();

    }

    private void inicio() {
        palavra = palavras[(int) (Math.random() * palavras.length)];

        StringBuffer underline = new StringBuffer(palavra.length());
        for (int i = 0; i < palavra.length(); i++) {
            underline.append("_");
        }
        resultado.setText(underline);

        for (View touchable : listadeBotoes) {
            if (touchable instanceof Button)
                (touchable).setEnabled(true);
        }
        (findViewById(R.id.imageForca)).setBackgroundResource(R.drawable.forca);
        indiceImagem=0;
    }

    public void onBtnClick(View v) {
        Button b = (Button) v;
        b.setEnabled(false);
        letra = b.getText().toString();
        int i = palavra.indexOf(letra);
        if (i == -1) alteraForca();
        else insereLetras(i);
        verificaFim();
    }

    private void verificaFim() {
        String texto = resultado.getText().toString();
        if (texto.indexOf("_") == -1) {
            Toast.makeText(this, "Fim de partida.\nVocê ganhou", Toast.LENGTH_LONG).show();
            inicio();
        }
    }

    private void insereLetras(int pos) {
        StringBuffer underline = new StringBuffer(resultado.getText());
        for (int i = 0; i < palavra.length(); i++) {
            if (i == pos) underline.replace(i, i + 1, letra);
        }
        resultado.setText(underline);
        int i2 = palavra.indexOf(letra, pos + 1);
        //Toast.makeText(this,"pos:"+i2,Toast.LENGTH_LONG).show();
        if (i2 != -1) insereLetras(i2);
    }

    private void alteraForca() {
        switch (indiceImagem) {
            case 0:
                ((ImageView) findViewById(R.id.imageForca)).setBackgroundResource(R.drawable.cabeca);
                indiceImagem++;
                break;
            case 1:
                ((ImageView) findViewById(R.id.imageForca)).setBackgroundResource(R.drawable.cabecacorpo);
                indiceImagem++;
                break;
            case 2:
                ((ImageView) findViewById(R.id.imageForca)).setBackgroundResource(R.drawable.cabecacorpoumbraco);
                indiceImagem++;
                break;
            case 3:
                ((ImageView) findViewById(R.id.imageForca)).setBackgroundResource(R.drawable.cabecacorpodoisbracos);
                indiceImagem++;
                break;
            case 4:
                ((ImageView) findViewById(R.id.imageForca)).setBackgroundResource(R.drawable.cabecacorpodoisbracosumaperna);
                indiceImagem++;
                break;
            case 5:
                ((ImageView) findViewById(R.id.imageForca)).setBackgroundResource(R.drawable.cabecacorpodoisbracosduaspernas);
                Toast.makeText(this, "Fim de partida.\nA palavra era " + palavra, Toast.LENGTH_LONG).show();
                inicio();
                break;
        }
    }
}
