package runjoy.data;

/**
 * Created by tian on 2017/3/26.
 */

public class Weather {

    //城市名称
    private String city;

    //天气状况（如：天气晴朗）
    private String status;

    //温度（单位 摄氏度）
    private int temperature;

    //PM2.5
    private int pm;

    public Weather(String city, String status, int temperature, int pm) {
        this.city = city;
        this.status = status;
        this.temperature = temperature;
        this.pm = pm;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getPm() {
        return pm;
    }

    public void setPm(int pm) {
        this.pm = pm;
    }
}
