package com.lavinapatel.rxjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lavinapatel.rxjava.R;
import com.lavinapatel.rxjava.models.Post;

import java.util.ArrayList;

/**
 * Created by lavina on 15-Jan-17.
 */
public class PostsAdapter extends ArrayAdapter<Post>{

    public PostsAdapter(Context context, ArrayList<Post> postsList) {
        super(context,0, postsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Post post = getItem(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_post_item, parent, false);

        TextView title = (TextView) convertView.findViewById(R.id.textViewItemTitle);
        title.setText(post.title);

        return convertView;
    }
}
