package com.cbc.myblog;

import com.cbc.myblog.monitoring.MonitoringApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitWebConfig(classes = MonitoringApplication.class)
@WebAppConfiguration
public class MonitoringApplicationTest {

    @Test
    public void contextLoads() {
    }

}