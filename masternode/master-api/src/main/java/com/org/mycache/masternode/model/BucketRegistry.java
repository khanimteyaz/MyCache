package com.org.mycache.masternode.model;

import com.org.mycache.core.model.node.Bucket;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.*;

/**
 * Created by imteyaz.khan on 01/10/17.
 */
public class BucketRegistry {
    private static BucketRegistry instance_=new BucketRegistry();
    private List<RegisteredBucket> registeredBuckets;
    private AtomicInteger clusterVersion=new AtomicInteger(0);

    private BucketRegistry() {
        registeredBuckets=new ArrayList<>();
    }

    public static BucketRegistry instance() {
        return instance_;
    }

    public int version() {
        return clusterVersion.intValue();
    }

    public RegisteredBucket[] getRegisterCopy(){
        /* Taking exclusive lock because the read and write are very low to this data structure
        but wanted to avoid the race if any time and read and write happen at the same time.
        Duration of this is very low, because max bucket we can expect here is in integer
        range very poorly configure system.
         */
        synchronized (registeredBuckets) {
            RegisteredBucket copy[]= new RegisteredBucket[registeredBuckets.size()];
            return registeredBuckets.stream().collect(Collectors.toList()).toArray(copy);
        }
    }

    public void addBucket(Bucket bucket) {
        checkNotNull(bucket);
        synchronized (registeredBuckets) {
            registeredBuckets.add(new RegisteredBucket(bucket,System.currentTimeMillis()));
            sortBuckets();
            clusterVersion.incrementAndGet();
        }
    }

    public void removeBucket(Bucket bucket) {
        checkNotNull(bucket);
        if(!registeredBuckets.contains(new RegisteredBucket(bucket,System.currentTimeMillis()))) {
            return;
        }
        synchronized (registeredBuckets) {
            registeredBuckets.remove(new RegisteredBucket(bucket,System.currentTimeMillis()));
            sortBuckets();
            clusterVersion.incrementAndGet();
        }
    }

    public void touch(Bucket bucket) {
        checkNotNull(bucket);
        if(registeredBuckets.contains(bucket)) {
            registeredBuckets.get(registeredBuckets.indexOf(bucket)).touch();
        }
    }

    public boolean isPresent(Bucket bucket) {
        checkNotNull(bucket);
        return registeredBuckets.contains(new RegisteredBucket(bucket,System.currentTimeMillis()));
    }

    @PostConstruct
    private void sortBuckets() {
        sort(registeredBuckets);
    }
}
