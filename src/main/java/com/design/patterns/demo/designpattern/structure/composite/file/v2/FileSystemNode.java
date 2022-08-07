package com.design.patterns.demo.designpattern.structure.composite.file.v2;

import java.util.ArrayList;
import java.util.List;

/**
 * FileSystemNode类
 *
 * 如果我们开发的是一个大型系统，
 * 从扩展性（文件或目录可能会对应不同的操作）、
 * 业务建模（文件和目录从业务上是两个概念）、
 * 代码的可读性（文件和目录区分对待更加符合人们对业务的认知）的角度来说，
 * 我们最好对文件和目录进行区分设计，定义为 File 和 Directory 两个类。
 *
 * 按照这个设计思路，我们对代码进行重构。重构之后的代码如下所示：
 *
 * @author wangjixue
 * @date 8/7/22 5:43 PM
 */
public abstract class FileSystemNode {

    protected String path;

    public FileSystemNode(String path) {
        this.path = path;
    }

    public abstract int countNumOfFiles();

    public abstract long countSizeOfFiles();

    public String getPath() {
        return path;
    }
}

class File extends FileSystemNode {

    public File(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        return 1;
    }

    @Override
    public long countSizeOfFiles() {
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            return 0;
        }
        return file.length();
    }
}

class Directory extends FileSystemNode {
    private List<FileSystemNode> subNodes = new ArrayList<>();

    public Directory(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        int numOfFiles = 0;
        for (FileSystemNode subNode : subNodes) {
            numOfFiles += subNode.countNumOfFiles();
        }
        return numOfFiles;
    }

    @Override
    public long countSizeOfFiles() {
        long countOfFiles = 0;
        for (FileSystemNode subNode : subNodes) {
            countOfFiles += subNode.countSizeOfFiles();
        }
        return countOfFiles;
    }

    public void addSubNode(FileSystemNode node) {
        subNodes.add(node);
    }

    public void removeSubNode(FileSystemNode node) {
        int size = subNodes.size();
        int i = 0;
        for (; i < size; i++) {
            if (subNodes.get(i).getPath().equalsIgnoreCase(node.getPath())) {
                break;
            }
        }
        if (i < size) {
            subNodes.remove(i);
        }
    }
}


