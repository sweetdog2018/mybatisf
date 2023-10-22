package com.example.mybatisf.plugin;

import org.apache.ibatis.session.RowBounds;

public class PageRowBounds extends RowBounds {
    private Long total;

    public PageRowBounds(){
        super();
    }

    public PageRowBounds(int offset,int limit){
        super(offset,limit);
    }

    public Long getTotal(){
        return total;
    }

    public void setTotal(Long total){
        this.total=total;
    }
}
