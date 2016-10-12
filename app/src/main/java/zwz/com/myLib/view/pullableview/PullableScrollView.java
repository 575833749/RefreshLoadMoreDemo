package zwz.com.myLib.view.pullableview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class PullableScrollView extends ScrollView implements Pullable
{
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

	public PullableScrollView(Context context)
	{
		super(context);
	}

	public PullableScrollView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public PullableScrollView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public boolean canPullDown()
	{

		if (getScrollY() == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean canPullUp()
	{
//		if (!isCanPullUp){
//			return false;
//		}
		if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
			return true;
		else
			return false;
	}

}
