package zwz.com.myLib.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zwz.com.myLib.R;
import zwz.com.myLib.view.xrecyclerview.BaseRefreshHeader;
import zwz.com.myLib.view.xrecyclerview.LoadingMoreFooter;
import zwz.com.myLib.view.xrecyclerview.XRecyclerView;

public class RecyclerViewActivity extends AppCompatActivity {

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    refresh();
                    //STATE_REFRESH_SUCCESS刷新成功  STATE_REFRESH_FAIL刷新失败
                    mRecyclerView.refreshComplete(BaseRefreshHeader.STATE_REFRESH_SUCCESS);
                    break;
                case 2:
                    loadMore();
                    //加载成功 STATE_COMPLETE   加载失败STATE_REFRESH_FAIL
                    mRecyclerView.loadMoreComplete(LoadingMoreFooter.STATE_COMPLETE);
                    break;
            }
        }
    };
    private XRecyclerView mRecyclerView;
    private List<String>mDatas;
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (XRecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //TODO 刷新
                mHandler.sendEmptyMessageDelayed(1,3000);
            }

            @Override
            public void onLoadMore() {

                //TODO 加载更多
                mHandler.sendEmptyMessageDelayed(2,3000);
            }
        });
        initData();
    }


    private void initData(){
        mDatas=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDatas.add("数据"+(i+1));
        }

        mAdapter = new MyAdapter(mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void refresh(){
        mDatas.clear();
        for (int i = 0; i < 10; i++) {
            mDatas.add("数据"+(i+1));
        }
        mAdapter.notifyDataSetChanged();
    }

    private void loadMore(){
        int size = mDatas.size();
        for (int i = size; i < size+10; i++) {
            mDatas.add("数据"+(i+1));
        }
        mAdapter.notifyDataSetChanged();
    }


    class MyAdapter extends RecyclerView.Adapter{
        private List<String>datas;
        public MyAdapter(List<String> datas) {
            this.datas=datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(RecyclerViewActivity.this).inflate(R.layout.item,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            ViewHolder h = (ViewHolder) holder;
            h.mTextView.setText(datas.get(position));

        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView mTextView;
            public ViewHolder(View itemView) {
                super(itemView);
                mTextView= (TextView) itemView.findViewById(R.id.tv);
            }
        }
    }
}
