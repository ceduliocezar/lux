package com.ceduliocezar.lux.data.backdrop;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by cedulio on 27/11/16.
 */
public class BackdropRepositoryImplTest {


    private static final List<Backdrop> BACKDROPS = Lists.newArrayList(new Backdrop(), new Backdrop());
    private static final List<Backdrop> EMPTY_BACKDROPS = Lists.newArrayList();

    @Mock
    private BackdropServiceApi backdropServiceApi;

    private BackdropRepositoryImpl backdropRepositoryImpl;

    @Captor
    private ArgumentCaptor<BackdropServiceApi.BackdropServiceApiCallback> apiCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<Integer> movieIdArgumentCaptor;

    @Mock
    private BackdropRepository.LoadBackdropCallback callback;


    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        backdropRepositoryImpl = new BackdropRepositoryImpl(backdropServiceApi);
    }

    @Test
    public void getBackdrops() throws Exception {

        int movieId = 0;

        backdropRepositoryImpl.getBackdrops(movieId, callback);

        verify(backdropServiceApi).getBackdrops(movieIdArgumentCaptor.capture(), apiCallbackArgumentCaptor.capture());

        apiCallbackArgumentCaptor.getValue().onLoad(BACKDROPS);

        verify(callback).onLoadBackdrops(BACKDROPS);

    }

    @Test
    public void getBackdrops_empty() throws Exception {

        int movieId = 0;

        backdropRepositoryImpl.getBackdrops(movieId, callback);

        verify(backdropServiceApi).getBackdrops(movieIdArgumentCaptor.capture(), apiCallbackArgumentCaptor.capture());

        apiCallbackArgumentCaptor.getValue().onLoad(EMPTY_BACKDROPS);

        verify(callback).onLoadBackdrops(EMPTY_BACKDROPS);

    }

    @Test
    public void getBackdrops_error() throws Exception {

        int movieId = 0;

        backdropRepositoryImpl.getBackdrops(movieId, callback);

        verify(backdropServiceApi).getBackdrops(movieIdArgumentCaptor.capture(), apiCallbackArgumentCaptor.capture());

        Exception exception = Mockito.mock(Exception.class);
        apiCallbackArgumentCaptor.getValue().errorLoading(exception);

        verify(callback).errorLoadingBackdrops(exception);

    }

}