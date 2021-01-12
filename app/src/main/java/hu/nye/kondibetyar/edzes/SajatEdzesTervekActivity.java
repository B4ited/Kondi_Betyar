package hu.nye.kondibetyar.edzes;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.database.DatabaseHelper;
import hu.nye.kondibetyar.edzes.edit.EditTervekActivity;


public class SajatEdzesTervekActivity extends AppCompatActivity {
    public static final String BUTTON_ID="hu.nye.kondibetyar.edzes.BUTTON_ID";
    private ImageButton edit;
    private LinearLayout ll;
    private ImageButton menu;
    private Intent intent;
    private DatabaseHelper myDb;
    private Cursor res;
    public int Button_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzestervek);
        menu = this.findViewById(R.id.ib_menu);
        edit=this.findViewById(R.id.ib_add);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("MenuActivity");
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EditTervekActivity");
            }
        });
        getSupportActionBar().setTitle("Edzés");
        loadButtonData();
    }

    public void loadButtonData(){
        myDb=new DatabaseHelper(SajatEdzesTervekActivity.this);
        res=myDb.getMenuData("edzes_terv",null);
        if(res.getCount()==0) Toast.makeText(this,"Nincs még edzés terved!",Toast.LENGTH_LONG).show();
        int id;
        String nev;
        while (res.moveToNext()){
            id=Integer.parseInt((res.getString(0)));
            nev=(res.getString(1));
            CreateButton(id,nev);
        }
    }

    public void CreateButton(int id, String text){
        EditTervekActivity main=new EditTervekActivity();
            ll = this.findViewById(R.id.l_tema);
            Button btn = new Button(this);
            btn.setText(text);
            btn.setId(id);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button_id=v.getId();
                    OpenActivity("EdzesActivity");
                }
            });
            ll.addView(btn);

    }

    public void OpenActivity(String Activity) {
        if (Activity == "EdzesActivity") {
            intent = new Intent(this, SajatEdzesActivity.class);
            intent.putExtra(BUTTON_ID, Button_id);
            startActivity(intent);
        }
        if(Activity == "EditTervekActivity"){
            intent = new Intent(this, EditTervekActivity.class);
            startActivity(intent);
        }
        if(Activity=="MenuActivity"){
            intent=new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }


}