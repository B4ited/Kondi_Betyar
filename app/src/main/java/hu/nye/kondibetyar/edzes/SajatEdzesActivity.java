package hu.nye.kondibetyar.edzes;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.database.DatabaseHelper;

public class SajatEdzesActivity extends AppCompatActivity {
    public static final String TERV_ID="hu.nye.kondibetyar.edzes.TERV_ID";
    public static final String TERV_NEV="hu.nye.kondibetyar.edzes.TERV_NEV";
    public static final String BUTTON_ID="hu.nye.kondibetyar.edzes.BUTTON_ID";
    private LinearLayout ll;
    private ImageButton menu;
    public Intent intent;
    private String terv_nev;
    public String title_id;
    private TextView title;
    private DatabaseHelper myDb;
    private Cursor res;
    private int Button_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes);
        ll = this.findViewById(R.id.l_tema);
        menu = this.findViewById(R.id.ib_menu);
        title=this.findViewById(R.id.t_title);
        intent=getIntent();
        title_id = String.valueOf(intent.getIntExtra(SajatEdzesTervekActivity.BUTTON_ID, 1));
        terv_nev=loadTitle(title_id);
        getSupportActionBar().setTitle(terv_nev);
        title.setText("Heti terv");
        getSupportActionBar().setTitle("Edzés");
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("MenuActivity");
            }
        });
        loadButtonData();
        }


    public String loadTitle(String id){
        myDb=new DatabaseHelper(SajatEdzesActivity.this);
        res=myDb.getTitleId("edzes_terv",id);
        res.moveToNext();
        return res.getString(0);
    }


    public void loadButtonData(){
        String[] nev={null,"Hétfő","Kedd","Szerda","Csütörtök","Péntek","Szombat","Vasárnap"};
        for (int i=1; i<=7; i++) CreateButton(i,nev[i]);
    }

    private void CreateButton(int id,String text){
        Button btn = new Button(this);
        btn.setText(text);
        btn.setId(id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button_id=v.getId();
                OpenActivity("EdzesNapActivity");
            }
        });
        ll.addView(btn);
    }

    private void OpenActivity(String Activity) {
        if (Activity == "EdzesNapActivity") {
            intent = new Intent(this, SajatEdzesNapActivity.class);
            intent.putExtra(TERV_ID,title_id);
            intent.putExtra(TERV_NEV,terv_nev);
            intent.putExtra(BUTTON_ID,Button_id);
            startActivity(intent);
        }
        if(Activity=="MenuActivity"){
            intent=new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }
}
