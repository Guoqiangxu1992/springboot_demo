package com.xu.systemdemo.Demo.dao;

import com.xu.systemdemo.Demo.dto.Demo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoDao {
    public List<Demo> getListDemo(Demo demo);

}
