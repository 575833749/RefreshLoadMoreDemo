package zwz.com.myLib.view.xrecyclerview;

/**
 * Created by jianghejie on 15/11/22.
 */
public interface BaseRefreshHeader {
    void onMove(float delta);

    boolean releaseAction();

    void refreshComplate(int state);

    /**
     * 正常状态
     */
    int STATE_NORMAL = 0;
    /**
     * 下拉状态
     */
    int STATE_RELEASE_TO_REFRESH = 1;
    /**
     * 正在刷新状态
     */
    int STATE_REFRESHING = 2;
    /**
     * 刷新成功状态
     */
    int STATE_REFRESH_SUCCESS = 3;
    /**
     * 刷新失败状态
     */
    int STATE_REFRESH_FAIL = 4;
}
