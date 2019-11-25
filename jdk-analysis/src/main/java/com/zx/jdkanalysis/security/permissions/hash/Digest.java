package com.zx.jdkanalysis.security.permissions.hash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

public class Digest {

  public static void main(String[] args) throws IOException, GeneralSecurityException {
    MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
    byte[] input = Files.readAllBytes(Paths.get("E:/idea-project/j2se/java8/src/main/java/com/spring/java8/permissions/auth/AuthTest.policy"));
    byte[] hash = messageDigest.digest(input);
    StringBuilder d = new StringBuilder();
    for (byte aHash : hash) {
      int v = aHash & 0xFF;
      if (v < 16) {
        d.append("0");
      }
      d.append(Integer.toString(v, 16).toUpperCase()).append(" ");
    }
    System.out.println(d);
  }
}
