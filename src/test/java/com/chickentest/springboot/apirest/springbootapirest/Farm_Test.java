package com.chickentest.springboot.apirest.springbootapirest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import com.chickentest.springboot.apirest.springbootapirest.dao.IFarmDao;
import com.chickentest.springboot.apirest.springbootapirest.models.Farm;

@DataJpaTest 
@ActiveProfiles("test")
public class Farm_Test {

	@Autowired
	private IFarmDao farmDao;
	
	@Test
	public void saveFarm() {
		
		Farm farm = new Farm();
		farm.setName("Little Farm");
		farm.setMoney(2500);
		farm.setLimitOfEggs(50);
		farm.setLimitOfChickens(15);
		farm.setSellingEggPrice(10);
		farm.setSellingChickenPrice(15);
		farm.setBuyingChickenPrice(12);
		farm.setBuyingEggPrice(13);

		Farm savedFarm = farmDao.save(farm);		
		Farm retrievedFarm = farmDao.findById(savedFarm.getId()).orElse(null);		
	
		assertThat(retrievedFarm.getName()).isEqualTo("Little Farm");
		assertThat(retrievedFarm.getMoney()).isEqualTo(2500);
		assertThat(retrievedFarm.getLimitOfEggs()).isEqualTo(50);
		assertThat(retrievedFarm.getLimitOfChickens()).isEqualTo(15);
		assertThat(retrievedFarm.getSellingEggPrice()).isEqualTo(10);
		assertThat(retrievedFarm.getSellingChickenPrice()).isEqualTo(15);
		assertThat(retrievedFarm.getBuyingChickenPrice()).isEqualTo(12);
		assertThat(retrievedFarm.getBuyingEggPrice()).isEqualTo(13);
		assertThat(Farm.getZero()).isEqualTo(0);
		assertThat(Farm.getPercentOfDiscount()).isEqualTo(0.5);
		assertThat(Farm.getMinDaysToMove()).isEqualTo(1);
		assertThat(Farm.getMaxDaysToMove()).isEqualTo(5);

	}
	
}
