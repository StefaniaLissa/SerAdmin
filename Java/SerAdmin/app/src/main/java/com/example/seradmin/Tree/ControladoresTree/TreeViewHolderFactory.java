package com.example.seradmin.Tree.ControladoresTree;

import android.view.View;

public interface TreeViewHolderFactory {

    /**
     * Provide a TreeViewHolder class depend on the current view
     * @param view The list item view
     * @param layout The layout xml file id for current view
     * @return A TreeViewHolder instance
     */
    TreeViewHolder getTreeViewHolder(View view, int layout);
}