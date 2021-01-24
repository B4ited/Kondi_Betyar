package hu.nye.kondibetyar.gyakorlatok.edit;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.database.DatabaseHelper;
import hu.nye.kondibetyar.edzes.SajatEdzesTervekActivity;
import hu.nye.kondibetyar.edzes.edit.EditTervekActivity;
import hu.nye.kondibetyar.gyakorlatok.EgyeniGyakorlatokActivity;


public class EditGyakorlatokEgyeniActivity extends AppCompatActivity {
    private ImageButton add;
    private EditText text;
    private Intent intent;
    private ImageButton menu;
    private ImageButton edit;
    private Cursor res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyakorlatok_edit_egyeni);
        menu = this.findViewById(R.id.ib_menu);
        add = this.findViewById(R.id.b_send);
        text = this.findViewById(R.id.et_nev);
        edit=this.findViewById(R.id.ib_edit);
        getSupportActionBar().setTitle("Gyakorlatok");
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("MenuActivity");
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                loadButtonData();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDb=new DatabaseHelper(EditGyakorlatokEgyeniActivity.this);

                if(text.getText().toString().matches("")) toastMsg("Üres mező!");
                else {
                    if (myDb.insertData("gyakorlatok", text.getText().toString(),null, null, null))

                        System.out.println("Sikerült!");
                    else System.out.println("Nem sikerült!");
                    OpenActivity("EgyeniGyakorlatokActivity");
                }
            }
        });

    }

    public void loadButtonData(){
        DatabaseHelper myDb=new DatabaseHelper(EditGyakorlatokEgyeniActivity.this);
        res=myDb.getMenuData("gyakorlatok",null);
        if(res.getCount()==0) Toast.makeText(this,"Nincs még edzés gyakorlatod!",Toast.LENGTH_LONG).show();
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
        tv.setClickable(true);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=v.getId();
                DatabaseHelper myDb=new DatabaseHelper(EditGyakorlatokEgyeniActivity.this);
                if(myDb.deleteData("gyakorlatok",String.valueOf(id)));
                toastMsg("Sikeres törlés!");
                startActivity(getIntent());

            }
        });
        ll.addView(tv);

    }

    private void OpenActivity(String Activity) {


        if (Activity == "EgyeniGyakorlatokActivity") {
            intent = new Intent(this, EgyeniGyakorlatokActivity.class);
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