package android.support.v7.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import com.example.lawson.androidsummery.delegate.IanViewFactory;

/**
 * Created by Ian.Lu on 2018/9/18.
 * Project : AndroidSummary
 */
public class IanAppCompatDelegateV14 extends AppCompatDelegateImplV14 {
    IanAppCompatDelegateV14(Context context, Window window, AppCompatCallback callback) {
        super(context, window, callback);
    }

    /**
     * 个人认为重写该方法是曲线救国的意思。
     * 因为最关键的LayoutInflater.Factory2接口的onCreateView方法，被设了final，哈哈哈哈哈哈哈哈哈
     */
    @Override
    View callActivityOnCreateView(View parent, String name, Context context, AttributeSet attrs) {
//        View view = super.createView(parent, name, context, attrs);
//        if (view != null) {
//            return view;
//        }

        View view = IanViewFactory.createView(parent, name, context, attrs);
        if (view != null) {
            return view;
        }

        return super.callActivityOnCreateView(parent, name, context, attrs);
    }
}
