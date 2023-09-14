package com.ssafy.chartflow;

import com.ssafy.chartflow.info.service.RankingService;
import com.ssafy.chartflow.user.entity.User;
import com.ssafy.chartflow.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ChartflowApplication implements ApplicationRunner {
	@Autowired
	private UserService userService;

	@Autowired
	private RankingService rankingService;
	public static void main(String[] args) {
		SpringApplication.run(ChartflowApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		List<User> users = userService.getAllUsers();
		rankingService.initializeRanking(users);
	}
}
