package com.yuri.cavalcante.cursospringudemy.resourses.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static String decodeParam(String nome) {
		try {
			
			return URLDecoder.decode(nome, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			return "";
		}
	}

	public static List<Integer> decodeIntList(String categorias){
		
		String[] vet = categorias.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i<vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}

		return list;
		//(List<Integer>) Arrays.asList(categorias.split(",")).stream().map(item -> Integer.parseInt(item)).collect(Collectors.toList());
	} 

}
