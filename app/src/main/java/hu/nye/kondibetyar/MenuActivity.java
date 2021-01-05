package hu.nye.kondibetyar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import hu.nye.kondibetyar.edzes.EdzesTervekActivity;
import hu.nye.kondibetyar.etrend.EtrendActivity;
import hu.nye.kondibetyar.gyakorlatok.GyakorlatokActivity;
import hu.nye.kondibetyar.kapcsolat.KapcsolatActivity;

public class MenuActivity extends AppCompatActivity {
    private ImageButton edzes;
    private ImageButton gyakorlat;
    private ImageButton etkezes;
    private ImageButton kapcsolat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        edzes = this.findViewById(R.id.b_edzes);
        gyakorlat= this.findViewById(R.id.b_gyakorlat);
        etkezes = this.findViewById(R.id.b_etrend);
        kapcsolat = this.findViewById(R.id.b_kapcsolat);

        edzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EdzesTervekActivity");
            }
        });
        gyakorlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("Gyakorlatok");
            }
        });
        etkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("Etrend");
            }
        });
        kapcsolat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("Kapcsolat");
            }
        });
    }
    public void OpenActivity(String Activity){
        if(Activity=="EdzesTervekActivity"){
        Intent intent=new Intent(this, EdzesTervekActivity.class);
        startActivity(intent);
        }
        else if(Activity=="Gyakorlatok"){
            Intent intent=new Intent(this, GyakorlatokActivity.class);
            startActivity(intent);
        }
        else if(Activity=="Etrend"){
            Intent intent=new Intent(this, EtrendActivity.class);
            startActivity(intent);
        }
        else if(Activity=="Kapcsolat"){
            Intent intent=new Intent(this, KapcsolatActivity.class);
            startActivity(intent);
        }
        else System.out.println("Hibás Átvitel!");
    }
}