package ntu.bt8.newspapervnexpress;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvNews;
    private NewsAdapter adapter;
    private String RSS_URL = "https://vnexpress.net/rss/cuoi.rss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvNews = findViewById(R.id.rvNews);
        rvNews.setLayoutManager(new LinearLayoutManager(this));

        new FetchRssTask().execute(RSS_URL);
    }

    private class FetchRssTask extends AsyncTask<String, Void, List<NewsItem>> {
        @Override
        protected List<NewsItem> doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                return RssParser.parse(inputStream);
            } catch (Exception e) {
                Log.e("RssTask", "Error fetching data", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<NewsItem> newsList) {
            if (newsList != null) {
                adapter = new NewsAdapter(MainActivity.this, newsList);
                rvNews.setAdapter(adapter);
            } else {
                Toast.makeText(MainActivity.this, "Không thể tải dữ liệu", Toast.LENGTH_SHORT).show();
            }
        }
    }
}