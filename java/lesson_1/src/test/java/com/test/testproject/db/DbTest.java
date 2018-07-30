package com.test.testproject.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;

import static com.test.testproject.jooq.generated.Tables.TEST;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbTest {

	@Autowired
    private org.jooq.DSLContext dslContext;

	@Autowired
	private DataSourceTransactionManager txManager;

	@Test
	public void selectWorks() {

	    dslContext.selectFrom(TEST).fetch().forEach(r -> {
	        System.out.println(r.getTestText());
        });
	}

}
