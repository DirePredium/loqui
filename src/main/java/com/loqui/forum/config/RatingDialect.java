package com.loqui.forum.config;

import com.loqui.forum.utils.RatingUtil;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Collections;
import java.util.Set;

@Configuration
public class RatingDialect extends AbstractDialect implements IExpressionObjectDialect {

    RatingDialect(){
        super("Rating Dialect");
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return new IExpressionObjectFactory() {
            @Override
            public Set<String> getAllExpressionObjectNames() {
                return Collections.singleton("ratingUtil");
            }

            @Override
            public Object buildObject(IExpressionContext context, String expressionObjectName) {
                return new RatingUtil();
            }

            @Override
            public boolean isCacheable(String expressionObjectName) {
                return true;
            }
        };
    }
}
