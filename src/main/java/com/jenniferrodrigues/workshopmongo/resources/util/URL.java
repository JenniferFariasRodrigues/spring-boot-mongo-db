package com.jenniferrodrigues.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

//Consulta simples com query methods
//No subpacote resources.util, criar classe utilitária URL com um método para decodificar parâmetro de URL

public class URL {
	//decodeparam Retornara o string codificado ou 
	//vazio se der interrupcao
	public static String decodeParam(String text) throws UnsupportedEncodingException{
		return URLDecoder.decode(text, "UTF-8");
	}

}
