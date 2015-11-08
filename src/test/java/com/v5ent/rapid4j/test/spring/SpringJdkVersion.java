package com.v5ent.rapid4j.test.spring;

import org.springframework.core.JdkVersion;

public class SpringJdkVersion {
	public static void main(String[] args) {
		System.out.println(JdkVersion.getMajorJavaVersion());
		System.out.println(JdkVersion.getJavaVersion());
	}
}
