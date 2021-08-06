package com.example.demo.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.springframework.stereotype.Service;

import com.example.demo.util.EnrollAdmin;
import com.example.demo.util.RegisterUser;
@Service
public class FabCarService {
	
	private boolean adminExists;
	private boolean userExists;
	
	public FabCarService() {
		try {
			adminExists = EnrollAdmin.enrollAdmin();
			userExists = RegisterUser.registerUser();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String queryAllCar() throws Exception {
		// TODO Auto-generated method stub
		if (!adminExists) {
			adminExists = EnrollAdmin.enrollAdmin();
		}
		
		if (!userExists) {
			userExists = RegisterUser.registerUser();
		}
		
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallet.createFileSystemWallet(walletPath);

		// load a CCP
		Path networkConfigPath = Paths.get("resource", "connection-org1.yaml");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "user1").networkConfig(networkConfigPath).discovery(false);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {

			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
			Contract contract = network.getContract("fabcar");
//
			byte[] result;

			result = contract.evaluateTransaction("queryAllCars");
			return new String(result);
//			System.out.println(new String(result));
//
//			contract.submitTransaction("createCar", "CAR011", "VW", "Polo", "Grey", "Mary");
//
//			result = contract.evaluateTransaction("queryCar", "CAR011");
//			System.out.println(new String(result));
//
//			contract.submitTransaction("changeCarOwner", "CAR011", "mosangil");
//
//			result = contract.evaluateTransaction("queryCar", "CAR011");
//			System.out.println(new String(result));
		}
	}

	public String querySubmit(String carNumber) throws Exception {
		// TODO Auto-generated method stub
		if (!adminExists) {
			adminExists = EnrollAdmin.enrollAdmin();
		}
		
		if (!userExists) {
			userExists = RegisterUser.registerUser();
		}
		
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallet.createFileSystemWallet(walletPath);

		// load a CCP
		Path networkConfigPath = Paths.get("resource", "connection-org1.yaml");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "user1").networkConfig(networkConfigPath).discovery(false);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {

			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
			Contract contract = network.getContract("fabcar");
//
			byte[] result;

			result = contract.evaluateTransaction("queryCar", carNumber);
			
			return new String(result);
//					System.out.println(new String(result));
//
//					contract.submitTransaction("createCar", "CAR011", "VW", "Polo", "Grey", "Mary");
//
//					result = contract.evaluateTransaction("queryCar", "CAR011");
//					System.out.println(new String(result));
//
//					contract.submitTransaction("changeCarOwner", "CAR011", "mosangil");
//
//					result = contract.evaluateTransaction("queryCar", "CAR011");
//					System.out.println(new String(result));
		}
	}
}
