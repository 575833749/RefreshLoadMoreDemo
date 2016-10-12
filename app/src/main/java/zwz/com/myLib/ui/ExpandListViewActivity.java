package zwz.com.myLib.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zwz.com.myLib.R;
import zwz.com.myLib.view.pullableview.PullToRefreshLayout;
import zwz.com.myLib.view.pullableview.PullableExpandableListView;

public class ExpandListViewActivity extends AppCompatActivity {

    private PullToRefreshLayout mPullToRefreshLayout;
    private PullableExpandableListView mPullableExpandableListView;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    refresh();
                    //SUCCESS刷新成功  FAIL刷新失败
                    mPullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    break;
                case 2:
                    loadMore();
                    //加载成功 SUCCEED   加载失败FAIL
                    mPullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    break;
            }
        }
    };
    private List<String> mDatas;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_list_view);
        mPullableExpandableListView= (PullableExpandableListView) findViewById(R.id.exListView);
        mPullToRefreshLayout= (PullToRefreshLayout) findViewById(R.id.refresh_view);

        mPullableExpandableListView.isCanPullUp(true);

        mPullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                //TODO 刷新
                mHandler.sendEmptyMessageDelayed(1,3000);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                //TODO 加载更多
                mHandler.sendEmptyMessageDelayed(2,3000);
            }
        });

        initData();
    }



    private void initData(){
        mDatas=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDatas.add("分组"+(i+1));
        }

        mAdapter = new MyAdapter(mDatas);
        mPullableExpandableListView.setAdapter(mAdapter);
    }

    private void refresh(){
        mDatas.clear();
        for (int i = 0; i < 20; i++) {
            mDatas.add("分组"+(i+1));
        }
        mAdapter.notifyDataSetChanged();
    }

    private void loadMore(){
        int size = mDatas.size();
        for (int i = size; i < size+10; i++) {
            mDatas.add("分组"+(i+1));
        }
        mAdapter.notifyDataSetChanged();
    }


    class MyAdapter extends BaseExpandableListAdapter{
        List<String> datas;
        LayoutInflater mInflater;


        public MyAdapter(List<String> datas) {
            this.datas=datas;
            mInflater=LayoutInflater.from(ExpandListViewActivity.this);
        }


        @Override
        public int getGroupCount() {
            return datas.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 10;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }




        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            GViewHolder holder;
            if (convertView==null){
                convertView=mInflater.inflate(R.layout.group,null);
                holder=new GViewHolder(convertView);
            }else{
                holder = (GViewHolder) convertView.getTag();
            }

            holder.mTextView.setText(datas.get(groupPosition));


            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=mInflater.inflate(R.layout.item,null);
            }
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }


        class GViewHolder{
            TextView mTextView;
            public GViewHolder(View view) {
                mTextView= (TextView) view.findViewById(R.id.tvGroup);
                view.setTag(this);
            }
        }
    }
}
