package com.edu.knowit.knowit.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edu.knowit.knowit.Models.HomeItemModel;
import com.edu.knowit.knowit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nuwan Deshapriya on 5/10/2018.
 */

public class HomeListAdapter extends ArrayAdapter<HomeItemModel> implements View.OnClickListener{

     private final String TAG="HomeListAdapter";

    private ArrayList<HomeItemModel> dataSet;//create array object list
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtId;
        TextView txtAuthor;
        ImageView imgAuthorImage;
        TextView txtDate;
        TextView txtTitle;
        ImageView imgImage;
        TextView txtDesc;
        LinearLayout imgLayout;
    }

    public HomeListAdapter(ArrayList<HomeItemModel> data, Context context) {
        super(context, R.layout.row_home_item, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        HomeItemModel dataModel=(HomeItemModel) object;
        System.out.println("call");
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        HomeItemModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        HomeListAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new HomeListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_home_item, parent, false);

            viewHolder.txtId = (TextView) convertView.findViewById(R.id.id);
            viewHolder.txtAuthor = (TextView) convertView.findViewById(R.id.name);
            viewHolder.imgAuthorImage = (ImageView) convertView.findViewById(R.id.authorImage);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.date);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.subject);
            viewHolder.imgImage = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.txtDesc = (TextView) convertView.findViewById(R.id.description);
            viewHolder.imgLayout = (LinearLayout) convertView.findViewById(R.id.imgLayout);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HomeListAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtId.setText(dataModel.getId());
        viewHolder.txtAuthor.setText(dataModel.getAuthor());
        viewHolder.txtDate.setText(dataModel.getDate());
        viewHolder.txtTitle.setText(dataModel.getTitle());
        viewHolder.txtDesc.setText(dataModel.getDescription());

        if(dataModel.getImage()!=null && !dataModel.getImage().isEmpty()){
            viewHolder.imgLayout.setVisibility(View.VISIBLE);
            Picasso.get().load(dataModel.getImage()).into(viewHolder.imgImage);
        }else{
            viewHolder.imgLayout.setVisibility(View.GONE);
        }

        if(dataModel.getAuthor_img()!=null && !dataModel.getAuthor_img().isEmpty()){
            Picasso.get().load(dataModel.getAuthor_img()).into(viewHolder.imgAuthorImage);
        }

        return convertView;
    }
}
