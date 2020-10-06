package com.mdomeck.codefellowship;

import com.mdomeck.codefellowship.controllers.ApplicationUserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class CodefellowshipApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ApplicationUserController applicationUserController;


	@Test
	void contextLoads() {
	}

//	@Test
//	public void homePageShouldRenderWithH3AndA(){
//		this.mockMvc.perform((get("/")))
//	}


}
