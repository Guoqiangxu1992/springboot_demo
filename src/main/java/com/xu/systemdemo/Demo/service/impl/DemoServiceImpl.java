package com.xu.systemdemo.Demo.service.impl;

import com.xu.systemdemo.Demo.dao.DemoDao;
import com.xu.systemdemo.Demo.dto.Demo;
import com.xu.systemdemo.Demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService{
    @Autowired
    private DemoDao demoDao;
    @Override
    public List<Demo> test(Demo demo) {
        return demoDao.getListDemo(demo);
    }
}
