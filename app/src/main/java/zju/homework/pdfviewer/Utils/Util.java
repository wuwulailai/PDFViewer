package zju.homework.pdfviewer.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import zju.homework.pdfviewer.Activitiy.LoginActivity;
import zju.homework.pdfviewer.Activitiy.MainActivity;


/**
 * Created by stardust on 2016/11/13.
 */

public class Util {

    public static final int REQUEST_OPEN_DOCUMENT = 1;
    public static final int REQUEST_ASK_FOR_PERMISSION = 2;
    public static final int REQUEST_LOGIN = 3;

    public static boolean requestExternalStorageRwPermission(@NonNull Activity activity, int requestCode) {
        // On Android 6.0+ we ask for SD card access permission.
        // Since documents can be annotated we ask for write permission as well.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity,
                        new String[] { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE },
                        requestCode);
                return false;
            }
        }
        return true;
    }

    public static void showOpenFileDialog(@NonNull Activity activity){
        Intent intent = new Intent(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ?
                Intent.ACTION_OPEN_DOCUMENT : Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/*");

        activity.startActivityForResult(intent, REQUEST_OPEN_DOCUMENT);

    }

    public static String getStringFromInputStream(InputStream is) throws IOException{

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;

        while ( (line = br.readLine()) != null ){
            sb.append(line);
        }
        is.close();
        br.close();
        return sb.toString();
    }


    public static void userLogin(@NonNull Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);

        activity.startActivityForResult(intent, REQUEST_LOGIN);
    }

}


