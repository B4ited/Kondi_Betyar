package hu.nye.kondibetyar.edzes.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.edzes.EdzesTervekActivity;
import hu.nye.kondibetyar.database.DatabaseHelper;

public class EditTervekActivity  extends AppCompatActivity {
    private Button edit;
    private EditText add;
    private Intent intent;
    private ImageButton menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes_edit_tervek);
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
                DatabaseHelper myDb=new DatabaseHelper(EditTervekActivity.this);
                if(myDb.insertData("edzes_terv",add.getText().toString(),null,null,null,null,null,null,null,null))
                    System.out.println("Sikerült!");
                else System.out.println("Nem sikerült!");
                OpenActivity("EdzesTervekActivity",add.getText().toString());
            }
        });

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
}


