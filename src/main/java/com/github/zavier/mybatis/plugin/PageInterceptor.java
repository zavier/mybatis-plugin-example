package com.github.zavier.mybatis.plugin;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;

// 需要添加拦截器注解
@Intercepts({
        // 这里需要声明拦截的类，方法名称、参数信息
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class PageInterceptor implements Interceptor {
    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    public static void page(Integer page, Integer size) {
        final Page p = new Page();
        p.setPage(page);
        p.setSize(size);
        pageThreadLocal.set(p);
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Page page = pageThreadLocal.get();
        if (page == null) {
            return invocation.proceed();
        }

        try {
            final int offset = (page.getPage() - 1) * page.getSize();

            final StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            final BoundSql boundSql = statementHandler.getBoundSql();
            final String sql = boundSql.getSql();

            // SQL是否是查询
            final boolean selectSql = isSelectSql(sql);
            if (selectSql) {
                // 修改SQL数据后重新赋值回去
                String newSql = sql + " limit " + page.getSize() + " offset " + offset;
                final MetaObject metaObject = SystemMetaObject.forObject(boundSql);
                metaObject.setValue("sql", newSql);
            }
        } finally {
            pageThreadLocal.remove();
        }

        return invocation.proceed();
    }

    private boolean isSelectSql(String sql) {
        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, DbType.mysql);
        final SQLStatement sqlStatement = parser.parseStatement();
        return sqlStatement instanceof SQLSelectStatement;
    }

    static class Page {
        private Integer page;
        private Integer size;

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }
    }
}