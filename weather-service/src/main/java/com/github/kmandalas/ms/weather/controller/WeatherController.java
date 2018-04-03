package com.github.kmandalas.ms.weather.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@RestController("/weather")
@Slf4j
@RefreshScope
public class WeatherController {

	@Value("${default-city}")
	private String defaultCity;

	@GetMapping("/default")
	@ResponseStatus(HttpStatus.OK)
	public String getWeather() {
		log.info("");

		return "The weather in " + defaultCity + " is fine!";
	}

}
