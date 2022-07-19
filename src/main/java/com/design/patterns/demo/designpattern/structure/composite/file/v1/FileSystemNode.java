package com.design.patterns.demo.designpattern.structure.composite.file.v1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * FileSystemNode类
 *
 * 需求：设计一个类来表示文件系统中的目录，能方便地实现下面这些功能：
 *
 * 1. 动态地添加、删除某个目录下的子目录或文件；
 * 2. 统计指定目录下的文件个数；
 * 3. 统计指定目录下的文件总大小。
 *
 * @author wangjixue
 * @date 7/19/22 11:14 PM
 */
@Data
public class FileSystemNode {
    private String path;
    private boolean isFile;
    private List<FileSystemNode> subNodes = new ArrayList<>();

    public FileSystemNode(String path, boolean isFile) {
        this.path = path;
        this.isFile = isFile;
    }

    /**
     * 动态地添加某个目录下的子目录或文件
     *
     * @param node
     */
    public void addSubNode(FileSystemNode node) {
        subNodes.add(node);
    }

    /**
     * 动态地删除某个目录下的子目录或文件
     *
     * @param node
     */
    public void removeSubNode(FileSystemNode node) {
        int size = subNodes.size();
        int i = 0;
        for (; i < size; i++) {
            if(subNodes.get(i).getPath().equalsIgnoreCase(node.getPath())){
                break;
            }
        }
        if(i < size){
            subNodes.remove(i);
        }

    }

    /**
     * 统计指定目录下的文件个数
     * 注意：使用的是递归排序算法
     * @return
     */
    public int countNumOfFiles() {
        if(isFile){
            return 1;
        }
        int numOfFiles = 0;
        for (FileSystemNode subNode : subNodes) {
            numOfFiles += subNode.countNumOfFiles();
        }
        return numOfFiles;
    }

    /**
     * 统计指定目录下的文件大小
     * 注意：使用的是递归排序算法
     * @return
     */
    public long countSizeOfFiles() {
        if(isFile){
            File file = new File(path);
            if(!file.exists()){
                return 0;
            }
            return file.length();
        }
        long sizeOfFiles = 0;
        for (FileSystemNode subNode : subNodes) {
            sizeOfFiles += subNode.countSizeOfFiles();
        }
        return 0l;
    }

}
