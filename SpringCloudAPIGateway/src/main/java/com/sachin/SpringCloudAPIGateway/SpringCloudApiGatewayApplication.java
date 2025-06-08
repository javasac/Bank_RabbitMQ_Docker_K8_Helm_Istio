package com.sachin.SpringCloudAPIGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudApiGatewayApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringCloudApiGatewayApplication.class, args);
	}

	/**@Bean
	public RouteLocator BankRouteConfig(RouteLocatorBuilder routeLocatorBuilder)
	{
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/bank/accounts/**")
						.filters(f -> f.rewritePath("/bank/accounts/(?<segment>.*)", "/${segment}")
								.addRequestHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://ACCOUNTS"))
				.route(p -> p
						.path("/bank/cards/**")
						.filters(f -> f.rewritePath("/bank/cards/(?<segment>.*)", "/${segment}")
								.addRequestHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CARDS"))
				.route(p -> p
						.path("/bank/loans/**")
						.filters(f -> f.rewritePath("/bank/loans/(?<segment>.*)", "/${segment}")
								.addRequestHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://LOANS")).build();
	}*/
}
