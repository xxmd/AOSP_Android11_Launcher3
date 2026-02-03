package custom.launcher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.google.android.libraries.launcherclient.ILauncherOverlay;
import com.google.android.libraries.launcherclient.ILauncherOverlayCallback;

import custom.common.util.LogUtil;
import custom.launcher.entity.Constants;
import custom.launcher.entity.converter.HorizonScrollConverter;

public class OverlayWindowCore {
    private Activity activity;
    private boolean serviceConnected = false;
    private SafeLauncherOverlay safeLauncherOverlay;
    private HorizonScrollConverter horizonScrollConverter;

    public OverlayWindowCore(Activity activity) {
        this.activity = activity;
        this.horizonScrollConverter = new HorizonScrollConverter();
    }

    public void bindService() {
        Intent intent = buildOverlayWindowIntent();
        boolean bindServiceSuccess = activity.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                LogUtil.step("onServiceConnected service: " + service);
                ILauncherOverlay launcherOverlay = ILauncherOverlay.Stub.asInterface(service);
                ILauncherOverlayCallback callback = new ILauncherOverlayCallback.Stub() {
                    @Override
                    public void overlayScrollChanged(float progress) throws RemoteException {
                        LogUtil.debug("ILauncherOverlayCallback overlayScrollChanged progress: " + progress);
                    }

                    @Override
                    public void overlayStatusChanged(int status) throws RemoteException {
                        LogUtil.debug("ILauncherOverlayCallback overlayStatusChanged status: " + status);
                    }

                    @Override
                    public void onServiceStatus(Bundle bundle) throws RemoteException {
                        LogUtil.step("ILauncherOverlaylauncherOverlay onServiceStatus bundle: " + bundle);
                        int serviceStatus = bundle.getInt("service_status");
                        serviceConnected = true;
                    }
                };
                Bundle bundle = buildAttachWindowBundle();
                safeLauncherOverlay = new SafeLauncherOverlay(launcherOverlay);
                safeLauncherOverlay.windowAttached2(bundle, callback);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                LogUtil.debug("onServiceDisconnected name: " + name);
                serviceConnected = false;
            }
        }, Context.BIND_AUTO_CREATE);
        LogUtil.step("bindServiceSuccess: " + bindServiceSuccess);
    }

    private Intent buildOverlayWindowIntent() {
        Intent intent = new Intent(Constants.OVERLAY_SERVICE_INTENT);
        intent.setClassName(Constants.OVERLAY_PACKAGE_NAME, Constants.OVERLAY_SERVICE_NAME);
        intent.setData(Uri.parse("app://somepath"));
        return intent;
    }

    public void addScrollListener(View rootView) {
        HorizonScrollConverter.ScrollListener scrollListener = horizonScrollConverter.getScrollListener();
        LogUtil.step(String.format("addScrollListener rootView: %s, scrollListener: %s", rootView, scrollListener));
        if (rootView == null || scrollListener != null) {
            return;
        }
        int measuredWidth = rootView.getMeasuredWidth();
        if (measuredWidth == 0) {
            measuredWidth = rootView.getContext().getResources().getDisplayMetrics().widthPixels;
        }
        horizonScrollConverter.setMaxScrollDistance(measuredWidth);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return horizonScrollConverter.onTouchEvent(event);
            }
        });
        horizonScrollConverter.setScrollListener(new HorizonScrollConverter.ScrollListener() {
            @Override
            public void onScrollStart() {
                LogUtil.debug(String.format("onScrollStart"));
                if (serviceConnected) {
                    safeLauncherOverlay.startScroll();
                }
            }

            @Override
            public void onScrolling(float deltaX, float progress) {
                LogUtil.debug(String.format("onScrolling deltaX: %f, progress: %f", deltaX, progress));
                if (serviceConnected) {
                    safeLauncherOverlay.onScroll(progress);
                }
            }

            @Override
            public void onScrollEnd(float deltaX, float velocityX) {
                LogUtil.debug(String.format("onScrollEnd deltaX: %f, velocityX: %f", deltaX, velocityX));
                if (serviceConnected) {
                    safeLauncherOverlay.endScroll();
                }
            }
        });
    }

    private Bundle buildAttachWindowBundle() {
        Bundle bundle = new Bundle();
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        bundle.putParcelable("layout_params", attributes);
        Configuration configuration = activity.getResources().getConfiguration();
        bundle.putParcelable("configuration", configuration);
        return bundle;
    }
}
