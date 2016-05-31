package com.iyihua.itimes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import util.DateUtil;

//import com.iyihua.itimes.mapper.ItemMapper;
import com.iyihua.itimes.mapper.SuperItemMapper;
import com.iyihua.itimes.model.Item;
import com.iyihua.itimes.repository.ItemRepository;
import com.iyihua.model.base.ItemDTO;
import com.iyihua.model.base.ItemListDTO;
import com.iyihua.model.component.Page;
import com.iyihua.model.query.UserItemQueryDTO;
import com.iyihua.remote.base.ItemRemote;

@Service
public class ItemService implements ItemRemote {

//	@Autowired
//	ItemMapper itemMapper;
	@Autowired
	SuperItemMapper superItemMapper;
	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<ItemDTO> findItemsByUserId(Long userId) {
		Assert.notNull(userId, "userId can not be null!");
		List<ItemDTO> result = new ArrayList<ItemDTO>();
//		List<Item> items = itemMapper.findAll();
//		if (items != null && items.size() > 0) {
//			for (Item item : items) {
//				ItemDTO dto = new ItemDTO();
//				BeanUtils.copyProperties(item, dto);
//				result.add(dto);
//			}
//		}
		return result;
	}

	@Override
	public List<ItemDTO> findItemsByQueryParams(UserItemQueryDTO query) {
		List<ItemDTO> result = new ArrayList<ItemDTO>();
		List<Item> items = superItemMapper.findItemsByParams(query);
		if (items != null) {
			for (Item item : items) {
				ItemDTO dto = new ItemDTO();
				BeanUtils.copyProperties(item, dto);
				result.add(dto);
			}
		}
		return result;
	}

	@Override
	public Page<ItemDTO> findItemsByQueryParamsWithPage(UserItemQueryDTO query) {
		Page<ItemDTO> pages = new Page<ItemDTO>();
		List<ItemDTO> result = new ArrayList<ItemDTO>();
//		List<Item> items = superItemMapper.findItemsByParams(query);
		List<Item> items = superItemMapper.listItems(query);
		if (items != null) {
			for (Item item : items) {
				ItemDTO dto = new ItemDTO();
				BeanUtils.copyProperties(item, dto);
				result.add(dto);
			}
		}
//		int count = superItemMapper.countItemsByParams(query);
		int count = superItemMapper.countItems(query);
		pages.setItems(result);
		pages.setTotal(count);
		pages.setMax(count / pages.getSize() + 1);
		return pages;
	}

	@Override
	public ItemDTO saveItem(ItemDTO item) {
		Assert.notNull(item, "Item must not be null!");
		Item save = new Item();
		BeanUtils.copyProperties(item, save);
		save = itemRepository.save(save);
		BeanUtils.copyProperties(save, item);
		return item;
	}

	@Override
	public void deleteItem(Long itemId) {
		itemRepository.delete(itemId);
	}

	@Override
	public ItemListDTO listNext(ItemListDTO itemListDTO) {
		Assert.notNull(itemListDTO.getUserId(), "userId can not be null!");
		itemListDTO.getStart();
		itemListDTO.getEnd();
		
		UserItemQueryDTO query = new UserItemQueryDTO();
		query.setDateStringStart(DateUtil.format(itemListDTO.getStart(), DateUtil.SIMPLE_PATTERN));
		query.setDateStringEnd(DateUtil.format(itemListDTO.getEnd(), DateUtil.SIMPLE_PATTERN));
		query.setUserId(itemListDTO.getUserId());
		List<Item> items = superItemMapper.listItems(query);
		List<ItemDTO> result = new ArrayList<ItemDTO>();
		if (items != null) {
			for (Item item : items) {
				ItemDTO dto = new ItemDTO();
				BeanUtils.copyProperties(item, dto);
				result.add(dto);
			}
		}
		itemListDTO.setItems(result);
		return itemListDTO;
	}

}
