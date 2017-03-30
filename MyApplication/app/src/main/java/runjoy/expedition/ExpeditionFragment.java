package runjoy.expedition;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import runjoy.R;
import runjoy.data.City;
import runjoy.data.Route;


public class ExpeditionFragment extends Fragment implements ExpeditionContract.View {

    private ExpeditionContract.Presenter mPresenter;

    private LinearLayout historyLayout;

    private ImageView backButton;

    private ImageButton historyButton;

    private ImageView img_expedition;

    private TextView tv_expeditionTop;

    private TextView tv_expeditionButtom;

    private TextView tv_expeditionStart;

    private TextView tv_expeditionEnd;

    private ImageView img_expeditionBar1;

    private ImageView img_expeditionBar2;

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

        historyLayout = (LinearLayout) getActivity().findViewById(R.id.layout_expedition_history);

        historyButton = (ImageButton) getActivity().findViewById(R.id.btn_expediontHistory);

        img_expedition = (ImageView) getActivity().findViewById(R.id.img_expedition);

        tv_expeditionTop = (TextView) getActivity().findViewById(R.id.tv_expeditionTop);

        tv_expeditionButtom = (TextView) getActivity().findViewById(R.id.tv_expeditionButtom);

        tv_expeditionStart = (TextView) getActivity().findViewById(R.id.tv_expeditionStart);

        tv_expeditionEnd = (TextView) getActivity().findViewById(R.id.tv_expeditionEnd);

        img_expeditionBar1 = (ImageView) getActivity().findViewById(R.id.img_expeditionBar1);

        img_expeditionBar2 = (ImageView) getActivity().findViewById(R.id.img_expeditionBar2);

        backButton.setVisibility(View.GONE);
        historyLayout.setVisibility(View.GONE);

        img_expedition.setImageResource(R.drawable.expedition0055);

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
        tv_expeditionStart.setText(route.getStart());
        tv_expeditionEnd.setText(route.getEnd());

        double allDistance = route.getAllDistance();
        double distance = route.getDistance();
        int time = route.getTime();
        double ar = route.getArDistance();

        int per =(int) ((distance / allDistance) * 100);

        System.out.println("!!!!!!!!" + per);
        String top =  "您已经跑了" + String.valueOf(time) + "天     剩余" + String.valueOf(allDistance - distance) + "公里" ;
        String buttom = "贡献额外 " + ar + " 公里";

        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, (float) (per/100.0));
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, (float) ((100-per)/100.0));

        if (per == 100) per = 99;
        img_expedition.setImageResource(R.drawable.expedition0001 + per);
        img_expeditionBar1.setLayoutParams(param1);
        img_expeditionBar2.setLayoutParams(param2);
        tv_expeditionTop.setText(top);
        tv_expeditionButtom.setText(buttom);
    }

    @Override
    public void showHistory(City city) {

    }

    @Override
    public void setPresenter(ExpeditionContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
