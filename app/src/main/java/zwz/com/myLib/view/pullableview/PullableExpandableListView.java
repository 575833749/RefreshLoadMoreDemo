package zwz.com.myLib.view.pullableview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

public class PullableExpandableListView extends ExpandableListView implements
		Pullable {
	/**
	 * 是否允许上拉刷新,true允许
	 */
	private boolean isCanPullUp;

	public void isCanPullUp(boolean isCanPullUp) {
		this.isCanPullUp = isCanPullUp;
	}

	public boolean isCanPullUp() {
		return isCanPullUp;
	}

	public PullableExpandableListView(Context context) {
		super(context);
	}

	public PullableExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PullableExpandableListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean canPullDown() {
		try {
			if (getCount() == 0) {
				// 没有item的时候也可以下拉刷新
				return true;
			} else // 滑到顶部了
				return getFirstVisiblePosition() == 0 && getChildAt(0) != null
						&& getChildAt(0).getTop() >= 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	/**
	 * 判断是否可以上拉，如果不需要上拉功能可以直接return false
	 *
	 * @return true如果可以上拉否则返回false
	 */
	@Override
	public boolean canPullUp() {

		if (!isCanPullUp){
			return false;
		}

		try {
			if (getCount() == 0) {
				// 没有item的时候也不可以上拉加载
				return true;
			} else if (getLastVisiblePosition() == (getCount() - 1)) {
				// 滑到底部了
				if (getChildAt(getLastVisiblePosition()
						- getFirstVisiblePosition()) != null
						&& getChildAt(
								getLastVisiblePosition()
										- getFirstVisiblePosition())
								.getBottom() <= getMeasuredHeight())
					return true;
			}
		} catch (Exception e) {
		}

		return false;
	}

}
