package adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorapp.R;

import java.util.ArrayList;

import DataBase.Notification_Favorite_Appoint_DB;
import models.Notification_Model;

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.ViewHolderClass> {
    ArrayList<Notification_Model> arrayList;
    Activity activity;
    Notification_Favorite_Appoint_DB db;

    public Notification_Adapter(Activity activity, ArrayList<Notification_Model> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public Notification_Adapter.ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new ViewHolderClass(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull Notification_Adapter.ViewHolderClass holder, int position) {
    Notification_Model noti = arrayList.get(position);
        holder.tv_notif_title.setText(noti.getTitle());
        holder.tv_notif_desc.setText(noti.getDescription());
        holder.tv_notif_time.setText(noti.getTime());
        db= new Notification_Favorite_Appoint_DB(activity);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView tv_notif_title , tv_notif_desc ,tv_notif_time;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tv_notif_title = itemView.findViewById(R.id.tv_notif_title);
            tv_notif_desc = itemView.findViewById(R.id.tv_notif_desc);
            tv_notif_time = itemView.findViewById(R.id.tv_notif_time);

        }
    }
}

