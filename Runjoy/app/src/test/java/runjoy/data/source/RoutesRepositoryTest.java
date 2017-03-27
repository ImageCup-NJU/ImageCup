package runjoy.data.source;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.hamcrest.CoreMatchers.is;

import runjoy.data.Route;
import runjoy.data.source.local.RoutesLocalDataSource;

import static org.junit.Assert.assertThat;

/**
 * Created by xiaomai on 2017/2/7.
 */

public class RoutesRepositoryTest {

    private Route route1;

    @Mock
    private RoutesLocalDataSource routeDataSource;

    @Mock
    private Context mContext;

    @Before
    public void setupRoutesRepository() {
        MockitoAnnotations.initMocks(this);
        routeDataSource=RoutesLocalDataSource.getInstance(mContext);
    }

    @Test
    public void saveRoute(){
        Route route=new Route("1","北京","南京",1300,1000,862357);

        routeDataSource.saveRoute(route);


        routeDataSource.getMyRoute(new RouteDataSource.GetRouteCallback(){
            @Override
            public void onRouteLoaded(Route mRoute) {
                route1=mRoute;
            }
            @Override
            public void onDataNotAvailable() {

            }
        });
        System.out.print(route1.getAllDistance()+"****************");
        assertThat(route, is(route1));
    }

}
