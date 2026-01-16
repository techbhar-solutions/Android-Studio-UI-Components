package com.example.retrofitexample;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView txtResult;
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
        txtResult = findViewById(R.id.txtResult);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // API create karna
        JsonApi jsonApi = retrofit.create(JsonApi.class);
        // 3. Call kiya (Order diya)
        Call<List<PostModel>> call = jsonApi.getPosts();
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (!response.isSuccessful()) {
                    txtResult.setText("Code: " + response.code());
                    return;
                }
                // Data aa gaya! Ab ise decorate karte hain
                List<PostModel> posts = response.body();

                // Saare posts ko jodkar ek lambi string banayenge
                StringBuilder content = new StringBuilder();

                // Sirf pehle 5 posts dikhate hain (testing ke liye)
                for (int i = 0; i < 5; i++) {
                    PostModel post = posts.get(i);

                    content.append("ID: ").append(post.getTitle()).append("\n");
                    content.append("Title: ").append(post.getTitle()).append("\n");
                    content.append("Body: ").append(post.getBody()).append("\n\n");
                    content.append("-----------------\n\n");
                }

                // Screen par chipka do
                txtResult.setText(content.toString());


            }
            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                // Agar internet band hai ya koi error hai
                txtResult.setText("Error: " + t.getMessage());
            }

        });
    }
}
