package com.example.udacity_sixth_app;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsClass> {

    public NewsAdapter(Context context, List<NewsClass> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) { listItemView = LayoutInflater.from(getContext()).
                    inflate(R.layout.list_item, parent, false); }

        NewsClass newsClass = (NewsClass) getItem(position);

        TextView titleView = listItemView.findViewById(R.id.title);
        titleView.setText(newsClass.getMtitle());

        TextView sectionView = listItemView.findViewById(R.id.section);
        sectionView.setText(newsClass.getMsection());

        TextView authorView = listItemView.findViewById(R.id.author);
        authorView.setText(newsClass.getMauthor());

        TextView dateView = listItemView.findViewById(R.id.date);
        dateView.setText(newsClass.getMdate());

        return listItemView;
    }
}
