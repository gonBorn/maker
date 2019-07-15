package com.platform.maker.repository;

import com.platform.maker.dataobject.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationRepositoryTest {
    @Autowired
    private ApplicationRepository repository;

    @Test
    public void  findByCourseIdInOrderByAppStatusTest() {
        List<Integer> list = Arrays.asList(1,2,3);
        List<Application> res = repository.findByCourseIdInOrderByAppStatus(list);
        System.out.println(res.size());
    }
}