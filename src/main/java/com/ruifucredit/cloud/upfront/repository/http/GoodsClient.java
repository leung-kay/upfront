package com.ruifucredit.cloud.upfront.repository.http;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.ruifucredit.cloud.commodity.support.dto.Goods;
import com.ruifucredit.cloud.kit.dto.Outcoming;

import lombok.SneakyThrows;

@Repository
public class GoodsClient {
	
	@Autowired
	private RestTemplate rest;
	
	@SneakyThrows
	public Outcoming<Goods> queryGoods(String url) {

		RequestEntity<Void> request = RequestEntity.get(new URI(url)).accept(MediaType.APPLICATION_JSON)
				.acceptCharset(StandardCharsets.UTF_8).build();

		ResponseEntity<Outcoming<Goods>> responseFromGoods = rest.exchange(request,
				new ParameterizedTypeReference<Outcoming<Goods>>() {
				});

		if (responseFromGoods.getStatusCode() == HttpStatus.OK) {
			return responseFromGoods.getBody();
		} else {
			throw new RuntimeException(responseFromGoods.toString());
		}

	}

}
