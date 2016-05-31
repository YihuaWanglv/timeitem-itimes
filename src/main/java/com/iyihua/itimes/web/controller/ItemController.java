package com.iyihua.itimes.web.controller;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iyihua.itimes.component.security.LoginSessionManager;
import com.iyihua.model.base.ItemDTO;
import com.iyihua.model.component.Page;
import com.iyihua.model.query.UserItemQueryDTO;
import com.iyihua.remote.base.ItemRemote;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private ItemRemote itemService;
	@Autowired
	LoginSessionManager loginSessionManager;

	@RequestMapping(method = RequestMethod.GET)
	public List<ItemDTO> findItemsByUser() {

		Long userId = loginSessionManager.getSessionUserId();
		return itemService.findItemsByUserId(userId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ItemDTO addItem(@RequestBody ItemDTO item) {
		item.setUserId(loginSessionManager.getSessionUserId());
		Date date = item.getDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String week = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1);
		item.setYear(year);
		item.setMonth(month);
		item.setDay(day);
		item.setWeek(week);
		return itemService.saveItem(item);
	}

	@RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
	public ItemDTO updateItem(@RequestBody ItemDTO updatedItem, @PathVariable Long itemId) {
		updatedItem.setItemId(itemId);
		return itemService.saveItem(updatedItem);
	}

	@RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable Long itemId) {
		itemService.deleteItem(itemId);
	}

	// @RequestMapping(value = "/list", method = RequestMethod.GET)
	// public ItemListDTO listItems(String last) {
	// Integer type = 0;
	// Date lastDate = DateUtil.parseToDate(last, DateUtil.SIMPLE_PATTERN);
	// Date next = DateUtil.addDateByDays(lastDate, 1);
	// ItemListDTO list = itemService.listNext(new
	// ItemListDTO(loginSessionManager.getSessionUserId(), type, next, next,
	// DateUtil.addDateByDays(next, 1), null));
	// return list;
	// }

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Page<ItemDTO> listItems(Integer page) {

		UserItemQueryDTO query = new UserItemQueryDTO();
		query.setPage((page != null && page > -1) ? page : 0);
		query.setUserId(loginSessionManager.getSessionUserId());
		Page<ItemDTO> list = itemService.findItemsByQueryParamsWithPage(query);
		return list;
	}
}
