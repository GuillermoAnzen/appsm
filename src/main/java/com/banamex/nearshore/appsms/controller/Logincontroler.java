package com.banamex.nearshore.appsms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banamex.nearshore.appsms.exception.NearshoreDatabaseMicroserviceException;
import com.banamex.nearshore.databasems.Data;
import com.banamex.nearshore.databasems.DatabaseMicroserviceClientService;
import com.banamex.nearshore.databasems.ResultBase;
import com.banamex.nearshore.util.Constants;

@RestController
public class Logincontroler {
	@Autowired
	private DatabaseMicroserviceClientService databaseMicroserviceClientService;


	@RequestMapping(value = "/login" ,  method = RequestMethod.POST )
    public Object  login( @RequestBody HashMap<String, String> datos ) { 	
	 	
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		ResultBase datoUser = callLogin(datos.get("usuario") , datos.get("password"));
		  
		return datoUser;
		
	}
	
	private ResultBase callLogin(String user, String pass){
		HashMap<String, Object> requestParams = new HashMap<String, Object>();

		List<Data> queryParams = new ArrayList<>();
		Data queryParam01 = new Data();
		queryParam01.setIndex(1);
		queryParam01.setType("String");
		queryParam01.setValue(user);
		queryParams.add(queryParam01);
		
		Data queryParam02 = new Data();
		queryParam02.setIndex(2);
		queryParam02.setType("String");
		queryParam02.setValue(pass);
		queryParams.add(queryParam02);
		

		requestParams.put("tipoQuery", Constants.QUERY_STATEMENT_TYPE);
		requestParams.put("sql", "select Segundo_Nombre,Id_Perfil,ApellidoMaterno,Email,Apellido_Paterno,roles_id,Id_Usuarios,Primer_Nombre  from usuario where   Email=? and Clave=? ");
		requestParams.put("data", queryParams);

		ResultBase resultBase = null;
		try {
			resultBase = databaseMicroserviceClientService.callBase(requestParams);
			 
		} catch (Exception e) {
			throw new NearshoreDatabaseMicroserviceException(e.getMessage());
		}

		return resultBase;
	}
	
	
}
