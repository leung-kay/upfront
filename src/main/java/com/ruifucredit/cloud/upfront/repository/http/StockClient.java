package com.ruifucredit.cloud.upfront.repository.http;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.ruifucredit.cloud.inventory.support.dto.Stock;
import com.ruifucredit.cloud.kit.dto.Outcoming;

import lombok.SneakyThrows;

@Repository
public class StockClient {
	
	@Autowired
	private RestTemplate rest;

	@SneakyThrows
	public Outcoming<List<Stock>> queryStocks(String url) {

		RequestEntity<Void> request = RequestEntity.get(new URI(url)).accept(MediaType.APPLICATION_JSON)
				.acceptCharset(StandardCharsets.UTF_8).build();

		ResponseEntity<Outcoming<List<Stock>>> responseFromGoods = rest.exchange(request,
				new ParameterizedTypeReference<Outcoming<List<Stock>>>() {
				});

		if (responseFromGoods.getStatusCode() == HttpStatus.OK) {
			return responseFromGoods.getBody();
		} else {
			throw new RuntimeException(responseFromGoods.toString());
		}

	}
	
}
