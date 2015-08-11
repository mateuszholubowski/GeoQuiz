package com.codete.geoquiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mateuszholubowski on 10/08/15.
 */
public class Utilities {


    public static Dialog createAlertDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setNegativeButton(context.getString(R.string.ok), null);
        return builder.show();
    }

    public static Dialog createAlertDialog(Context context, String message) {
        return createAlertDialog(context, context.getString(R.string.error), message);
    }


    public static ProgressDialog createProgressDialog(Context context, String title) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setMessage(title);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }


    public static void saveBitmapOnSd(Bitmap bitmap, File outputFile) throws IOException {
        if(!outputFile.exists())
            outputFile.createNewFile();

            FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outputFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);

            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Log.e("", outputFile.getAbsolutePath());
    }

}
