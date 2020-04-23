package com.fufufu.discom.ui.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.fufufu.discom.R;
import com.fufufu.discom.data.model.Review;

import java.util.Objects;

public class ReviewPagedAdapter extends PagedListAdapter<Review, ReviewPagedAdapter.ViewHolder> {
    ReviewPagedAdapter() {
        super(Review.DIFF_CALLBACK);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_detail_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(getItem(position)!= null){
            holder.tvAuthor.setText(Objects.requireNonNull(getItem(position)).getAuthor());
            holder.tvContent.setText(Objects.requireNonNull(getItem(position)).getContent());
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAuthor;
        private TextView tvContent;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tv_review_author_value);
            tvContent = itemView.findViewById(R.id.tv_review_content_value);
        }
    }

}
