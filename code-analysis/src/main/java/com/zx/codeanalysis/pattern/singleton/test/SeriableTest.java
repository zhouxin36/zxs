package com.zx.codeanalysis.pattern.singleton.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author zhouxin
 * @date 2018/10/16
 */
public class SeriableTest {

    private final static Logger logger = LoggerFactory.getLogger(SeriableTest.class);

    public static void main(String[] args) {

        Seriable s1 = null;
        Seriable s2 = Seriable.getInstance();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("Seriable.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();


            FileInputStream fis = new FileInputStream("Seriable.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s1 = (Seriable)ois.readObject();
            ois.close();

            logger.info(s1.toString());
            logger.info(s2.toString());
            logger.info((s1 == s2) + "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
