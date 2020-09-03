package com.jenniferrodrigues.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.sun.el.parser.ParseException;

//Consulta simples com query methods
//No subpacote resources.util, criar classe utilitária URL com um método para decodificar parâmetro de URL

public class URL {
	//decodeparam Retornara o string codificado ou 
	//vazio se der interrupcao
	public static String decodeParam(String text) throws UnsupportedEncodingException{
		return URLDecoder.decode(text, "UTF-8");
	}
	
	public static Date convertDate(String textDate, Date defaultValue) throws java.text.ParseException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-Mm-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return sdf.parse(textDate);
	}

}
