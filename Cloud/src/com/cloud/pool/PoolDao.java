package com.cloud.pool;

public interface PoolDao {
 public void save(Pool pool);
 public void delete(int id);
 public PoolVO findById(int id);
 public void update(PoolVO pool);
}
