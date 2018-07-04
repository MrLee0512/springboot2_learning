package com.liz.springboot2_learning.webflux.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by liz on 2018/7/3 14:17
 */
@RestController
public class WebFluxController {

	/**
	 * spring webflux实现sse
	 */
	@GetMapping("/sse")
	public Flux<ServerSentEvent<Integer>> randomNumbers() {
		return Flux.interval(Duration.ofSeconds(1))
				.map(l -> Tuples.of(l, ThreadLocalRandom.current().nextInt()))
				.map(tuple -> ServerSentEvent.<Integer>builder()
						.comment("comment")
						.id(String.valueOf(tuple.getT1()))
						.event("randomNumbers")
						.data(tuple.getT2())
						.retry(Duration.ofSeconds(10)).build());
	}

}
