package com.github.zavier.mybatis.plugin;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


// 需要添加拦截器注解
@Intercepts({
        // 这里需要声明拦截的类，方法名称、参数信息
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
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

            // 1. 通过 invocation 获取拦截到的方法相关信息
            final Executor executor = (Executor) invocation.getTarget();
            final Object[] args = invocation.getArgs();

            // 2. 获取全部的参数(6个)
            MappedStatement mappedStatement = (MappedStatement) args[0];
            Object param = args[1];
            RowBounds rowBounds = (RowBounds) args[2];
            ResultHandler resultHandler = (ResultHandler) args[3];
            CacheKey cacheKey = null;
            BoundSql boundSql = null;
            // 拦截的是有boundSql参数的方法
            if (args.length == 6) {
                cacheKey = (CacheKey) args[4];
                boundSql = (BoundSql) args[5];
                // 修改boundSql中的 sql 语句
                changePageBoundSql(page, boundSql);
            }
            // 拦截的是无boundSql参数的方法
            if (args.length == 4) {
                boundSql = mappedStatement.getBoundSql(param);

                // 修改boundSql中的 sql 语句(先修改sql后再创建cacheKey)
                changePageBoundSql(page, boundSql);
                cacheKey = executor.createCacheKey(mappedStatement, param, rowBounds, boundSql);
            }

            // 3. 调用原方法执行
            return executor.query(mappedStatement, page, rowBounds, resultHandler, cacheKey, boundSql);

        } finally {
            pageThreadLocal.remove();
        }

    }

    private void changePageBoundSql(Page page, BoundSql boundSql) {
        final int offset = (page.getPage() - 1) * page.getSize();

        // 修改SQL，添加分页参数
        final String sql = boundSql.getSql();
        String newSql = sql + " limit " + page.getSize() + " offset " + offset;
        // 重新设置 BoundSql 实例中的 sql 属性值
        final MetaObject metaObject = SystemMetaObject.forObject(boundSql);
        metaObject.setValue("sql", newSql);
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