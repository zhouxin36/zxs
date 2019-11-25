package com.zx.jdkanalysis.security.permissions2;

import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author zhouxin
 * @since 2019/3/26
 */
public class FileUtil {
  // 工程 A 执行文件的路径
  private static final String FOLDER_PATH = "E:\\idea-project\\zxs\\jdk-analysis\\src\\main\\java\\com\\zx\\jdkanalysis\\security\\permissions2";

  public static void makeFile(String fileName) {
    try {
      // 尝试在工程 A 执行文件的路径中创建一个新文件
      File fs = new File(FOLDER_PATH + "\\" + fileName);
      fs.createNewFile();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void doPrivilegedAction(final String fileName) {
    // 用特权访问方式创建文件
    AccessController.doPrivileged((PrivilegedAction<String>) () -> {
      makeFile(fileName);
      return null;
    });
  }
}
