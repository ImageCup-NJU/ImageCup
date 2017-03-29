package runjoy.data;

import android.text.format.Time;

import java.util.Date;

/**
 * Created by xiaomai on 2017/1/16.
 * 用户单次跑步的信息
 */

public class RunInfo {

    //本次跑步距离
    private double distance;

    //本次跑步用时
    private long time;

    //完成任务数量
    private int missionNum;

    //为到达目标地点增加了多少距离
    private double addDistance;

    //ar任务增加的距离
    private int arDistance;

    //日期
    private Date date;

    public RunInfo(double distance,long time,int missionNum,double addDistance,int arDistance,Date date){
        this.setDistance(distance);
        this.setTime(time);
        this.setMissionNum(missionNum);
        this.setAddDistance(addDistance);
        this.setArDistance(arDistance);
        this.setDate(date);
    }

    public int getArDistance() {
        return arDistance;
    }

    public void setArDistance(int arDistance) {
        this.arDistance = arDistance;
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

    public int getMissionNum() {
        return missionNum;
    }

    public void setMissionNum(int missionNum) {
        this.missionNum = missionNum;
    }

    public double getAddDistance() {
        return addDistance;
    }

    public void setAddDistance(double addDistance) {
        this.addDistance = addDistance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
