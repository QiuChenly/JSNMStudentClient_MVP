package com.qiuchenly.stuclient.view;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.qiuchenly.stuclient.R;

import Basic.BaseApp;

public class LoginActivity extends BaseApp {
    com.dd.CircularProgressButton circularProgressButton = null;
    TextView tLogin, tPass;

    public LoginActivity() {
        super();
    }

    @Override
    public void loadComplete() {

    }

    @Override
    public void SetOnClickListener() {
        circularProgressButton = $(R.id.bLoginButton, true);
        circularProgressButton.setIndeterminateProgressMode(true);
        tLogin = $(R.id.tlogin_user, false);
        tPass = $(R.id.tlogin_pass, false);
    }

    @Override
    public void BeClick(View v) {
        switch (v.getId()) {
            case R.id.bLoginButton:
                loginTask();
                break;
            default:
                break;
        }
    }

    void loginTask(){
        circularProgressButton.setClickable(false);
        circularProgressButton.setProgress(1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean LoginResult=false;
                if (LoginResult){
                    circularProgressButton.setProgress(100);


                    return;
                }else{
                    circularProgressButton.setProgress(-1);
                }
                circularProgressButton.setClickable(true);
                circularProgressButton.setProgress(0);
            }
        },2000);





//        ValueAnimator animator=ValueAnimator.ofInt(1,100);
//        animator.setDuration(1500);
//        animator.setInterpolator(new AnticipateInterpolator());
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                circularProgressButton.setProgress((int) valueAnimator.getAnimatedValue());
//            }
//        });
//        animator.start();
    }

    @Override
    public int SetThisContentView() {
        return R.layout.activity_login;
    }
}
