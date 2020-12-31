package hu.nye.kondibetyar.edzes;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class EdzesActivity extends AppCompatActivity {
    public static final String NUMBER="hu.nye.kondibetyar.edzes.NUMBER";
    private LinearLayout ll;
    private ImageButton edit;
    private ImageButton menu;
    public Intent intent;
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
        edit=this.findViewById(R.id.ib_add);
        title=this.findViewById(R.id.t_title);
        intent=getIntent();
        title_id= String.valueOf(intent.getIntExtra(EdzesTervekActivity.NUMBER,1));
       title.setText(loadTitle(title_id)+" heti terv");
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
       // loadButtonData();
        }


    public String loadTitle(String id){
        myDb=new DatabaseHelper(EdzesActivity.this);
        res=myDb.getTitleId("edzes_terv",id);
        res.moveToNext();
        return res.getString(0);
    }


    public void loadButtonData(){
        myDb=new DatabaseHelper(EdzesActivity.this);
        res=myDb.getMenuData("edzes_heti");
        if(res.getCount()==0) Toast.makeText(this,"Nincs még heti terved!",Toast.LENGTH_LONG);
        int id;
        String nev;
        while (res.moveToNext()){
            id=Integer.parseInt((res.getString(0)));
            nev=(res.getString(1));
            CreateButton(id,nev);
        }
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
            intent = new Intent(this, EdzesNapActivity.class);
            intent.putExtra(NUMBER,Button_id);
            System.out.println(Button_id);
            startActivity(intent);
        }
        if(Activity == "EditEdzesActivity"){
            intent = new Intent(this, EditEdzesActivity.class);
            intent.putExtra(NUMBER,Button_id);
            startActivity(intent);
        }
        if(Activity=="MenuActivity"){
            intent=new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }
}
