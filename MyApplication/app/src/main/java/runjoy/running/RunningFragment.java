package runjoy.running;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import runjoy.R;
import runjoy.data.Route;
import runjoy.data.RunInfo;
import runjoy.expedition.ExpeditionActivity;
import runjoy.homepage.HomePageActivity;
import runjoy.settings.SettingsActivity;
import runjoy.tool.enums.RunModeEnum;


public class RunningFragment extends Fragment implements RunningContract.View {

    private boolean flag;

    private RunningContract.Presenter mPresenter;

    private LinearLayout layout_running_frag_main;

    private LinearLayout layout_running_frag_mission;

    private LinearLayout layout_running_frag_end;

    private LinearLayout layout_running_frag_free_setting;

    private TextView tv_time;

    private TextView tv_distance;

    private TextView tv_missionNum;

    private TextView tv_end_time;

    private TextView tv_end_distance;

    private TextView tv_end_missionNum;

    private TextView tv_missionInfo;

    private TextView tv_runningTitle;

    private TextView tv_runningEndTitle;

    private TextView tv_expeditionTop;

    private TextView tv_expeditionButtom;

    private TextView tv_expeditionStart;

    private TextView tv_expeditionEnd;

    private ImageView img_expeditionBar1;

    private ImageView img_expeditionBar2;

    private long mainCalcTime;

    private Timer tmr;

    public static RunningFragment newInstance() {
        return new RunningFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.running_frag, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        mainCalcTime = 0;

        super.onActivityCreated(savedInstanceState);

        layout_running_frag_main = (LinearLayout) getActivity().findViewById(R.id.layout_running_frag_main);

        layout_running_frag_mission = (LinearLayout) getActivity().findViewById(R.id.layout_running_frag_mission);

        layout_running_frag_end = (LinearLayout) getActivity().findViewById(R.id.layout_running_frag_end);

        layout_running_frag_free_setting = (LinearLayout) getActivity().findViewById(R.id.layout_running_frag_free_setting);

        layout_running_frag_mission.setVisibility(View.GONE);

        layout_running_frag_end.setVisibility(View.GONE);

        Button btn_acceptMission = (Button) getActivity().findViewById(R.id.btn_acceptMission);

        Button btn_cancelMission = (Button) getActivity().findViewById(R.id.btn_cancelMission);

        Button btn_waitMission = (Button) getActivity().findViewById(R.id.btn_waitMission);

        ImageButton btn_stopRunning = (ImageButton) getActivity().findViewById(R.id.btn_stopRunning);

        Button btn_exitRunning = (Button) getActivity().findViewById(R.id.btn_exitRunning);

        Button btn_showDistance = (Button) getActivity().findViewById(R.id.btn_showDistance);

        Button btn_accpetFreeRunningSetting = (Button) getActivity().findViewById(R.id.btn_accpetFreeRunningSetting);

        tv_time = (TextView) getActivity().findViewById(R.id.tv_time);

        tv_distance = (TextView) getActivity().findViewById(R.id.tv_distance);

        tv_missionNum = (TextView) getActivity().findViewById(R.id.tv_missionNum);

        tv_end_time = (TextView) getActivity().findViewById(R.id.tv_end_time);

        tv_end_distance = (TextView) getActivity().findViewById(R.id.tv_end_distance);

        tv_end_missionNum = (TextView) getActivity().findViewById(R.id.tv_end_missionNum);

        tv_missionInfo = (TextView) getActivity().findViewById(R.id.tv_missionInfo);

        tv_runningTitle = (TextView) getActivity().findViewById(R.id.tv_runningTitle);

        tv_runningEndTitle = (TextView) getActivity().findViewById(R.id.tv_runningEndTitle);

        tv_expeditionTop = (TextView) getActivity().findViewById(R.id.tv_expeditionTop);

        tv_expeditionButtom = (TextView) getActivity().findViewById(R.id.tv_expeditionButtom);

        tv_expeditionStart = (TextView) getActivity().findViewById(R.id.tv_expeditionStart);

        tv_expeditionEnd = (TextView) getActivity().findViewById(R.id.tv_expeditionEnd);

        img_expeditionBar1 = (ImageView) getActivity().findViewById(R.id.img_expeditionBar1);

        img_expeditionBar2 = (ImageView) getActivity().findViewById(R.id.img_expeditionBar2);

        tv_distance.setText("0.0");

        tv_time.setText("00:00");

        tv_missionNum.setText("0");

        btn_acceptMission.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               /*
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                */
                layout_running_frag_mission.setVisibility(View.GONE);
                stopTime();
                mPresenter.newMission();

            }
        });

        btn_cancelMission.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                layout_running_frag_mission.setVisibility(View.GONE);
            }
        });

        btn_waitMission.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                layout_running_frag_mission.setVisibility(View.GONE);
                mPresenter.delayMessage();
            }
        });

        btn_stopRunning.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stopTime();
                layout_running_frag_main.setVisibility(View.GONE);
                layout_running_frag_end.setVisibility(View.VISIBLE);
                mPresenter.endRun(mainCalcTime);
            }
        });

        btn_exitRunning.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent intent = new Intent(getActivity(), HomePageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btn_showDistance.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent intent = new Intent(getActivity(), ExpeditionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btn_accpetFreeRunningSetting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                layout_running_frag_free_setting.setVisibility(View.GONE);
                mPresenter.startRun();
                mPresenter.startMonitor();
                startTime();
            }
        });

        flag = false;
    }

    private void startTime() {
        if (!flag) {
            flag = true;
            tmr = new Timer();
            tmr.schedule(new timerTask(mainCalcTime), new Date(), 1000);
        }
    }

    private void stopTime() {
        if (flag) {
            tmr.cancel();
            flag = false;
        }
    }

    private class timerTask extends TimerTask {
        long calcTime;

        timerTask(long nowTime) {
            calcTime = nowTime;
        }

        @Override
        public void run() {
            calcTime++;
            mainCalcTime = calcTime;
            Message msg = new Message();
            msg.what = 1;
            mHandler.sendMessage(msg);
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            tv_time.setText(changeTime(mainCalcTime));
        };
    };

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showMap(RunModeEnum mode, Map<Double, Double> passPoint) {
        if (mode == RunModeEnum.DIYRun) {
            tv_runningTitle.setText("自定义跑进行中");
            tv_runningEndTitle.setText("自定义跑结束");
        }
    }

    @Override
    public void showMessageDialog(String missionInfo) {
        tv_missionInfo.setText(missionInfo);
        layout_running_frag_mission.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAR(int arId) {
        stopTime();
    }

    @Override
    public void continueRun() {
        startTime();
    }

    @Override
    public void showDistance(double distance) {
        tv_distance.setText(String.valueOf(distance));
    }

    @Override
    public void showCompleteMission(int num) {
        tv_missionNum.setText(String.valueOf(num));
    }



    @Override
    public void showEndInfo(RunInfo runInfo, Route route) {
        tv_end_time.setText(changeTime(runInfo.getTime()));
        tv_end_distance.setText(String.valueOf(runInfo.getDistance()));
        tv_end_missionNum.setText(String.valueOf(runInfo.getMissionNum()));



        tv_expeditionStart.setText(route.getStart());
        tv_expeditionEnd.setText(route.getEnd());

        double allDistance = route.getAllDistance();
        double distance = route.getDistance();
        int time = route.getTime();
        double ar = route.getArDistance();

        int per =(int) ((distance / allDistance) * 100);

        System.out.println("!!!!!!!!" + per);
        String top =  "您的旅程还剩余" + String.valueOf(allDistance - distance) + "公里" ;
        String buttom = "贡献额外 " + ar + " 公里";

        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, (float) (per/100.0));
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, (float) ((100-per)/100.0));

        if (per == 100) per = 99;
        img_expeditionBar1.setLayoutParams(param1);
        img_expeditionBar2.setLayoutParams(param2);
        tv_expeditionTop.setText(top);
        tv_expeditionButtom.setText(buttom);
    }

    @Override
    public void setPresenter(RunningContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private String toTwoByte(String str) {

        String temp = str;
        while (temp.length() < 2) temp = "0" + temp;
        return temp;
    }

    private String changeTime(long time) {
        String timeStr = "";
        if (time < 3600) {
            timeStr = toTwoByte(String.valueOf((int)(time / 60))) + ":" + toTwoByte(String.valueOf((int)(time % 60)));
        } else {
            timeStr = toTwoByte(String.valueOf((int)(time / 3600))) + ":" + toTwoByte(String.valueOf((int)((time % 3600) / 60))) + ":" + toTwoByte(String.valueOf((int)((time % 3600) % 60)));
        }
        return timeStr;
    }
}
