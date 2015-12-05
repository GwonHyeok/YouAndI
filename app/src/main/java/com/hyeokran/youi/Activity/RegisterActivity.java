package com.hyeokran.youi.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyeokran.youi.R;
import com.hyeokran.youi.network.NetworkResponseInterface;
import com.hyeokran.youi.network.api.RegisterApi;

/**
 * 회원 가입 액티비티
 * Created by GwonHyeok on 2015. 11. 26..
 */
public class RegisterActivity extends BaseToolbarActivity {

    private EditText mNameEditText, mMailEditText, mPwdEditText;
    private Button mSubmitButon;

    private RegisterApi mRegisterApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        viewInit();
        networkInit();
    }

    /* View 초기화 */
    private void viewInit() {
        this.mNameEditText = (EditText) findViewById(R.id.register_name_edittext);
        this.mMailEditText = (EditText) findViewById(R.id.register_email_edittext);
        this.mPwdEditText = (EditText) findViewById(R.id.register_pwd_edittext);
        this.mSubmitButon = (Button) findViewById(R.id.register_submit_button);

        /* 버튼 리스너 추가 */
        this.mSubmitButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidInput()) {
                    /* Do Register */
                    mRegisterApi.startConnection();
                } else {
                    Toast.makeText(getApplicationContext(), "모두 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void networkInit() {
        mRegisterApi = new RegisterApi(this);
        mRegisterApi.setNetworkResponseInterface(new NetworkResponseInterface() {
            @Override
            public void getNetworkData(String apiName, String errorMsg, int code) {
                /* Register 결과 콜백 */
                finish();
            }
        });
    }

    /**
     * 입력한 값이 올바른지 확인하는 메소드
     * 햔재는 값을 적었는지 아닌지만 확인한다.
     *
     * @return 입력한 값의 옳바른 데이터 유무
     */
    private boolean checkValidInput() {
        String name = mNameEditText.getText().toString();
        String mail = mMailEditText.getText().toString();
        String pwd = mPwdEditText.getText().toString();

        return !name.isEmpty() & !mail.isEmpty() & !pwd.isEmpty();
    }
}