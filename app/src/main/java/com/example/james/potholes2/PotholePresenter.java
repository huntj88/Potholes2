package com.example.james.potholes2;

import android.util.Log;

import com.example.james.potholes2.retrofit.model.pothole.Pothole;
import com.example.james.potholes2.retrofit.remote.APIService;
import com.example.james.potholes2.retrofit.remote.ApiUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by James on 4/13/2017.
 */

public class PotholePresenter {

    // load data from realm.
    // in background do api call and update realm.
    // realm will auto update recyclerview

    private PotholeView view;
    private APIService apiService;
    private String TAG = "pothole presentor";
    private Realm realm;

    public PotholePresenter(PotholeView view, AuthModel authModel)
    {
        this.view = view;
        apiService = ApiUtils.getAPIService(authModel);
        realm = Realm.getDefaultInstance();
    }

    public Realm getRealm()
    {
        return realm;
    }

    public void closeRealm()
    {
        realm.close();
    }


    public void getAndSaveAllPotholesFromServer()
    {
        apiService.getAllPotholes().enqueue(new Callback<List<Pothole>>() {
            @Override
            public void onResponse(Call<List<Pothole>> call, final Response<List<Pothole>> response) {
                if(response.isSuccessful()) {
                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            for(Pothole p: response.body())
                            {
                                Pothole.create(realm,p);
                            }

                            Log.d(TAG,"retrofit get all response saved");
                        }
                    });
                }
                else
                {
                    Log.e(TAG, "retrofit code: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Pothole>> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void getPotholeByID(int potholeID)
    {

    }

    public interface PotholeView{
    }
}
