package com.example.paulnikonowicz.zocdocapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.paulnikonowicz.zocdocapplication.event.MovieDataResponse;
import com.example.paulnikonowicz.zocdocapplication.event.StatusEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressBarFragment extends Fragment {

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.progressbar_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        textView = (TextView) getView().findViewById(R.id.myTextProgress);
        textView.setText("Starting");
        getView().setVisibility(View.VISIBLE);

        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);

        textView = null;

        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadBasicListData(MovieDataResponse event){
        getView().setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateStatus(StatusEvent event) {
        textView.setText(event.message);
    }
}
