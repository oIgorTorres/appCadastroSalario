package br.ulbra.appcalculadorasalario;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class PrincipalActivity extends AppCompatActivity {

    EditText edtSalarioBruto, edtFilhos;
    Button btnCalcular;
    RadioGroup rgOpcoes;
    TextView txtResp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        edtSalarioBruto = findViewById(R.id.edtSalarioBruto);
        edtFilhos = findViewById(R.id.edtFilhos);
        btnCalcular = findViewById(R.id.btnCalcular);
        rgOpcoes = (RadioGroup) findViewById(R.id.rgOpcoes);
        txtResp = findViewById(R.id.txtResp);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                int filhos;
                double salarioB, inss = 0, ir = 0, salarioF = 0, salarioL = 0, resp;


                salarioB = Double.parseDouble(edtSalarioBruto.getText().toString());
                filhos = Integer.parseInt(edtFilhos.getText().toString());

                if (salarioB > 0) {
                    if (salarioB <= 1212.00) {
                        inss = 0.075 * salarioB;

                    } else if (salarioB >= 1212.01 && salarioB <= 2427.35) {
                        inss = 0.09 * salarioB;

                    } else if (salarioB >= 2427.36 && salarioB <= 3641.03) {
                        inss = 0.012 * salarioB;

                    } else if (salarioB >= 3641.04) {
                        inss = 0.015 * salarioB;

                    }

                } else {
                    Toast.makeText(PrincipalActivity.this, "Salário Bruto precisa ser maior ou igaul a zero", Toast.LENGTH_SHORT).show();
                }

                if (salarioB > 0) {
                    if (salarioB <= 1903.98) {
                        ir = 0;

                    } else if (salarioB >= 1903.99 && salarioB <= 2826.65) {
                        ir = 0.075 * salarioB;

                    } else if (salarioB >= 2826.66 && salarioB <= 3751.05) {
                        ir = 0.015 * salarioB;

                    } else if (salarioB >= 3751.06) {
                        ir = 0.0225 * salarioB;

                    }

                } else {
                    Toast.makeText(PrincipalActivity.this, "Salário Bruto precisa ser maior ou igual a zero", Toast.LENGTH_SHORT).show();
                }
                salarioF = 56.47 * filhos;
                salarioL = salarioB - inss - ir + salarioF;

                DecimalFormat df = new DecimalFormat("#.##");
                String salarioFf = df.format(salarioF);

                DecimalFormat df2 = new DecimalFormat("#.##");
                String salarioLf = df.format(salarioL);

                DecimalFormat df3 = new DecimalFormat("#.##");
                String inssF = df.format(inss);

                DecimalFormat df4 = new DecimalFormat("#.##");
                String irF = df.format(ir);

                int op = rgOpcoes.getCheckedRadioButtonId();
                if (op == R.id.radioMasculino){
                    txtResp.setText("Inss: R$ " +inssF+ "\n Imposto de renda: R$ " +irF+ "\n Salário família: R$ " +salarioFf+ "\n Salário líquido: R$ " +salarioLf+ "\n \nObservação: mude os valores nos campos e clique novamente no botão calcular para um novo cálculo.");
                }else if (op == R.id.radioFeminino){
                    txtResp.setText("Sra, o desconto do seu salário bruto são de: R$ " +inssF+ "\n Imposto de renda: R$ " +irF+ "\n Salário família: R$ " +salarioFf+ "\n Salário líquido: R$ " +salarioLf+ "\n \nObservação: mude os valores nos campos e clique novamente no botão calcular para um novo cálculo.");
                }



            }
        });
    }
}


