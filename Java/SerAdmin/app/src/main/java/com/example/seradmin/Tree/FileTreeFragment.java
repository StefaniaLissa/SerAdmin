package com.example.seradmin.Tree;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seradmin.R;
import com.example.seradmin.Tree.ControladoresTree.TreeViewAdapter;
import com.example.seradmin.Tree.ControladoresTree.TreeViewHolderFactory;
import com.google.firebase.database.core.utilities.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FileTreeFragment extends Fragment {

    private TreeViewAdapter treeViewAdapter;

    private static final String TAG = "FileTreeFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_arbol, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.files_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setNestedScrollingEnabled(false);

        TreeViewHolderFactory factory = (v, layout) -> new FileViewHolder(v);

        treeViewAdapter = new TreeViewAdapter(factory);
        recyclerView.setAdapter(treeViewAdapter);

        TreeNode javaDirectory = new TreeNode("Java", R.layout.list_item_file);
        javaDirectory.addChild(new TreeNode("FileJava1.java", R.layout.list_item_file));
        javaDirectory.addChild(new TreeNode("FileJava2.java", R.layout.list_item_file));
        javaDirectory.addChild(new TreeNode("FileJava3.java", R.layout.list_item_file));

//        TreeNode gradleDirectory = new TreeNode("Gradle", R.layout.list_item_file);
//        gradleDirectory.addChild(new TreeNode("FileGradle1.gradle", R.layout.list_item_file));
//        gradleDirectory.addChild(new TreeNode("FileGradle2.gradle", R.layout.list_item_file));
//        gradleDirectory.addChild(new TreeNode("FileGradle3.gradle", R.layout.list_item_file));
//
//        javaDirectory.addChild(gradleDirectory);
//
        TreeNode lowLevelRoot = new TreeNode("LowLevel", R.layout.list_item_file);

        List<TreeNode> fileRoots = new ArrayList<>();
        fileRoots.add(javaDirectory);
//        fileRoots.add(lowLevelRoot);
//        fileRoots.add(cSharpDirectory);
//        fileRoots.add(gitFolder);

        treeViewAdapter.updateTreeNodes(fileRoots);

        treeViewAdapter.setTreeNodeClickListener((treeNode, nodeView) -> {
            Log.d(TAG, "Click on TreeNode with value " + treeNode.getValue().toString());
        });

        treeViewAdapter.setTreeNodeLongClickListener((treeNode, nodeView) -> {
            Log.d(TAG, "LongClick on TreeNode with value " + treeNode.getValue().toString());
            return true;
        });

        return view;
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        final int menuId = item.getItemId();
//        if (menuId == R.id.expand_all_action) {
//            treeViewAdapter.expandAll();
//        }
//        else if (menuId == R.id.collapse_all_action) {
//            treeViewAdapter.collapseAll();
//        }
//        else if (menuId == R.id.expand_selected_action) {
//            treeViewAdapter.expandNode(treeViewAdapter.getSelectedNode());
//        }
//        else if (menuId == R.id.collapse_selected_action) {
//            treeViewAdapter.collapseNode(treeViewAdapter.getSelectedNode());
//        }
//        else if (menuId == R.id.expand_selected_branch_action) {
//            treeViewAdapter.expandNodeBranch(treeViewAdapter.getSelectedNode());
//        }
//        else if (menuId == R.id.collapse_selected_branch_action) {
//            treeViewAdapter.collapseNodeBranch(treeViewAdapter.getSelectedNode());
//        }
//        else if (menuId == R.id.expand_selected_level_action) {
//            treeViewAdapter.expandNodesAtLevel(2);
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
