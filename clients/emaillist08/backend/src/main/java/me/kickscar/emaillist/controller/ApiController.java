package me.kickscar.emaillist.controller;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import me.kickscar.emaillist.dto.JsonResult;
import me.kickscar.emaillist.exception.GlobalExceptionHandler;
import me.kickscar.emaillist.vo.EmaillistVo;

@Slf4j
@RestController
public class ApiController {
	
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	
	//
	// @LoadBalanced: GW 사용
	// 
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/api")
	public ResponseEntity<JsonResult> read(@RequestParam(value="kw", required=true, defaultValue="") String keyword) {		
		LOGGER.info("Request[GET /api]");
		
		// GW 사용
		// EmaillistVo[] result = restTemplate.getForObject("http://service-emaillist/api?kw="+keyword, EmaillistVo[].class);
		//
		EmaillistVo[] result = restTemplate.getForObject("http://localhost:5555/emaillist/api?kw="+keyword, EmaillistVo[].class);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JsonResult.success(Arrays.asList(result)));
	}
	
	@PostMapping("/api")
	public ResponseEntity<JsonResult> create(@RequestBody EmaillistVo vo) {
		// GW 사용
		// EmaillistVo result = restTemplate.postForObject("http://service-emaillist/api", vo, EmaillistVo.class);
		// 
		EmaillistVo result = restTemplate.postForObject("http://localhost:5555/emaillist/api", vo, EmaillistVo.class);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JsonResult.success(result));
	}
}
