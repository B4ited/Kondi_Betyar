package hu.nye.kondibetyar.gyakorlatok;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import hu.nye.kondibetyar.R;

public class GyakorlatokKategoriaActivity extends AppCompatActivity {
    private TextView title;
    private TextView test;

    protected void kategoriaGeneralas(String kategoria, String[] kategoriak, int kezdet, int veg){
        LinearLayout linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for(int i = kezdet; i <= veg; i++){
            TextView tv = new TextView(this);
            tv.setText(kategoriak[i]);
            linearLayout.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyakorlatok_kategoria);

        title=this.findViewById(R.id.gyakorlatok_fotitle);
        Intent intent = getIntent();
        String kategoria = intent.getStringExtra("kategoria");
        title.setText(kategoria);

        test=this.findViewById(R.id.gyakorlatok_test);
        
        int n = 0;
        String sor = null;
        String[] kategoriak = null;
        AssetManager am = this.getAssets();
        BufferedReader br;
        try {
            InputStream is = am.open("gyakorlatCim.txt");
            br = new BufferedReader(new InputStreamReader(is));

            while ((sor = br.readLine()) !=null){
                n++;
            }
            //.reset() nem működik ???
            is = am.open("gyakorlatCim.txt");
            br = new BufferedReader(new InputStreamReader(is));

            kategoriak = new String[n];
            n = 0;
            while ((sor = br.readLine()) !=null){
                kategoriak[n] = sor;
                n++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        int kezdetIndex = 0, vegIndex = 0;

        switch (kategoria){
            case "mell":
                title.setText("Mellizom gyakorlatok");
                for (int i = 0; i < n;i++){
                    if(kategoriak[i].equals("mell"))
                        kezdetIndex = i+1;
                    if(kategoriak[i].equals("mellVeg"))
                        vegIndex = i-1;
                }
                break;
            case "hát":
                title.setText("Hátizom gyakorlatok");
                for (int i = 0; i < n;i++){
                    if(kategoriak[i].equals("hat"))
                        kezdetIndex = i+1;
                    if(kategoriak[i].equals("hatVeg"))
                        vegIndex = i-1;
                }
                break;
            case "váll":
                title.setText("Vállizom gyakorlatok");
                for (int i = 0; i < n;i++){
                    if(kategoriak[i].equals("vall"))
                        kezdetIndex = i+1;
                    if(kategoriak[i].equals("vallVeg"))
                        vegIndex = i-1;
                }
                break;
        }

        //generálás
    }
}
