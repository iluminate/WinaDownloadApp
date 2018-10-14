package pe.com.kestrada.winadownload.util;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Util {
    private static final String COVERTMP3IO = "https://www.convertmp3.io";
    private static final String ETIQUETA_PUNTO = ".";
    private static final String CSS_AHREF = "a[href]";
    private static final String ATTR_HREF = "href";
    private static final String FORMATO_MP3 = "mp3";
    private static final String URLAPI = COVERTMP3IO + "/widget/button/?video=";
    public static void descargarMusica(String url, final DownloadManager downloadManager) {
        final String getURL = URLAPI + url;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(getURL).get();
                    Elements links = doc.select(CSS_AHREF);
                    for (Element link : links) {
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(COVERTMP3IO + link.attr(ATTR_HREF)));
                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                        request.setAllowedOverRoaming(false);
                        request.setVisibleInDownloadsUi(true);
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, link.text() + ETIQUETA_PUNTO + FORMATO_MP3);
                        downloadManager.enqueue(request);
                        break;
                    }
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }).start();
    }
}
