package com.zx.jdkanalysis.security.permissions;

public class PermissionTest {


    public static void main(String[] args) throws Exception {
        System.setProperty("java.security.policy", "E:/idea-project/zxs/jdk-analysis/src/main/java/com/zx/jdkanalysis/security/permissions/PermissionTest.policy");
        System.setSecurityManager(new SecurityManager());
        // 过滤元素
        WordCheckPermission p = new WordCheckPermission("C+", "insert");
        SecurityManager manager = System.getSecurityManager();
        try{
            if(manager != null){
                manager.checkPermission(p);
                System.out.println("权限通过"+p);
            }
        }catch (SecurityException ex){
            System.out.println("I am sorry, but I cannot do that.");
            ex.printStackTrace();
        }
    }
}

