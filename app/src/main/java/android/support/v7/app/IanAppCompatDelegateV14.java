package android.support.v7.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import com.example.lawson.androidsummery.delegate.IanAccessibilityDelegateManager;
import com.example.lawson.androidsummery.delegate.IanViewFactory;

/**
 * Created by Ian.Lu on 2018/9/18.
 * Project : AndroidSummary
 */
public class IanAppCompatDelegateV14 extends AppCompatDelegateImplV14 {
    IanAppCompatDelegateV14(Context context, Window window, AppCompatCallback callback) {
        super(context, window, callback);
    }

    @Override
    View callActivityOnCreateView(View parent, String name, Context context, AttributeSet attrs) {
        //代理了TextView等控件，这里先注释
//        View view = super.createView(parent, name, context, attrs);
//        if (view != null) {
//            view.setAccessibilityDelegate(IanAccessibilityDelegateManager.getDelegate());
//            return view;
//        }

        View view = IanViewFactory.createView(parent, name, context, attrs);
        if (view != null) {
            view.setAccessibilityDelegate(IanAccessibilityDelegateManager.getDelegate());
            return view;
        }

        return super.callActivityOnCreateView(parent, name, context, attrs);
    }
}
