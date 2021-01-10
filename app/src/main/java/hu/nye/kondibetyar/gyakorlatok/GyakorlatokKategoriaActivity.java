package hu.nye.kondibetyar.gyakorlatok;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import hu.nye.kondibetyar.R;

public class GyakorlatokKategoriaActivity extends AppCompatActivity {
    private TextView title;
    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyakorlatok_kategoria);

        title=this.findViewById(R.id.gyakorlatok_fotitle);
        Intent intent = getIntent();
        String kategoria = intent.getStringExtra("kategoria");
        title.setText(kategoria);

        test=this.findViewById(R.id.gyakorlatok_test);
        RandomAccessFile raf;
        
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
            case "Mellizom Gyakorlatok":
                for (int i = 0; i < n;i++){
                    if(kategoriak[i].equals("mell"))
                        kezdetIndex = i;
                    if(kategoriak[i].equals("mellVeg"))
                        vegIndex = i;
                }
                break;
            case "Hátizom Gyakorlatok":
                for (int i = 0; i < n;i++){
                    if(kategoriak[i].equals("hat"))
                        kezdetIndex = i;
                    if(kategoriak[i].equals("hatVeg"))
                        vegIndex = i;
                }
                break;
        }

        //generálás
    }
}
