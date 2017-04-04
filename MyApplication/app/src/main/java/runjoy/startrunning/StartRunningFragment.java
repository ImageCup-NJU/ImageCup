package runjoy.startrunning;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import runjoy.R;
import runjoy.diyroute.DIYActivity;
import runjoy.running.RunningActivity;
import runjoy.tool.enums.RunModeEnum;


public class StartRunningFragment extends Fragment implements StartRunningContract.View {

    private StartRunningContract.Presenter mPresenter;

    public static StartRunningFragment newInstance() {
        return new StartRunningFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.startrunning_frag, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button btn_cancelRun = (Button) getActivity().findViewById(R.id.btn_cancelRun);
        final ImageButton btn_DIYRun = (ImageButton) getActivity().findViewById(R.id.btn_DIYRun);
        final ImageButton btn_freeRun = (ImageButton) getActivity().findViewById(R.id.btn_freeRun);
        btn_cancelRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        btn_DIYRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.selectMode(RunModeEnum.DIYRun);
                getActivity().finish();
                Intent intent = new Intent(getActivity(), DIYActivity.class);
                startActivity(intent);
            }
        });

        btn_freeRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.selectMode(RunModeEnum.FreeRun);
                getActivity().finish();
                Intent intent = new Intent(getActivity(), RunningActivity.class);
                intent.putExtra("type", 0);
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
    public void setPresenter(StartRunningContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
