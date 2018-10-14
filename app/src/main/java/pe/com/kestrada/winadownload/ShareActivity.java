package pe.com.kestrada.winadownload;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pe.com.kestrada.winadownload.util.Util;

public class ShareActivity extends AppCompatActivity {

    private DownloadManager downloadManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_share);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int PERMISSION_REQUEST_CODE = 1;
            if (ContextCompat.checkSelfPermission(ShareActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(ShareActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(ShareActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_CODE);
                }
            }
        }

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                String link = intent.getStringExtra(Intent.EXTRA_TEXT);
                if (link!= null) {
                    String getURL = "https://www.convertmp3.io/widget/button/?video=" + link;
                    Util.descargarMusica(getURL, downloadManager);
                }
            }
        }
        finish();
    }
}
