package anguiano.carlos.com.githubuser.api;

import anguiano.carlos.com.githubuser.model.GitHubUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Carlos Anguiano on 15/12/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public interface GitHubClient {
    @GET("users/{user}")
    Call<GitHubUser>getFeed(@Path("user")String user);
}
