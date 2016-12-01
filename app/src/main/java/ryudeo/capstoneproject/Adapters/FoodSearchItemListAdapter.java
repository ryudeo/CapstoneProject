package ryudeo.capstoneproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ryudeo.capstoneproject.Database.FoodInfo;
import ryudeo.capstoneproject.R;

/**
 * Created by RYU on 2016. 11. 29..
 */

public class FoodSearchItemListAdapter extends RecyclerView.Adapter<FoodSearchItemListAdapter.SearchItemViewHolder>
{
    private Context mContext;
    private ArrayList<FoodInfo> mFoodInfoList;
    private ListItemClickCallback mListItemClickCallback;

    public void setListItemClickCallback(ListItemClickCallback listItemClickCallback) {
        mListItemClickCallback = listItemClickCallback;
    }

    public void setFoodInfoList(ArrayList<FoodInfo> foodInfoList) {
        mFoodInfoList = foodInfoList;
    }

    public FoodSearchItemListAdapter(ArrayList<FoodInfo> items, Context mContext)
    {
        this.mContext = mContext;
        this.mFoodInfoList = items;
    }

    @Override
    public SearchItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(mContext).inflate(R.layout.search_list_item, parent, false);
        return new SearchItemViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(SearchItemViewHolder holder, int position) {


        FoodInfo foodInfo = mFoodInfoList.get(position);

        holder.cardTitleTextView.setText(foodInfo.getName() + " (" + foodInfo.getEa() + ")");
        holder.cardDetailsTextView.setText(foodInfo.getKcal());
    }

    @Override
    public int getItemCount() {
        return mFoodInfoList.size();
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
