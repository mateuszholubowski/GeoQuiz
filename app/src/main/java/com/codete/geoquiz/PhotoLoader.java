package com.codete.geoquiz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.IOException;

/**
 * Created by Mateusz on 2015-08-11.
 */
public class PhotoLoader {

    private static final String PHOTO_URL = "http://www.cats.org.uk/uploads/images/pages/photo_latest14.jpg";
    private static final String TAG = "PhotoLoader";

    public void loadImage(final Context context, final ImageView imageView) {

        final String PHOTO_SD_PATH = context.getCacheDir() + "/photo";

        final Target imageTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.e(TAG, "onBitmapLoaded");
                imageView.setImageBitmap(bitmap);
                try {
                    Utilities.saveBitmapOnSd(bitmap, new File(PHOTO_SD_PATH));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.e(TAG, "onBitmapFailed");
                imageView.setImageDrawable(errorDrawable);
                if (context != null)
                    Toast.makeText(context, "Cannot load image", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.e(TAG, "onPrepareLoad");
                imageView.setImageDrawable(placeHolderDrawable);
            }
        };

        imageView.setTag(imageTarget);

        Uri uri = Uri.fromFile(new File(PHOTO_SD_PATH));
        Picasso.with(context).load(uri).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "onSuccess");
            }

            @Override
            public void onError() {
                Log.e(TAG, "onError");
                if (context != null)
                    Picasso.with(context).load(PHOTO_URL).placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder).into
                            (imageTarget);
            }
        });

    }

}
