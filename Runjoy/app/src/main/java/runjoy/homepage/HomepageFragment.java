package runjoy.homepage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import runjoy.R;
import runjoy.data.Route;
import runjoy.data.RunInfo;


public class HomepageFragment extends Fragment implements HomePageContract.View {

    private HomePageContract.Presenter mPresenter;

    TextView tv_time;

    TextView tv_distance;

    TextView tv_missionNum;

    TextView tv_addDistance;

    public static HomepageFragment newInstance() {
        return new HomepageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.homepage_frag, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_time = (TextView) getActivity().findViewById(R.id.tv_time);
        tv_distance = (TextView) getActivity().findViewById(R.id.tv_distance);
        tv_missionNum = (TextView) getActivity().findViewById(R.id.tv_missionNum);
        tv_addDistance = (TextView) getActivity().findViewById(R.id.tv_addDistance);
    }

    private String toTwoByte(String str) {

        String temp = str;
        while (temp.length() < 2) temp = "0" + temp;
        return temp;
    }

    @Override
    public void showLastRun(RunInfo runInfo) {
        double time = runInfo.getTime();
        String timeStr = "";
        if (time < 3600) {
            timeStr = toTwoByte(String.valueOf((int)(time / 60))) + ":" + toTwoByte(String.valueOf((int)(time % 60)));
        } else {
            timeStr = toTwoByte(String.valueOf((int)(time / 3600))) + ":" + toTwoByte(String.valueOf((int)((time % 3600) / 60))) + ":" + toTwoByte(String.valueOf((int)((time % 3600) % 60)));
        }
        tv_time.setText(timeStr);
        tv_distance.setText(String.valueOf(runInfo.getDistance()));
        tv_missionNum.setText(String.valueOf(runInfo.getMissionNum()));
        tv_addDistance.setText(String.valueOf(runInfo.getAddDistance()) + "KM");
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showMyTrip(Route route) {

    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
