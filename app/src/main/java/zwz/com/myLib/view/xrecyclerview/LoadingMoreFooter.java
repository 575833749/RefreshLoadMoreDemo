package zwz.com.myLib.view.xrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;

import zwz.com.myLib.R;

import static zwz.com.myLib.R.id.progressBar;
import static zwz.com.myLib.R.string.load_error;


public class LoadingMoreFooter extends LinearLayout {

    private ProgressBarCircularIndeterminate progressCon;
    private Context mContext;
    /**
     * 正在加载更多
     */
    public final static int STATE_LOADING = 0;
    /**
     * 加载完成
     */
    public final static int STATE_COMPLETE = 1;
    /**
     * 没有更多数据
     */
    public final static int STATE_NOMORE = 2;
    /**
     * 加载失败
     */
    public final static int STATE_FAIL = 3;
    private TextView mText;
    private int currentState;
    private XRecyclerView xRecyclerView;
	public LoadingMoreFooter(Context context, XRecyclerView xRecyclerView) {
		super(context);
        this.xRecyclerView=xRecyclerView;
		initView(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public LoadingMoreFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
    public void initView(Context context ){
        currentState=STATE_COMPLETE;
        mContext = context;
        View footerView= LayoutInflater.from(context).inflate(R.layout.listview_footer,null);

        setGravity(Gravity.CENTER);
        progressCon = (ProgressBarCircularIndeterminate) footerView.findViewById(progressBar);
        mText= (TextView) footerView.findViewById(R.id.text_footer_view);
        mText.setText(getResources().getString(R.string.listview_load_done));
        mText.setGravity(Gravity.CENTER_VERTICAL);
//        progressCon.setVisibility(View.GONE);
        addView(footerView);

        LayoutParams params= new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentState==STATE_COMPLETE||currentState==STATE_FAIL){
                    XRecyclerView.LoadingListener loadingListener=xRecyclerView.getLoadingListener();
                    if (loadingListener!=null){
                        xRecyclerView.loadMoreData();
                    }
                }
            }
        });
    }


    public void  setState(int state) {
        switch(state) {
            case STATE_LOADING:
                currentState=STATE_LOADING;
                mText.setText(mContext.getText(R.string.listview_loading));
                progressCon.setVisibility(View.VISIBLE);
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_COMPLETE:
                currentState=STATE_COMPLETE;
                mText.setText(mContext.getText(R.string.listview_load_done));
                progressCon.setVisibility(View.GONE);
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_FAIL:
                currentState=STATE_FAIL;
                mText.setText(mContext.getText(load_error));
                progressCon.setVisibility(View.GONE);
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_NOMORE:
                currentState=STATE_NOMORE;
                mText.setText(mContext.getText(R.string.nomore_loading));
                progressCon.setVisibility(View.GONE);
                this.setVisibility(View.VISIBLE);
                break;
        }

    }
}
