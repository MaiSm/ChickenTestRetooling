package com.chickentest.springboot.apirest.springbootapirest;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import com.chickentest.springboot.apirest.springbootapirest.dao.IChickenDao;
import com.chickentest.springboot.apirest.springbootapirest.dao.IFarmDao;
import com.chickentest.springboot.apirest.springbootapirest.models.Chicken;
import com.chickentest.springboot.apirest.springbootapirest.models.Farm;
 
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class FarmRestController_Test {
	
	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private IFarmDao farmDao;	
	
	@Autowired 
	private IChickenDao chickenDao;
	

	@Test
	public void findAll_Test() {
		
		ResponseEntity<?> responseNull = this.restTemplate.getForEntity("http://localhost:" +port+"/farms", String.class);
		assertThat(responseNull.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		
		Farm farm = new Farm();
		farm.setName("Controller Farm");
		farm.setMoney(2500);
		farm.setLimitOfEggs(50);
		farm.setLimitOfChickens(15);
		farm.setSellingEggPrice(10);
		farm.setSellingChickenPrice(15);
		farm.setBuyingChickenPrice(12);
		farm.setBuyingEggPrice(13);

		farmDao.save(farm);		
	
		ResponseEntity<Farm[]> response = this.restTemplate.getForEntity("http://localhost:" +port+"/farms", Farm[].class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		Farm[] retrievedFarms = response.getBody();
		assertThat(retrievedFarms.length).isEqualTo(1);
		assertThat(retrievedFarms[0].getName()).isEqualTo("Controller Farm");
	}
	
	@Test
	public void findById_Test() {
		Farm farm = new Farm();
		farm.setName("Controller Farm");
		farm.setMoney(2500);
		farm.setLimitOfEggs(50);
		farm.setLimitOfChickens(15);
		farm.setSellingEggPrice(10);
		farm.setSellingChickenPrice(15);
		farm.setBuyingChickenPrice(12);
		farm.setBuyingEggPrice(13);

		Farm savedFarm = farmDao.save(farm);		
		
		ResponseEntity<Farm> response = this.restTemplate.getForEntity("http://localhost:" +port+"/farms/"+savedFarm.getId(), Farm.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		Farm retrieveFarm = response.getBody();
		assertThat(retrieveFarm.getName()).isEqualTo("Controller Farm");
		
		ResponseEntity<String> responseNull = this.restTemplate.getForEntity("http://localhost:" +port+"/farms/"+(savedFarm.getId()+1), String.class);
		assertThat(responseNull.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	
	}
	
	@Test
	public void update_Test() {
		
		Farm farm = new Farm();
		farm.setName("Controller Farm");
		farm.setMoney(2500);
		farm.setLimitOfEggs(50);
		farm.setLimitOfChickens(15);
		farm.setSellingEggPrice(10);
		farm.setSellingChickenPrice(15);
		farm.setBuyingChickenPrice(12);
		farm.setBuyingEggPrice(13);

		Farm savedFarm = farmDao.save(farm);
				
		savedFarm.setLimitOfEggs(500);
		savedFarm.setLimitOfChickens(150);
		savedFarm.setSellingEggPrice(100);
		savedFarm.setSellingChickenPrice(150);
		savedFarm.setBuyingChickenPrice(120);
		savedFarm.setBuyingEggPrice(0);
		
		this.restTemplate.put("http://localhost:" +port+"/farms/" + savedFarm.getId(), savedFarm);
		
		Farm notUpdatedFarm = farmDao.findById(savedFarm.getId()).orElse(null);
		assertThat(notUpdatedFarm.getLimitOfEggs()).isEqualTo(50);
		
		savedFarm.setBuyingEggPrice(130);

		this.restTemplate.put("http://localhost:" +port+"/farms/" + savedFarm.getId(), savedFarm);

		Farm updatedFarm = farmDao.findById(savedFarm.getId()).orElse(null);
		assertThat(updatedFarm.getName()).isEqualTo("Controller Farm");
		assertThat(updatedFarm.getMoney()).isEqualTo(2500);
		assertThat(updatedFarm.getLimitOfEggs()).isEqualTo(500);
		assertThat(updatedFarm.getLimitOfChickens()).isEqualTo(150);
		assertThat(updatedFarm.getSellingEggPrice()).isEqualTo(100);
		assertThat(updatedFarm.getSellingChickenPrice()).isEqualTo(150);
		assertThat(updatedFarm.getBuyingChickenPrice()).isEqualTo(120);
		assertThat(updatedFarm.getBuyingEggPrice()).isEqualTo(130);

	}
	
	@Test
	public void addDays_Test() {
					
		Farm farm = new Farm();
		farm.setName("Controller Farm");
		farm.setMoney(2500);
		farm.setLimitOfEggs(50);
		farm.setLimitOfChickens(15);
		farm.setSellingEggPrice(10);
		farm.setSellingChickenPrice(15);
		farm.setBuyingChickenPrice(12);
		farm.setBuyingEggPrice(13);

		Farm savedFarm = farmDao.save(farm);
		
		this.restTemplate.getForEntity("http://localhost:" +port+"/farms/" + savedFarm.getId()+ "/chickens/buy/2", String.class);
		List<Chicken> chickens = (List<Chicken>) chickenDao.findAll();		
		for(Chicken eachChicken : chickens) {
			eachChicken.setFarm(savedFarm);
			chickenDao.save(eachChicken);
		}
		
		Chicken chickenControl = chickens.get(0);
		int oldDays = chickenControl.getDays();
		int oldDaysSinceLastEggs = chickenControl.getDaysSinceLastEggs();
		int daysMoved = 2;

		ResponseEntity<String> responseWrong = this.restTemplate.getForEntity("http://localhost:"+port+"/farms/time/"+8, String.class);
		assertThat(responseWrong.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);

		ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:"+port+"/farms/time/"+daysMoved, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	
		Chicken chickenUpdated = chickenDao.findById(chickenControl.getId()).orElse(null);		
		assertThat(chickenUpdated.getDays()).isEqualTo(oldDays + daysMoved);
		assertThat(chickenUpdated.getDaysSinceLastEggs()).isEqualTo(oldDaysSinceLastEggs + daysMoved);

	}
	
}
