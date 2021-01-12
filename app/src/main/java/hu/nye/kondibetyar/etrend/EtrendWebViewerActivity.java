package hu.nye.kondibetyar.etrend;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.R;

public class EtrendWebViewerActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etrend_webviewer);
        webView=findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://shopbuilder.hu/etrendek-a3281");
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        getSupportActionBar().setTitle("Étrend");
    }

    @Override
    public void onBackPressed(){
        if(webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
}
