package ryudeo.capstoneproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ryudeo.capstoneproject.Database.ExerInfo;
import ryudeo.capstoneproject.R;

/**
 * Created by RYU on 2016. 12. 1..
 */

public class ExerSearchItemListAdapter extends RecyclerView.Adapter<ExerSearchItemListAdapter.SearchItemViewHolder>{

    private Context mContext;
    private ArrayList<ExerInfo> mExerInfoList;
    private ExerSearchItemListAdapter.ListItemClickCallback mListItemClickCallback;

//    private int lastPosition = -1;

    public void setListItemClickCallback(ExerSearchItemListAdapter.ListItemClickCallback listItemClickCallback) {
        mListItemClickCallback = listItemClickCallback;
    }

    public void setExerInfoList(ArrayList<ExerInfo> exerInfoList) {
        mExerInfoList = exerInfoList;
    }

    public ExerSearchItemListAdapter(ArrayList<ExerInfo> items, Context mContext) {
        this.mContext = mContext;
        this.mExerInfoList = items;
    }

    public ExerSearchItemListAdapter.SearchItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(mContext).inflate(R.layout.search_list_item, parent, false);
        return new ExerSearchItemListAdapter.SearchItemViewHolder(rootView);
    }

    public void onBindViewHolder(ExerSearchItemListAdapter.SearchItemViewHolder holder, int position) {


        ExerInfo exerInfo = mExerInfoList.get(position);

        holder.cardTitleTextView.setText(exerInfo.getName());
        holder.cardDetailsTextView.setText(String.valueOf(exerInfo.getQuantity()) + "(" + "Kcal/30min" + ")");
    }

    public int getItemCount() {
        return mExerInfoList.size();
    }

    public class SearchItemViewHolder extends RecyclerView.ViewHolder {

        public TextView cardTitleTextView;
        public TextView cardDetailsTextView;

        public SearchItemViewHolder(View view) {
            super(view);
            cardTitleTextView = (TextView)view.findViewById(R.id.card_title);
            cardDetailsTextView = (TextView)view.findViewById(R.id.card_details);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mListItemClickCallback != null) {
                        mListItemClickCallback.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface ListItemClickCallback {

        void onItemClick(int position);
    }
}
