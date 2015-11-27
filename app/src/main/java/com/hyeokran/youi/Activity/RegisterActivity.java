package com.hyeokran.youi.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyeokran.youi.R;

/**
 * 회원 가입 액티비티
 * Created by GwonHyeok on 2015. 11. 26..
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText mNameEditText, mMailEditText, mPwdEditText;
    private Button mSubmitButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        viewInit();
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
                } else {
                    Toast.makeText(getApplicationContext(), "모두 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
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