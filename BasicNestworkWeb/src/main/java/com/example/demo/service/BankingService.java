package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.springframework.stereotype.Service;

import com.example.demo.util.EnrollAdmin;
import com.example.demo.util.RegisterUser;

@Service
public class BankingService {

	boolean flag = false, flag2 = false;
	Contract contract;

	public BankingService() {
		try {
			// 지갑 생성 admin
			if (!flag) {
				flag = EnrollAdmin.createWallet();

			}
			// 지갑 생성 user
			if (!flag2) {
				flag2 = RegisterUser.createWallet();
			}
			contract= getContract();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setAsset(String id, String amount) throws Exception {
		String asset=getAsset(id);

		int asset2 = Integer.parseInt(asset);
		int amount2 = Integer.parseInt(amount);
		amount = (asset2 + amount2) + "";

		contract.submitTransaction("set", id, amount);

	}
	
	public void sendAsset(String from_id, String to_id, String amount) throws Exception {
		

		contract.submitTransaction("invoke", from_id, to_id, amount);

	}

	public String getAsset(String id) throws Exception {
		byte[] result;

		result = contract.evaluateTransaction("query", id);
		return new String(result);

	}

	private Contract getContract() throws Exception {
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallet.createFileSystemWallet(walletPath);

		// load a CCP
		Path networkConfigPath = Paths.get("resource/connection.json");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "user1").networkConfig(networkConfigPath).discovery(false);

		Gateway gateway = builder.connect();
		// create a gateway connection

		// get the network and contract
		Network network = gateway.getNetwork("mychannel");
		return network.getContract("chaincode_example02_node");

	}
	
	public String getHistory(String id) throws Exception {
		byte[] result;

		result = contract.evaluateTransaction("getHistoryForKey", id);
		return new String(result);

	}

}