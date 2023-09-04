package com.example.Sudoku.model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Sudoku.entity.User;
import com.example.Sudoku.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private SimpleHashAlgo algo;
	
	public boolean validatePwd(String password) {
		List<String> passwords = repo.findPasswords();
		if (passwords.isEmpty()) {
			//TODO: deal with the cycle not being found for whatever reason
			System.out.println("passwords could not be found");
		}
		
		try {
			String encryptedPwd = algo.toHexStr(algo.obtainSHA(password));
			for(String each:passwords) {
				String encryptedEach = algo.toHexStr(algo.obtainSHA(each));
			if (encryptedPwd.equals(encryptedEach)) {
				return true;
				}
			}
			return false;
		}
			catch (NoSuchAlgorithmException obj){  
			System.out.println("An exception is generated for the incorrect algorithm: " + obj);  
			} 
		return false;
	}
	
}
