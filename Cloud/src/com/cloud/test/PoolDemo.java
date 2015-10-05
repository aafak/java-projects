package com.cloud.test;
import com.cloud.pool.Pool;
import com.cloud.pool.PoolDaoImpl;
import com.cloud.pool.PoolVO;


public class PoolDemo {

	public static void main(String[] args){

		Pool pool = new PoolVO();
		pool.setId(3);
		pool.setName("newpool1");

		PoolDaoImpl poolDao=new PoolDaoImpl();
		//poolDao.save(pool);
		
		poolDao.update((PoolVO)pool);
		//int poolId=1;
		//poolDao.delete(poolId);

	}
}
