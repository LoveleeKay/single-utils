package com.llys.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.llys.data.Student;

/**
 * 构造树功能
 *
 * @author liyongsheng
 * @date 2021/12/23 13:38
 **/
public class TreeUtils {

    public static void main(String[] args) {
        // 构建node列表
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();

        Map map1 = new HashMap<>();
        map1.put("student", new Student("llys", 28));
        TreeNode<String> treeNode1 = new TreeNode<>("1", "0", "系统管理", 5);
        treeNode1.setExtra(map1);
        nodeList.add(treeNode1);
        nodeList.add(new TreeNode<>("11", "1", "用户管理", 222222));
        nodeList.add(new TreeNode<>("111", "11", "用户添加", 0));
        nodeList.add(new TreeNode<>("2", "0", "店铺管理", 1));
        nodeList.add(new TreeNode<>("21", "2", "商品管理", 44));
        nodeList.add(new TreeNode<>("221", "2", "商品管理2", 2));

        // 配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("order");
        treeNodeConfig.setIdKey("rid");
        // 最大递归深度
        treeNodeConfig.setDeep(3);

        // 转换器
        List<Tree<String>> treeNodes = TreeUtil.build(nodeList, "0", treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getWeight());
            tree.setName(treeNode.getName());
            // 扩展属性 ...
            tree.putExtra("extraField", 666);
            tree.putExtra("other", new Object());
            String key1 = "student";
            tree.putExtra(key1, Optional.ofNullable(treeNode.getExtra()).map((tmpMap) -> tmpMap.get(key1)).orElse("123"));
        });
        System.out.println(treeNodes);
        // 0表示最顶层的id是0
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");
        System.out.println(treeList);

    }
}
