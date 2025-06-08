package com.sachin.SpringCloudAPIGateway.filters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(1)
public class RequestTraceFilter implements GlobalFilter
{
    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        String correlationID = null;
        HttpHeaders reqHeaders = exchange.getRequest().getHeaders();

        if (isCorrelationIdPresent(reqHeaders))
        {
            logger.debug("Bank-Correlation-ID found in RequestTraceFilter = " + filterUtility.getCorrelationId(reqHeaders));
        }
        else
        {
            correlationID = generateCorrelationID();
            exchange = filterUtility.setCorrelationId(exchange, correlationID);
            logger.debug("BANK-CORRELATION-ID generated in RequestTraceFilter = ", correlationID);
        }
        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders)
    {
        if (filterUtility.getCorrelationId(requestHeaders) != null)
            return true;
        else
            return false;
    }

    private String generateCorrelationID()
    {
        return java.util.UUID.randomUUID().toString();
    }
}
