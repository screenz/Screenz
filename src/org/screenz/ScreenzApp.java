package org.screenz;

import org.screenz.data.PixelsPerInch;
import org.screenz.views.grid.PositionUpdater;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenzApp {
    public static final PositionUpdater PositionUpdater = new PositionUpdater();
    
    private DisplayMetrics displayMetrics;
    private Activity activity;
    private PixelsPerInch ppi;
    
    ScreenzApp() {}
    
    void initialize(Activity activity) {
        this.activity = activity;
        displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        initializePixelsPerInch();
    }
    
    private void initializePixelsPerInch() {
        if (displayMetrics == null)
            ppi = PixelsPerInch.Factory.Empty;
        
        ppi = new PixelsPerInch(displayMetrics.xdpi, displayMetrics.ydpi);
    }
    
    public PixelsPerInch getPixelsPerInch() {
        return ppi;
    }
    
    public float getExactPixelsPerInchX() {
        if (displayMetrics != null)
            return displayMetrics.xdpi;
        return 160;
    }
    
    public float getExactPixelsPerInchY() {
        if (displayMetrics != null)
            return displayMetrics.ydpi;
        return 160;
    }
    
    public DisplayMetrics getDisplayMetrics() {
        return displayMetrics;
    }
    
    public static ScreenzApp getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    private static class SingletonHolder { 
        private static final ScreenzApp INSTANCE = new ScreenzApp();
    }

    public Activity getActivity() {
        return activity;
    }
}
