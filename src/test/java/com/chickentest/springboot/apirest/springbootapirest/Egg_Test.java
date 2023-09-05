package com.chickentest.springboot.apirest.springbootapirest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import com.chickentest.springboot.apirest.springbootapirest.dao.IEggDao;
import com.chickentest.springboot.apirest.springbootapirest.dao.IFarmDao;
import com.chickentest.springboot.apirest.springbootapirest.models.Farm;
import com.chickentest.springboot.apirest.springbootapirest.models.Egg;

@DataJpaTest 
@ActiveProfiles("test")
public class Egg_Test {

	@Autowired
	private IEggDao eggDao;
	
	@Autowired
	private IFarmDao farmDao;
	
	@Test
	public void saveEgg() {
		
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
		
		Egg egg = new Egg(10, 1, savedFarm);
		egg.setPrice(20);
		egg.setDays(2);
		egg.setFarm(savedFarm);
		
		Egg savedEgg = eggDao.save(egg);		
		Egg retrievedEgg = eggDao.findById(savedEgg.getId()).orElse(null);		
	
		assertThat(retrievedEgg.getPrice()).isEqualTo(20);
		assertThat(retrievedEgg.getDays()).isEqualTo(2);
		assertThat(retrievedEgg.getFarm().getId()).isEqualTo(savedFarm.getId());
		assertThat(Egg.getZero()).isEqualTo(0);
		assertThat(Egg.getDaysToBorn()).isEqualTo(10);
		assertThat(Egg.getDaysWhenBoughtEgg()).isEqualTo(1);
		
	}
	
}
