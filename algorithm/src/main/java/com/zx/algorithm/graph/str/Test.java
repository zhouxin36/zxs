package com.zx.algorithm.graph.str;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author zhouxin
 * @since 2019/6/4
 */
public class Test {

    private final static Logger logger = LoggerFactory.getLogger(Test.class);

    private static String getFileStr(){
        File f = new File("F:/ideaproject/zxs/algorithm/build.gradle");
        StringBuilder stringBuilder = new StringBuilder();
        String str;
        if(f.exists()){
            try {
                BufferedReader fileReader = new BufferedReader(new FileReader(f));
                while ((str = fileReader.readLine()) != null){
                    stringBuilder.append(str);
                }
            }catch (Exception e){
                throw new RuntimeException("文件不存在");
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String fileStr = getFileStr();
        String str = "('junit:junit:4.12')";
        KMP kmp = new KMP(str);
        BM bm = new BM(str);
        logger.info("位置:{}",kmp.search(fileStr));
        logger.info("位置:{}",kmp.KMPsearch(fileStr));
        logger.info("位置:{}",bm.BMsearch(fileStr));
    }
}
