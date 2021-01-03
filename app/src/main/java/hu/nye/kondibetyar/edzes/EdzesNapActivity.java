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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.database.DatabaseHelper;
import hu.nye.kondibetyar.edzes.edit.EditEdzesActivity;
import hu.nye.kondibetyar.edzes.edit.EditNapActivity;
import hu.nye.kondibetyar.edzes.edit.EditTervekActivity;

public class EdzesNapActivity extends AppCompatActivity {
    public static final String ID="hu.nye.kondibetyar.edzes.ID";
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
    public String title_id;
    public boolean add;



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
        add=intent.getBooleanExtra(EditNapActivity.BOOLEAN,false);
        if(!add) title_id = String.valueOf(intent.getIntExtra(EdzesActivity.NUMBER, 1));
        else title_id =intent.getStringExtra(EditNapActivity.TEXT);
        title.setText(loadTitle(title_id));
        leiras.setText(loadText(title_id));
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

    public String loadText(String id){
        myDb=new DatabaseHelper(EdzesNapActivity.this);
        res=myDb.getTextId(id);
        res.moveToNext();
        if(res.getCount()==0) return null;
        else return res.getString(0);
    }

    public void OpenActivity(String Activity) {
        if(Activity == "EditNapActivity"){
            intent = new Intent(this, EditNapActivity.class);
            intent.putExtra(ID,title_id);
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
