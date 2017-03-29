package runjoy.data;

/**
 * Created by JiachenWang on 2017/1/11.
 * 账户信息
 */

public class Account {

    //用户姓名
    private String name;

    //用户坚持跑步天数
    private int useDays;

    //ar任务贡献
    private double arContribute;

    //任务出现频率
    private double missionFrequent;

    //用户邮箱
    private String email;

    public Account(String name, int useDays, double arContribute, double missionFrequent, String email) {
        this.name = name;
        this.useDays = useDays;
        this.arContribute = arContribute;
        this.missionFrequent = missionFrequent;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUseDays() {
        return useDays;
    }

    public void setUseDays(int useDays) {
        this.useDays = useDays;
    }

    public double getArContribute() {
        return arContribute;
    }

    public void setArContribute(double arContribute) {
        this.arContribute = arContribute;
    }

    public double getMissionFrequent() {
        return missionFrequent;
    }

    public void setMissionFrequent(double missionFrequent) {
        this.missionFrequent = missionFrequent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


