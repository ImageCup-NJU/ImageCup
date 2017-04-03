package runjoy.achievement;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import runjoy.R;
import runjoy.data.Account;


public class AchievementFragment extends Fragment implements AchievementContract.View {

    private AchievementContract.Presenter mPresenter;

    private TextView tv_achieve_top_1;

    private TextView tv_achieve_top_2;

    private TextView tv_achieve_top_3;

    private TextView tv_achieve_top_4;

    private TextView tv_achieve_top_5;

    private TextView tv_achieve_mid_1;

    private TextView tv_achieve_mid_2;

    private TextView tv_achieve_mid_3;

    private TextView tv_achieve_mid_4;

    private TextView tv_achieve_mid_5;

    private TextView tv_achieve_bottom_1;

    private TextView tv_achieve_bottom_2;

    private TextView tv_achieve_bottom_3;

    private TextView tv_achieve_bottom_4;

    private TextView tv_achieve_bottom_5;


    public static AchievementFragment newInstance() {
        return new AchievementFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.achievement_frag, container, false);

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_achieve_top_1 = (TextView) getActivity().findViewById(R.id.tv_achieve_top_1);
        tv_achieve_top_2 = (TextView) getActivity().findViewById(R.id.tv_achieve_top_2);
        tv_achieve_top_3 = (TextView) getActivity().findViewById(R.id.tv_achieve_top_3);
        tv_achieve_top_4 = (TextView) getActivity().findViewById(R.id.tv_achieve_top_4);
        tv_achieve_top_5 = (TextView) getActivity().findViewById(R.id.tv_achieve_top_5);
        tv_achieve_mid_1 = (TextView) getActivity().findViewById(R.id.tv_achieve_mid_1);
        tv_achieve_mid_2 = (TextView) getActivity().findViewById(R.id.tv_achieve_mid_2);
        tv_achieve_mid_3 = (TextView) getActivity().findViewById(R.id.tv_achieve_mid_3);
        tv_achieve_mid_4 = (TextView) getActivity().findViewById(R.id.tv_achieve_mid_4);
        tv_achieve_mid_5 = (TextView) getActivity().findViewById(R.id.tv_achieve_mid_5);
        tv_achieve_bottom_1= (TextView) getActivity().findViewById(R.id.tv_achieve_bottom_1);
        tv_achieve_bottom_2= (TextView) getActivity().findViewById(R.id.tv_achieve_bottom_2);
        tv_achieve_bottom_3= (TextView) getActivity().findViewById(R.id.tv_achieve_bottom_3);
        tv_achieve_bottom_4= (TextView) getActivity().findViewById(R.id.tv_achieve_bottom_4);
        tv_achieve_bottom_5= (TextView) getActivity().findViewById(R.id.tv_achieve_bottom_5);

        tv_achieve_top_1.setText("目标：完成一次跑步");
        tv_achieve_mid_1.setText("完成后您将解锁第一条路线");
        tv_achieve_bottom_1.setText("南京 - 上海");

        tv_achieve_top_2.setText("目标：完成一次AR任务");
        tv_achieve_mid_2.setText("完成后您将解锁新的路线");
        tv_achieve_bottom_2.setText("上海 - 厦门");

        tv_achieve_top_3.setText("目标：在三个不同的地方解救小猫");
        tv_achieve_mid_3.setText("完成后您将在现有任务中");
        tv_achieve_bottom_3.setText("奖励 5 公里里程");

        tv_achieve_top_4.setText("目标：正确收集不同分类的垃圾");
        tv_achieve_mid_4.setText("完成后您将解锁");
        tv_achieve_bottom_4.setText("新的 AR 互动任务");

        tv_achieve_top_5.setText("目标：连续10天跑步超过2公里");
        tv_achieve_mid_5.setText("完成后您将解锁新的路线");
        tv_achieve_bottom_5.setText("上海 - 西安");

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(AchievementContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
