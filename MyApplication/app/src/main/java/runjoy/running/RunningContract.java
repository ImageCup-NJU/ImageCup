package runjoy.running;

import java.util.Map;

import runjoy.BasePresenter;
import runjoy.BaseView;
import runjoy.data.Route;
import runjoy.data.RunInfo;
import runjoy.tool.enums.RunModeEnum;

/**
 * Created by JiachenWang on 2017/1/11.
 */

public interface RunningContract {

    interface View extends BaseView<Presenter> {

        /**
         * 自定义路线下用户跑步的地图
         * @param mode
         * @param passPoint     <经度,纬度> mode为自由跑则为null
         */
        void showMap(RunModeEnum mode, Map<Double, Double> passPoint);

        /**
         * 提示用户，“接受任务”，“拒绝任务”，“再跑两分钟再说”
         * @param missionInfo    任务描述
         */
        void showMessageDialog(String missionInfo);

        /**
         * 进入AR游戏，暂停跑步时间
         * @param arId  AR的ID
         *
         */
        void showAR(int arId);

        /**
         * 完成任务，继续跑步，继续记录跑步时间
         */
        void continueRun();

        /**
         * 显示当前已跑的路程
         * @param distance  路程数
         *
         */
        void showDistance(double distance);

        /**
         * 显示已完成的任务数量
         * @param num
         */
        void showCompleteMission(int num);

        /**
         * 展示结束跑步相关信息
         * @param runInfo   本次跑步信息
         * @param route    里程信息
         */
        void showEndInfo(RunInfo runInfo, Route route);

    }

    interface Presenter extends BasePresenter {

        /**
         * 开始跑步,记录跑步路程，完成任务数量，如果是自由跑步，则根据跑步路程进行任务，如果是自定义跑步，则用户位置进行任务
         */
        void startRun();

        /**
         * 结束跑步，显示本次跑步时间，路程，完成的任务数量，为到达目标地点增加了多少距离，并且更新与目标地点的距离和花费时间
         * @param useTime    用户跑步用时
         */
        void endRun(Long useTime);

        /**
         * 延时两分钟再通知用户，即延时两分钟再调用showMessageDialog
         */
        void delayMessage();

        /**
         * 进行任务
         */
        void newMission();

        /**
         * 完成任务
         * @param result    任务完成结果（这个方法由c#代码调用，参数传递暂时先写成这样。。。）
         */
        void completeMission(String result);

        /**
         * 开始监控用户的位置，到达任务点（自由跑步为用户跑了一定路程就到达任务点，diy为用户到达自己设置的任务点）
         * 提醒用户，调用showMessageDialog
         */
        void startMonitor();
    }
}
