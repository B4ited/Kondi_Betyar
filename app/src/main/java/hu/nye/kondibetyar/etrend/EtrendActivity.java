package hu.nye.kondibetyar.etrend;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.R;


public class EtrendActivity extends AppCompatActivity {
    private Intent intent;
    private ImageButton menu;
    private ImageButton search;
    private EditText suly;
    private  EditText magas;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private  EditText kor;
    private Button show;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etrend);
        menu=findViewById(R.id.ib_menu);
        search=findViewById(R.id.ib_check);
        suly=findViewById(R.id.et_súly);
        magas=findViewById(R.id.et_magassag);
        radioGroup=findViewById(R.id.radioGroup);
        kor=findViewById(R.id.et_kor);
        show=findViewById(R.id.b_sajat_edzes);
        result=findViewById(R.id.tv_result);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("Menu");
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("WebViewerActivity");
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton=findViewById(selectedId);

              //  toastMsg(radioButton.getText().toString());

              if(suly.getText().toString().matches("") || magas.getText().toString().matches("") || kor.getText().toString().matches(""))
                   toastMsg("Minden adatot kikel tölteni!");
              else result.setText(BMIScale(BMICalulator(suly.getText().toString(),magas.getText().toString(),radioButton.getText().toString(),kor.getText().toString())));
            }
        });

        getSupportActionBar().setTitle("Étrend");
    }

    public  String BMIScale(double result){
        String str=String.valueOf(result);
        return "Testzsír százalékod: "+str.substring(0,5)+"%";
    }

    public double BMICalulator(String suly, String magas,String neme,String kor){
        double height=Double.parseDouble(String.valueOf(magas))/100;
        double BMI = (Double.parseDouble(String.valueOf(suly)) /Math.pow(height,2));
        double result=1.20*BMI + 0.23*Integer.parseInt(kor);
        if(neme.matches("Férfi"))result-=16.2;
        if(neme.matches("Nő"))result-=5.4;
        return result;
    }



    public void OpenActivity(String Activity) {
        if (Activity == "Menu") intent = new Intent(this, Menu.class);
        if (Activity == "WebViewerActivity") intent = new Intent(this, EtrendWebViewerActivity.class);
        startActivity(intent);

    }

    public void toastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }
}
