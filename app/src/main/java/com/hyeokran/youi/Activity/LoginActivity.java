package com.hyeokran.youi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyeokran.youi.R;
import com.hyeokran.youi.Util.UserDataPreferenceManager;
import com.hyeokran.youi.network.NetworkResponseInterface;
import com.hyeokran.youi.network.api.LoginApi;

/**
 * 로그인 액티비티
 * Created by uran on 15. 11. 15..
 */
public class LoginActivity extends BaseToolbarActivity {
    /* 이메일 EditText, 비밀번호 EditText */
    private EditText mEmailEditText, mPwdEditText;

    /* 로그인 버튼 */
    private Button mSigninButton;

    /* 회원가입 버튼 */
    private View mSignupView;

    /* 로그인 API */
    private LoginApi mLoginApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewInit();
        networkInit();

        setUpSignInButton();
        setUpSignUpButton();
    }

    /* View 초기화 작업 */
    private void viewInit() {
        mEmailEditText = (EditText) findViewById(R.id.login_email_edittext);
        mPwdEditText = (EditText) findViewById(R.id.login_pwd_edittext);
        mSigninButton = (Button) findViewById(R.id.login_signin_button);
        mSignupView = findViewById(R.id.login_signup_button);
    }

    private void networkInit() {
        mLoginApi = new LoginApi(this);
        mLoginApi.setNetworkResponseInterface(new NetworkResponseInterface() {
            @Override
            public void getNetworkData(String apiName, String errorMsg, int code) {
                UserDataPreferenceManager.getInstance().setToken("YOU_AND_I_TEMP_TOKEN_IS_DEBUG_MODE");
                finish();
                startActivity(new Intent(getApplicationContext(), SplashActivity.class));

            }
        });
    }

    /* 로그인 버튼 설정 */
    private void setUpSignInButton() {
        /* 로그인 버튼 눌렀을떄 작동 */
        this.mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidData()) {
                    /*
                     * 로그인 하는 작업을 실행한다
                     * 아직 네트워크 연결 부분을 추가 하지 않음
                     */
                    mLoginApi.startConnection();
                } else {
                    /*
                     * Something Wrong !!
                     * 이메일 혹은 비밀번호가 입력 되지 않은 상태
                     * 다이얼로그를 띄울지 토스트를 띄울지 정해주세요
                     */
                    Toast.makeText(getApplicationContext(), "모두 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
            }

            /*
             * 이메일과 비밀번호가 전부 적혀 있는지 확인한다
             * 만약 둘중 하나라도 값이 적혀 있지 않다면 false를 반환
             */
            public boolean checkValidData() {
                String email = mEmailEditText.getText().toString();
                String pwd = mPwdEditText.getText().toString();

                return !email.isEmpty() && !pwd.isEmpty();
            }
        });
    }

    /* 회원가입 버튼 설정 */
    private void setUpSignUpButton() {

        /* 회원가입 버튼 눌렀을때 작동 */
        this.mSignupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 회원가입 화면으로 이동 */
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}