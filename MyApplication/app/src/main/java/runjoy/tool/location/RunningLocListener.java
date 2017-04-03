package runjoy.tool.location;

import runjoy.data.TargetPoint;

/**
 * Created by JiachenWang on 2017/2/4.
 */

public interface RunningLocListener extends LocListener {

    /**
     * 抵达目标点
     *
     * @param target 目标点信息
     */
    void onTargetArrical(TargetPoint target);
}
