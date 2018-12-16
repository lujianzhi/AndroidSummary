package com.example.lawson.androidsummery.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lawson.androidsummery.R;
import com.example.lawson.androidsummery.greendao.been.User;
import com.example.lawson.androidsummery.greendao.gen.DaoMaster;
import com.example.lawson.androidsummery.greendao.gen.DaoSession;
import com.example.lawson.androidsummery.greendao.gen.UserDao;

import java.util.List;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView nameTv;
    private TextView ageTv;
    private TextView queryTv;
    private Button insertBtn;
    private Button deleteBtn;
    private Button updateBtn;
    private Button queryBtn;

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);

        initDB();
        initData();

        nameTv = findViewById(R.id.name);
        ageTv = findViewById(R.id.age);
        queryTv = findViewById(R.id.query_tv);
        insertBtn = findViewById(R.id.insert);
        deleteBtn = findViewById(R.id.delete);
        updateBtn = findViewById(R.id.update);
        queryBtn = findViewById(R.id.query);

        insertBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        queryBtn.setOnClickListener(this);
    }

    private void initData() {
        if (userDao.queryBuilder().count() > 100) {
            return;
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setName("user" + i);
            user.setAge(1 + (int) (Math.random() * 10));
            userDao.insert(user);
        }
        long finishTime = System.currentTimeMillis();
        Log.i("Ian", "insert durTime : " + (finishTime - startTime));

    }

    /**
     * 初始化数据库
     */
    private void initDB() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "ian_db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }

    /**
     * 插入数据
     */
    private void insert() {
        User user = new User();
        user.setName(getViewString(nameTv));
        user.setAge(getInteger(getViewString(ageTv)));
        userDao.insert(user);
    }

    /**
     * 查
     */
    private void query() {
        List<User> userList;
        if (TextUtils.isEmpty(getViewString(nameTv)) && getInteger(getViewString(ageTv)) != -1) {
            userList = userDao.queryBuilder()
                    .where(UserDao.Properties.Age.eq(getInteger(getViewString(ageTv))))
                    .orderAsc(UserDao.Properties.UserId)
                    .build()
                    .list();
        } else if (!TextUtils.isEmpty(getViewString(nameTv)) && getInteger(getViewString(ageTv)) == -1) {
            userList = userDao.queryBuilder()
                    .where(UserDao.Properties.Name.eq(getViewString(nameTv)))
                    .orderAsc(UserDao.Properties.UserId)
                    .build()
                    .list();
        } else {
            userList = userDao.queryBuilder()
                    .where(UserDao.Properties.Name.eq(getViewString(nameTv)),
                            UserDao.Properties.Age.eq(getInteger(getViewString(ageTv))))
                    .orderAsc(UserDao.Properties.UserId)
                    .build()
                    .list();
        }
        queryTv.setText(userList.toString());
    }

    /**
     * 改
     */
    private void update() {
        User user = userDao.queryBuilder().where(UserDao.Properties.Name.eq(getViewString(nameTv))).build().unique();
        if (user != null) {
            user.setName("新--" + getViewString(nameTv));
            userDao.update(user);
            queryTv.setText(userDao.queryBuilder().where(UserDao.Properties.Name.eq("新--" + getViewString(nameTv))).build().unique().toString());
        } else {
            queryTv.setText("查询不到指定用户");
        }
    }

    /**
     * 删
     */
    private void delete() {
        User user = userDao.queryBuilder().where(UserDao.Properties.Name.eq(getViewString(nameTv))).build().unique();
        if (user != null) {
            userDao.delete(user);
            queryTv.setText("已删除" + user.toString());
        } else {
            queryTv.setText("查询不到指定用户");
        }
    }

    private String getViewString(TextView textView) {
        String str = textView.getText().toString();
        return TextUtils.isEmpty(str) ? "" : str;
    }

    private int getInteger(String intStr) {
        return TextUtils.isEmpty(intStr) ? -1 : Integer.parseInt(intStr);
    }

    @Override
    public void onClick(View v) {
        long startTime = System.currentTimeMillis();
        switch (v.getId()) {
            case R.id.insert:
                insert();
                break;
            case R.id.delete:
                delete();
                break;
            case R.id.update:
                update();
                break;
            case R.id.query:
                query();
                break;
        }
        long finishTime = System.currentTimeMillis();
        Log.i("Ian", "insert durTime : " + (finishTime - startTime));
    }
}
