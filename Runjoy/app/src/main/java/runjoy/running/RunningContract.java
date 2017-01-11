package runjoy.running;

import runjoy.BasePresenter;
import runjoy.BaseView;

/**
 * Created by JiachenWang on 2017/1/11.
 */

public interface RunningContract {

    interface View extends BaseView<Presenter> {

        void showMessageDialog();

        void showAR();
    }

    interface Presenter extends BasePresenter {
        void newMission();

        void completeMission();
    }
}
