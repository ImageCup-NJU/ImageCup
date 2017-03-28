package runjoy.tool;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by JiachenWang on 2017/3/28.
 */

public class ToastUtil {

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg,
                Toast.LENGTH_LONG).show();
    }

    public static void showerror(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg,
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
