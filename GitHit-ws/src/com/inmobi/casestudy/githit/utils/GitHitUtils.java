package com.inmobi.casestudy.githit.utils;

import javax.servlet.http.HttpServletResponse;

public class GitHitUtils {
	public static void enableCORS(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
	}
}
