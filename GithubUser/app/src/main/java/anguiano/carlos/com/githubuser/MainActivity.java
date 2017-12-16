package anguiano.carlos.com.githubuser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import anguiano.carlos.com.githubuser.api.GitHubClient;
import anguiano.carlos.com.githubuser.api.ServiceGenerator;
import anguiano.carlos.com.githubuser.model.GitHubUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    Button searchBtn;
    TextView responseText;
    EditText editText;
    ProgressBar progressBar;
    GitHubClient gitHubClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gitHubClient = ServiceGenerator.createService(GitHubClient.class);

        searchBtn = findViewById(R.id.main_btn_lookup);
        responseText = findViewById(R.id.main_text_response);
        editText = findViewById(R.id.main_edit_username);
        progressBar = findViewById(R.id.main_progress);
        progressBar.setVisibility(View.INVISIBLE);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchForUser();
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchForUser();
                }
                return handled;
            }
        });
    }


    public void searchForUser() {
        String user = editText.getText().toString();
        progressBar.setVisibility(View.VISIBLE);

        final Call<GitHubUser> call = gitHubClient.getFeed(user);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Response<GitHubUser> response) {
                GitHubUser gitModel = response.body();
                if (gitModel != null) {
                    responseText.setText(getString(R.string.main_response_text,
                            gitModel.getName(),
                            gitModel.getBlog(),
                            gitModel.getCompany()));
                } else {
                    responseText.setText("");
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.main_error_text),
                            Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Throwable t) {
                responseText.setText(""); //Error needs to be handled properly
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
