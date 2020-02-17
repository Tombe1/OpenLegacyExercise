package com.OpenLegacyExercise;

import com.OpenLegacyExercise.Exceptions.ItemNotFoundException;
import com.OpenLegacyExercise.dto.Item;
import com.OpenLegacyExercise.repository.ItemDB;
import com.OpenLegacyExercise.Exceptions.ItemCantBeAddedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OpenLegacyExerciseApplication {
	// H2 DB:
	// http://localhost:8080/h2-console/
	// jdbc:h2:mem:testdb

	//Pack and Run app via command line (DockerFile):
	// java -jar OpenLegacyExercise-0.0.1-SNAPSHOT.jar

	// Angular:
	//http://localhost:8080/
	// Swagger:
	//http://localhost:8080/swagger-ui.html#/item-controller


	static Logger logger = LoggerFactory.getLogger(OpenLegacyExerciseApplication.class);
	public static void main(String[] args) throws ItemCantBeAddedException, ItemNotFoundException {

		ConfigurableApplicationContext ctx = SpringApplication.run(OpenLegacyExerciseApplication.class, args);
		//HardCodedItems
		Item item1 = new Item("PC" , 100 ,569874563l);
		Item item2 = new Item("HDMI" , 500 , 123456789);
		Item item3 = new Item("Omg" , 500 , 123456);

		ItemDB item = ctx.getBean(ItemDB.class);
		item.addItem(item1);
		item.addItem(item2);
		item.addItem(item3);

		item.updateStockById(item.itemDetails(1));
		logger.info(String.valueOf(item.inventoryItemsList()));
		logger.info(String.valueOf(item.itemDetails(1)));
		logger.info(String.valueOf(item.quantityByInventoryCode(569874563)));
		logger.info(String.valueOf(item.quantityByName("PC")));
		logger.info(String.valueOf(item.inventoryItemsList()));


	}

}
