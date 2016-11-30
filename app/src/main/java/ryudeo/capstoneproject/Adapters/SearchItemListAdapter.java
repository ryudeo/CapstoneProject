package ryudeo.capstoneproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import ryudeo.capstoneproject.Database.FoodInfo;
import ryudeo.capstoneproject.R;

/**
 * Created by RYU on 2016. 11. 29..
 */

public class SearchItemListAdapter extends RecyclerView.Adapter<SearchItemListAdapter.SearchItemViewHolder>
{
    private Context mContext;
    private ArrayList<FoodInfo> mFoodInfoList;
    private ListItemClickCallback mListItemClickCallback;

    private int lastPosition = -1;

    public void setListItemClickCallback(ListItemClickCallback listItemClickCallback) {
        mListItemClickCallback = listItemClickCallback;
    }

    public void setFoodInfoList(ArrayList<FoodInfo> foodInfoList) {
        mFoodInfoList = foodInfoList;
    }

    public SearchItemListAdapter(ArrayList<FoodInfo> items, Context mContext)
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

    private void setAnimation(View viewToAnimate, int position)
    {
        // 새로 보여지는 뷰라면 애니메이션을 해줍니다
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public interface ListItemClickCallback {

        void onItemClick(int position);
    }

}
