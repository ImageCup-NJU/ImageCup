package runjoy.settings;


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


public class SettingsFragment extends Fragment implements SettingsContract.View {

    private Account account;

    private SettingsContract.Presenter mPresenter;

    private LinearLayout handle_settingsDistance;

    private TextView tv_userName;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.settings_frag, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handle_settingsDistance = (LinearLayout) getActivity().findViewById(R.id.handle_settingsDistance);

        tv_userName = (TextView) getActivity().findViewById(R.id.tv_userName);

        handle_settingsDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText edtTxt_missionFrequent = new EditText(getActivity());
                edtTxt_missionFrequent.setText(String.valueOf(account.getMissionFrequent()));

                AlertDialog.Builder inputDialog = new AlertDialog.Builder(getActivity());

                inputDialog.setTitle("修改任务出现频率").setView(edtTxt_missionFrequent);

                inputDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String str_missionFrequent = edtTxt_missionFrequent.getText().toString();

                                double db_missionFrequent = Double.valueOf(str_missionFrequent);

                                mPresenter.saveUserInfo(account.getName(), "", db_missionFrequent);

                                Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
                            }
                }).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showResultIndicator() {

    }

    @Override
    public void showUserInfo(Account account) {

        this.account = account;

        tv_userName.setText(account.getName());
    }

    @Override
    public void setPresenter(SettingsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
