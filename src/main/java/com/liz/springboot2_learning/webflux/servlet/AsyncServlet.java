package com.liz.springboot2_learning.webflux.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * webflux中的异步servlet示例，此servlet仅限于tomcat容器
 * Created by liz on 2018/7/2 17:22
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/asyncServlet"})
public class AsyncServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long s1 = System.currentTimeMillis();
		/*
		  开启异步
		  调用此方法将导致相关响应的提交被延迟，直到在返回的{@link AsyncContext}上调用{@link AsyncContext＃complete}，或者异步操作已超时
		 */
		AsyncContext asyncContext = req.startAsync(req, resp);

		CompletableFuture.runAsync(() -> doSomething(asyncContext, req, resp));

		System.out.println("耗时：" + (System.currentTimeMillis() - s1));
	}

	private void doSomething(AsyncContext asyncContext, HttpServletRequest request, HttpServletResponse response) {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			response.getWriter().write("ojbk");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 通知结束
		asyncContext.complete();
	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doGet(req, resp);
//	}
}
