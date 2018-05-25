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
        holder.textViewEP1.setText(dumpList.get(position).getEp1());
        holder.textViewEP2.setText(dumpList.get(position).getEp2());

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
        public AppCompatTextView textViewEP1;
        public AppCompatTextView textViewEP2;


        public UserViewHolder(View view) {
            super(view);
            textViewMailId = (AppCompatTextView) view.findViewById(R.id.textViewMailId);
            textViewEP1 = (AppCompatTextView) view.findViewById(R.id.textViewEP1);
            textViewEP2 = (AppCompatTextView) view.findViewById(R.id.textViewEP2);
        }
    }

}
