package custom.launcher.entity.converter;

import android.view.MotionEvent;
import android.view.VelocityTracker;

import custom.common.util.LogUtil;

/**
 * 消费触屏事件，将其转化成横向滚动事件
 */
public class HorizonScrollConverter implements TouchToScrollConverter {
    private VelocityTracker velocityTracker;
    private float downX;
    private float deltaX;
    private float maxScrollDistance = 1080f;
    private ScrollListener scrollListener;
    private boolean isFirstMove = true;

    public interface ScrollListener {
        void onScrollStart();

        void onScrolling(float deltaX, float progress);

        void onScrollEnd(float deltaX, float velocityX);
    }

    public void setScrollListener(ScrollListener listener) {
        this.scrollListener = listener;
    }

    public ScrollListener getScrollListener() {
        return scrollListener;
    }

    public void setMaxScrollDistance(float maxDistance) {
        this.maxScrollDistance = Math.max(100f, maxDistance);
    }

    @Override
    public Boolean onTouchEvent(MotionEvent event) {
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                if (isFirstMove) {
                    LogUtil.debug("HorizontalScrollConsumer ACTION_DOWN");
                    downX = event.getX();
                    if (scrollListener != null) {
                        scrollListener.onScrollStart();
                    }
                    isFirstMove = false;
                } else {
                    LogUtil.debug("HorizontalScrollConsumer ACTION_MOVE");
                    deltaX = event.getX() - downX;
                    float progress = deltaX / maxScrollDistance;
                    if (scrollListener != null) {
                        scrollListener.onScrolling(deltaX, progress);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                LogUtil.debug("HorizontalScrollConsumer ACTION_UP|ACTION_CANCEL");
                velocityTracker.computeCurrentVelocity(1000);
                float velocityX = velocityTracker.getXVelocity();
                if (scrollListener != null) {
                    scrollListener.onScrollEnd(deltaX, velocityX);
                }
                reset();
                break;
        }
        return true;
    }

    public void reset() {
        downX = 0f;
        deltaX = 0f;
        isFirstMove = true;
        recycleVelocityTracker();
    }

    private void recycleVelocityTracker() {
        if (velocityTracker != null) {
            velocityTracker.recycle();
            velocityTracker = null;
        }
    }
}