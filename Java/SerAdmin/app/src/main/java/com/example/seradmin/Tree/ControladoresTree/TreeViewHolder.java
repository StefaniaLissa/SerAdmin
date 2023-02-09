package com.example.seradmin.Tree.ControladoresTree;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.utilities.TreeNode;

public class TreeViewHolder extends RecyclerView.ViewHolder {

    private int nodePadding = 50;

    public TreeViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bindTreeNode(TreeNode node) {
        int padding = node.getLevel() * nodePadding;
        itemView.setPadding(
                padding,
                itemView.getPaddingTop(),
                itemView.getPaddingRight(),
                itemView.getPaddingBottom());
    }

    public void setNodePadding(int padding) {
        this.nodePadding = padding;
    }

    public int getNodePadding() {
        return nodePadding;
    }
}

