package org.screenz;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.codeswimmer.android.os.DeviceIsRunning;

public class ScreenzActivity extends Activity {
    @SuppressWarnings("unused")
    private static final String TAG = ScreenzActivity.class.getSimpleName();
    private TextView resolutionView;
    private TextView densityView;
    private TextView deviceBuildInfoView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ScreenzApp.getInstance().initialize(this);
        storeWidgetHandles();
        updateWidgetValues();
        
        if (DeviceIsRunning.Honeycomb)
            customizeActionBar();
        
        /*
        System.out.println("\n***********************************************************************************");
        System.out.println(String.format("brand: %s", Build.BRAND));
        System.out.println(String.format("device: %s", Build.DEVICE));
        System.out.println(String.format("display: %s", Build.DISPLAY));
        System.out.println(String.format("fingerprint: %s", Build.FINGERPRINT));
        System.out.println(String.format("hardware: %s", Build.HARDWARE));
        System.out.println(String.format("manufacturer: %s", Build.MANUFACTURER));
        System.out.println(String.format("model: %s", Build.MODEL));
        System.out.println(String.format("product: %s", Build.PRODUCT));
        System.out.println(String.format("tags: %s", Build.TAGS));
        System.out.println("***********************************************************************************");
         */
    }
    
    private void customizeActionBar() {
        getActionBar().setDisplayShowCustomEnabled(true);
        getActionBar().setCustomView(R.layout.custom_action_bar);
        getActionBar().show();
    }
    
    private void storeWidgetHandles() {
        resolutionView = (TextView) findViewById(R.id.resolutionView);
        densityView = (TextView) findViewById(R.id.densityView);
        deviceBuildInfoView = (TextView) findViewById(R.id.deviceBuildInfoView);
    }
    
    private void updateWidgetValues() {
        DisplayMetrics dm = ScreenzApp.getInstance().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        
        String resolutionText = String.format("%s x %s", width, height);
        resolutionView.setText(resolutionText);
        densityView.setText(String.format("%d dpi", dm.densityDpi));

        String buildInfoText = String.format("%s %s", Build.MANUFACTURER, Build.MODEL);
        deviceBuildInfoView.setText(buildInfoText);
    }
}