package hu.nye.kondibetyar.edzes.edit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.database.DatabaseHelper;
import hu.nye.kondibetyar.edzes.SajatEdzesNapActivity;

public class EditNapActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String BUTTON_ID="hu.nye.kondibetyar.edzes.edit.BUTTON_ID";
    public static final String LEIRAS="hu.nye.kondibetyar.edzes.edit.LEIRAS";
    public static final String BOOLEAN="hu.nye.kondibetyar.edzes.edit.BOOLEAN";
    public static final String TERV_NEV="hu.nye.kondibetyar.edzes.TERV_NEV";
    private LinearLayout ll;
    private ImageButton menu;
    private ImageButton hozzaad;
    private Intent intent;
    private TextView title;
    private EditText leiras;
    private Button send;
    private Button pihenő;
    private String text;
    private Spinner spinner;
    private String button_id;
    private  String terv_nev;
    public String gyakorlatok;
    private Cursor res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes_edit_nap);
        ll=this.findViewById(R.id.l_tema);
        menu = this.findViewById(R.id.ib_menu);
        title=this.findViewById(R.id.t_title);
        leiras=this.findViewById(R.id.et_textarea);
        hozzaad=this.findViewById(R.id.ib_hozzaad);
        send=this.findViewById(R.id.b_send);
        pihenő=this.findViewById(R.id.b_pihenőnap);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        loadSpinnerData();
        intent=getIntent();
        button_id=intent.getStringExtra(SajatEdzesNapActivity.BUTTON_ID);
        text=intent.getStringExtra(SajatEdzesNapActivity.LEIRAS);
        terv_nev=intent.getStringExtra(SajatEdzesNapActivity.TERV_NEV);
        getSupportActionBar().setTitle(terv_nev);


        SajatEdzesNapActivity main=new SajatEdzesNapActivity();
        title.setText(main.loadDay(button_id));
        leiras.setText(text);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("MenuActivity");
            }
        });
        pihenő.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leiras.setText("Pihenőnap");
                OpenActivity("EdzesNapActivity");
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EdzesNapActivity");
            }
        });

        hozzaad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String before_szoveg=leiras.getText().toString();
              leiras.setText(before_szoveg+"\n"+gyakorlatok);
            }
        });

    }

    private void loadSpinnerData() {
        DatabaseHelper myDb=new DatabaseHelper(EditNapActivity.this);
        ArrayList<String> list=myDb.getAllProvinces();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();
        gyakorlatok=label;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void OpenActivity(String Activity) {
        if(Activity == "EdzesNapActivity"){
            intent = new Intent(this, SajatEdzesNapActivity.class);
            DatabaseHelper myDb=new DatabaseHelper(EditNapActivity.this);
           res=myDb.getMenuData("edzes_nap",button_id);
            if(res.getCount()==0) {
                //toastMsg("Nincs még gyakorlataid");
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
    public void toastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }
}
