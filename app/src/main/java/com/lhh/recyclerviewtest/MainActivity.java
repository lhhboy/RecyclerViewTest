package com.lhh.recyclerviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();
    FruitAdapter adapter;
    EditText editText;
    Button imageButton;
    ImageView imageView;
    Bitmap bitmap = null;
    public final static int RESULT_LOAD_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton = findViewById(R.id.image_button);
        imageView = findViewById(R.id.image_view);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //RecyclerView的布局管理工作交给LayoutManager,LayoutManager中制定了一套可扩展的布局排列接口。
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        editText = findViewById(R.id.edit_view);
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
   /* private void initFruits() {
        for (int i = 0;i<30;i++){
            Fruit apple=new Fruit("apple",R.drawable.ic_launcher_background);
            fruitList.add(apple);
            Fruit pear=new Fruit("pear",R.drawable.ic_launcher_foreground);
            fruitList.add(pear);
        }
    }*/
    }

    public void onViewClick(View view) {
        Fruit apple = new Fruit((editText.getText().toString()),bitmap );
        fruitList.add(apple);
        adapter.notifyDataSetChanged();
    }

//    private String getRadomLengthName(String name) {
//        Random random = new Random();
//        int length = random.nextInt(20) + 1;
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < length; i++) {
//            builder.append(name);
//        }
//        return builder.toString();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);

            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(selectedImage));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);
            cursor.close();
        }
    }
}