package runjoy.data;

import java.util.List;
import java.util.Map;

/**
 * Created by JiachenWang on 2017/1/11.
 * 城市信息
 */

public class City {

    //历史的出发地，目的地
    private Map<String,String> route;

    //一一对应route，记录每个的用时
    private List<Long> time;

    public City(Map<String,String> route,List<Long> time){
        this.setRoute(route);
        this.setTime(time);
    }


    public Map<String, String> getRoute() {
        return route;
    }

    public void setRoute(Map<String, String> route) {
        this.route = route;
    }

    public List<Long> getTime() {
        return time;
    }

    public void setTime(List<Long> time) {
        this.time = time;
    }
}
