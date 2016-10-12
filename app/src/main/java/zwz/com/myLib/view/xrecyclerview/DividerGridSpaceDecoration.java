package zwz.com.myLib.view.xrecyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 朱伟志 on 2016/8/5 0005 18:06.
 */
public class DividerGridSpaceDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public DividerGridSpaceDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left=space;
        outRect.top=space;
    }
}
