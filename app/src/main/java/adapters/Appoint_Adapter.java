package adapters;

import static com.example.doctorapp.R.*;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorapp.R;

import java.util.ArrayList;

import DataBase.Notification_Favorite_Appoint_DB;
import models.Appoint_Model;
import models.Notification_Model;

public class Appoint_Adapter extends RecyclerView.Adapter<Appoint_Adapter.ViewHolderClass>{

    ArrayList<Appoint_Model> arrayList;
    Activity activity;
    Notification_Favorite_Appoint_DB db;


    public Appoint_Adapter(Activity activity, ArrayList<Appoint_Model> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Appoint_Adapter.ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.appoint_item, parent, false);
        return new Appoint_Adapter.ViewHolderClass(viewItem);

    }

    @Override
    public void onBindViewHolder(@NonNull Appoint_Adapter.ViewHolderClass holder, int position) {
        Appoint_Model appoint = arrayList.get(position);
        holder.tv_doctorName.setText(appoint.getName());
        holder.tv_specializeName.setText(appoint.getSpecialize());
        holder.tv_datetext.setText(appoint.getDate());
        holder.tv_timetext.setText(appoint.getTime());
        holder.tv_statetext.setText(appoint.getState());
        holder.iv_doctorimg.setImageResource(appoint.getImage());
        db = new Notification_Favorite_Appoint_DB(activity);

        if (appoint.getState().equals("Cancelled")) {
            holder.btn_cancle.setVisibility(View.GONE);
        } else {
            holder.btn_cancle.setVisibility(View.VISIBLE);
        }
         holder.btn_cancle.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
                 boolean success = db.canclAppoint(appoint.getId());
                 if (success) {
                     holder.btn_cancle.setBackgroundColor(Color.GRAY);
                     arrayList.remove(holder.getAdapterPosition());
                     notifyItemRemoved(holder.getAdapterPosition());
                     Toast.makeText(activity,"تم الغاء الموعد ", Toast.LENGTH_SHORT).show();
                 }
             }
         });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView tv_doctorName,tv_specializeName,tv_datetext,tv_timetext,tv_statetext;
        ImageView iv_doctorimg;
        Button btn_cancle;
        public ViewHolderClass( @NonNull View itemView) {
            super(itemView);

            tv_doctorName = itemView .findViewById(R.id.tv_doctorName);
            tv_specializeName = itemView .findViewById(R.id.tv_specializeName);
            tv_datetext = itemView .findViewById(R.id.tv_datetext);
            tv_timetext = itemView .findViewById(R.id.tv_timetext);
            tv_statetext = itemView .findViewById(R.id.tv_statetext);
            iv_doctorimg = itemView.findViewById(R.id.iv_doctorimg);
            btn_cancle = itemView.findViewById(R.id.btn_cancle);

        }
    }
}

