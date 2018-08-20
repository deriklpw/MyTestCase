package com.derik.demo.views.resource;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.derik.demo.R;
import com.derik.library.view.MsgToast;

public class MenuTestActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_test);
        textView = (TextView) findViewById(R.id.resource_menu_text);
        registerForContextMenu(textView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main2, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_icon:

                MsgToast.show(this, "icon");
                break;
            case R.id.menu_name:
                MsgToast.show(this, "name");
                break;
            case R.id.menu_sex:
                MsgToast.show(this, "sex");
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        menu.setHeaderTitle("Menu");
        menu.setHeaderIcon(R.drawable.ic_launcher);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_icon:
                MsgToast.show(this, "icon");
                break;
            case R.id.menu_name:
                MsgToast.show(this, "name");
                break;
            case R.id.menu_sex:
                MsgToast.show(this, "sex");
                break;
            default:
                break;
        }
        return true;
    }

}
