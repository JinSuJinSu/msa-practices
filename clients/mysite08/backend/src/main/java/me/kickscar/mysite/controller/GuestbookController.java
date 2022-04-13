package me.kickscar.mysite.controller;

import me.kickscar.mysite.dto.JsonResult;
import me.kickscar.mysite.vo.GuestbookVo;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/api/guestbook")
public class GuestbookController {

	private final RestTemplate restTemplate;

	public GuestbookController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("")
	public ResponseEntity<JsonResult> get(@RequestParam(value="no", required=true, defaultValue="0") Long startNo) {
		GuestbookVo[] response = restTemplate.getForObject("http://localhost:5555/guestbook/api?no="+startNo, GuestbookVo[].class);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(Arrays.asList(response)));
	}

	@PostMapping("")
	public ResponseEntity<JsonResult> post(@RequestBody GuestbookVo vo) {
		GuestbookVo response = restTemplate.postForObject("http://localhost:5555/guestbook/api", vo, GuestbookVo.class);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(response));
	}

	@DeleteMapping("/{no}")
	public ResponseEntity<JsonResult> delete(@PathVariable("no") Long no, @RequestParam(value="password", required=true, defaultValue="") String password) {
		// Header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		System.out.println("::::::::::::::::" + password);
		// Body
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("password", password);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		ResponseEntity<?> response = restTemplate.exchange("http://localhost:5555/guestbook/api/" + no, HttpMethod.POST, requestEntity, HashMap.class);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(response.getBody()));
	}
}