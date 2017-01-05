package ru.javawebinar.topjava.service;


import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.JpaUtil;

public class AbstractJpaMealServiceTest extends AbstractMealServiceTest {

    @Autowired
    protected JpaUtil jpaUtil;

    @Before
    public void setUp() throws Exception {
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
