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
import runjoy.register.RegisterActivity;


public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    private EditText edtTxt_userName;

    private EditText edtTxt_passWord;

    private Button btn_login;

    private Button btn_noAccount;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_frag, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn_login = (Button) getActivity().findViewById(R.id.btn_login);

        btn_noAccount = (Button) getActivity().findViewById(R.id.btn_noAccount);

        edtTxt_userName = (EditText) getActivity().findViewById(R.id.edtTxt_userName);

        edtTxt_passWord = (EditText) getActivity().findViewById(R.id.edtTxt_passWord);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean loginResult = mPresenter.checkAccount(edtTxt_userName.getText().toString(), edtTxt_passWord.getText().toString());
                if (loginResult) {
                    getActivity().finish();
                    Intent intent = new Intent(getActivity(), HomePageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

            }
        });

        btn_noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
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
    public void onStop() {
        super.onStop();
        edtTxt_userName.setText("");
        edtTxt_passWord.setText("");
    }

    @Override
    public void showLoadingIndicator() {
        Toast.makeText(getActivity().getApplicationContext(), "登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorInfo() {
        Toast.makeText(getActivity().getApplicationContext(), "用户名或密码错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
