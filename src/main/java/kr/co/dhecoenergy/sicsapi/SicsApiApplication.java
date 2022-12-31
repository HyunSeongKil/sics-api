package kr.co.dhecoenergy.sicsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import kr.vaiv.sdt.cmmn.misc.CmmnCrypto;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableCaching
@Slf4j
public class SicsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SicsApiApplication.class, args);

		log.debug("plain:{}, cipher:{}", "seongkil", CmmnCrypto.sha512("seongkil"));
	}

}
