package com.ceduliocezar.lux.presentation.custom.ui;

/**
 * Created by cedulio on 09/08/2016.
 */

import android.support.annotation.NonNull;
import android.widget.AbsListView;

import static com.google.common.base.Preconditions.checkNotNull;

public class EndlessScrollListener implements AbsListView.OnScrollListener {

    private final ScrollCallback callback;
    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private int startingPageIndex = 0;

    public EndlessScrollListener(@NonNull ScrollCallback callback) {
        this.callback = checkNotNull(callback, "scroll callback can not be null");
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }
        // If it's still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
            currentPage++;
        }

        // If it isn't currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        if (!loading && (firstVisibleItem + visibleItemCount + visibleThreshold) >= totalItemCount) {
            System.out.println("onLoadMore: currentPage: " + (currentPage + 1) + " " +
                    "totalItemCount:" + totalItemCount);
            loading = callback.onLoadMore(currentPage + 1, totalItemCount);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Don't take any action on changed
    }

    public interface ScrollCallback {

        /**
         * Called when user reaches threshold
         *
         * @param page
         * @param totalItemsCount
         * @return true if loading more data
         */
        boolean onLoadMore(int page, int totalItemsCount);
    }
}