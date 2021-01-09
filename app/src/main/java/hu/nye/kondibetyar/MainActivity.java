package hu.nye.kondibetyar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import hu.nye.kondibetyar.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    public DatabaseHelper myDb;
    private ImageButton menu;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myDb=new DatabaseHelper(this);
        menu = this.findViewById(R.id.ib_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity();
            }
        });
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);
    }


    public void OpenActivity(){
        Intent intent=new Intent(this, MenuActivity.class);
        startActivity(intent);

    }



}