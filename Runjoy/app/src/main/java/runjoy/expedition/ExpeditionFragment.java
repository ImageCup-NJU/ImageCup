package runjoy.expedition;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import runjoy.R;
import runjoy.data.City;
import runjoy.data.Route;
import runjoy.homepage.HomePageActivity;
import runjoy.startrunning.StartRunningContract;
import runjoy.tool.enums.RunModeEnum;


public class ExpeditionFragment extends Fragment implements ExpeditionContract.View {

    private ExpeditionContract.Presenter mPresenter;

    private LinearLayout historyLayout;

    private ImageView backButton;

    private ImageButton historyButton;

    public static ExpeditionFragment newInstance() {
        return new ExpeditionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.expedition_frag, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        backButton = (ImageView) getActivity().findViewById(R.id.btn_expediontBack);
        backButton.setVisibility(View.GONE);

        historyLayout = (LinearLayout) getActivity().findViewById(R.id.layout_expedition_history);
        historyLayout.setVisibility(View.GONE);

        historyButton = (ImageButton) getActivity().findViewById(R.id.btn_expediontHistory);

        historyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                historyLayout.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.VISIBLE);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                historyLayout.setVisibility(View.GONE);
                backButton.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showMap(Route route) {

    }

    @Override
    public void showHistory(City city) {

    }

    @Override
    public void setPresenter(ExpeditionContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
