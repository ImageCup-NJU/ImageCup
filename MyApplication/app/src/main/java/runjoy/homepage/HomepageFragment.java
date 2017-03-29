package runjoy.homepage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import runjoy.R;
import runjoy.data.Account;
import runjoy.data.Route;
import runjoy.data.RunInfo;
import runjoy.data.Weather;


public class HomepageFragment extends Fragment implements HomePageContract.View {

    private HomePageContract.Presenter mPresenter;

    TextView tv_time;

    TextView tv_distance;

    TextView tv_missionNum;

    TextView tv_addDistance;

    TextView tv_userName;

    TextView tv_useDays;

    TextView tv_weather;

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

        tv_userName = (TextView) getActivity().findViewById(R.id.tv_userName);

        tv_useDays = (TextView) getActivity().findViewById(R.id.tv_useDays);

        tv_weather = (TextView) getActivity().findViewById(R.id.tv_weather);
    }

    private String toTwoByte(String str) {

        String temp = str;
        while (temp.length() < 2) temp = "0" + temp;
        return temp;
    }

    private String changeTime(double time) {
        String timeStr = "";
        if (time < 3600) {
            timeStr = toTwoByte(String.valueOf((int)(time / 60))) + ":" + toTwoByte(String.valueOf((int)(time % 60)));
        } else {
            timeStr = toTwoByte(String.valueOf((int)(time / 3600))) + ":" + toTwoByte(String.valueOf((int)((time % 3600) / 60))) + ":" + toTwoByte(String.valueOf((int)((time % 3600) % 60)));
        }
        return timeStr;
    }

    @Override
    public void showLastRun(RunInfo runInfo) {
        String timeStr = changeTime(runInfo.getTime());

        tv_time.setText(timeStr);
        tv_distance.setText(String.valueOf(runInfo.getDistance()));
        tv_missionNum.setText(String.valueOf(runInfo.getMissionNum()));
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
    public void showWeather(Weather weather) {

        String weather_str = weather.getStatus() + "       气温： " + weather.getTemperature() + "℃       PM 2.5： " + weather.getPm();

        tv_weather.setText(weather_str);
    }

    @Override
    public void showUserInfo(Account account) {

        tv_addDistance.setText(String.valueOf(account.getArContribute()) + "KM");

        tv_useDays.setText("已坚持运动" + String.valueOf(account.getUseDays()) + "天！");

        tv_userName.setText(account.getName());
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
