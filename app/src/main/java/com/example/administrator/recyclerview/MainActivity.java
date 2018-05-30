package com.example.administrator.recyclerview;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView basicRecycler = (RecyclerView)findViewById(R.id.basicRecycler);

        List<String> list = new ArrayList<>();
        for(int i =0; i <20; i++){
            list.add("item =" +i);
        }

        basicRecycler.setLayoutManager(new LinearLayoutManager(this));
        basicRecycler.setAdapter(new MyAdapter(list));
        basicRecycler.addItemDecoration(new MyItemDecoration());

    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public MyViewHolder(View itemView){
            super(itemView);
            title = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        private List<String> list;
        public MyAdapter(List<String> list){
            this.list = list;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_dropdown_item_1line, viewGroup, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            String text = list.get(position);
            holder.title.setText(text);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }


    private class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int index = parent.getChildAdapterPosition(view)+1;
            if(index % 3 == 0)
                outRect.set(20,20,20,60);
            else
                outRect.set(20,20,20,20);

            view.setBackgroundColor(0xcdcdcdcd);
            ViewCompat.setElevation(view, 20.0f);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            int width = parent.getWidth();
            int height = parent.getHeight();

            Drawable dr = ResourcesCompat.getDrawable(getResources(), R.drawable.android, null);
            int drWidth = dr.getIntrinsicWidth();
            int drHeight = dr.getIntrinsicHeight();

            int left = width/2 - drWidth/2;
            int top = height/2 - drHeight/2;
            c.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.android),left, top, null);
        }
    }
}
