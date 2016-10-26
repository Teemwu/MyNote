package com.example.xiangsheng.mynote;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NotesDB notesDB;
    private SQLiteDatabase dbReader;

    private Button txtbtn,imgbtn,videobtn;
    private ListView lv;
    private Intent intent;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesDB=new NotesDB(this);
        dbReader=notesDB.getReadableDatabase();

        initView();
    }

    public void initView(){
        lv= (ListView) findViewById(R.id.list_item);
        txtbtn= (Button) findViewById(R.id.text);
        imgbtn= (Button) findViewById(R.id.img);
        videobtn= (Button) findViewById(R.id.video);
        txtbtn.setOnClickListener(this);
        imgbtn.setOnClickListener(this);
        videobtn.setOnClickListener(this);
    }
    public void onClick(View v){
        intent=new Intent(this,AddContent.class);
        switch (v.getId()){
            case  R.id.text:
                intent.putExtra("flag","1");
                startActivity(intent);
                break;
            case R.id.img:
                intent.putExtra("flag","2");
                startActivity(intent);
                break;
            case R.id.video:
                intent.putExtra("flag","3");
                startActivity(intent);
                break;
        }
    }
    //获取数据库数据
    public void selectDB(){
        Cursor cursor=dbReader.query(NotesDB.TABLE_NAME,null,null,null,null,null,null);
        adapter=new MyAdapter(this,cursor);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }
}
