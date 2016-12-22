package com.sitespect.sitespectdemo;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.sitespect.sdk.SiteSpect;
import com.sitespect.sdk.SiteSpectSessionMetrics;
import com.sitespect.sdk.SiteSpectStateListener;
import com.sitespect.sdk.SiteSpectVersionListener;

import java.util.Arrays;
import java.util.Date;

public class DemoApplication extends Application implements SiteSpectStateListener {
    // Unique site identifier for Demo App Campaign
    public static final String SITESPECT_SITE_ID = "589";

    // This needs to match variation group ID 789
    private static final String VERSION_ID = "789";

    private static DemoApplication singleton;

    public DemoApplication getInstance(){
        return singleton;
    }

    // On detection of matching version id, the onChanges listener is invoked, which will return the current
    // activity that is being currently viewed by the user. In this code editor example,
    // the featured item text color is changed to green.
    private SiteSpectVersionListener versionChangeListener = new SiteSpectVersionListener() {
        @Override
        public String getVersionId() {
            return VERSION_ID;
        }

        @Override
        public void onBaseline(Activity activity) {
        }

        @Override
        public void onChanges(Activity activity) {
            if (activity instanceof BaseSiteSpectActivity) {
                ((BaseSiteSpectActivity) activity).siteSpectCodeEditorChange();
            }
        }

        @Override
        public void onPreBaseline(Activity activity) {
        }

        @Override
        public void onPreChanges(Activity activity) {
        }
    };

    @Override
    public void onCreate() {
        SiteSpect.addVersionListener(versionChangeListener);
        super.onCreate();

        singleton = this;

        // Enable the SiteSpect SDK with Site ID
        SiteSpect.setLogLevel(SiteSpect.LogLevel.DEBUG);
        SiteSpect.addStateListener(this);
        SiteSpect.enableWithSiteID(SITESPECT_SITE_ID, this);
    }

    @Override
    public void campaignsInitialized() {
        Log.d("SiteSpectDemo", "Campaigns initialized");

    }

    @Override
    public void campaignStarted(long l, String s) {
        Log.d("SiteSpectDemo", "Campaign " + s + " started");
    }

    @Override
    public void versionStarted(String s) {
        Log.d("SiteSpectDemo", "Version " + s + " started");
    }

    @Override
    public void sessionCompletedMetrics(SiteSpectSessionMetrics metrics) {
        Log.d("SiteSpectTestApp", "Session started at " + new Date(metrics.getStartTime()));
        if (metrics.getLastViewed() == 0) {
            Log.d("SiteSpectTestApp", "No screens viewed for assigned campaign");
        } else {
            Log.d("SiteSpectTestApp", "Session last viewed at " + new Date(metrics.getLastViewed())
                    + " Session duration in seconds " + (metrics.getLastViewed() - metrics.getStartTime()) / 1000);
        }
        Log.d("SiteSpectTestApp", "Campaigns counted: " + Arrays.toString(metrics.getCountedCampaignIds().toArray()));
        for (SiteSpectSessionMetrics.MetricResult result : metrics.getMetricResults()) {
            Log.d("SiteSpectTestApp", "Metric ID " + result.getId() + " Hits:" + result.getHits() + " Value: " + result.getValue());
        }
    }
}
