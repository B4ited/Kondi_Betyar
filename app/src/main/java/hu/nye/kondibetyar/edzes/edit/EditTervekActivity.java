package hu.nye.kondibetyar.edzes.edit;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
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
import hu.nye.kondibetyar.edzes.EdzesTervekActivity;
import hu.nye.kondibetyar.database.DatabaseHelper;

public class EditTervekActivity  extends AppCompatActivity {
    private Button add;
    private EditText text;
    private Intent intent;
    private ImageButton menu;
    private ImageButton edit;
    private Cursor res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes_edit_tervek);
        menu = this.findViewById(R.id.ib_menu);
        add = this.findViewById(R.id.b_send);
        text = this.findViewById(R.id.et_nev);
        edit=this.findViewById(R.id.ib_edit);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("MenuActivity","mind1");
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
                DatabaseHelper myDb=new DatabaseHelper(EditTervekActivity.this);
                if(myDb.insertData("edzes_terv",text.getText().toString(),null,null,null,null,null,null,null,null))
                    System.out.println("Sikerült!");
                else System.out.println("Nem sikerült!");
                OpenActivity("EdzesTervekActivity",text.getText().toString());
            }
        });

    }

    public void loadButtonData(){
       DatabaseHelper myDb=new DatabaseHelper(EditTervekActivity.this);
        res=myDb.getMenuData("edzes_terv",null);
        if(res.getCount()==0) Toast.makeText(this,"Nincs még edzés terved!",Toast.LENGTH_LONG).show();
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
                DatabaseHelper myDb=new DatabaseHelper(EditTervekActivity.this);
                if(myDb.deleteData("edzes_terv",String.valueOf(id)));
                if(myDb.deleteData("edzes_heti",String.valueOf(id)));
                if(myDb.deleteData("edzes_nap",String.valueOf(id)));
                toastMsg("Sikeres törlés!");
                startActivity(getIntent());

            }
        });
        ll.addView(tv);

    }

    private void OpenActivity(String Activity,String nev) {
        if (Activity == "EdzesTervekActivity" && !nev.isEmpty()) {
            intent = new Intent(this, EdzesTervekActivity.class);
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


