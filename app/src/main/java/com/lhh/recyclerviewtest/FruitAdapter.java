package com.lhh.recyclerviewtest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;

    //内部类
    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;

        //viewHolder构造函数，这个itemView是RecyclerView子项的最外层布局，
        // 所有可以用findViewById获取布局中的ImageView和TextView的实例了。
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitView=itemView;
            fruitName = itemView.findViewById(R.id.fruit_name);
            fruitImage = itemView.findViewById(R.id.fruit_image);
        }
    }

    //FruitAdapter构造函数用于把要展示的数据源传进来，并赋值给一个全局变量mFruitList.
    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    //这个重写方法是创建ViewHolder实例的，加载fruit_item布局，
// 然后创建一个ViewHolder的实例，把加载进来的布局传入构造函数当中,最终将ViewHolder的实例返回。
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
       final ViewHolder holder = new ViewHolder(view);
       holder.fruitName.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int position = holder.getAdapterPosition();
               Log.d("lll", String.valueOf(position));
               Fruit fruit = mFruitList.get(position);
               Toast.makeText(view.getContext(),"点击了文本："+fruit.getName(),Toast.LENGTH_SHORT).show();;
           }
       });
       holder.fruitImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int position = holder.getAdapterPosition();
               Fruit fruit = mFruitList.get(position);
               Toast.makeText(view.getContext(),"你点击了图片："+fruit.getName(),Toast.LENGTH_SHORT).show();;

           }
       });
        return holder;
    }

    //这个方法是用于对RecyclerView子项的数据进行赋值的，会把每个子项被滚动到屏幕内的时候执行
    //这里我们通过position参数得到当前的Fruit实例，
// 然后再将数据设置到ViewHolder的ImageView和TextView当中即可。
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageBitmap(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

}
