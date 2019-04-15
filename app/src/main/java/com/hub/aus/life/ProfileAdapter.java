package com.hub.aus.life;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private Context mCtx;
    private List<Profile> profileList;

    public ProfileAdapter(Context mCtx, List<Profile> profileList) {
        this.mCtx = mCtx;
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.activity_profile_adapter, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Profile profile = profileList.get(position);

        holder.textViewName.setText(profile.getName());
        holder.textViewBrand.setText(profile.getString1());
        holder.textViewDesc.setText(profile.getString2());
        holder.textViewPrice.setText(profile.getBatch());
        holder.textViewQty.setText(profile.getMobile());
        holder.textViewQty2.setText(profile.getAddress());
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewBrand, textViewDesc, textViewPrice, textViewQty,textViewQty2;

        public ProfileViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textview_name);
            textViewBrand = itemView.findViewById(R.id.textview_brand);
            textViewDesc = itemView.findViewById(R.id.textview_desc);
            textViewPrice = itemView.findViewById(R.id.textview_price);
            textViewQty = itemView.findViewById(R.id.textview_quantity);
            textViewQty2 = itemView.findViewById(R.id.textview_quantity2);

        }
    }
}