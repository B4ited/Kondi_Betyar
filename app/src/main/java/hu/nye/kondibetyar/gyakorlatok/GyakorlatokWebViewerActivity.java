package hu.nye.kondibetyar.gyakorlatok;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.R;

public class GyakorlatokWebViewerActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyakorlatok_webviewer);
        webView=findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://shopbuilder.hu/gyakorlatok-a2256");
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        getSupportActionBar().setTitle("Gyakorlatok");
    }

    @Override
    public void onBackPressed(){
        if(webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
}
