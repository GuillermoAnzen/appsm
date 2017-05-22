package com.banamex.nearshore.appsms.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.banamex.nearshore.util.Constants;

@ControllerAdvice
public class NearshoreExceptionResolver {

	private final Logger logger = LoggerFactory.getLogger(NearshoreExceptionResolver.class);

	@ExceptionHandler(NearshoreDatabaseMicroserviceException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody NearshoreErrorDescriptor resolveNearshoreDatabaseMicroserviceException(HttpServletRequest req,
			HttpServletResponse resp, Exception e) {
		NearshoreDatabaseMicroserviceException nearshoreDatabaseMicroserviceException = (NearshoreDatabaseMicroserviceException) e;
		
		NearshoreErrorDescriptor nearshoreErrorDescriptor = new NearshoreErrorDescriptor();

		nearshoreErrorDescriptor.setUrl(req.getRequestURI());
		nearshoreErrorDescriptor.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		nearshoreErrorDescriptor.setErrorCode(Constants.DATABASE_MICROSERVICE_ERROR_CODE);

		logger.error(this.fetchExceptionStackTrace(nearshoreDatabaseMicroserviceException));

		return nearshoreErrorDescriptor;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody NearshoreErrorDescriptor resolveException(HttpServletRequest req, HttpServletResponse resp,
			Exception e) {
		NearshoreErrorDescriptor nearshoreErrorDescriptor = new NearshoreErrorDescriptor();

		nearshoreErrorDescriptor.setUrl(req.getRequestURI());
		nearshoreErrorDescriptor.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		nearshoreErrorDescriptor.setErrorCode(Constants.UNKNOWN_EXCEPTION_ERROR_CODE);

		logger.error(this.fetchExceptionStackTrace(e));

		return nearshoreErrorDescriptor;
	}

	private String fetchExceptionStackTrace(Exception e) {
		String exceptionStackTrace = "";
		try {
			StringWriter stringWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(stringWriter));
			exceptionStackTrace = stringWriter.toString();
			stringWriter.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return exceptionStackTrace;
	}

}
