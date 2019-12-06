package com.example.coals.instargramnewstest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] mDataset = {"1", "2"};

    RequestQueue queue;
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3, container, false);
        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        queue = Volley.newRequestQueue(container.getContext());
        getNews();

        return view;
    }

    private void getNews() {
        String url = "https://newsapi.org/v2/top-headlines?country=kr&apiKey=89a3a7b6c6194cbead591d2c5396ae0b";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray arrayArtcles = jsonObject.getJSONArray("articles");

                    ArrayList<NewsData> news = new ArrayList<>();

                    for (int i = 0, j = arrayArtcles.length(); i < j; i++) {
                        JSONObject obj = arrayArtcles.getJSONObject(i);

                        Log.d("NEWS", obj.toString());

                        NewsData newsData = new NewsData();
                        newsData.setTitle(obj.getString("title"));
                        newsData.setUrlToImage(obj.getString("urlToImage"));
                        newsData.setDescription(obj.getString("description"));
                        newsData.setUrl(obj.getString("url"));
                        news.add(newsData);
                    }
                    adapter = new MyAdapter(news, context, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Object obj = v.getTag();
                            if (obj != null) {
                                int position = (int) obj;
                                ((MyAdapter) adapter).getNews(position).getUrl(); //클래스 형변환 -> 부모의 자식확인(친자확인)
                                Intent intent = new Intent(context, NewsDetailActivity.class);
                                intent.putExtra("news", ((MyAdapter) adapter).getNews(position));
                                startActivity(intent);
                            }
                        }
                    });
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        queue.add(stringRequest);
    }
}

