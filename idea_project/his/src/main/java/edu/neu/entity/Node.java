package edu.neu.entity;

/**
 * ClassName:Node
 * Package:edu.neu.entity
 * Description: 页面中的zTree需要用到的节点
 *
 * @Date:2019/9/1 19:55
 * @Author:HetFrame
 */
public class Node {
    private int id;
    private int pid;
    private String name;
    private String open;
    private String isParent;
    private String file;

    public Node(int id, int pid, String name,String open) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.open = open;
    }

    public Node(int id, int pid, String name, String open, String isParent) {
        super();
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.open = open;
        this.isParent = isParent;
    }

    public Node(int id, int pid, String name, String open, String isParent, String file) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.open = open;
        this.isParent = isParent;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
