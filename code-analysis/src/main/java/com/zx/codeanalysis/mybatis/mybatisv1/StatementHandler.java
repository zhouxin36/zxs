package com.zx.codeanalysis.mybatis.mybatisv1;

import com.zx.codeanalysis.base.DateSource;

/**
 * @author zhouxin
 * @date 2018/10/23
 */
public interface StatementHandler {
    public <T> T query(String statement, Object[] parameter, DateSource dateSource, Class<T> returnType);
}
