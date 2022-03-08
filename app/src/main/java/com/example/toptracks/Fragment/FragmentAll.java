package com.example.toptracks.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.toptracks.Adapter.MusicAdapter;
import com.example.toptracks.Listener.MusicInterface;
import com.example.toptracks.Model.Artist;
import com.example.toptracks.Model.Attr;
import com.example.toptracks.Model.Example;
import com.example.toptracks.Model.Image;
import com.example.toptracks.Model.Music;
import com.example.toptracks.Model.Toptracks;
import com.example.toptracks.Model.Track;
import com.example.toptracks.Presenter.MusicPresenter;
import com.example.toptracks.R;
import com.example.toptracks.Service.MusicAPI;
import com.example.toptracks.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentAll extends Fragment implements MusicInterface {

    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;
    private List<Music> musicList = new ArrayList<>();
    private List<Track> trackList = new ArrayList<>();
    private List<Image> imageList = new ArrayList<>();
    private String url = "https://ws.audioscrobbler.com/2.0/";
    private String api_key = "4bae3a6d607a824a4eb8dc9455402d76";

    private MusicPresenter musicPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        recyclerView = view.findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        getMusicList();
        musicPresenter = new MusicPresenter(this);
        musicPresenter.getMusicList();


        return view;
    }

//    private void getMusicList(){
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        MusicAPI musicAPI = retrofit.create(MusicAPI.class);
//        //https://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist=cher&api_key=4bae3a6d607a824a4eb8dc9455402d76&format=json
//        Call<Example> exampleCall = musicAPI.getTrack(api_key);
//        exampleCall.enqueue(new Callback<Example>() {
//            @Override
//            public void onResponse(Call<Example> call, Response<Example> response) {
//                if(response.code()==404){
//                    Toast.makeText(getContext(),"Please Enter a valid City",Toast.LENGTH_LONG).show();
//                }
//                else if(!(response.isSuccessful())){
//                    Toast.makeText(getContext(),response.code()+" ",Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                Example myData = response.body();
//
//                Toptracks toptracks = myData.getToptracks();
////                trackList.addAll(toptracks.getTrack());
//                trackList = toptracks.getTrack();
//                for (int i = 0; i < trackList.size() ; i++){
//                    Track track = trackList.get(i);
//                    Attr attr = track.getAttr();
//                    Artist artist = track.getArtist();
//                    imageList.addAll(track.getImage());
//                    Image image = imageList.get(imageList.size()-1);
//                    Music music = new Music(track.getName(),artist.getName(),attr.getRank(),track.getListeners(),image.getText());
//                    musicList.add(music);
//                }
//                musicAdapter = new MusicAdapter(getContext(),musicList);
//                recyclerView.setAdapter(musicAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<Example> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public void onFetchSuccess(List<Music> musicList) {
        musicAdapter = new MusicAdapter(getContext(),musicList);
        recyclerView.setAdapter(musicAdapter);
    }

    @Override
    public void onFetchFault(Exception e) {

    }
}