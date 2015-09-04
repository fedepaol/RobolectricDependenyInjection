/*
 *
 *  * Copyright (C) 2015 Federico Paolinelli.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.whiterabbit.robolectricdependency.client;

import android.content.Context;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class GitHubClient {
    private static final String API_URL = "https://api.github.com";
    private final GitHubService mService;

    public GitHubClient(Context c) {
        OkHttpClient okHttpClient = new OkHttpClient();
        File cacheDir = c.getCacheDir();
        Cache cache = null;
        cache = new Cache(cacheDir, 1024);
        okHttpClient.setCache(cache);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        mService = retrofit.create(GitHubService.class);

    }

    public Call<List<Repo>> getRepos(String user) {
        return mService.listRepos(user);
    }
}
