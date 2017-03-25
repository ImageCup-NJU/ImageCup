package runjoy.expedition;

import android.support.annotation.NonNull;

import runjoy.startrunning.StartRunningContract;
import runjoy.tool.enums.RunModeEnum;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by foxwel on 2017/3/22.
 */

public class ExpeditionPresenter_stub implements ExpeditionContract.Presenter{

    @NonNull
    private final ExpeditionContract.View mExpeditionView;


    public ExpeditionPresenter_stub(@NonNull ExpeditionContract.View expeditionView){
        mExpeditionView = checkNotNull(expeditionView);
        mExpeditionView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public boolean newCity(String city) {
        return true;
    }

    @Override
    public boolean modifyCity(String city) {
        return true;
    }
}
