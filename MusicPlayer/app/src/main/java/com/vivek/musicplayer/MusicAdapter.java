package com.vivek.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private ArrayList<MusicDetails> musicList;      //data: the names displayed
    private RVClickListener RVlistener;         //listener defined in main activity
    private Boolean isLinearLayout;
    private Context context;


    //passing in the data and the listener defined in the main activity
    public MusicAdapter(ArrayList<MusicDetails> musicList, RVClickListener listener, Boolean isLinearLayout) {
        this.musicList = musicList;
        this.RVlistener = listener;
        this.isLinearLayout = isLinearLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        // inflate from music layout.
        LayoutInflater inflater = LayoutInflater.from(context);
        View listView = inflater.inflate(R.layout.music, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView, RVlistener);
        return viewHolder;
    }

    //method for binding data to holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.songName.setText(musicList.get(position).getSongName());
        holder.artistName.setText(musicList.get(position).getArtistName());
        holder.image.setImageResource(musicList.get(position).getImageId());
        holder.videoURL = musicList.get(position).getSongURL();
        holder.artistWikiURL = musicList.get(position).getArtistWiki();
        holder.songWikiURL = musicList.get(position).getSongWiki();
    }


    @Override
    public int getItemCount() {
        return musicList.size();
    }

    /*
        This class creates a wrapper object around a view that contains the layout for an individual item in the list.
        It also implements the onClickListener so each ViewHolder in the list is clickable.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {
        public TextView songName;
        public TextView artistName;
        public ImageView image;
        private RVClickListener listener;
        public String videoURL;
        public String songWikiURL;
        public String artistWikiURL;

        public ViewHolder(@NonNull View itemView, RVClickListener passedListener) {
            super(itemView);
            songName = itemView.findViewById(R.id.SongName);
            artistName = itemView.findViewById(R.id.ArtistName);
            image = itemView.findViewById(R.id.imageView);

            //set context menu for each list item (long click)
            itemView.setOnCreateContextMenuListener(this);
            this.listener = passedListener;

            //set short click listener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener == null) {
                return;
            }
            listener.onClick(v, getAdapterPosition());
            openURL(videoURL);
        }

        // method for inflating Context menu and options
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            //inflate menu from xml
            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.context_menu, menu);
            menu.getItem(0).setOnMenuItemClickListener(onMenu);
            menu.getItem(1).setOnMenuItemClickListener(onMenu);
            menu.getItem(2).setOnMenuItemClickListener(onMenu);
        }

        /*
            listener for menu items clicked
        */
        private final MenuItem.OnMenuItemClickListener onMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.toString().equals("Play Music")) {
                    openURL(videoURL);
                } else if (item.toString().equals("Show Song Wikipedia")) {
                    openURL(songWikiURL);
                } else if (item.toString().equals("Show Artist Wikipedia")) {
                    openURL(artistWikiURL);
                } else
                    return false;
                return true;
            }
        };

        // method for opening Url
        public void openURL(String videoURL) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoURL));
            context.startActivity(browserIntent);
        }
    }
}
