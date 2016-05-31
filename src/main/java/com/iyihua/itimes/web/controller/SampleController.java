package com.iyihua.itimes.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.iyihua.itimes.mapper.SuperItemMapper;
import com.iyihua.itimes.model.Category;
import com.iyihua.itimes.model.Item;
import com.iyihua.itimes.model.User;
import com.iyihua.itimes.service.MyService;
import com.iyihua.model.query.UserItemQueryDTO;
import com.iyihua.remote.base.ItemRemote;

//@Controller
public class SampleController {

	@Autowired
	MyService myService;
	
//	@Autowired
//	UserRemote userService;
	
	@Autowired
	ItemRemote itemService;
	
	@Autowired
	SuperItemMapper superItemMapper;
	
//	@RequestMapping("/")
//	@ResponseBody
	@SuppressWarnings("unused")
	String home() {
		String data = "";
		
		User user = myService.getUser("iyihua", 0);
		if (user != null) {
			System.err.println("user:" + user.getName());
		} else {
			System.err.println("null...........");
		}
		List<Category> categories = myService.listCategory();
		if (categories != null) {
			for (Category category : categories) {
				data += categories.toString();
			}
		}
		
//		UserDTO ud = userService.findUserById(2L);
//		if (ud != null) {
//			data += ud.getId() + "," + ud.getName();
//		}
//		List<ItemDTO> items = itemService.findItemsByUserId(1L);
//		if (items != null && items.size() > 0) {
//			for (ItemDTO i : items) {
//				data += i.getDescription();
//			}
//		}
		UserItemQueryDTO query = new UserItemQueryDTO();
//		query.setCategoryId(1L);
		query.setDateString("2016-01-10");
		List<Item> items = superItemMapper.findItemsByParams(query);
		if (items != null) {
			for (Item i : items) {
				data += "\n" + i.toString();				
			}
		}
		return "Hello World!\n" + data;
	}

	
}
