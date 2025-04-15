package com.qwerky9.ItemViewer.entity;

import jakarta.persistence.*;

@Entity
@Table(name="item_stats")
public class Item {
    @Id
    @Column(name = "id_name" , unique = true)
    private Integer idName;
    @Column(name = "item_name")
    private String name;
    @Column(name = "item_option")
    private String option;
    @Column(name = "item_time")
    private String time;
    @Column(name = "item_meshfilename")
    private String meshfilename;
    @Column(name = "item_desc")
    private String desc;


    public Item() {
    }

    public Item(Integer Id,Integer idName, String name, String option, String time, String meshfilename, String desc) {
        this.idName = idName;
        this.name = name;
        this.option = option;
        this.time = time;
        this.meshfilename = meshfilename;
        this.desc = desc;
    }

    public Integer getIdName() {
        return idName;
    }

    public void setIdName(Integer idName) {
        this.idName = idName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMeshfilename() {
        return meshfilename;
    }

    public void setMeshfilename(String meshfilename) {
        this.meshfilename = meshfilename;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
