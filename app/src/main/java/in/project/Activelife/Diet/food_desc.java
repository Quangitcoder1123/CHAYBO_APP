package in.project.Activelife.Diet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import in.project.Activelife.R;

public class food_desc extends AppCompatActivity
{
    WebView webView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_link);

        webView=(WebView)findViewById(R.id.webview3);


        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Food Portal");
        pd.setMessage("Loading...!");

        webView.setWebViewClient(new WebViewClient()
                                 {
                                     @Override
                                     public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                         super.onPageStarted(view, url, favicon);
                                         pd.show();
                                     }

                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         super.onPageFinished(view, url);
                                         pd.dismiss();
                                     }
                                 }
        );

        String linkdata=getIntent().getStringExtra("linkvalue");
        webView.loadUrl(linkdata);


    }
}