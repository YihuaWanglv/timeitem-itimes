package com.iyihua.itimes.repository.provider;

import org.apache.ibatis.jdbc.SQL;

import com.iyihua.itimes.model.Item;

public class SampleProvider {

	public String selectUser(long userId) {
		return "select * from user where user_id=" + userId;
	}

	public String findUserByParams(final Item item) {
		return new SQL() {
			{
				SELECT("i.*");
				FROM("items i");
				if (item.getUserId() != null) {
					VALUES("i.user_id", "#{userId}");
				}
				if (item.getCategoryId() != null) {
					VALUES("i.category_id", "#{categoryId}");
				}
			}
		}.toString();
	}
}
