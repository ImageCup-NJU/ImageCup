package runjoy.running;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.Map;

import runjoy.R;
import runjoy.data.RunInfo;
import runjoy.expedition.ExpeditionActivity;
import runjoy.homepage.HomePageActivity;
import runjoy.startrunning.StartRunningContract;
import runjoy.tool.enums.RunModeEnum;


public class RunningFragment extends Fragment implements RunningContract.View {

    private RunningContract.Presenter mPresenter;

    private LinearLayout layout_running_frag_main;

    private LinearLayout layout_running_frag_mission;

    private LinearLayout layout_running_frag_end;

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
        super.onActivityCreated(savedInstanceState);

        layout_running_frag_main = (LinearLayout) getActivity().findViewById(R.id.layout_running_frag_main);

        layout_running_frag_mission = (LinearLayout) getActivity().findViewById(R.id.layout_running_frag_mission);

        layout_running_frag_end = (LinearLayout) getActivity().findViewById(R.id.layout_running_frag_end);

        layout_running_frag_end.setVisibility(View.GONE);

        Button btn_acceptMission = (Button) getActivity().findViewById(R.id.btn_acceptMission);

        Button btn_cancelMission = (Button) getActivity().findViewById(R.id.btn_cancelMission);

        Button btn_waitMission = (Button) getActivity().findViewById(R.id.btn_waitMission);

        ImageButton btn_stopRunning = (ImageButton) getActivity().findViewById(R.id.btn_stopRunning);

        Button btn_exitRunning = (Button) getActivity().findViewById(R.id.btn_exitRunning);

        Button btn_showDistance = (Button) getActivity().findViewById(R.id.btn_showDistance);


        btn_acceptMission.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                layout_running_frag_mission.setVisibility(View.GONE);
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
            }
        });

        btn_stopRunning.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                layout_running_frag_main.setVisibility(View.GONE);
                layout_running_frag_end.setVisibility(View.VISIBLE);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showMap(RunModeEnum mode, Map<Double, Double> passPoint) {

    }

    @Override
    public void showMessageDialog() {

    }

    @Override
    public void showAR(int arId) {

    }

    @Override
    public void continueRun() {

    }

    @Override
    public void showDistance(double distance) {

    }

    @Override
    public void showCompleteMission(int num) {

    }

    @Override
    public void showEndInfo(RunInfo runInfo) {

    }

    @Override
    public void setPresenter(RunningContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
