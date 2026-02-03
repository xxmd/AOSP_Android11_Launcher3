package custom.launcher.entity.converter;

import android.view.MotionEvent;

public interface TouchToScrollConverter {
    Boolean onTouchEvent(MotionEvent event);
}
