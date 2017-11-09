package com.prayxiang.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.Toast;

import com.prayxiang.recyclerview.extension.MultiTypeAdapter;
import com.prayxiang.recyclerview.extension.StickyAdapter;
import com.prayxiang.recyclerview.extension.manager.StickyHeadersLinearLayoutManager;
import com.prayxiang.recyclerview.extension.tools.SimpleViewBound;

import java.util.Arrays;


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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        MultiTypeAdapter adapter = new StickyAdapter();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new StickyHeadersLinearLayoutManager<StickyAdapter>(this));
        adapter.register(String.class,new SimpleViewBound<String>(R.layout.view_bound_item, com.prayxiang.common.BR.data)
                .addOnClickListener((view, item, i) -> Toast.makeText(view.getContext(), "测试", Toast.LENGTH_SHORT).show()));


        recyclerView.setAdapter(adapter);
        adapter.display(Arrays.asList("1", "2", "3","4","5","11", "12", "13","14","15","21", "22", "23","24","25"));
    }
}
