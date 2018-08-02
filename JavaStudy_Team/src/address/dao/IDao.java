package address.dao;

import java.util.List;

public interface IDao<T, K> {
	public void insert(T vo);
	public void update(T vo);
	
	public List select(K primaryKey);
	public List selectAll(); // select * from 테이블명;
	void delete(String name, String phone);
}
