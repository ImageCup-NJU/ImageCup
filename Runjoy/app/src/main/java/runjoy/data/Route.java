package runjoy.data;

/**
 * Created by xiaomai on 2017/1/14.
 * 用户当前跑的线路信息以及当前状态
 */

public class Route {

    private String id;
    //出发地
    private String start;

    //目的地
    private String end;

    //路线全长
    private double allDistance;

    //已跑路程
    private double distance;

    //已用时间(秒)
    private long time;

    public Route(String id,String start,String end,double allDistance,double distance,long time){
        this.setId(id);
        this.start=start;
        this.end=end;
        this.allDistance=allDistance;
        this.distance=distance;
        this.time=time;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double getAllDistance() {
        return allDistance;
    }

    public void setAllDistance(double allDistance) {
        this.allDistance = allDistance;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
