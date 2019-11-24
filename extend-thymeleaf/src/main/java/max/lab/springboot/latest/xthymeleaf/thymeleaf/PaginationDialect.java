package max.lab.springboot.latest.xthymeleaf.thymeleaf;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Component
public class PaginationDialect extends AbstractDialect implements IExpressionObjectDialect {
    public PaginationDialect() {
        super("Pagination Dialect");
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return new IExpressionObjectFactory() {

            @Override
            public Set<String> getAllExpressionObjectNames() {
                return Collections.singleton("pg");
            }

            @Override
            public Object buildObject(IExpressionContext context, String expressionObjectName) {
                return new PaginationUtils();
            }

            @Override
            public boolean isCacheable(String expressionObjectName) {
                return false;
            }
        };
    }

    public static Optional<HttpServletRequest> getCurrentHttpRequest() {
        return ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(requestAttributes -> ServletRequestAttributes.class.isAssignableFrom(requestAttributes.getClass()))
                .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
                .map(ServletRequestAttributes::getRequest);
    }

    public static final class PaginationUtils {
        public String getTableHeaderUrl(Page<?> page, String column) {
            return getCurrentHttpRequest().map(request -> {
                String servletPath = request.getServletPath();
                Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());
                parameterMap.put("column", new String[] { column });
                String order = ofNullable(page.getSort().getOrderFor(column))
                        .map(o -> o.isAscending() ? DESC : ASC)
                        .orElse(ASC).toString();
                parameterMap.put("order", new String[] { order });
                parameterMap.put("page", new String[] { String.valueOf(page.getNumber()) });
                String queryString = getQueryString(parameterMap);
                return String.format("%s?%s", servletPath, queryString);
            }).orElse("#");
        }

        private String getQueryString(Map<String, String[]> parameterMap) {
            return parameterMap.entrySet().stream()
                                .filter(e -> !"reset".equalsIgnoreCase(e.getKey()))
                                .map(e -> new AbstractMap.SimpleEntry<>(
                                        e.getKey(),
                                        Stream.of(e.getValue()).map(s -> {
                                            String str = s;
                                            try {
                                                str = URLEncoder.encode(s, UTF_8.toString());
                                            } catch (UnsupportedEncodingException e1) { }
                                            return str;
                                        }).collect(joining(","))
                                ))
                                .map(e -> String.format("%s=%s", e.getKey(), e.getValue()))
                                .collect(joining("&"));
        }

        public String getTableHeaderIconClass(Page<?> page, String column) {
            String upOrDown = ofNullable(page.getSort().getOrderFor(column))
                    .map(o -> o.isAscending() ? "up" : "down")
                    .orElse("up");
            return String.format("fa fa-long-arrow-%s fa-sm", upOrDown);
        }

        public boolean isSortingBy(Page<?> page, String column) {
            return ofNullable(page.getSort().getOrderFor(column)).isPresent();
        }

        public String getSortingColumn(Page<?> page) {
            return page.getSort().iterator().next().getProperty();
        }

        public String getSortingOrder(Page<?> page) {
            return (page.getSort().iterator().next().isAscending() ? ASC : DESC).toString();
        }

        public String getPageUrl(int index) {
            return getCurrentHttpRequest().map(request -> {
                String servletPath = request.getServletPath();
                Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());
                parameterMap.put("page", new String[] { String.valueOf(index) });
                String queryString = getQueryString(parameterMap);
                return String.format("%s?%s", servletPath, queryString);
            }).orElse("#");
        }
    }
}
