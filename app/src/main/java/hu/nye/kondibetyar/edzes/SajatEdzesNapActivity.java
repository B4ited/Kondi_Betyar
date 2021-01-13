package hu.nye.kondibetyar.edzes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.database.DatabaseHelper;
import hu.nye.kondibetyar.edzes.edit.EditNapActivity;

public class SajatEdzesNapActivity extends AppCompatActivity {
    public static final String BUTTON_ID="hu.nye.kondibetyar.edzes.BUTTON_ID";
    public static final String LEIRAS="hu.nye.kondibetyar.edzes.LEIRAS";
    public static final String TERV_NEV="hu.nye.kondibetyar.edzes.TERV_NEV";
    private ImageButton edit;
    private LinearLayout ll;
    private Intent intent;
    private TextView title;
    private TextView leiras;
    private DatabaseHelper myDb;
    private Cursor res;
    private ImageButton menu;
    private Spinner spinner;
    public String button_id;
    public String terv_nev;
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
        getSupportActionBar().setTitle("Edzés");
        intent=getIntent();
        add=intent.getBooleanExtra(EditNapActivity.BOOLEAN,false);
        if(!add)
        {
            button_id = String.valueOf(intent.getIntExtra(SajatEdzesActivity.BUTTON_ID, 1));
            terv_nev=intent.getStringExtra(SajatEdzesActivity.TERV_NEV);

        }
        else {
            button_id = intent.getStringExtra(EditNapActivity.BUTTON_ID);
            terv_nev=intent.getStringExtra(EditNapActivity.TERV_NEV);
        }
        getSupportActionBar().setTitle(terv_nev);
        title.setText(loadDay(button_id));
        leiras.setText(loadText(button_id,terv_nev));
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


    public  String loadDay(String id){
        String day="";
        switch (id){
            case "1":
                day="Hétfő";
                break;
            case "2":
                day="Kedd";
                break;
            case "3":
                day="Szerda";
                break;
            case "4":
                day="Csütörtök";
                break;
            case "5":
                day="Péntek";
                break;
            case "6":
                day="Szombat";
                break;
            case "7":
                day="Vasárnap";
                break;
        }
        return day;
    }

    public String loadText(String id,String terv_nev){
        myDb=new DatabaseHelper(SajatEdzesNapActivity.this);
        res=myDb.getTextId(id,terv_nev);
        res.moveToNext();
        if(res.getCount()==0){
            toastMsg("Nincs még terved!");
            return null;
        }
        else return res.getString(0);
    }

    public void OpenActivity(String Activity) {
        if(Activity == "EditNapActivity"){
            intent = new Intent(this, EditNapActivity.class);
            intent.putExtra(BUTTON_ID,button_id);
            intent.putExtra(LEIRAS,leiras.getText().toString());
            intent.putExtra(TERV_NEV,terv_nev);
            startActivity(intent);
        }
        if(Activity=="MenuActivity"){
            intent=new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }

    public void toastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }

}
