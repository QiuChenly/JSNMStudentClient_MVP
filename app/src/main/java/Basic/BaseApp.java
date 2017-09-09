package Basic;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Author  : QiuChenLy
 * Date    : 2017.8.26 AM 9:54
 * Func    : 基础Activity类 所有Activity应继承此类
 * LastEdit: 2017.8.26 AM 9:54
 */

abstract public class BaseApp extends Activity implements View.OnClickListener{
    //双击返回间隔计数
    private long lastPressTime = 0;

    //设置双击返回
    private boolean setDoubleClickReturn = true;

    //设置不显示Actionbar
    private boolean setActionBarDisable = true;

    //设置透明状态栏
    private boolean setStatusBarAlpha = true;


    public BaseApp(boolean setDoubleClickReturn, boolean setActionBarDisable, boolean setStatusBarAlpha) {
        this.setDoubleClickReturn = setDoubleClickReturn;
        this.setActionBarDisable = setActionBarDisable;
        this.setStatusBarAlpha = setStatusBarAlpha;
    }
    public BaseApp() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(SetThisContentView());
        if (setStatusBarAlpha) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );
        }

        if (setActionBarDisable) {
            ActionBar action = getActionBar();
            if (action != null && action.isShowing())
                getActionBar().hide();
        }
        SetOnClickListener();
        loadComplete();
    }

    @Override
    public void onClick(View view) {
        BeClick(view);
    }

    public abstract void loadComplete();

    public abstract void SetOnClickListener();

    public abstract void BeClick(View v);

    public <T extends View> T $(int id,boolean setOnClick){
        T view = (T)findViewById(id);
        //顺手设置被点击事件
        if (setOnClick){
            view.setOnClickListener(this);
        }
        return view;
    }
    public <T extends View> T $(int id,boolean setOnClick,View views){
        T view = (T)views.findViewById(id);
        //顺手设置被点击事件
        if (setOnClick){
            view.setOnClickListener(this);
        }
        return view;
    }


    public abstract int SetThisContentView();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long time = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (time - lastPressTime > 2000) {
                showToast("再按一次退出App~");
                lastPressTime = time;
            } else {
                finish();
                System.exit(0);
            }
        }
        return true;
    }

    public <T> void showToast(T Message) {
        Toast.makeText(this, Message.toString(), Toast.LENGTH_SHORT).show();
    }
    public <T> void showToast(T Message,boolean isLong) {
        Toast.makeText(this, Message.toString(), isLong?Toast.LENGTH_LONG:Toast.LENGTH_SHORT).show();
    }

    public <T> void startActivity(Class<T> classA,boolean AllowFinish){
        startActivity(new Intent(this,classA));
        if(AllowFinish)
            finish();
    }

    public<T> void logs(T Message){
        Log.d(getClass().getSimpleName(),Message.toString());
    }
}
