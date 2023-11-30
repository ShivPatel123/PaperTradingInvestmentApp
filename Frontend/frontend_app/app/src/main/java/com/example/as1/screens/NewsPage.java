package com.example.as1.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.as1.Controllers.NewsArticle;
import com.example.as1.ExternalControllers.VolleySingleton;
import com.example.as1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsPage extends AppCompatActivity {

    Button back_btn, prev_btn, next_btn, fullArticle_btn;
    ImageView imageView;
    TextView index_display, news_title,news_summary, authors_display, src_display, server_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_page);
        final int[] news_index = {0};

        //get all news in an array
        ArrayList<NewsArticle> newsArticles = new ArrayList<>();
        //get index of stock page display
        Intent newsIntent = getIntent();
        int id = newsIntent.getIntExtra("index", 0);
        //get news of that index
        newsArticles = getNews(this.getApplicationContext(), id);

        imageView = findViewById(R.id.news_image);
        index_display = findViewById(R.id.index_txt);
        news_title = findViewById(R.id.article_title);
        news_summary = findViewById(R.id.summary_txt);
        authors_display = findViewById(R.id.authors_txt);
        src_display = findViewById(R.id.source_txt);
        server_notes = findViewById(R.id.server_notes);


        //Page Left Button
        prev_btn = findViewById(R.id.prev_newsPageBtn);
        ArrayList<NewsArticle> newsArticles1 = newsArticles;
        final int[] news_nextIndex = new int[1];
        prev_btn.setOnClickListener(view -> {
            if(newsArticles1 == null){
                server_notes.setText("Stock List is null, please try again later");
            }
            else {
                int size = newsArticles1.size()-1;
                if(news_index[0] == 0){
                    news_index[0] = size;
                    news_nextIndex[0] = size;
                }
                else {
                    news_nextIndex[0] = news_index[0] - 1;
                    news_index[0] = news_nextIndex[0];
                }
                NewsArticle nextArticle;
                nextArticle = newsArticles1.get(news_nextIndex[0]);
                index_display.setText(""+ news_index[0] + "/50");
                news_summary.setText(nextArticle.getSummary());
                news_title.setText("" + nextArticle.getTitle());
                authors_display.setText("" + nextArticle.getAuthors());
                src_display.setText(nextArticle.getSource());
            }
        });

        //Page Right Button
        next_btn = findViewById(R.id.next_newsPageBtn);
        ArrayList<NewsArticle> newsArticles2 = newsArticles;
        final int[] nextIndex = new int[1];
        next_btn.setOnClickListener(view -> {
            if(newsArticles2 == null){
                server_notes.setText("Stock List is null, please try again later");
            }
            else {
                int size = newsArticles2.size()-1;
                if(news_index[0] >= size){
                    news_index[0] = 0;
                    nextIndex[0] = 0;
                }
                else {
                    nextIndex[0] = news_index[0] + 1;
                    news_index[0] = nextIndex[0];
                }
                NewsArticle nextArticle;
                nextArticle = newsArticles2.get(nextIndex[0]);
                index_display.setText(""+ news_index[0] + "/50");
                news_summary.setText(nextArticle.getSummary());
                news_title.setText("" + nextArticle.getTitle());
                authors_display.setText("" + nextArticle.getAuthors());
                src_display.setText(nextArticle.getSource());
            }
        });
    }


    public ArrayList<NewsArticle> getNews(Context context, int id) {
        String URL_JSON_OBJECT = "http://10.90.75.130:8080/news/" + id;
        ArrayList<NewsArticle> newsArticles = new ArrayList<>();
        imageView = findViewById(R.id.news_image);
        index_display = findViewById(R.id.index_txt);
        news_title = findViewById(R.id.article_title);
        news_summary = findViewById(R.id.summary_txt);
        authors_display = findViewById(R.id.authors_txt);
        src_display = findViewById(R.id.source_txt);
        server_notes = findViewById(R.id.server_notes);

        //Create new request
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null,
                response -> {
                    Log.i(" full response", "get News: " + response.toString());

                    JSONObject object;
                    JSONArray feed;
                    String[] authors = {};
                    NewsArticle newsObject = new NewsArticle("empty", "n", "n","n", "n", authors);
                        try {
                            feed = response.getJSONArray("feed");
                            for (int i = 0; i < response.length(); i++) {
                                object = (JSONObject) feed.get(i);
                                newsObject.setAuthors((String[]) object.get("authors"));
                                newsObject.setImage(object.getString("banner_image"));
                                newsObject.setSource(object.getString("source"));
                                newsObject.setTitle(object.getString("title"));
                                newsObject.setUrl(object.getString("url"));
                                newsObject.setSummary(object.getString("summary"));
                                newsArticles.add(newsObject);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    NewsArticle nextArticle;
                    nextArticle = newsArticles.get(0);
                    index_display.setText("0/50");
                    news_summary.setText(nextArticle.getSummary());
                    news_title.setText("" + nextArticle.getTitle());
                    authors_display.setText("" + nextArticle.getAuthors());
                    src_display.setText(nextArticle.getSource());

                },

                error -> Log.i("Error ", "getNews(): " + error.getMessage())) {
        };
        // Adding request to request queue
        VolleySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
        return newsArticles;
    }
}
