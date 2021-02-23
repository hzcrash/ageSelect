package com.android.wheelselect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wu on 2015/12/17.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.AgeItemViewHolder> {


    private Context mContext;
    /**
     * 起始年龄
     */
    private int startNum;
    /**
     * 终止年龄
     */
    private int endNum;
    /**
     * 显示的数据个数
     */
    public int ITEM_NUM = 21;



    public MyAdapter(Context context, int startNum, int endNum) {
        mContext = context;
        this.startNum = startNum;
        this.endNum = endNum;
    }

    @Override
    public AgeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_age_item, parent, false);
        //设置宽度
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width =(int)getItemWidth();
        return new AgeItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AgeItemViewHolder holder, int position) {
        holder.mTextView.setText(String.valueOf(startNum + position));
        if ((startNum + position) % 10 == 8) {
            holder.mTextView.setVisibility(View.VISIBLE);
            holder.mBarIndicator.getLayoutParams().height = 30;
        } else {
            holder.mTextView.setVisibility(View.GONE);
            holder.mBarIndicator.getLayoutParams().height = 20;
            ((LinearLayout.LayoutParams)(holder.mBarIndicator.getLayoutParams())).bottomMargin = 10;
        }

        holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.unselected));

    }

    /**
     *
     * 高亮中心, 更新前后位置
     * @param position 在list中的位置
     */
    public void highlightItem(int position) {
        int offset = ITEM_NUM / 2;
        for (int i = position - offset; i <= position + offset; ++i)
            notifyItemChanged(i);
    }



    @Override
    public int getItemCount() {
        return endNum - startNum + 1;
    }

    /**
     * 获取每一个条目的宽度
     *
     * @return
     */
    public float getItemWidth() {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels / ITEM_NUM;
    }


    /**
     * ViewHolder类
     */
    public class AgeItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        private View mBarIndicator;

        public AgeItemViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item_age);
            mBarIndicator = itemView.findViewById(R.id.vertical_bar);
            mTextView.setTag(this);
        }

        public TextView getmTextView() {
            return mTextView;
        }
    }
}
