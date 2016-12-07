package com.bytebeats.zookeeper;

import com.bytebeats.zookeeper.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2016-12-07 23:28
 */
public class BaseConfigDemo {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected String address;
    protected int timeout;

    public static void main(String[] args) {

        new BaseConfigDemo().start();
    }

    protected void start(){
        try {
            Properties props = PropertyUtils.load("zookeeper.properties");
            address = props.getProperty("address");
            timeout = Integer.parseInt(props.getProperty("timeout", "5000"));

            logger.info("address:{}, timeout:{}", address, timeout);

            start0();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void start0() throws IOException {

    }
}