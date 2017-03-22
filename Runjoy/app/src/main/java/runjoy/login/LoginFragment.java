package runjoy.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import runjoy.R;
import runjoy.homepage.HomePageActivity;


public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_frag, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button btn_login = (Button) getActivity().findViewById(R.id.btn_login);
        final EditText edtTxt_userName = (EditText) getActivity().findViewById(R.id.edtTxt_userName);
        final EditText edtTxt_passWord = (EditText) getActivity().findViewById(R.id.edtTxt_passWord);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean loginResult = mPresenter.checkAccount(edtTxt_userName.toString(), edtTxt_passWord.toString());
                if (loginResult) {
                    Intent intent = new Intent(getActivity(), HomePageActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void showLoadingIndicator() {
        Toast.makeText(getActivity().getApplicationContext(), "用户名或密码错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorInfo() {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
