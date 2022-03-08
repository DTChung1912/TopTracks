package com.example.toptracks.Fragment.toptracks;

import com.example.toptracks.Model.Artist;
import com.example.toptracks.Model.Attr;
import com.example.toptracks.Model.Example;
import com.example.toptracks.Model.Image;
import com.example.toptracks.Model.Music;
import com.example.toptracks.Model.Toptracks;
import com.example.toptracks.Model.Track;
import com.example.toptracks.Service.MusicAPI;
import com.example.toptracks.base.BasePresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentTopTracksPresenter extends BasePresenter<TopTrackIterator.TopTrackView> implements TopTrackIterator.TopTrackPresenter {

    @Override
    public void fetchTopTracks() {

        ArrayList<Music> musicList = new ArrayList<>();
        ArrayList<Image> imageList = new ArrayList<>();
        String url = "https://ws.audioscrobbler.com/2.0/";
        String api_key ="4bae3a6d607a824a4eb8dc9455402d76";

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MusicAPI musicAPI = retrofit.create(MusicAPI.class);
        Call<Example> exampleCall = musicAPI.getTrack(api_key);
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
                    getMvpView().onFailed("Ko có data");
                    return;
                }

                for (int i = 0; i < toptracks.getTrack().size() ; i++){
                    Track track = toptracks.getTrack().get(i);
                    Attr attr = track.getAttr();
                    Artist artist = track.getArtist();
                    imageList.addAll(track.getImage());
                    Image image = imageList.get(imageList.size()-1);
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
}
