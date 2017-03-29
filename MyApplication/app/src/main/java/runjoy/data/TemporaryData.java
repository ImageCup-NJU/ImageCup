package runjoy.data;

import java.util.Map;

import runjoy.tool.enums.RunModeEnum;

/**
 * Created by lenovo on 2017/1/24.
 */

public class TemporaryData {

    private Map<Double,Double> route=null;

    private RunModeEnum runMode;

    private static TemporaryData INSTANCE;

    private TemporaryData(){}

    public static TemporaryData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TemporaryData();
        }
        return INSTANCE;
    }

    public Map<Double, Double> getRoute() {
        return route;
    }

    public void setRoute(Map<Double, Double> route) {
        this.route = route;
    }

    public RunModeEnum getRunMode() {
        return runMode;
    }

    public void setRunMode(RunModeEnum runMode) {
        this.runMode = runMode;
    }
}
