package org.lscode.commons.utils;

import org.apache.commons.collections.CollectionUtils;
import org.lscode.commons.model.CommonTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonTreeUtils {

    public CommonTree assembleTree(List<CommonTree> unfoldDefaultTrees) {

        if(CollectionUtils.isEmpty(unfoldDefaultTrees)){
            return null;
        }
        CommonTree root = new CommonTree();
        root.setId("root");
        root.setPid("null");
        root.setName("全部类型");
        for(CommonTree node : unfoldDefaultTrees){
            if(node.getPid() == null){
                node.setPid("root");
            }
        }
        unfoldDefaultTrees.add(root);

        //分组
//        unfoldDefaultTrees.get(0).setPid("root");
        Map<String, List<CommonTree>> collect = unfoldDefaultTrees.stream().collect(Collectors.groupingBy(CommonTree::getPid));
        //树形结构 肯定有一个根部，我的这个根部的就是parentId.euqal("0"),而且只有一个就get（"0"）
//        CommonTree defaultTree = collect.get("root").get(0);
        //拼接数据
        forEach(collect, root);

        return root;
    }

    private void addChild(CommonTree parent, CommonTree child){
        if(parent.getChildren() == null){
            parent.setChildren(new ArrayList<>());
        }
        parent.getChildren().add(child);
    }

    private static void forEach(Map<String, List<CommonTree>> collect, CommonTree root) {
        List<CommonTree> defaultTrees = collect.get(root.getId());
        if(defaultTrees != null){
            //排序
            defaultTrees.sort(Comparator.comparing(CommonTree::getLevel));
            defaultTrees.stream().sorted(Comparator.comparing(CommonTree::getLevel)).collect(Collectors.toList());
            root.setChildren(defaultTrees);
            root.getChildren().forEach(t->{
                forEach(collect,t);
            });
        }
    }
}
