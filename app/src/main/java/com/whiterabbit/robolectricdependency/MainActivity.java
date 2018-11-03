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

package com.whiterabbit.robolectricdependency;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.whiterabbit.robolectricdependency.client.GitHubClient;
import com.whiterabbit.robolectricdependency.client.Repo;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class MainActivity extends AppCompatActivity {
    @Inject
    GitHubClient mClient;

    @BindView(R.id.first_repo)
    TextView mFirstRepo;

    @BindView(R.id.second_repo)
    TextView mSecondRepo;


    private List<Repo> repositories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ((RobolectricApplication) getApplication()).getComponent().inject(this);
        fillRepositories();
    }

    private void fillRepositories() {
        Call<List<Repo>> call = mClient.getRepos("fedepaol");
        call.enqueue(new Callback<List<Repo>>() {

            @Override
            public void onResponse(Response<List<Repo>> response) {
                List<Repo> result = response.body();
                if (result.size() > 0) {
                    mFirstRepo.setText(response.body().get(0).getName());
                }
                if (result.size() > 1) {
                    mSecondRepo.setText(response.body().get(1).getName());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // Do something smart.
                // Or run!
            }
        });
    }
}
