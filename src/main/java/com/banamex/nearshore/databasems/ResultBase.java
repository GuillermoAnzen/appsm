package com.banamex.nearshore.databasems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultBase {

	private boolean success=false;
	private String mensaje="";
	private Object data;
	private int codigo=1000;
	private String exception="NoException";
	//private final Logger log = LoggerFactory.getLogger(ResultBase.class);
	
	public ResultBase(){}
	
	public ResultBase(boolean success, String mensaje, Object data, int codigo) {
		super();
		this.success = success;
		this.mensaje = mensaje;
		this.data = data;
		this.codigo = codigo;
	}
		
	public ResultBase(boolean success, String mensaje, Object data, int codigo,
			String exception) {
		super();
		this.success = success;
		this.mensaje = mensaje;
		this.data = data;
		this.codigo = codigo;
		this.exception = exception;
	}
	
	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/*
	 * Metodo que crea el request, es decir, el formato de HashMap que al ser convertido a json, es
	 * aceptado por le microservicio de Base de Datos.
	 * @return Regresa HashMap formado para request del microservicio Base de Datos.
	 * @param datos Parametro con los datos a ingresar.
	 * @param tipoQuery 1=Query de tipo Update, Insert, Delete 2=Query de tipo Select
	 * @param sql Consulta sql a ejecutar.
	 */
	public HashMap<String,Object> getResquest(HashMap<String,Object> datos, int tipoQuery, String sql){
		
		//log.debug("Datos:"+Utils.getJson(datos));
		HashMap<String,Object> request = new HashMap<String,Object>();
		List<Data> data = new ArrayList<Data>();
		Data d = new Data();
		request.put("tipoQuery", String.valueOf(tipoQuery));
		request.put("sql",sql);
		
		for(int x=0; x<datos.size(); x++){
			d = new Data();
			d.setIndex((x+1));
			d.setType(getType(datos.get(String.valueOf(x+1))));
			d.setValue((String) datos.get(String.valueOf(x+1)));
			data.add(d);
			//tiposDatos.put(String.valueOf((x+1)), getType(datos.get(String.valueOf(x+1))));
		}
		request.put("data", data);	
		return request;
	}
	/*
	 * Metodo que regresa el tipo de dato en string que el microservicio tiene registrado para comparar y iniciar el 
	 * tipo de dato
	 * @param ob Tipo de objeto a comprobar el tipo
	 */
	public String getType(Object ob){
		
		if(ob.getClass().getName().equals("java.lang.String")){// java.lang.String
			return "string";
		}
		if(ob.getClass().getName().equals("java.lang.Integer")){//java.lang.Integer
			return "int";
		}
		if(ob.getClass().getName().equals("java.lang.Long")){ //java.lang.Long
			return "long";
		}
		if(ob.getClass().getName().equals("java.math.BigDecimal")){ //java.math.BigDecimal
			return "bigdecimal";
		}
		if(ob.getClass().getName().equals("java.math.BigDecimal")){ //java.lang.Boolean
			return "boolean";
		}
		if(ob.getClass().getName().equals("[B")){//[B
			return "bytes";
		}		
		if(ob.getClass().getName().equals("java.lang.Double")){ //java.lang.Double
			return "double";
		}
		if(ob.getClass().getName().equals("//java.lang.Float")){ //java.lang.Float
			return "float";
		}
		if(ob.getClass().getName().equals("java.lang.Short")){ //java.lang.Short
			return "short";
		}
		if(ob.getClass().getName().equals("javax.sql.rowset.serial.SerialBlob")){//javax.sql.rowset.serial.SerialBlob
			return "blob";
		}
		if(ob.getClass().getName().equals("javax.sql.rowset.serial.SerialClob")){//javax.sql.rowset.serial.SerialClob
			return "clob";
		}
		if(ob.getClass().getName().equals("java.lang.Object")){ //java.lang.Object
			return "object";
		}
		if(ob.getClass().getName().equals("java.lang.Byte")){ //java.lang.Byte
			return "byte";
		}
		if(ob.getClass().getName().equals("java.sql.Time")){//java.sql.Time
			return "time";
		}		
		if(ob.getClass().getName().equals("java.sql.Timestamp")){//java.sql.Timestamp
			return "timestamp";
		}				
		if(ob.getClass().getName().equals("java.sql.Date") || ob.getClass().getName().equals("java.util.Date")){//java.sql.Date; || java.util.Date;
			return "date";
		}
		return "object";
	}
	@Override
	public String toString() {
		return "ResultBase [success=" + success + ", mensaje=" + mensaje
				+ ", data=" + data + ", codigo=" + codigo + "]";
	}
	
}
