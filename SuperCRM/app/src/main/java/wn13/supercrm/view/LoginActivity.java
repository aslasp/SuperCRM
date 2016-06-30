package wn13.supercrm.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import wn13.supercrm.R;
import wn13.supercrm.values.Global;

public class LoginActivity extends Activity {

    EditText idFld,pswdFld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idFld=(EditText) findViewById(R.id.login_idFld);
        pswdFld=(EditText) findViewById(R.id.login_pswdFld);

        //为登录按钮增加监听
        Button loginBtn=(Button)findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCheck();
            }
        });
    }

    private void loginCheck(){
        String id=idFld.getText().toString();
        Global.userid=id;
        String pswd=pswdFld.getText().toString();
        Intent intent=new Intent();
        intent.putExtra("id",id);
        intent.putExtra("pswd",pswd);
        intent.setClass(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
