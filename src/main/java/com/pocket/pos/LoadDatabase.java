package com.pocket.pos;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pocket.pos.model.Bill;
import com.pocket.pos.model.BillItem;
import com.pocket.pos.model.BillSecondParty;
import com.pocket.pos.model.BillType;
import com.pocket.pos.model.Bulk;
import com.pocket.pos.model.Product;
import com.pocket.pos.model.Role;
import com.pocket.pos.model.User;
import com.pocket.pos.repository.BillRepo;
import com.pocket.pos.repository.BillSecondPartyRepo;
import com.pocket.pos.repository.BulkRepo;
import com.pocket.pos.repository.ProductRepo;
import com.pocket.pos.repository.UserRepo;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ProductRepo productRepo, BillRepo billRepo , BulkRepo bulkRepo , UserRepo userRepo ,BillSecondPartyRepo secondPartyRepo) {

    return args -> {
    	
    	
    	
    	BillSecondParty sp1 = new BillSecondParty("Stest1", "01126098408");
    	BillSecondParty sp2 = new BillSecondParty("Stest2", "01003781533");
    	BillSecondParty sp3 = new BillSecondParty("Stest3", "01113172731");
    	BillSecondParty sp4 = new BillSecondParty("Stest4", "01119312902");
    	BillSecondParty sp5 = new BillSecondParty("Stest5", "01050584549");
    	
    	
    	
    	Product p1 = new Product("P1", LocalDate.now().plusYears(5));
    	Product p2 = new Product("P2", LocalDate.now().plusYears(4));
    	Product p3 = new Product("P3", LocalDate.now().plusYears(3));
    	Product p4 = new Product("P4", LocalDate.now().plusYears(2));
    	Product p5 = new Product("P5", LocalDate.now().plusYears(1));
    	
    	
    	
    	Bulk b1 = new Bulk(181.5d, 50d, p1);
    	Bulk b2 = new Bulk(177.4d, 70d, p1);
    	Bulk b3 = new Bulk(191.5d, 20d, p1);
    	Bulk b4 = new Bulk(80d, 100d, p2);
    	Bulk b5 = new Bulk(71.5d, 120d, p2);
    	Bulk b6 = new Bulk(75.8d, 90d, p2);
    	Bulk b7 = new Bulk(14d, 200d, p3);
    	Bulk b8 = new Bulk(12.5d, 250d, p3);
    	Bulk b9 = new Bulk(16d, 170d, p3);
    	Bulk b10 = new Bulk(30d, 130d, p4);
    	Bulk b11 = new Bulk(35d, 110d, p4);
    	Bulk b12 = new Bulk(33.5d, 140d, p4);
    	Bulk b13 = new Bulk(105d, 100d, p5);
    	Bulk b14 = new Bulk(100d, 90d, p5);
    	Bulk b15 = new Bulk(102.5d, 80d, p5);
    	
    	
        
        Bill bi1 = new Bill(4000.0, BillType.PAY, sp1);
        Bill bi2 = new Bill(7000.0, BillType.PAY, sp2);
        Bill bi3 = new Bill(8200.0, BillType.PAY, sp3);
        Bill bi4 = new Bill(0.0, BillType.PAY, sp4);
       
        
        Bill bi7 = new Bill(2000d, BillType.SELL, sp2);
        Bill bi8 = new Bill(6000.0, BillType.SELL, sp3);
        Bill bi9 = new Bill(8200.0, BillType.SELL, sp4);
        Bill bi10 = new Bill(0.0, BillType.SELL, sp5);
        
        bi1.getItems().add(new BillItem(181.5, 20d, b1));
        bi1.getItems().add(new BillItem(80d, 30d, b4));
        bi2.getItems().add(new BillItem(33.5d, 140d, b12));
        bi2.getItems().add(new BillItem(12.5d, 250d, b8));
        bi3.getItems().add(new BillItem(102.5d, 80d, b15));
        bi4.getItems().add(new BillItem(16d, 100d, b9));
        bi4.getItems().add(new BillItem(14d, 100d, b7));
        
        bi7.getItems().add(new BillItem(181.5, 20d, b1));
        bi7.getItems().add(new BillItem(80d, 30d, b4));
        bi8.getItems().add(new BillItem(33.5d, 140d, b12));
        bi8.getItems().add(new BillItem(12.5d, 250d, b8));
        bi9.getItems().add(new BillItem(102.5d, 80d, b15));
        bi10.getItems().add(new BillItem(16d, 100d, b9));
        bi10.getItems().add(new BillItem(14d, 100d, b7));
        
        
        bi1.calculateBill();
        bi2.calculateBill();
        bi3.calculateBill();
        bi4.calculateBill();
        
        bi7.calculateBill();
        bi8.calculateBill();
        bi9.calculateBill();
        bi10.calculateBill();
        
        log.info("Inserting Users"+userRepo.save(new User("Test Super Admin","01284414243","Test1234","Utest1",Role.SUPERADMIN)));
    	log.info("Inserting Users"+userRepo.save(new User("Test Admin","01226933342","Test1234","Utest2",Role.ADMIN)));
    	log.info("Inserting Users"+userRepo.save(new User("Test Sales","01090870302","Test1234","Utest3",Role.SALES)));
        
        log.info("Inserting BillSecondParty"+secondPartyRepo.save(sp1));
    	log.info("Inserting BillSecondParty"+secondPartyRepo.save(sp2));
    	log.info("Inserting BillSecondParty"+secondPartyRepo.save(sp3));
    	log.info("Inserting BillSecondParty"+secondPartyRepo.save(sp4));
    	log.info("Inserting BillSecondParty"+secondPartyRepo.save(sp5));
        
        log.info("Inserting Products"+productRepo.save(p1));
    	log.info("Inserting Products"+productRepo.save(p2));
    	log.info("Inserting Products"+productRepo.save(p3));
    	log.info("Inserting Products"+productRepo.save(p4));
    	log.info("Inserting Products"+productRepo.save(p5));
        
        log.info("Inserting Bulks"+bulkRepo.save(b1));
    	log.info("Inserting Bulks"+bulkRepo.save(b2));
    	log.info("Inserting Bulks"+bulkRepo.save(b3));
    	log.info("Inserting Bulks"+bulkRepo.save(b4));
    	log.info("Inserting Bulks"+bulkRepo.save(b5));
        log.info("Inserting Bulks"+bulkRepo.save(b6));
    	log.info("Inserting Bulks"+bulkRepo.save(b7));
        log.info("Inserting Bulks"+bulkRepo.save(b8));
        log.info("Inserting Bulks"+bulkRepo.save(b9));
        log.info("Inserting Bulks"+bulkRepo.save(b10));
        log.info("Inserting Bulks"+bulkRepo.save(b11));
        log.info("Inserting Bulks"+bulkRepo.save(b12));
        log.info("Inserting Bulks"+bulkRepo.save(b13));
        log.info("Inserting Bulks"+bulkRepo.save(b14));
        log.info("Inserting Bulks"+bulkRepo.save(b15));
        
        log.info("Inserting Bill"+billRepo.save(bi1));
        log.info("Inserting Bill"+billRepo.save(bi2));
        log.info("Inserting Bill"+billRepo.save(bi3));
        log.info("Inserting Bill"+billRepo.save(bi4));
        log.info("Inserting Bill"+billRepo.save(bi7));
        log.info("Inserting Bill"+billRepo.save(bi8));
        log.info("Inserting Bill"+billRepo.save(bi9));
        log.info("Inserting Bill"+billRepo.save(bi10));
        
        
        
    };
  }
}