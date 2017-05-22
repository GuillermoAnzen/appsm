package com.banamex.nearshore.databasems;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.banamex.nearshore.appsms.controller.AplicacionesController.DatabaseMicroserviceClient;

@Service
public class DatabaseMicroserviceClientService {
	
	@Autowired
	private DatabaseMicroserviceClient databaseMicroserviceClient;

	public ResultBase callBase(@RequestBody HashMap<String,Object> datos) {
		return databaseMicroserviceClient.getResultQuery(datos);
	}
	
}