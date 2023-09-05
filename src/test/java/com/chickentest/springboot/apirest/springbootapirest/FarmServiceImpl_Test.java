package com.chickentest.springboot.apirest.springbootapirest;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.chickentest.springboot.apirest.springbootapirest.dao.IFarmDao;
import com.chickentest.springboot.apirest.springbootapirest.models.Farm;
import com.chickentest.springboot.apirest.springbootapirest.services.IFarmService;

@SpringBootTest
@ActiveProfiles("test")
public class FarmServiceImpl_Test {

	@Autowired
	private IFarmDao farmDao;	
	
	@Autowired
	private IFarmService farmService;

	@Test
	public void findAndUpdateFarm_Test() {
		
		Farm farm = new Farm();
		farm.setName("Service Farm");
		farm.setMoney(2500);
		farm.setLimitOfEggs(50);
		farm.setLimitOfChickens(15);
		farm.setSellingEggPrice(10);
		farm.setSellingChickenPrice(15);
		farm.setBuyingChickenPrice(12);
		farm.setBuyingEggPrice(13);

		Farm savedFarm = farmDao.save(farm);		
		Farm retrievedFarm = farmService.findById(savedFarm.getId());	
		assertThat(retrievedFarm.getName()).isEqualTo("Service Farm");

		List<Farm> farmList = (List<Farm>) farmService.findAll();
		assertThat(farmList.size()).isEqualTo(1);
		assertThat(farmList.get(0).getName()).isEqualTo("Service Farm");
		
		retrievedFarm.setLimitOfEggs(80);		
		Farm updatedFarm = farmService.update(retrievedFarm.getId(), retrievedFarm);
		assertThat(updatedFarm.getLimitOfEggs()).isEqualTo(80);
		
	}
	
	
	
}
