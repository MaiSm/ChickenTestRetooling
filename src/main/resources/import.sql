INSERT INTO farms(money, name, number_of_days, selling_egg_price, selling_chicken_price, buying_egg_price, buying_chicken_price, limit_of_eggs, limit_of_chickens) VALUES (1500, "Greendale Farm", 0 ,60, 500, 40, 300, 30, 10)
SET @farm_id1 = (SELECT id FROM farms WHERE name = "Greendale Farm");
INSERT INTO eggs(price, days, farm_id) VALUES (50, 1, @farm_id1)
INSERT INTO eggs(price, days, farm_id) VALUES (30, 4, @farm_id1)
INSERT INTO chickens(price, days, days_since_last_eggs, farm_id) VALUES (400, 2, 0, @farm_id1)
INSERT INTO chickens(price, days, days_since_last_eggs, farm_id) VALUES (200, 12, 4, @farm_id1)