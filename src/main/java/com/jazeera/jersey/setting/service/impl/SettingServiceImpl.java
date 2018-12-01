package com.jazeera.jersey.setting.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jazeera.jersey.setting.repository.VersionRepository;
import com.jazeera.jersey.setting.service.SettingsService;

@Service
@Transactional
public class SettingServiceImpl implements SettingsService {
	
	Logger logger = LoggerFactory.getLogger(SettingServiceImpl.class);
	@Autowired private VersionRepository versionRepository;

	@Override
	public String getVersion() {
		return versionRepository.findByActive(true).getVersion();
	}

	
	
}
