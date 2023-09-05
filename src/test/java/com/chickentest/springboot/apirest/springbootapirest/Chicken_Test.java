package com.chickentest.springboot.apirest.springbootapirest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import com.chickentest.springboot.apirest.springbootapirest.dao.IChickenDao;
import com.chickentest.springboot.apirest.springbootapirest.dao.IFarmDao;
import com.chickentest.springboot.apirest.springbootapirest.models.Farm;
import com.chickentest.springboot.apirest.springbootapirest.models.Chicken;

@DataJpaTest 
@ActiveProfiles("test")
public class Chicken_Test {

	@Autowired
	private IChickenDao chickenDao;
	
	@Autowired
	private IFarmDao farmDao;
	
	@Test
	public void saveChicken() {
		
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
		
		Chicken chicken = new Chicken(10, 1, 1, savedFarm);
		chicken.setPrice(20);
		chicken.setDays(2);
		chicken.setDaysSinceLastEggs(2);
		chicken.setFarm(savedFarm);
		
		Chicken savedChicken = chickenDao.save(chicken);		
		Chicken retrievedChicken = chickenDao.findById(savedChicken.getId()).orElse(null);		
	
		assertThat(retrievedChicken.getPrice()).isEqualTo(20);
		assertThat(retrievedChicken.getDays()).isEqualTo(2);
		assertThat(retrievedChicken.getDaysSinceLastEggs()).isEqualTo(2);
		assertThat(retrievedChicken.getFarm().getId()).isEqualTo(savedFarm.getId());
		assertThat(Chicken.getZero()).isEqualTo(0);
		assertThat(Chicken.getDaysoflife()).isEqualTo(15);
		assertThat(Chicken.getDaysToPutEggs()).isEqualTo(5);
		assertThat(Chicken.getDaysWhenBoughtChicken()).isEqualTo(2);
		assertThat(Chicken.getEggsByChicken()).isEqualTo(2);

	}
	
}
