package hu.nye.kondibetyar.edzes.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.database.DatabaseHelper;
import hu.nye.kondibetyar.edzes.EdzesNapActivity;

public class EditNapActivity extends AppCompatActivity {
    public static final String BUTTON_ID="hu.nye.kondibetyar.edzes.edit.BUTTON_ID";
    public static final String LEIRAS="hu.nye.kondibetyar.edzes.edit.LEIRAS";
    public static final String BOOLEAN="hu.nye.kondibetyar.edzes.edit.BOOLEAN";
    public static final String TERV_NEV="hu.nye.kondibetyar.edzes.TERV_NEV";
    private LinearLayout ll;
    private ImageButton menu;
    private Intent intent;
    private TextView title;
    private EditText leiras;
    private Button send;
    private String text;
    private String button_id;
    private  String terv_nev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes_edit_nap);
        ll=this.findViewById(R.id.l_tema);
        menu = this.findViewById(R.id.ib_menu);
        title=this.findViewById(R.id.t_title);
        leiras=this.findViewById(R.id.et_textarea);
        send=this.findViewById(R.id.b_send);

        intent=getIntent();
        button_id=intent.getStringExtra(EdzesNapActivity.BUTTON_ID);
        text=intent.getStringExtra(EdzesNapActivity.LEIRAS);
        terv_nev=intent.getStringExtra(EdzesNapActivity.TERV_NEV);


        EdzesNapActivity main=new EdzesNapActivity();
        title.setText(main.loadDay(button_id));
        leiras.setText(text);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("MenuActivity");
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EdzesNapActivity");
            }
        });
    }

    public void OpenActivity(String Activity) {
        if(Activity == "EdzesNapActivity"){
            intent = new Intent(this, EdzesNapActivity.class);
            DatabaseHelper myDb=new DatabaseHelper(EditNapActivity.this);
            if(text.isEmpty()) {
                if (myDb.insertData("edzes_nap",null,button_id,terv_nev, leiras.getText().toString()))
                    System.out.println("CREATED");
                }
                else {
                    if (myDb.updateData("edzes_nap",button_id,terv_nev,leiras.getText().toString()))
                        System.out.println("UPDATED");
                }
            intent.putExtra(BOOLEAN,true);
            intent.putExtra(BUTTON_ID,button_id);
            intent.putExtra(TERV_NEV,terv_nev);
            intent.putExtra(LEIRAS,leiras.getText().toString());
            startActivity(intent);
        }
        if(Activity=="MenuActivity"){
            intent=new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }
}
