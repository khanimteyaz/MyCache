package com.org.mycache.masternode.model;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by imteyaz.khan on 01/10/17.
 */
public class BucketRegistryTest {

    @Before
    public void setup() {
        System.out.println("BucketRegistryTest.setup");
        RegisteredBucket[] registeredBuckets=BucketRegistry.instance().getRegisterCopy();
        for(RegisteredBucket registeredBucket:registeredBuckets) {
            BucketRegistry.instance().removeBucket(registeredBucket.getBucket());
        }
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerException() {
        BucketRegistry.instance().addBucket(null);
    }

    @Test
    public void addNewBucketTest() {
        Bucket bucket=new Bucket(1,"198.162.6.0",8080);
        BucketRegistry.instance().addBucket(bucket);
        RegisteredBucket[] registeredBuckets=BucketRegistry.instance().getRegisterCopy();
        assertEquals(registeredBuckets[0].getBucket().getHost(), "198.162.6.0");
        assertEquals(registeredBuckets[0].getBucket().getPort(), 8080);
        assertEquals(registeredBuckets[0].getBucket().getId(),1);
        assertTrue(registeredBuckets[0].getLastAccessedTime() <=System.currentTimeMillis());
    }

    @Test
    public void addMultipleNewBucketTest() {
        Bucket bucket1=new Bucket(1,"198.162.6.0",8080);
        Bucket bucket2=new Bucket(2,"198.162.6.1",8080);
        BucketRegistry.instance().addBucket(bucket1);
        BucketRegistry.instance().addBucket(bucket2);
        RegisteredBucket[] registeredBuckets=BucketRegistry.instance().getRegisterCopy();
        assertEquals(registeredBuckets[0].getBucket().getHost(), "198.162.6.0");
        assertEquals(registeredBuckets[0].getBucket().getPort(), 8080);
        assertEquals(registeredBuckets[0].getBucket().getId(), 1);

        assertEquals(registeredBuckets[1].getBucket().getHost(), "198.162.6.1");
        assertEquals(registeredBuckets[1].getBucket().getPort(), 8080);
        assertEquals(registeredBuckets[1].getBucket().getId(),2);
        assertTrue(registeredBuckets[1].getLastAccessedTime() <= System.currentTimeMillis());
    }

    @Test
    public void removeBucket() {
        Bucket bucket1=new Bucket(1,"198.162.6.0",8080);
        Bucket bucket2=new Bucket(2,"198.162.6.1",8080);
        BucketRegistry.instance().addBucket(bucket1);
        BucketRegistry.instance().addBucket(bucket2);

        Bucket remove=new Bucket(1,"198.162.6.0",8080);
        BucketRegistry.instance().removeBucket(remove);
        RegisteredBucket[] registeredBuckets=BucketRegistry.instance().getRegisterCopy();

        assertEquals(registeredBuckets[0].getBucket().getHost(), "198.162.6.1");
        assertEquals(registeredBuckets[0].getBucket().getPort(), 8080);
        assertEquals(registeredBuckets[0].getBucket().getId(),2);
        assertTrue(registeredBuckets[0].getLastAccessedTime() <= System.currentTimeMillis());
    }
}
