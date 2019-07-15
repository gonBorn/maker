package com.platform.maker.repository;

import com.platform.maker.dataobject.CourseSchool;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseSchoolRepositoryTest {
    @Autowired
    private CourseSchoolRepository repository;

    @Test
    public void saveTest(){
        CourseSchool courseSchool = new CourseSchool(2,"123","1号楼","111",new Date(),new Date(2016,7,16));
        /** year - year 减去 1900，它必须是 0 到 8099 之间的数。（注意，8099 是由 9999 减去 1900 得到的。）
         month - 0 到 11 之间的数
         day - 1 到 31 之间的数 */
        CourseSchool res = repository.save(courseSchool);
        Assert.assertNotNull(res);
    }
    @Test
    public void findByEndTimeLessThanOrderByStartTime() {
        List<CourseSchool> courseSchoolList = repository.findByEndTimeLessThanOrderByStartTime(new Date());
        Assert.assertNotNull(courseSchoolList);
    }
}