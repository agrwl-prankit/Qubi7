package com.prankit.qubi7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prankit.qubi7.model.GetUserModel;

import java.util.List;

public class ShowUserAdapter extends RecyclerView.Adapter<ShowUserAdapter.ViewHolder>{

    private Context context;
    private List<GetUserModel.Datum> userList;

    public ShowUserAdapter(Context context, List<GetUserModel.Datum> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_user_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(userList.get(position).getId().toString());
        holder.fName.setText(userList.get(position).getFirst_name());
        holder.lName.setText(userList.get(position).getLast_name());
        holder.email.setText(userList.get(position).getEmail());
        Glide.with(context).load(userList.get(position).getAvatar()).placeholder(R.drawable.icon_no_profile).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView fName, lName, email, id;
        ImageView avatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fName = itemView.findViewById(R.id.user_first_name);
            lName = itemView.findViewById(R.id.user_last_name);
            email = itemView.findViewById(R.id.user_email);
            id = itemView.findViewById(R.id.user_id);
            avatar = itemView.findViewById(R.id.user_avatar);
        }
    }
}
