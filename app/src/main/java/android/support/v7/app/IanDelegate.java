package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Window;

/**
 * Created by Ian.Lu on 2018/9/18.
 * Project : AndroidSummary
 */
public class IanDelegate {

    public static AppCompatDelegate create(Activity activity, AppCompatCallback callback) {
        return create(activity, activity.getWindow(), callback);
    }

    private static AppCompatDelegate create(Context context, Window window, AppCompatCallback callback) {
        int version = Build.VERSION.SDK_INT;
        if (version >= 24) {
            return new IanAppCompatDelegateImplN(context, window, callback);
        } else if (version >= 23) {
            return new IanAppCompatDelegateImplV23(context, window, callback);
        } else if (version >= 14) {
            //这里我们就重写了callActivityOnCreateView方法。
            return new IanAppCompatDelegateV14(context, window, callback);
        } else if (version >= 11) {
            //同理
            return new AppCompatDelegateImplV11(context, window, callback);
        } else {
            //由于是依次继承关系，这个类又是AppCompatDelegateImplBase的子类
            //这里没必要去覆盖一些方法
            return new AppCompatDelegateImplV9(context, window, callback);
        }
    }
}
