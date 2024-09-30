
package br.com.aula.text;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void calcular(View view) {
        TextInputEditText nome = findViewById(R.id.TextInputEditNome);
        TextInputEditText peso = findViewById(R.id.TextInputEditPeso);
        TextInputEditText altura = findViewById(R.id.TextInputEditAltura);

        TextView result1 = findViewById(R.id.result1);
        TextView result2 = findViewById(R.id.result2);

        String nome1 = nome.getText().toString();
        String peso1 = peso.getText().toString();
        String altura1 = altura.getText().toString();

        double numPeso = Double.parseDouble(peso1);
        double numAltura = Double.parseDouble(altura1);
        double numImc = numPeso / (numAltura * numAltura);

        String imc = String.valueOf(numImc);

        DecimalFormat df = new DecimalFormat("#.##");
        imc = df.format(numImc);

        result1.setText(imc);


            // Classificação do IMC
        if (numImc < 18.5) {
            result2.setText("Abaixo do peso");
            Intent intent = new Intent(this, Magreza.class);
            startActivity(intent);
        }
        else if (numImc >= 18.5 && numImc <=24.9) {
            result2.setText("Peso normal");
            Intent intent = new Intent(this, PesoNormal.class);
            startActivity(intent);
        }
        else if (numImc >= 25 && numImc <=29.9) {
            result2.setText("Sobrepeso");
            Intent intent = new Intent(this, Sobrepeso.class);
            startActivity(intent);
        }
        else if (numImc >= 30 && numImc <=34.9) {
            result2.setText("Obesidade grau 1");
            Intent intent = new Intent(this, ObesidadeI.class);
            startActivity(intent);
        }
        else if (numImc >= 35) {
            result2.setText("Obesidade grau 2");
            Intent intent = new Intent(this, ObesidadeII.class);
            startActivity(intent);
        }

        // Validação de campos
        if(numPeso < 40 || numPeso > 150 || numAltura < 1.2 || numAltura > 2.5)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Erro de validação");
            builder.setMessage("Dados invalidos. Favor inserir corretamente");


            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });


            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    public void limpar(View view) {

        TextView result1 = findViewById(R.id.result1);
        TextView result2 = findViewById(R.id.result2);

            result1.setText("");
            result2.setText("");


    }

}