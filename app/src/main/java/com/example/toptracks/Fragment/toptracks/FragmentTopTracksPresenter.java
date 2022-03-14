package com.example.toptracks.Fragment.toptracks;

import android.util.Log;
import android.widget.Toast;

import com.example.toptracks.Model.Artist;
import com.example.toptracks.Model.Attr;
import com.example.toptracks.Model.Example;
import com.example.toptracks.Model.Image;
import com.example.toptracks.Model.Music;
import com.example.toptracks.Model.Toptracks;
import com.example.toptracks.Model.Track;
import com.example.toptracks.Service.APIClient;
import com.example.toptracks.Service.MusicAPI;
import com.example.toptracks.base.BasePresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTopTracksPresenter extends BasePresenter<TopTrackIterator.TopTrackView> implements TopTrackIterator.TopTrackPresenter {
    String api_key ="4bae3a6d607a824a4eb8dc9455402d76";
    int limit = 0;
    @Override
    public void fetchTopTracks(boolean isSwipeRefesh) {
        ArrayList<Music> musicList = new ArrayList<>();
        MusicAPI musicAPI = APIClient.getAPIClient().create(MusicAPI.class);
        Log.d("limit", "limit: "+limit);
        if (isSwipeRefesh){
            limit = 5;
        } else {
            limit += 5;
        }
        if (limit >= 50){
            limit = 50;
        }
        Call<Example> exampleCall = musicAPI.getTrack(api_key,limit);
        exampleCall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (!response.isSuccessful()) {
                    getMvpView().onFailed(response.message());
                    return;
                }

                Example myData = response.body();

                Toptracks toptracks = myData.getToptracks();

                if (toptracks.getTrack().isEmpty() || toptracks.getTrack() == null) {
                    return;
                }
                for (int i = 0; i < toptracks.getTrack().size() ; i++){
                    Track track = toptracks.getTrack().get(i);
                    Attr attr = track.getAttr();
                    Artist artist = track.getArtist();
                    Image image = track.getImage().get(3);
                    Music music = new Music(track.getName(),artist.getName(),attr.getRank(),track.getListeners(),image.getText());
                    musicList.add(music);
                }
                getMvpView().onFetchSuccess(musicList);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getMvpView().onFailed(t.getLocalizedMessage());
            }
        });
    }

    public void addProgessBar(){
        getMvpView().onProgessbar();
    }

    @Override
    public void addSwipeRefesh() {
        getMvpView().onSwipeRefesh();
    }
}
