package com.bexperr.app.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class Utility {
	
	private static final Logger logger = LogManager.getLogger(Utility.class);
	public String removeAcentos(String input) {
		// Cadena de caracteres original a sustituir.
		String original = "·‡‰ÈËÎÌÏÔÛÚˆ˙˘uÒ¡¿ƒ…»ÀÕÃœ”“÷⁄Ÿ‹—Á«";
		// Cadena de caracteres ASCII que reemplazar·n los originales.
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String output = input;
		for (int i = 0; i < original.length(); i++) {
			// Reemplazamos los caracteres especiales.
			output = output.replace(original.charAt(i), ascii.charAt(i));
		} // for i
		return output;
	}// remove1

	
	public  String quitarCaracteresNoValidos(String cadena) {
		// Para el reemplazo usamos un string vacÌo
		return cadena.replaceAll("\"", "'");
		
	}
	
	public  String quitarSaltos(String cadena) {
		// Para el reemplazo usamos un string vacÌo
		cadena = cadena.replaceAll("\t", " ");
		cadena = cadena.replaceAll("\r", " ");
		return cadena.replaceAll("\n", " ");
	}
	
	public  String quitarSaltosHtml(String cadena) {
		cadena = cadena.replaceAll("<br>", "");
		return cadena;
	}

	public Long DiferenciaFechas(String vinicio, String vfinal) {
		System.out.println("DiferenciaFechas  :: horas");
		System.out.println("vinicio "+vinicio);
		System.out.println("vfinal "+vfinal);
		

		Date dinicio = null, dfinal = null;
		long milis1, milis2, diff;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			// PARSEO STRING A DATE
			dinicio = sdf.parse(vinicio);
			dfinal = sdf.parse(vfinal);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Se ha producido un error en el parseo");
		}

		// INSTANCIA DEL CALENDARIO GREGORIANO
		Calendar cinicio = Calendar.getInstance();
		Calendar cfinal = Calendar.getInstance();

		// ESTABLECEMOS LA FECHA DEL CALENDARIO CON EL DATE GENERADO ANTERIORMENTE
		cinicio.setTime(dinicio);
		cfinal.setTime(dfinal);
		milis1 = cinicio.getTimeInMillis();
		milis2 = cfinal.getTimeInMillis();
		diff = milis2 - milis1;

		// calcular la diferencia en segundos

		// diffSegundos = Math.abs(diff / 1000);

		// calcular la diferencia en minutos

		//long diffMinutos = Math.abs(diff / (60 * 1000));

		//long restominutos = diffMinutos % 60;

		// calcular la diferencia en horas

		long diffHoras = (diff / (60 * 60 * 1000));

		// calcular la diferencia en dias

		//long diffdias = Math.abs(diff / (24 * 60 * 60 * 1000));

		/*
		 * System.out.println("En segundos: " + diffSegundos + " segundos.");
		 * 
		 * System.out.println("En minutos: " + diffMinutos + " minutos.");
		 * 
		 * System.out.println("En dias: " + diffdias + " dias.");
		 */

		System.out.println("En horas: " + diffHoras + " horas.");

		Long devolver = (Math.abs(diffHoras));
		return devolver;
	}
	
	public Long DiferenciaFechasDias(String vinicio, String vfinal) {
		System.out.println("vinicio "+vinicio);
		System.out.println("vfinal "+vfinal);
		Date dinicio = null, dfinal = null;
		long milis1, milis2, diff;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			// PARSEO STRING A DATE
			dinicio = sdf.parse(vinicio);
			dfinal = sdf.parse(vfinal);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Se ha producido un error en el parseo");
		}

		// INSTANCIA DEL CALENDARIO GREGORIANO
		Calendar cinicio = Calendar.getInstance();
		Calendar cfinal = Calendar.getInstance();

		// ESTABLECEMOS LA FECHA DEL CALENDARIO CON EL DATE GENERADO ANTERIORMENTE
		cinicio.setTime(dinicio);
		cfinal.setTime(dfinal);
		milis1 = cinicio.getTimeInMillis();
		milis2 = cfinal.getTimeInMillis();
		diff = milis2 - milis1;

		long diffdias = Math.abs(diff / (24 * 60 * 60 * 1000));


		System.out.println("En dias: " + diffdias);

		Long devolver = (Math.abs(diffdias));
		return devolver;
	}
	
	public boolean validatePhoneNumber(String phoneNo) {
		//validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{10}")) return true;
		//validating phone number with -, . or spaces
		else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
		//validating phone number with extension length from 3 to 5
		else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
		//validating phone number where area code is in braces ()
		else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
		//return false if nothing matches the input
		else return false;
		
	}
	
	public String fileToB64(MultipartFile file) {
		String strB64 = null;
			System.out.println(file.getOriginalFilename());
			try {
			InputStream fileContent = file.getInputStream();
			byte[] buff = new byte[8000];
			int bytesRead = 0;
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			while((bytesRead = fileContent.read(buff)) != -1) {
		           bao.write(buff, 0, bytesRead);
		        }

		        byte[] data = bao.toByteArray();
		        byte[] encoded = Base64.encodeBase64(data);
				strB64 = new String(encoded);
				//System.out.println("strB64 "+ strB64);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Utility > fileToB64" + e.getMessage());
		}
		return strB64;
	}
	
	public boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
	
	public static String getFechaCadena(String cadenaFecha, Date date){
		SimpleDateFormat d = null;
		try {
			//hh am y pm , HH solo horas.
			if( cadenaFecha.isEmpty() ){
				d = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy  hh:mm a", new Locale("es", "MX"));
			}else {
				d = new SimpleDateFormat(cadenaFecha, new Locale("es", "MX"));
			}
		} catch (Exception e) {
			d = new SimpleDateFormat("dd/MM/yyyy  hh:mm:ss", new Locale("es", "MX"));
		}
		return d.format(date);
	}

	
	public static String getObjectSF(Object objeto){
		if(objeto != null){
			return (String)objeto;
		}else{
			return "";
		}
	}
}
