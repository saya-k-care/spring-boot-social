package com.mgbt.socialapp_backend.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgbt.socialapp_backend.model.entity.Feedback;
import com.mgbt.socialapp_backend.model.repository.IFeedbackRepository;

@Service
public class FeedbackService implements IService<Feedback> {

    @Autowired
    private IFeedbackRepository repository;

    @Override
    public List<Feedback> toList() {
        return repository.findAll();
    }

	@Override
	public Feedback save(Feedback entity) {
		// TODO Auto-generated method stub
		return repository.save(entity);
	}

	@Override
	public void delete(Feedback entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Feedback findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
