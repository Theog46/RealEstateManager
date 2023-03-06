package com.openclassrooms.realestatemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.Model.Agents;

import java.util.ArrayList;

public class AgentAdapter extends ArrayAdapter<Agents> {

    private static class ViewHolder {
        TextView name;
        ImageView img;
    }

    public AgentAdapter(Context context, ArrayList<Agents> agents) {
        super(context, R.layout.list_agent, agents);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Agents agents = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_agent, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.agent_name_list);
            holder.img = (ImageView) convertView.findViewById(R.id.agent_img_list);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(agents.getName());
        Glide.with(convertView)
                .load(agents.getPhoto())
                .circleCrop()
                .into(holder.img);

        return convertView;
    }
}
