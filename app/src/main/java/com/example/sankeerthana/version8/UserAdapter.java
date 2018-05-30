package com.example.sankeerthana.version8;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<Dump> dumpList;

    public UserAdapter(List<Dump> dumpList) {
        this.dumpList = dumpList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewMailId.setText(dumpList.get(position).getMailId());
        holder.textViewBillEP1.setText(dumpList.get(position).getBill_ep1());
        holder.textViewBillEP2.setText(dumpList.get(position).getBill_ep2());
        holder.textViewBillEP3.setText(dumpList.get(position).getBill_ep3());
        holder.textViewBillFlat.setText(dumpList.get(position).getBill_flat());
        holder.textViewUsageEP1.setText(dumpList.get(position).getUsage_ep1());
        holder.textViewUsageEP2.setText(dumpList.get(position).getUsage_ep2());
        holder.textViewUsageEP3.setText(dumpList.get(position).getUsage_ep3());
        holder.textViewUsageFlat.setText(dumpList.get(position).getUsage_flat());



    }

    @Override
    public int getItemCount() {
        Log.v(UserAdapter.class.getSimpleName(),""+dumpList.size());
        return dumpList.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewMailId;
        public AppCompatTextView textViewBillEP1;
        public AppCompatTextView textViewBillEP2;
        public AppCompatTextView textViewBillEP3;
        public AppCompatTextView textViewBillFlat;
        public AppCompatTextView textViewUsageEP1;
        public AppCompatTextView textViewUsageEP2;
        public AppCompatTextView textViewUsageEP3;
        public AppCompatTextView textViewUsageFlat;


        public UserViewHolder(View view) {
            super(view);
            textViewMailId = (AppCompatTextView) view.findViewById(R.id.textViewMailId);
            textViewBillEP1 = (AppCompatTextView) view.findViewById(R.id.textViewBillEP1);
            textViewBillEP2 = (AppCompatTextView) view.findViewById(R.id.textViewBillEP2);
            textViewBillEP3 = (AppCompatTextView) view.findViewById(R.id.textViewBillEP3);
            textViewBillFlat = (AppCompatTextView) view.findViewById(R.id.textViewBillFlat);
            textViewUsageEP1 = (AppCompatTextView) view.findViewById(R.id.textViewUsageEP1);
            textViewUsageEP2 = (AppCompatTextView) view.findViewById(R.id.textViewUsageEP2);
            textViewUsageEP3 = (AppCompatTextView) view.findViewById(R.id.textViewUsageEP3);
            textViewUsageFlat = (AppCompatTextView) view.findViewById(R.id.textViewUsageFlat);
        }
    }

}
