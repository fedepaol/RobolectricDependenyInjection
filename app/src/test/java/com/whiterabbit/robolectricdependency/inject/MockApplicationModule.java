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

package com.whiterabbit.robolectricdependency.inject;

import android.app.Application;

import com.whiterabbit.robolectricdependency.client.GitHubClient;
import com.whiterabbit.robolectricdependency.client.Repo;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockApplicationModule extends ApplicationModule {
    List<Repo> result;

    public MockApplicationModule(Application app) {
        super(app);
    }

    @Override
    GitHubClient provideClient() {
        GitHubClient client = mock(GitHubClient.class);
        Call<List<Repo>> mockCall = mock(Call.class);

        doAnswer(
               new Answer<List<Repo>> () {
                    @Override
                    public List<Repo> answer(InvocationOnMock invocation) throws Throwable {
                        Response<List<Repo>> res = Response.success(result);
                        ((Callback)invocation.getArguments()[0]).onResponse( res );
                        return null;
                    }
                }
        ).when(mockCall).enqueue(any(Callback.class));

        when(client.getRepos(anyString())).thenReturn(mockCall);
        return client;
    }

    public void setResult(List<Repo> result) {
        this.result = result;
    }

}
