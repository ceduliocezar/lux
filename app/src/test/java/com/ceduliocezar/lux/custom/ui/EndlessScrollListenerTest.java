package com.ceduliocezar.lux.custom.ui;

import android.widget.AbsListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ceduliocezar on 30/11/16.
 */
public class EndlessScrollListenerTest {

    @Mock
    private EndlessScrollListener.ScrollCallback scrollCallback;

    @Mock
    private AbsListView absListView;

    @Captor
    private ArgumentCaptor<Integer> nextPageArgumentCaptor;

    @Captor
    private ArgumentCaptor<Integer> totalItemsArgumentCaptor;

    private EndlessScrollListener endlessScrollListener;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.endlessScrollListener = new EndlessScrollListener(scrollCallback);
    }

    @Test
    public void onScroll_initial_state() throws Exception {

        int firstVisibleItem = 0;
        int visibleItemCount = 5;
        int totalItemCount = 40;

        endlessScrollListener.onScroll(absListView, firstVisibleItem, visibleItemCount, totalItemCount);
        verify(scrollCallback, never()).onLoadMore(anyInt(), anyInt());
    }

    @Test
    public void onScroll_scroll_a_bit_not_load() throws Exception {

        int firstVisibleItem = 10;
        int visibleItemCount = 5;
        int totalItemCount = 40;

        endlessScrollListener.onScroll(absListView, firstVisibleItem, visibleItemCount, totalItemCount);
        verify(scrollCallback, never()).onLoadMore(anyInt(), anyInt());
    }

    @Test
    public void onScroll_scroll_right_before_threshold() throws Exception {

        int firstVisibleItem = 29;
        int visibleItemCount = 5;
        int totalItemCount = 40;

        endlessScrollListener.onScroll(absListView, firstVisibleItem, visibleItemCount, totalItemCount);
        verify(scrollCallback, never()).onLoadMore(anyInt(), anyInt());
    }

    @Test
    public void onScroll_scroll_reach_threshold() throws Exception {

        int firstVisibleItem = 30;
        int visibleItemCount = 5;
        int totalItemCount = 40;

        endlessScrollListener.onScroll(absListView, firstVisibleItem, visibleItemCount, totalItemCount);
        verify(scrollCallback).onLoadMore(nextPageArgumentCaptor.capture(), totalItemsArgumentCaptor.capture());


        Integer expectedNextPage = new Integer(2);
        assertEquals(nextPageArgumentCaptor.getValue(), expectedNextPage);
    }

    @Test
    public void onScroll_scroll_right_after_threshold() throws Exception {

        int firstVisibleItem = 31;
        int visibleItemCount = 5;
        int totalItemCount = 40;

        endlessScrollListener.onScroll(absListView, firstVisibleItem, visibleItemCount, totalItemCount);
        verify(scrollCallback).onLoadMore(2, totalItemCount);
    }

    @Test
    public void onScroll_scroll_through_several_pages() throws Exception {

        int visibleItemCount = 5;
        int totalItems = 100;

        when(scrollCallback.onLoadMore(2, totalItems)).thenReturn(true);
        when(scrollCallback.onLoadMore(3, totalItems)).thenReturn(false);


        scrollThroughAPage(1, 90, visibleItemCount, totalItems);
        verify(scrollCallback).onLoadMore(2, totalItems);


        totalItems = 200;
        scrollThroughAPage(91, 190, visibleItemCount, totalItems);
        verify(scrollCallback).onLoadMore(3, totalItems);
    }

    private void scrollThroughAPage(int start, int end, int visibleItemCount, int totalItems) {
        for (int i = start; i <= end; i++) {
            endlessScrollListener.onScroll(absListView, i, visibleItemCount, totalItems);
        }
    }
}