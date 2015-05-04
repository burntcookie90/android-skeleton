package com.ataulm.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ataulm.basic.ItemView;

public final class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private final int verticalSpacing;
    private final SpanSizeLookup spanSizeLookup;

    private final int horizontalSpacingMinor;
    private final int horizontalSpacingHalf;
    private final int horizontalSpacingMajor;

    private Grid grid;

    public static SpacesItemDecoration newInstance(int horizontalSpacing, int verticalSpacing, SpanSizeLookup spanSizeLookup) {
        int maxSpansInRow = spanSizeLookup.getSpanCount();
        int numberOfGaps = maxSpansInRow - 1;
        int horizontalSpacingMajor = (int) (1f * numberOfGaps * horizontalSpacing / maxSpansInRow);
        int horizontalSpacingHalf = (int) (horizontalSpacing * 0.5);
        int horizontalSpacingMinor = horizontalSpacing - horizontalSpacingMajor;

        return new SpacesItemDecoration(verticalSpacing, spanSizeLookup, horizontalSpacingMinor, horizontalSpacingHalf, horizontalSpacingMajor);
    }

    private SpacesItemDecoration(int verticalSpacing, SpanSizeLookup spanSizeLookup, int horizontalSpacingMinor, int horizontalSpacingHalf, int horizontalSpacingMajor) {
        this.verticalSpacing = verticalSpacing;
        this.spanSizeLookup = spanSizeLookup;
        this.horizontalSpacingMinor = horizontalSpacingMinor;
        this.horizontalSpacingHalf = horizontalSpacingHalf;
        this.horizontalSpacingMajor = horizontalSpacingMajor;
    }

    public void onDatasetChanged() {
        grid = null;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (grid == null) {
            grid = Grid.newInstance(parent.getAdapter().getItemCount(), spanSizeLookup);
        }
        int position = parent.getChildPosition(view);
        adjustOffsets(grid, position, outRect, horizontalSpacingMinor, horizontalSpacingHalf, horizontalSpacingMajor, verticalSpacing);
        updateDebugInfo(grid, position, (ItemView) view);
    }

    private static void adjustOffsets(Grid grid, int itemPosition, Rect outRect, int minorItemMarginSpace, int halfSpace, int majorItemMarginSpace, int verticalSpacing) {
        adjustHorizontalOffsets(grid, itemPosition, outRect, minorItemMarginSpace, halfSpace, majorItemMarginSpace);

        if (grid.itemIsInFirstRow(itemPosition)) {
            outRect.top = 0;
        } else {
            outRect.top = verticalSpacing / 2;
        }

        if (grid.itemIsInLastRow(itemPosition)) {
            outRect.bottom = 0;
        } else {
            outRect.bottom = verticalSpacing / 2;
        }
    }

    private static void adjustHorizontalOffsets(Grid grid, int itemPosition, Rect outRect, int minorItemMarginSpace, int halfSpace, int majorItemMarginSpace) {
        if (grid.itemIsInFirstColumn(itemPosition) && grid.itemIsInLastColumn(itemPosition)) {
            outRect.left = 0;
            outRect.right = 0;
            return;
        }

        if (grid.itemIsInFirstColumn(itemPosition)) {
            outRect.left = 0;
            outRect.right = majorItemMarginSpace;
            return;
        }

        if (grid.itemIsInLastColumn(itemPosition)) {
            outRect.left = majorItemMarginSpace;
            outRect.right = 0;
            return;
        }

        if (grid.itemIsNextToItemInFirstColumn(itemPosition)) {
            outRect.left = minorItemMarginSpace;
        } else {
            outRect.left = halfSpace;
        }

        if (grid.itemIsNextToItemInLastColumn(itemPosition)) {
            outRect.right = minorItemMarginSpace;
        } else {
            outRect.right = halfSpace;
        }
    }

    private static void updateDebugInfo(Grid grid, int position, ItemView view) {
        StringBuilder place = new StringBuilder();
        if (grid.itemIsInFirstColumn(position)) {
            place.append("fc,");
        }

        if (grid.itemIsInLastColumn(position)) {
            place.append("lc,");
        }

        if (grid.itemIsInFirstRow(position)) {
            place.append("fr,");
        }

        if (grid.itemIsInLastRow(position)) {
            place.append("lr,");
        }

        if (place.length() > 0) {
            view.update(place.substring(0, place.length() - 1));
        } else {
            view.update("");
        }
    }

}
