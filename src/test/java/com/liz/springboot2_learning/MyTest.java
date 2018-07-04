package com.liz.springboot2_learning;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;

/**
 * Created by liz on 2018/7/3 15:52
 */
public class MyTest {

	@Test
	public void testWebClient() throws InterruptedException {
		Disposable disposable = WebClient.create("http://127.0.0.1:8080").get().uri("/sse").accept(MediaType.TEXT_EVENT_STREAM)
				.retrieve().bodyToFlux(Integer.class).subscribe(x -> {
					System.out.println(x);
				});

		Thread.sleep(10000);
		disposable.dispose();
	}
}
