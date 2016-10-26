package com.example.xiangsheng.mynote;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by xiangsheng on 2016-10-26.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private Cursor cursor;
    private LinearLayout layout;

    public MyAdapter(Context context,Cursor cursor){
        this.context=context;
        this.cursor=cursor;
    }
    @Override
    public int getCount() {
        //返回cursor长度
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       //加载视图的权限
        LayoutInflater inflater=LayoutInflater.from(context);
        layout= (LinearLayout) inflater.inflate(R.layout.cell,null);

        TextView contentTv= (TextView) layout.findViewById(R.id.list_content);
        TextView timeTv= (TextView) layout.findViewById(R.id.lt);
        ImageView imageView= (ImageView) layout.findViewById(R.id.list_img);
        ImageView videoIv= (ImageView) layout.findViewById(R.id.list_video);

        cursor.moveToPosition(position);
        String content=cursor.getString(cursor.getColumnIndex("content"));
        String time=cursor.getString(cursor.getColumnIndex("time"));
        contentTv.setText(content);
        timeTv.setText(time);

        return layout;
    }
}
