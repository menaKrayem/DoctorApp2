package adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorapp.R;

import java.util.ArrayList;

import models.Specialization_Model;
//فصل 2
public class Specialization_Adapter extends RecyclerView.Adapter<Specialization_Adapter.ViewHolderClass>{
    ArrayList<Specialization_Model> arrayList;
    Activity activity;


    public Specialization_Adapter(Activity activity, ArrayList<Specialization_Model> arrayList){
        this.activity = activity;
        this.arrayList = arrayList;
    }
    
    @NonNull
    @Override
    public Specialization_Adapter.ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.specialization_item,parent,false);
        return  new ViewHolderClass(viewItem);

    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Specialization_Adapter.ViewHolderClass holder, int position) {
        // 2. ربط بيانات المودل بالعناصر

        holder.tv_spec_brain.setText(arrayList.get(position).getName()+"");
        holder.iv_spec_brain.setImageResource(arrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder{
        public ImageView iv_spec_brain;
        public TextView tv_spec_brain;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            iv_spec_brain =itemView.findViewById(R.id.iv_spec_brain);
            tv_spec_brain =itemView.findViewById(R.id.tv_spec_brain);

        }
    }
}
