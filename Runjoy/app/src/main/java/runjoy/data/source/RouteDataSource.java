package runjoy.data.source;

import android.support.annotation.NonNull;

import runjoy.data.City;
import runjoy.data.Route;
import runjoy.data.RunInfo;

/**
 * Created by xiaomai on 2017/1/14.
 */

public interface RouteDataSource {

    interface GetRouteCallback {

        void onRouteLoaded(Route task);

        void onDataNotAvailable();
    }

    interface GetRunCallback {

        void onRunLoaded(RunInfo runInfo);

        void onDataNotAvailable();
    }

    /**
     * 获得上次跑步信息
     * @return
     */
    void getLastRun(@NonNull GetRunCallback callback);

    /**
     * 获得当前跑步目标及相关信息
     * @return
     */
    void getMyRoute(@NonNull GetRouteCallback callback);

    /**
     * 获得历史跑步信息
     * @return
     */
    City getHistoryRoute();

    /**
     * 保存本次跑步信息
     * @param runInfo
     */
    void saveRun(@NonNull RunInfo runInfo);

    /**
     * 更新跑步目标信息
     * @param route
     */
    void updateRoute(@NonNull Route route);

    /**
     * 新建跑步目标信息
     * @param route
     */
    void saveRoute(@NonNull Route route);

}
