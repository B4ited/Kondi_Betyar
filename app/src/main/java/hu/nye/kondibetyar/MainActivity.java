package hu.nye.kondibetyar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;

import hu.nye.kondibetyar.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    public DatabaseHelper myDb;
   private ImageButton menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);
        menu = this.findViewById(R.id.ib_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity();
            }
        });

    }


    public void OpenActivity(){
        Intent intent=new Intent(this, MenuActivity.class);
        startActivity(intent);

    }



}