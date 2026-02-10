package adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import DataBase.Notification_Favorite_Appoint_DB;
import activities.Favorite;
import models.Tips_Model;

public class Tips_Adapter extends RecyclerView.Adapter<Tips_Adapter.ViewHolderClass> {

    ArrayList<Tips_Model> arrayList;
    Activity activity;
    Notification_Favorite_Appoint_DB db;

    public Tips_Adapter(Activity activity, ArrayList<Tips_Model> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Tips_Adapter.ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.tips_item, parent, false);
        return new ViewHolderClass(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull Tips_Adapter.ViewHolderClass holder, int position) {
        // الحصول على البيانات
        Tips_Model currentItem = arrayList.get(position);
        holder.tv_somking.setText(currentItem.getTitle());
        Glide.with(holder.itemView.getContext())
                .load(currentItem.getImage())
                .into(holder.iv_tips1);
//        holder.iv_tips1.setImageResource(Integer.parseInt(currentItem.getImage()));
        db = new Notification_Favorite_Appoint_DB(activity);

        if (db.checkIfTitle(currentItem.getTitle(),currentItem.getImage())) {
            holder.iv_heart.setImageResource(R.drawable.heartred);
        } else {
            holder.iv_heart.setImageResource(R.drawable.heart);
        }

        holder.iv_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db.checkIfTitle(currentItem.getTitle(),currentItem.getImage())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle("Delete from favorite?");
                    builder.setPositiveButton("DELETE", (dialog, which) -> {
                        boolean isDeleted = db.deleteFavorite(currentItem.getTitle());
                        if (isDeleted) {
                            holder.iv_heart.setImageResource(R.drawable.heart);
                            if (activity instanceof Favorite) {
                                arrayList.remove(position);
                                notifyDataSetChanged();
                            }
                            Toast.makeText(activity, "تم الحذف بنجاح", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("CANCEL", null);
                    builder.show();
                } else {
                    db.insertFavorite(currentItem.getTitle(), currentItem.getImage());                    holder.iv_heart.setImageResource(R.drawable.heartred);
                    Toast.makeText(activity, "تمت الإضافة ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class ViewHolderClass extends RecyclerView.ViewHolder {
             ImageView iv_tips1,iv_heart;
             TextView tv_somking;

            public ViewHolderClass(@NonNull View itemView) {
                super(itemView);
                tv_somking = itemView.findViewById(R.id.tv_somking);
                iv_tips1 = itemView.findViewById(R.id.iv_tips1);
                iv_heart = itemView.findViewById(R.id.iv_heart);
            }
        }
    }
