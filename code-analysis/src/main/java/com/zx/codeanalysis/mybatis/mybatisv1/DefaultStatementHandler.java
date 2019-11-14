package com.zx.codeanalysis.mybatis.mybatisv1;

import com.zx.codeanalysis.base.DateSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author zhouxin
 * @date 2018/10/22
 */
public class DefaultStatementHandler implements StatementHandler{

    private final static Logger logger = LoggerFactory.getLogger(DefaultStatementHandler.class);

    private MyConfiguration configuration;

    public DefaultStatementHandler(MyConfiguration configuration) {
        this.configuration = configuration;
    }

    public <T> T query(String statement, Object[] parameter, DateSource dateSource, Class<T> returnType) {
        logger.info("StatementHandler执行");
        ResultSet resultSet;
        try (Connection connection = DriverManager.getConnection(dateSource.getUrl()
                , dateSource.getUserName(), dateSource.getPassword())) {
            Class.forName(dateSource.getDriverClass());
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            for (int i = 0; parameter != null && i < parameter.length; i++) {
                preparedStatement.setString(i+1, String.valueOf(parameter[i]));
            }
            resultSet = preparedStatement.executeQuery();
            return configuration.getResultSetHandler().handler(resultSet, returnType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
