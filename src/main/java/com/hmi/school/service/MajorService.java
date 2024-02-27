package com.hmi.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hmi.school.dao.MajorDAO;
import com.hmi.school.entity.Major;

@Service
public class MajorService {

	private final MajorDAO majorDAO;

	public MajorService(MajorDAO majorDAO) {
		super();
		this.majorDAO = majorDAO;
	}

	public List<Major> getAllMajors() {
		return (List<Major>) majorDAO.findAll();
	}

	// retrieve
	public Major getMajorById(Long majorId) {
		Optional<Major> majorOptional = majorDAO.findById(majorId);
		if (majorOptional.isEmpty()) {
			System.out.println("Major with id : " + majorId + " is not found.");
			throw new RuntimeException();
		}
		return majorOptional.get();
	}

	// create or update
	public Major saveMajor(Major major) {
		return majorDAO.save(major);
	}

	// delete
	public void deleteMajorById(Long majorId) {
		majorDAO.deleteById(majorId);
	}

}
