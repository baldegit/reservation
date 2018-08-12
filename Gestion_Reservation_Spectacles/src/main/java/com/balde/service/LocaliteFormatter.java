//package com.balde.service;
//
//import java.text.ParseException;
//import java.util.Locale;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.Formatter;
//import org.springframework.stereotype.Service;
//
//import com.balde.entity.Localities;
//import com.balde.repository.LocalitiesRepository;
//
//@Service
//public class LocaliteFormatter implements Formatter<Localities>{
//	
//	@Autowired
//	LocalitiesRepository localiteRepo;
//
//	@Override
//	public String print(Localities object, Locale locale) {
//		// TODO Auto-generated method stub
//		return (object != null ? object.getId().toString() : "");
//	}
//
//	@Override
//	public Localities parse(String text, Locale locale) throws ParseException {
//		// TODO Auto-generated method stub
//		Integer id = Integer.valueOf(text);
//		return this.localiteRepo.getOne(id);
//	}
//
//}
