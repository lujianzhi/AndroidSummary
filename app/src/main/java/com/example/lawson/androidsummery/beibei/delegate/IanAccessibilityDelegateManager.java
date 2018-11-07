package com.example.lawson.androidsummery.beibei.delegate;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by Ian.Lu on 2018/9/19.
 * Project : AndroidSummary
 */
public class IanAccessibilityDelegateManager {

    private static View.AccessibilityDelegate delegate = new IanAccessibilityDelegate();

    public static View.AccessibilityDelegate getDelegate() {
        return delegate;
    }

    static class IanAccessibilityDelegate extends View.AccessibilityDelegate {

        @Override
        public void sendAccessibilityEvent(View host, int eventType) {
            super.sendAccessibilityEvent(host, eventType);
            switch (eventType) {
                case AccessibilityEvent.TYPE_VIEW_CLICKED:
                    ClickHelper.click(host);
                    break;
                default:
                    break;
            }
        }
    }
}
