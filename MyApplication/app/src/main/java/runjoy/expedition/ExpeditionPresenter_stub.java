package runjoy.expedition;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import runjoy.data.City;

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
        Map<String, String> map = new HashMap<>();
        map.put("南京","杭州");
        map.put("南京","天津");
        List<Long> list = new ArrayList<>();
        list.add(Long.valueOf(57));
        list.add(Long.valueOf(78));
        City city = new City(map, list);
        mExpeditionView.showHistory(city);
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
