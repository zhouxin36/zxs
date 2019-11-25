package com.zx.distributed;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DistributedApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(DistributedApplication.class)
        .run(args);
  }
}
