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
    private ArrayList<FoodInfo> mFoodInfoListResult;

    private int lastPosition = -1;

    public SearchItemListAdapter(ArrayList<FoodInfo> items, Context mContext)
    {
        this.mContext = mContext;
        this.mFoodInfoListResult = items;
        mFoodInfoList = new ArrayList<>();
        mFoodInfoList.addAll(mFoodInfoListResult);
    }

    @Override
    public SearchItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(mContext).inflate(R.layout.search_list_item, parent, false);
        return new SearchItemViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(SearchItemViewHolder holder, int position) {


        FoodInfo foodInfo = mFoodInfoList.get(position);

        holder.cardTitleTextView.setText(foodInfo.getName());
        holder.cardDetailsTextView.setText(Integer.toString(foodInfo.getQuantity()));
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

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mFoodInfoListResult.clear();
        if (charText.length() == 0) {
            mFoodInfoListResult.addAll(mFoodInfoList);
        } else {
            for (FoodInfo foodInfo : mFoodInfoList) {
                if (foodInfo.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mFoodInfoListResult.add(foodInfo);
                }
            }
        }
        notifyDataSetChanged();
    }


}
