package custom.launcher;

import android.app.Activity;
import android.os.Bundle;

import com.android.launcher3.Launcher;
import com.android.systemui.plugins.shared.LauncherOverlayManager;

import java.io.PrintWriter;

import custom.common.util.LogUtil;

public class LauncherOverlayManagerImpl implements LauncherOverlayManager {
    private Launcher mLauncher;

    public LauncherOverlayManagerImpl(Launcher launcher) {
        this.mLauncher = launcher;
    }


    @Override
    public void onDeviceProvideChanged() {
        LauncherOverlayManager.super.onDeviceProvideChanged();
        LogUtil.debug("LauncherOverlayManagerImpl onDeviceProvideChanged");
    }

    @Override
    public void onAttachedToWindow() {
        LauncherOverlayManager.super.onAttachedToWindow();
        LogUtil.debug("LauncherOverlayManagerImpl onAttachedToWindow");
    }

    @Override
    public void onDetachedFromWindow() {
        LauncherOverlayManager.super.onDetachedFromWindow();
        LogUtil.debug("LauncherOverlayManagerImpl onDetachedFromWindow");
    }

    @Override
    public void dump(String prefix, PrintWriter w) {
        LauncherOverlayManager.super.dump(prefix, w);
    }

    @Override
    public void openOverlay() {
        LauncherOverlayManager.super.openOverlay();
        LogUtil.debug("LauncherOverlayManagerImpl openOverlay");
    }

    @Override
    public void hideOverlay(boolean animate) {
        LauncherOverlayManager.super.hideOverlay(animate);
        LogUtil.debug("LauncherOverlayManagerImpl hideOverlay animate: " + animate);
    }

    @Override
    public void hideOverlay(int duration) {
        LauncherOverlayManager.super.hideOverlay(duration);
        LogUtil.debug("LauncherOverlayManagerImpl hideOverlay duration: " + duration);
    }

    @Override
    public boolean startSearch(byte[] config, Bundle extras) {
        LogUtil.debug("LauncherOverlayManagerImpl startSearch extras: " + extras);
        return LauncherOverlayManager.super.startSearch(config, extras);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        LauncherOverlayManager.super.onActivityCreated(activity, bundle);
        LogUtil.debug("LauncherOverlayManagerImpl onActivityCreated activity: " + activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        LauncherOverlayManager.super.onActivityStarted(activity);
        LogUtil.debug("LauncherOverlayManagerImpl onActivityStarted activity: " + activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LauncherOverlayManager.super.onActivityResumed(activity);
        LogUtil.debug("LauncherOverlayManagerImpl onActivityResumed activity: " + activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LauncherOverlayManager.super.onActivityPaused(activity);
        LogUtil.debug("LauncherOverlayManagerImpl onActivityPaused activity: " + activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LauncherOverlayManager.super.onActivityStopped(activity);
        LogUtil.debug("LauncherOverlayManagerImpl onActivityStopped activity: " + activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        LauncherOverlayManager.super.onActivitySaveInstanceState(activity, bundle);
        LogUtil.debug("LauncherOverlayManagerImpl onActivitySaveInstanceState activity: " + activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LauncherOverlayManager.super.onActivityDestroyed(activity);
        LogUtil.debug("LauncherOverlayManagerImpl onActivityDestroyed activity: " + activity);
    }

    @Override
    public void onActivityPostCreated(Activity activity, Bundle savedInstanceState) {
        LauncherOverlayManager.super.onActivityPostCreated(activity, savedInstanceState);
    }

    @Override
    public void onActivityPostDestroyed(Activity activity) {
        LauncherOverlayManager.super.onActivityPostDestroyed(activity);
    }

    @Override
    public void onActivityPostPaused(Activity activity) {
        LauncherOverlayManager.super.onActivityPostPaused(activity);
    }

    @Override
    public void onActivityPostResumed(Activity activity) {
        LauncherOverlayManager.super.onActivityPostResumed(activity);
    }

    @Override
    public void onActivityPostSaveInstanceState(Activity activity, Bundle outState) {
        LauncherOverlayManager.super.onActivityPostSaveInstanceState(activity, outState);
    }

    @Override
    public void onActivityPostStarted(Activity activity) {
        LauncherOverlayManager.super.onActivityPostStarted(activity);
    }

    @Override
    public void onActivityPostStopped(Activity activity) {
        LauncherOverlayManager.super.onActivityPostStopped(activity);
    }

    @Override
    public void onActivityPreCreated(Activity activity, Bundle savedInstanceState) {
        LauncherOverlayManager.super.onActivityPreCreated(activity, savedInstanceState);
    }

    @Override
    public void onActivityPreDestroyed(Activity activity) {
        LauncherOverlayManager.super.onActivityPreDestroyed(activity);
    }

    @Override
    public void onActivityPrePaused(Activity activity) {
        LauncherOverlayManager.super.onActivityPrePaused(activity);
    }

    @Override
    public void onActivityPreResumed(Activity activity) {
        LauncherOverlayManager.super.onActivityPreResumed(activity);
    }

    @Override
    public void onActivityPreSaveInstanceState(Activity activity, Bundle outState) {
        LauncherOverlayManager.super.onActivityPreSaveInstanceState(activity, outState);
    }

    @Override
    public void onActivityPreStarted(Activity activity) {
        LauncherOverlayManager.super.onActivityPreStarted(activity);
    }

    @Override
    public void onActivityPreStopped(Activity activity) {
        LauncherOverlayManager.super.onActivityPreStopped(activity);
    }

    public LauncherOverlayManagerImpl() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
