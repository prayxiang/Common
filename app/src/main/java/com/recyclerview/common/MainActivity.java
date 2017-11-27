package com.recyclerview.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.recyclerview.recyclerview.extension.BaseAdapter;
import com.recyclerview.recyclerview.extension.tools.SimpleViewBound;

import java.util.Arrays;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {
//    String post(String url, String json) throws IOException {
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        try (Response response = client.newCall(request).execute()) {
//            return response.body().string();
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
       BaseAdapter adapter = new BaseAdapter();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        adapter.setLimit(10);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.register(String.class,new SimpleViewBound<String>(R.layout.view_bound_item, com.recyclerview.common.BR.data)
                .addOnClickListener((view, item, i) -> Toast.makeText(view.getContext(), "测试", Toast.LENGTH_SHORT).show()));

//
        recyclerView.setAdapter(adapter);
        adapter.display(Arrays.asList("1", "2", "3","4","5","11", "12", "13","14","15","21", "22", "23","24","25"));
        adapter.setLoadListener(new BaseAdapter.LoadListener() {
            int count;
            @Override
            public void load(int offset) {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        count++;
                        if(offset==3){
                            adapter.insert(Collections.emptyList());
                        }else {
                            adapter.insert(Arrays.asList("1", "2", "3","4","5","11", "12", "13","14","15","21", "22", "23","24","25"));
                        }

                    }
                },3000);
            }
        });
    }
}
