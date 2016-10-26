package com.example.xiangsheng.mynote;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiangsheng on 2016-10-26.
 */

public class AddContent extends AppCompatActivity implements View.OnClickListener{
    private String val;
    private Button savebtn,deletebtn;
    private EditText et;
    private ImageView c_img;
    private VideoView v_video;

    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent);

        val=getIntent().getStringExtra("flag");

        savebtn= (Button) findViewById(R.id.save);
        deletebtn= (Button) findViewById(R.id.delete);
        et= (EditText) findViewById(R.id.et);
        c_img= (ImageView) findViewById(R.id.c_img);
        v_video= (VideoView) findViewById(R.id.c_video);

        savebtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);

        notesDB=new NotesDB(this);
        dbWriter=notesDB.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                addDB();
                //将当前activity关闭
                finish();
                break;
            case R.id.delete:
                //将当前activity关闭
                finish();
                break;
        }
    }

    //添加数据库
    public void addDB(){
        ContentValues cv=new ContentValues();
        cv.put(NotesDB.CONTENT,et.getText().toString());
        cv.put(NotesDB.TIME,getTime());
        dbWriter.insert(NotesDB.TABLE_NAME,null,cv);
    }

    //获取时间
    public String getTime(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date=new Date();
        String str=format.format(date);
        return str;
    }
}
