/*
 * This file is generated by jOOQ.
*/
package com.test.testproject.jooq.generated;


import com.test.testproject.jooq.generated.tables.FlywaySchemaHistory;
import com.test.testproject.jooq.generated.tables.Test;
import com.test.testproject.jooq.generated.tables.records.FlywaySchemaHistoryRecord;
import com.test.testproject.jooq.generated.tables.records.TestRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<TestRecord, Integer> IDENTITY_TEST = Identities0.IDENTITY_TEST;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = UniqueKeys0.FLYWAY_SCHEMA_HISTORY_PK;
    public static final UniqueKey<TestRecord> TEST_PKEY = UniqueKeys0.TEST_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<TestRecord, Integer> IDENTITY_TEST = Internal.createIdentity(Test.TEST, Test.TEST.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "flyway_schema_history_pk", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK);
        public static final UniqueKey<TestRecord> TEST_PKEY = Internal.createUniqueKey(Test.TEST, "test_pkey", Test.TEST.ID);
    }
}
