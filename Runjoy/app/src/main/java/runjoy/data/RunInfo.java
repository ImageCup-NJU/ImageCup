package runjoy.data;

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

    //奖励信息(如：为到达目标地点缩短了多少距离，缩短了多少时间等)
    private String message;

    public RunInfo(double distance,long time,int missionNum,double addDistance,String message){
        this.setDistance(distance);
        this.setTime(time);
        this.setMissionNum(missionNum);
        this.setAddDistance(addDistance);
        this.setMessage(message);
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
