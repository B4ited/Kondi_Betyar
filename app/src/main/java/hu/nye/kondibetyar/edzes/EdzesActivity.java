package hu.nye.kondibetyar.edzes;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.edzes.edit.EditEdzesActivity;
import hu.nye.kondibetyar.edzes.edit.EditNapActivity;
import hu.nye.kondibetyar.edzes.edit.EditTervekActivity;

public class EdzesActivity extends AppCompatActivity {
    public static final String TEXT="hu.nye.kondibetyar.edzes.TEXT";
    private LinearLayout ll;
    private ImageButton edit;
    private ImageButton menu;
    private Intent intent;
    private TextView title;
    private String nev;
    private String text;
    public int Button_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes);
        ll = this.findViewById(R.id.l_tema);
        menu = this.findViewById(R.id.ib_menu);
        edit=this.findViewById(R.id.ib_add);
        title=this.findViewById(R.id.t_title);

        intent=getIntent();
        nev=intent.getStringExtra(EdzesTervekActivity.TEXT);
        Button_db=intent.getIntExtra(EditEdzesActivity.NUMBER,0);
        title.setText(nev+" heti terv");
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("MenuActivity");
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { OpenActivity("EditEdzesActivity");
                }
            });
        if(Button_db>0)
        {
            nev=intent.getStringExtra(EditEdzesActivity.TEXT);
            text=intent.getStringExtra(EditEdzesActivity.TEXT2);
            title.setText(nev);
            CreateButton(text);
        }

        }

    private void CreateButton(String text){
        Button btn = new Button(this);
        btn.setText(text);
        btn.setId(Button_db);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EdzesNapActivity");
            }
        });
        ll.addView(btn);
        Button_db++;
    }

    private void OpenActivity(String Activity) {
        if (Activity == "EdzesNapActivity") {
            intent = new Intent(this, EdzesNapActivity.class);
            intent.putExtra(TEXT,text);
            startActivity(intent);
        }
        if(Activity == "EditEdzesActivity"){
            intent = new Intent(this, EditEdzesActivity.class);
            intent.putExtra(TEXT,title.getText().toString());
            startActivity(intent);
        }
        if(Activity=="MenuActivity"){
            intent=new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }
}
