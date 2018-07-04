package com.liz.springboot2_learning.webflux.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * SSE(server-sent event)服务器推送事件
 * 每个事件之间通过空行来分隔。对于每一行来说，冒号（“:”）前面表示的是该行的类型，冒号后面则是对应的值。可能的类型包括：
 * 类型为空白，表示该行是注释，会在处理时被忽略。
 * 类型为 data，表示该行包含的是数据。以 data 开头的行可以出现多次。所有这些行都是该事件的数据。
 * 类型为 event，表示该行用来声明事件的类型。浏览器在收到数据时，会产生对应类型的事件。
 * 类型为 id，表示该行用来声明事件的标识符。
 * 类型为 retry，表示该行用来声明浏览器在连接断开之后进行再次连接之前的等待时间。
 * Created by liz on 2018/7/3 11:20
 */
@WebServlet("/sse")
public class SSEServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 注意Content-type
		resp.setContentType("text/event-stream");
		resp.setCharacterEncoding("UTF-8");

		for (int i = 0; i < 5; i++) {
			resp.getWriter().write("testEvent\n");
			resp.getWriter().write("id:" + i + "\n");
			resp.getWriter().write("event:event\n");
			resp.getWriter().write("data:" + i + "\n");
			resp.getWriter().write("retry:" + i * 100 + "\n\n");
			resp.getWriter().flush();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
