package runjoy.startrunning;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import runjoy.R;
import runjoy.data.Route;
import runjoy.data.RunInfo;
import runjoy.homepage.HomePageActivity;
import runjoy.homepage.HomePageContract;


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
        btn_cancelRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_cancelRun.setVisibility(View.GONE);
                Intent intent = new Intent(getActivity(), HomePageActivity.class);
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
    public void setPresenter(StartRunningContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
