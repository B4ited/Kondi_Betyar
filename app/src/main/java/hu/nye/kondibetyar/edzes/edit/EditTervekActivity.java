package hu.nye.kondibetyar.edzes.edit;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.edzes.EdzesActivity;
import hu.nye.kondibetyar.edzes.EdzesTervekActivity;

public class EditTervekActivity  extends AppCompatActivity {
    public static final String TEXT="hu.nye.kondibetyar.edzes.edit.TEXT";
    public static final String NUMBER="hu.nye.kondibetyar.edzes.edit.NUMBER";
    private Button edit;
    private EditText add;
    public int button_db;
    private Intent intent;
    private ImageButton menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes_edit_tervek);
        /*EdzesTervekActivity main=new EdzesTervekActivity();
        button_db=main.Button_db;*/
        button_db=1;
        menu = this.findViewById(R.id.ib_menu);
        edit = this.findViewById(R.id.b_send);
        add = this.findViewById(R.id.et_nev);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("MenuActivity","mind1");
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EdzesTervekActivity",add.getText().toString());
            }
        });

    }



    private void OpenActivity(String Activity,String nev) {
        if (Activity == "EdzesTervekActivity" && !nev.isEmpty()) {
            intent = new Intent(this, EdzesTervekActivity.class);
            intent.putExtra(TEXT,nev);
            intent.putExtra(NUMBER,button_db);
            startActivity(intent);
            button_db++;
        }
        if(Activity=="MenuActivity"){
            intent=new Intent(this, MenuActivity.class);
            startActivity(intent);
        }

    }
}


