package com.fernando.zeus.zeusapi;

import com.fernando.zeus.zeusapi.services.DemandaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZeusapiApplicationTests {

	@Autowired
	DemandaService demandaService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testePesquisa(){

	}


}
