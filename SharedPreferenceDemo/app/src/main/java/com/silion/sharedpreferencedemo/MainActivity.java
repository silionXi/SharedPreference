package com.silion.sharedpreferencedemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText mNameEditText;
    EditText mPassWordEditText;
    CheckBox mSaveCheckBox;
    Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNameEditText = (EditText) findViewById(R.id.editUserName);
        mPassWordEditText = (EditText) findViewById(R.id.editUserPassWord);
        mSaveCheckBox = (CheckBox) findViewById(R.id.saveCheckBox);
        SharedPreferences sharedPre = getSharedPreferences("my_sharedPre", MODE_PRIVATE);
        editor = sharedPre.edit();
        String name = sharedPre.getString("name", "");
        mNameEditText.setText(name);
        Boolean isCheck = sharedPre.getBoolean("save", false);
        mSaveCheckBox.setChecked(isCheck);
    }

    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.login: {
                String name = mNameEditText.getText().toString();
                String passWord = mPassWordEditText.getText().toString().trim();
                if (name.equals("silion") && passWord.equals("880317")) {
                    if (mSaveCheckBox.isChecked()) {
                        editor.putString("name", name);
                        editor.putBoolean("save", true);
                        editor.commit();
                    } else {
                        editor.remove("name");
                        editor.remove("save");
                        editor.commit();
                    }
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "账号或密码失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.cancel: {
                finish();
            }
            default:
                break;
        }
    }
}
