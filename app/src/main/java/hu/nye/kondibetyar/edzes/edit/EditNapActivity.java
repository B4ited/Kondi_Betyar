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
import hu.nye.kondibetyar.edzes.EdzesNapActivity;

public class EditNapActivity extends AppCompatActivity {
    public static final String TEXT="hu.nye.kondibetyar.edzes.edit.TEXT";
    public static final String TEXT2="hu.nye.kondibetyar.edzes.edit.TEXT2";
    public static final String NUMBER="hu.nye.kondibetyar.edzes.edit.NUMBER";
    private LinearLayout ll;
    private ImageButton menu;
    private Intent intent;
    private TextView title;
    private EditText leiras;
    private Button send;
    private String nev;
    private String text;
    public int db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes_edit_nap);
        db=1;
        ll=this.findViewById(R.id.l_tema);
        menu = this.findViewById(R.id.ib_menu);
        title=this.findViewById(R.id.t_title);
        leiras=this.findViewById(R.id.et_textarea);
        send=this.findViewById(R.id.b_send);


        intent=getIntent();
        nev=intent.getStringExtra(EdzesNapActivity.TEXT);
        text=intent.getStringExtra(EdzesNapActivity.TEXT2);

        title.setText(nev);
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
            intent.putExtra(TEXT,title.getText().toString());
            intent.putExtra(TEXT2,leiras.getText().toString());
            intent.putExtra(NUMBER,db);
            startActivity(intent);
        }
        if(Activity=="MenuActivity"){
            intent=new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }
}
