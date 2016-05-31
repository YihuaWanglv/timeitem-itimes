package com.iyihua.itimes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.iyihua.itimes.model.Tag;
import com.iyihua.itimes.repository.TagRepository;
import com.iyihua.model.base.TagDTO;
import com.iyihua.remote.base.TagRemote;

@Service
public class TagService implements TagRemote {

	@Autowired
	TagRepository tagRepository;
	
	@Override
	public List<TagDTO> findTagsByUserId(Long userId) {
		Assert.notNull(userId, "userId can not be null!");
		List<TagDTO> result = new ArrayList<TagDTO>();
		List<Tag> tags = tagRepository.findByUserId(userId);
		if (tags != null && tags.size() > 0) {
			for (Tag tag : tags) {
				TagDTO dto = new TagDTO();
				BeanUtils.copyProperties(tag, dto);
				result.add(dto);
			}
		}
		return result;
	}

	@Override
	public TagDTO saveTags(TagDTO tags) {
		Tag save = new Tag();
		BeanUtils.copyProperties(tags, save);
		save = tagRepository.save(save);
		BeanUtils.copyProperties(save, tags);
		return tags;
	}

	@Override
	public void deleteTags(Long tagsId) {
		tagRepository.delete(tagsId);
	}

}

