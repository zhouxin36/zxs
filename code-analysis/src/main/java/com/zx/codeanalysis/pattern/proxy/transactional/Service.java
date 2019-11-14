package com.zx.codeanalysis.pattern.proxy.transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zhouxin
 * @since 2019/2/22
 */
public class Service {


    @HEHETransactional
    public void hehe() throws SQLException {
        Connection connection = CGLIBTransactionalProxy.threadLocal.get();
        if(connection != null){
            PreparedStatement preparedStatement1 = connection.prepareStatement("update user set age = ? where id = ?");
            preparedStatement1.setObject(1,20);
            preparedStatement1.setObject(2,"dc1dc52e-79b9-11e8-9c15-fdc7eb479f4a");
            preparedStatement1.executeUpdate();

            PreparedStatement preparedStatement2 = connection.prepareStatement("update user set user_name = ? where id = ?");
            preparedStatement2.setObject(1,"hehe");
            preparedStatement2.setObject(2,"dc1dc52e-79b9-11e8-9c15-fdc7eb479f4a");
            preparedStatement2.executeUpdate();
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws SQLException {
        Service instance = (Service)new CGLIBTransactionalProxy().getInstance(Service.class);
        instance.hehe();
    }
}
