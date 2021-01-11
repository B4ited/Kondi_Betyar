package hu.nye.kondibetyar.edzes;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.R;

public class EdzesWebviewerActivty extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes_webviewer);
        webView=findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://shopbuilder.hu/edzestervek-a2257");
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        getSupportActionBar().setTitle("Edzés");
    }
    @Override
    public void onBackPressed(){
        if(webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
}
