package com.mgbt.socialapp_backend.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgbt.socialapp_backend.model.entity.Learning;
import com.mgbt.socialapp_backend.model.repository.ILearningRepository;

@Service
public class LearningService implements IService<Learning> {

    @Autowired
    private ILearningRepository repository;

    @Override
    public List<Learning> toList() {
        return repository.findAll();
    }

	@Override
	public Learning save(Learning entity) {
		// TODO Auto-generated method stub
		return repository.save(entity);
	}

	@Override
	public void delete(Learning entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Learning findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
