package hu.nye.kondibetyar.gyakorlatok;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.database.DatabaseHelper;
import hu.nye.kondibetyar.gyakorlatok.edit.EditGyakorlatokEgyeniActivity;

public class EgyeniGyakorlatokActivity extends AppCompatActivity {
    public static final String BUTTON_ID="hu.nye.kondibetyar.edzes.BUTTON_ID";
    private ImageButton edit;
    private LinearLayout ll;
    private ImageButton menu;
    private Intent intent;
    private DatabaseHelper myDb;
    private Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egyenigyakorlatok);
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
                OpenActivity("EditGyakorlatokEgyeniActivity");
            }
        });
        getSupportActionBar().setTitle("Gyakorlatok");
        loadButtonData();
    }

    public void loadButtonData(){
        myDb=new DatabaseHelper(EgyeniGyakorlatokActivity.this);
        res=myDb.getMenuData("gyakorlatok",null);
        if(res.getCount()==0) Toast.makeText(this,"Nincs még egyéni gyakorlatod!",Toast.LENGTH_LONG).show();
        int id;
        String nev;
        while (res.moveToNext()){
            id=Integer.parseInt((res.getString(0)));
            nev=(res.getString(1));
            CreateTextView(id,nev);
        }
    }

    public void CreateTextView(int id, String text){
        LinearLayout ll = this.findViewById(R.id.l_tema);
        TextView tv=new TextView(this);
        tv.setText(text);
        tv.setId(id);
        tv.setTextSize(45);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        ll.addView(tv);
    }



    public void OpenActivity(String Activity) {

        if(Activity == "EditGyakorlatokEgyeniActivity"){
            intent = new Intent(this, EditGyakorlatokEgyeniActivity.class);
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




