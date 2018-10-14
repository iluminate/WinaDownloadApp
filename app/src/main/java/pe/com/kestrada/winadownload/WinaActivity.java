package pe.com.kestrada.winadownload;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import pe.com.kestrada.winadownload.util.Util;

public class WinaActivity extends AppCompatActivity {
    private EditText mLink = null;
    private DownloadManager downloadManager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wina);

        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        mLink = findViewById(R.id.link);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int PERMISSION_REQUEST_CODE = 1;
            if (ContextCompat.checkSelfPermission(WinaActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(WinaActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(WinaActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_CODE);
                }
            }
        }
    }
    public void descargarMp3(View view) {
        String video = mLink.getText().toString();
        if (!video.isEmpty()) {
            Util.descargarMusica(video, downloadManager);
        }
    }
}
