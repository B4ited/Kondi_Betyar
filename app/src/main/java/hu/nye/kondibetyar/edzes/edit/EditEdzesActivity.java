package hu.nye.kondibetyar.edzes.edit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.database.DatabaseHelper;
import hu.nye.kondibetyar.edzes.EdzesActivity;
import hu.nye.kondibetyar.edzes.EdzesNapActivity;
import hu.nye.kondibetyar.edzes.EdzesTervekActivity;

public class EditEdzesActivity extends AppCompatActivity {
    public static final String TEXT="hu.nye.kondibetyar.edzes.TEXT";
    public static final String BOOLEAN="hu.nye.kondibetyar.edzes.BOOLEAN";
    private LinearLayout ll;
    private ImageButton add;
    private ImageButton menu;
    private EditText nev;
    private TextView title;
    public String text;
    private Intent intent;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes_edit_edzes);
        ll = this.findViewById(R.id.l_layout);
        title=this.findViewById(R.id.t_title);
        menu = this.findViewById(R.id.ib_menu);
        add=this.findViewById(R.id.ib_add);
        nev=this.findViewById(R.id.et_nev);

        intent=getIntent();
        text=intent.getStringExtra(EdzesActivity.TEXT);
        id=intent.getStringExtra(EdzesActivity.TEXT2);
        title.setText(text);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("MenuActivity",null,null);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { OpenActivity("EdzesActivity",nev.getText().toString(),id);
            }
        });
    }

    private void OpenActivity(String Activity,String text,String id) {
        if(Activity=="MenuActivity"){
            intent=new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
        if(Activity=="EdzesActivity" && !text.isEmpty()) {
            DatabaseHelper myDb=new DatabaseHelper(EditEdzesActivity.this);
            if(myDb.insertData("edzes_heti",null,null,id,nev.getText().toString(),null,null,null,null,null))
            intent = new Intent(this, EdzesActivity.class);
            intent.putExtra(BOOLEAN,true);
            intent.putExtra(TEXT,id);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Üres!",Toast.LENGTH_LONG);
        }
    }
}
