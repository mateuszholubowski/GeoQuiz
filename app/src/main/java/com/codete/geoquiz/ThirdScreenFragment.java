package com.codete.geoquiz;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdScreenFragment extends Fragment {



    public ThirdScreenFragment() {
        // Required empty public constructor
    }

    @InjectView(R.id.downloaded_img)
    ImageView downloadedImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_third_screen, container, false);
        ButterKnife.inject(this, contentView);

        PhotoLoader loader = new PhotoLoader();
        loader.loadImage(getActivity(), downloadedImg);

        return contentView;
    }


}
