package com.iyihua.itimes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.iyihua.itimes.model.Location;
import com.iyihua.itimes.repository.LocationRepository;
import com.iyihua.model.base.LocationDTO;
import com.iyihua.remote.base.LocationRemote;

@Service
public class LocationService implements LocationRemote {

	@Autowired
	LocationRepository locationRepository;
	
	@Override
	public List<LocationDTO> findLoationByUserId(Long userId) {
		Assert.notNull(userId, "userId can not be null!");
		List<LocationDTO> result = new ArrayList<LocationDTO>();
		List<Location> locations = locationRepository.findByUserId(userId);
		if (locations != null && locations.size() > 0) {
			for (Location location : locations) {
				LocationDTO dto = new LocationDTO();
				BeanUtils.copyProperties(location, dto);
				result.add(dto);
			}
		}
		return result;
	}

	@Override
	public LocationDTO saveLocation(LocationDTO location) {
		Assert.notNull(location, "Location can not be null!");
		Location save = new Location();
		BeanUtils.copyProperties(location, save);
		save = locationRepository.save(save);
		BeanUtils.copyProperties(save, location);
		return location;
	}

	@Override
	public void deleteLoation(Long locationId) {
		locationRepository.delete(locationId);
	}

}
