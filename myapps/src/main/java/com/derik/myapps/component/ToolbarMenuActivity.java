package com.derik.myapps.component;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.derik.myapps.R;

import java.lang.reflect.Method;

public class ToolbarMenuActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView;
    private TextView popText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_test);
        toolbar = (Toolbar) findViewById(R.id.toolbar_my_bar);
        textView = (TextView) findViewById(R.id.toolbar_my_text);
        popText = (TextView) findViewById(R.id.toolbar_my_popup);
        popText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(ToolbarMenuActivity.this, v);
                getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(ToolbarMenuActivity.this, ""+item.getItemId(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        registerForContextMenu(textView);

        //APP icon
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("ToolbarDemo");
        toolbar.setNavigationIcon(android.R.drawable.ic_media_previous);


        setSupportActionBar(toolbar);
        // 添加这一条以后才能当做actionbar使用
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View source, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(contextMenu, source, menuInfo);
        if (contextMenu != null) {
            try {
                //如果不为空,就反射拿到menu的setOptionalIconsVisible方法
                Method method = contextMenu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                //允许访问private方法
                method.setAccessible(true);
                //调用该方法设置允许显示icon
                method.invoke(contextMenu, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        MenuItem menuItem1 = contextMenu.add("MenuItem1");
        menuItem1.setIcon(android.R.drawable.ic_menu_edit);

        SubMenu subMenu1 = contextMenu.addSubMenu("SubMenu1");
        subMenu1.setIcon(android.R.drawable.ic_menu_add);
        subMenu1.add(0, 0, 0, "item1 in subMenu1");
        subMenu1.add(0, 1, 0, "item2 in subMenu1");
        subMenu1.add(1, 8, 0, "item3 in subMenu1");
        subMenu1.add(1, 9, 0, "item4 in subMenu1");
        //设置
        subMenu1.setGroupCheckable(0, true, true);
        subMenu1.setGroupCheckable(1, true, false);
        SubMenu subMenu2 = contextMenu.addSubMenu("SubMenu2");
        subMenu2.setIcon(android.R.drawable.ic_menu_add);
        subMenu2.add(1, 2, 0, "item1 in subMenu2");
        subMenu2.add(1, 3, 0, "item2 in subMenu2");
        SubMenu subMenuInSB1 = subMenu2.addSubMenu("Submenu1 in SubMenu1");
        subMenuInSB1.setIcon(android.R.drawable.ic_menu_add);
        subMenuInSB1.add(2, 4, 0, "item1 in subMenuInSB1");
        subMenuInSB1.add(2, 5, 0, "item2 in subMenuInSB1");
        SubMenu subMenuInSubMenuInSB1 = subMenuInSB1.addSubMenu("subMenuInSubMenuInSB1");
        subMenuInSubMenuInSB1.setIcon(android.R.drawable.ic_menu_add);
        subMenuInSubMenuInSB1.add(3, 6, 0, "item1 in subMenuInSubMenuInSB1");
        subMenuInSubMenuInSB1.add(3, 7, 0, "item2 in subMenuInSubMenuInSB1");

    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        super.onContextItemSelected(menuItem);
        int id = menuItem.getItemId();
        if (!menuItem.isChecked()) {
            menuItem.setChecked(true);
        } else {
            menuItem.setChecked(false);
        }
        switch (id) {
            case 0:
                System.out.println(id);
                break;
            case 1:
                System.out.println(id);
                break;
            case 2:
                System.out.println(id);
                break;
            case 3:
                System.out.println(id);
                break;
            case 4:
                System.out.println(id);
                break;
            case 5:
                System.out.println(id);
                break;
            case 6:
                System.out.println(id);
                break;
            case 7:
                System.out.println(id);

            case 8:
                System.out.println(id);
                break;
            case 9:

                System.out.println(id);
                break;

        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        int id = menuItem.getItemId();
        if (!menuItem.isChecked()) {
            menuItem.setChecked(true);
        } else {
            menuItem.setChecked(false);
        }
        switch (id) {
            case R.id.action_edit:
                Toast.makeText(this, "action_edit:" + id, Toast.LENGTH_LONG).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "action_settings:" + id, Toast.LENGTH_LONG).show();
                break;
            case R.id.action_share:
                Toast.makeText(this, "action_share:" + id, Toast.LENGTH_LONG).show();
                break;
            default:
                break;

        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterForContextMenu(textView);
    }
}
