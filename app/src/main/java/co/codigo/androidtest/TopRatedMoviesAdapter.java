package co.codigo.androidtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.util.List;

public class TopRatedMoviesAdapter extends RecyclerView.Adapter<TopRatedMoviesAdapter.ViewHolder>{

    private RequestManager glide;
    private List<TopRatedMoviesResult> topRatedMovies;
    private String imageBaseUrl;
    private String imageFileSize;
    private int titleColor;
    private int overviewColor;

    public TopRatedMoviesAdapter(RequestManager glide, List<TopRatedMoviesResult> topRatedMovies,
                                 String imageBaseUrl, String imageFileSize,
                                 int titleColor, int overviewColor) {
        this.glide = glide;
        this.topRatedMovies = topRatedMovies;
        this.imageBaseUrl = imageBaseUrl;
        this.imageFileSize = imageFileSize;
        this.titleColor = titleColor;
        this.overviewColor = overviewColor;
    }

    @NonNull
    @Override
    public TopRatedMoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedMoviesAdapter.ViewHolder holder, int position) {
        glide.load(imageBaseUrl + imageFileSize + topRatedMovies.get(position).getPoster_path())
                .into(holder.iv);

        holder.tvTitle.setText(topRatedMovies.get(position).getOriginal_title());
        holder.tvOverview.setText(topRatedMovies.get(position).getOverview());

        holder.tvTitle.setTextColor(titleColor);
        holder.tvOverview.setTextColor(overviewColor);
    }

    @Override
    public int getItemCount() {
        if (topRatedMovies == null) {
            return 0;
        } else {
            return topRatedMovies.size();
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tvTitle;
        private TextView tvOverview;

        public ViewHolder(View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
        }
    }
}
