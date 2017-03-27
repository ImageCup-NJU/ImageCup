package runjoy.data;

import android.content.Intent;

/**
 * Created by xiaomai on 2017/1/14.
 * 用户当前跑的线路信息以及当前状态
 */

public class Route {

    private Integer id;
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

    //是否完成
    private int ifComplete;

    public Route(Integer id, String start,String end,double allDistance,double distance,long time,int ifComplete){
        this.id=id;
        this.start=start;
        this.end=end;
        this.allDistance=allDistance;
        this.distance=distance;
        this.time=time;
        this.ifComplete=ifComplete;
    }

    public int getIfComplete() {
        return ifComplete;
    }

    public void setIfComplete(int ifComplete) {
        this.ifComplete = ifComplete;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
