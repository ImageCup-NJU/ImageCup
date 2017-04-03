package runjoy.achievement;

import android.support.annotation.NonNull;

import runjoy.data.Account;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by foxwel on 2017/3/22.
 */

public class AchievementPresenter_stub implements AchievementContract.Presenter{

    @NonNull
    private final AchievementContract.View mAchievementView;


    public AchievementPresenter_stub(@NonNull AchievementContract.View achievementView){
        mAchievementView = checkNotNull(achievementView);
        mAchievementView.setPresenter(this);
    }

    @Override
    public void start() {
    }
}
