package runjoy.register;


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
import runjoy.data.Account;
import runjoy.homepage.HomePageActivity;
import runjoy.login.LoginActivity;

public class RegisterFragment extends Fragment implements RegisterContract.View{

    private RegisterContract.Presenter mPresenter;


    private Button btn_register;

    private Button btn_haveAccount;

    private EditText edtTxt_userName;

    private EditText edtTxt_passWord;

    private EditText edtTxt_passWordRepeat;

    private EditText edtTxt_email;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.register_frag, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn_register = (Button) getActivity().findViewById(R.id.btn_register);

        btn_haveAccount = (Button) getActivity().findViewById(R.id.btn_haveAccount);

        edtTxt_userName = (EditText) getActivity().findViewById(R.id.edtTxt_userName);

        edtTxt_passWord = (EditText) getActivity().findViewById(R.id.edtTxt_passWord);

        edtTxt_passWordRepeat = (EditText) getActivity().findViewById(R.id.edtTxt_passWordRepeat);

        edtTxt_email = (EditText) getActivity().findViewById(R.id.edtTxt_email);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account();

                boolean registerResult = mPresenter.isAccountAvailable(edtTxt_userName.getText().toString());

                if (registerResult) {
                    mPresenter.saveAccount(account);
                    Toast.makeText(getActivity().getApplicationContext(), "注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "用户名不符合要求或已被占用",Toast.LENGTH_SHORT).show();
                    edtTxt_userName.setText("");
                }
            }
        });

        btn_haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
