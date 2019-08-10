package max.lab.springboot.latest.borrowservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Slf4j
@Component
public class PayloadFilter extends ZuulFilter {
    private final ObjectMapper objectMapper;

    public PayloadFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        boolean isJson = context.getOriginResponseHeaders().stream()
                .filter(pair -> pair.first().equals(CONTENT_TYPE) && pair.second().contains(APPLICATION_JSON.getMimeType()))
                .findAny().isPresent();
        return isJson;
    }

    @Override
    public Object run() throws ZuulException {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            boolean isGZipped = context.getResponseGZipped();
            InputStream responseDataStream = context.getResponseDataStream();
            InputStream inputStream = isGZipped ? new GZIPInputStream(responseDataStream) : responseDataStream;
            Object payload = objectMapper.readValue(inputStream, Object.class);
            Map map = ImmutableMap.of("data", payload, "requestId", RandomUtils.nextInt(1000000));
            ByteArrayOutputStream bytesOutputStream = new ByteArrayOutputStream();
            OutputStream outputStream = isGZipped ? new GZIPOutputStream(bytesOutputStream) : bytesOutputStream;
            objectMapper.writeValue(outputStream, map);
            byte[] bytes = bytesOutputStream.toByteArray();
            context.setResponseDataStream(new ByteArrayInputStream(bytes));
        } catch (Exception e) {
            log.error("Failed to modify payload", e);
        }

        return null;
    }
}
