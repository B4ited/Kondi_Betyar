package hu.nye.kondibetyar.edzes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.edzes.edit.EditTervekActivity;


public class EdzesTervekActivity extends AppCompatActivity {
    public static final String TEXT="hu.nye.kondibetyar.edzes.TEXT";
    private ImageButton edit;
    private LinearLayout ll;
    private ImageButton menu;
    public int Button_db;
    private String text;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzestervek);
        menu = this.findViewById(R.id.ib_menu);
        edit=this.findViewById(R.id.ib_add);
        Button_db=0;
        intent=getIntent();
        text=intent.getStringExtra(EditTervekActivity.TEXT);
        Button_db=intent.getIntExtra(EditTervekActivity.NUMBER,0);

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
        if(Button_db>0)CreateButton(text);
    }

    public void CreateButton(String text){
        EditTervekActivity main=new EditTervekActivity();
            ll = this.findViewById(R.id.l_tema);
            Button btn = new Button(this);
            btn.setText(text);
            btn.setId(Button_db);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OpenActivity("EdzesActivity");
                }
            });
            ll.addView(btn);
            Button_db++;
    }

    public void OpenActivity(String Activity) {
        if (Activity == "EdzesActivity") {
            Intent intent = new Intent(this, EdzesActivity.class);
            intent.putExtra(TEXT,text);
            startActivity(intent);
        }
        if(Activity == "EditTervekActivity"){
            Intent intent = new Intent(this, EditTervekActivity.class);
            startActivity(intent);
        }
        if(Activity=="MenuActivity"){
            intent=new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }


}