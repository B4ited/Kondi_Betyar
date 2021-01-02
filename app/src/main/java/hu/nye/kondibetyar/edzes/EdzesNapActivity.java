package hu.nye.kondibetyar.edzes;

import android.content.Intent;
import android.database.Cursor;
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
import hu.nye.kondibetyar.edzes.edit.EditEdzesActivity;
import hu.nye.kondibetyar.edzes.edit.EditNapActivity;
import hu.nye.kondibetyar.edzes.edit.EditTervekActivity;

public class EdzesNapActivity extends AppCompatActivity {
    public static final String TEXT="hu.nye.kondibetyar.edzes.TEXT";
    public static final String TEXT2="hu.nye.kondibetyar.edzes.TEXT2";
    private ImageButton edit;
    private LinearLayout ll;
    private Intent intent;
    private TextView title;
    private TextView leiras;
    private DatabaseHelper myDb;
    private Cursor res;
    private ImageButton menu;
    private String nev;
    private String text;
    public String title_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzesnap);
        ll=this.findViewById(R.id.l_tema);
        menu = this.findViewById(R.id.ib_menu);
        title=this.findViewById(R.id.t_title);
        leiras=this.findViewById(R.id.leiras);
        edit=this.findViewById(R.id.ib_edit);
        intent=getIntent();
        title_id=String.valueOf(intent.getIntExtra(EdzesTervekActivity.NUMBER, 1));
        title.setText(loadTitle(title_id));

        /*
            nev=intent.getStringExtra(EditNapActivity.TEXT);
            text=intent.getStringExtra(EditNapActivity.TEXT2);
            title.setText(nev);
            leiras.setText(text);
        */
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("MenuActivity");
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EditNapActivity");
            }
        });
    }

    public String loadTitle(String id){
        myDb=new DatabaseHelper(EdzesNapActivity.this);
        res=myDb.getTitleId("edzes_heti",id);
        res.moveToNext();
        return res.getString(0);
    }

    public void OpenActivity(String Activity) {
        if(Activity == "EditNapActivity"){
            intent = new Intent(this, EditNapActivity.class);
            intent.putExtra(TEXT,title.getText().toString());
            intent.putExtra(TEXT2,leiras.getText().toString());
            startActivity(intent);
        }
        if(Activity=="MenuActivity"){
            intent=new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }

}
