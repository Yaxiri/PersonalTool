package wasdev.sample.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechAlternative;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.Transcript;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;

public class App {
	
	static String Resultado;
	static ArrayList<String> Conversacion;
	
	public static void main(String[] args) throws IOException, InterruptedException, LineUnavailableException {
		//Resultado = "";
		SpeechToText();
		//System.out.println("INICIA IMPRESION*********");
		//System.out.println(Resultado);
		//System.out.println("TERMINA IMPRESION*********");
		//System.out.println(Traductor(Resultado, "ENGLISH"));
		//textToSpeech(Traductor(Resultado, "ENGLISH"), "ENGLISH");
		//Resultado = "";
		//ToneAnalyzer("HOLA");
	}

	public static void CreateJson(String pDatos) throws IOException {

		try {
			// Crear un objeto File se encarga de crear o abrir acceso a un
			// archivo que se especifica en su constructor
			//File archivo = new File("archivo.json");
			 File archivo = new File("../data.json");

			if (archivo.delete()) {
				System.out.println("El fichero ha sido borrado satisfactoriamente");
			} else {
				System.out.println("El fichero no puede ser borrado");
			}

			// Crear objeto FileWriter que sera el que nos ayude a escribir
			// sobre archivo
			FileWriter escribir = new FileWriter(archivo, true);

			// Escribimos en el archivo con el metodo write
			escribir.write(pDatos);

			// Cerramos la conexion
			escribir.close();
		}

		// Si existe un problema al escribir cae aqui
		catch (Exception e) {
			System.out.println("Error al escribir");
		}
	}

	public static void textToSpeech(String pText,String pIdioma){
		
		TextToSpeech service = new TextToSpeech();
		service.setUsernameAndPassword("3f07afb1-7751-4dd6-98f6-2f7059f96333", "5CqvQJicuUZR");
		
		File archivo = new File("audio.wav");

		if (archivo.delete()) {
			System.out.println("El fichero ha sido borrado satisfactoriamente");
		} else {
			System.out.println("El fichero no puede ser borrado");
		}
		
		
		if(pIdioma.equals("ENGLISH")){
			
			try {
				  InputStream stream = service.synthesize(pText, Voice.EN_ALLISON).execute();
				  InputStream in = WaveUtils.reWriteWaveHeader(stream);
				  
				  
				  
				  OutputStream out = new FileOutputStream("audio.wav");
				  byte[] buffer = new byte[1024];
				  int length;
				  while ((length = in.read(buffer)) > 0) {
				    out.write(buffer, 0, length);
				  }
				  out.close();
				  in.close();
				  stream.close();
				}
				catch (Exception e) {
				  e.printStackTrace();
				}
			
		}
		else if(pIdioma.equals("FRENCH")){
			try {
				  InputStream stream = service.synthesize(pText, Voice.FR_RENEE).execute();
				  InputStream in = WaveUtils.reWriteWaveHeader(stream);
				  
				  
				  
				  OutputStream out = new FileOutputStream("audio.wav");
				  byte[] buffer = new byte[1024];
				  int length;
				  while ((length = in.read(buffer)) > 0) {
				    out.write(buffer, 0, length);
				  }
				  out.close();
				  in.close();
				  stream.close();
				}
				catch (Exception e) {
				  e.printStackTrace();
				}
		}
		else if(pIdioma.equals("ITALIAN")){
			try {
				  InputStream stream = service.synthesize(pText, Voice.IT_FRANCESCA).execute();
				  InputStream in = WaveUtils.reWriteWaveHeader(stream);
				  
				  
				  
				  OutputStream out = new FileOutputStream("audio.wav");
				  byte[] buffer = new byte[1024];
				  int length;
				  while ((length = in.read(buffer)) > 0) {
				    out.write(buffer, 0, length);
				  }
				  out.close();
				  in.close();
				  stream.close();
				}
				catch (Exception e) {
				  e.printStackTrace();
				}
		}
		else if(pIdioma.equals("PORTUGUESE")){
			try {
				  InputStream stream = service.synthesize(pText, Voice.PT_ISABELA).execute();
				  InputStream in = WaveUtils.reWriteWaveHeader(stream);
				  
				  
				  
				  OutputStream out = new FileOutputStream("audio.wav");
				  byte[] buffer = new byte[1024];
				  int length;
				  while ((length = in.read(buffer)) > 0) {
				    out.write(buffer, 0, length);
				  }
				  out.close();
				  in.close();
				  stream.close();
				}
				catch (Exception e) {
				  e.printStackTrace();
				}
		}
		
	}
	
	public static String Traductor(String pDatos,String pIdioma){
		
		LanguageTranslator service = new LanguageTranslator();
		TranslationResult translationResult = null;
		service.setUsernameAndPassword("64af68c0-118b-42bf-b711-a51a4b885775", "EIAXzfLfYNg5");
		if(pIdioma.equals("ENGLISH")){
			translationResult = service.translate(pDatos, Language.SPANISH, Language.ENGLISH).execute();
		}
		else if(pIdioma.equals("FRENCH")){
			translationResult = service.translate(pDatos, Language.SPANISH, Language.FRENCH).execute();
		}
		else if(pIdioma.equals("ITALIAN")){
			translationResult = service.translate(pDatos, Language.SPANISH, Language.ITALIAN).execute();
		}
		else if(pIdioma.equals("PORTUGUESE")){
			translationResult = service.translate(pDatos, Language.SPANISH, Language.PORTUGUESE).execute();
		}
		return translationResult.getTranslations().get(0).getTranslation();
		
	}

	public static String ToneAnalyzer(String Analizar) throws IOException {
		com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer service = new com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer(
				com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer.VERSION_DATE_2016_05_19);
		service.setUsernameAndPassword("9354769b-afa9-4666-9417-9bd92a6c48cc", "8ZSrKP2Y1SGG");

		// Call the service and get the tone
		ToneAnalysis tone = service.getTone(Analizar, null).execute();
		// System.out.println(tone.toString());
		System.out.println("Termina analizador");
		return tone.toString();

	}

	public static void SpeechToText() throws InterruptedException, LineUnavailableException {
		Conversacion = new ArrayList<String>();
		Resultado="";
		SpeechToText service = new SpeechToText();
		service.setUsernameAndPassword("d6484b2a-587c-4819-ba78-2988c14b6f36", "uV7ngppwWo37");

		// Signed PCM AudioFormat with 16kHz, 16 bit sample size, mono
		int sampleRate = 16000;
		AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, false);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		try{
		if (!AudioSystem.isLineSupported(info)) {
			System.out.println("Line not supported");
			System.exit(0);
		}

		TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
		line.open(format);
		line.start();

		AudioInputStream audio = new AudioInputStream(line);

		RecognizeOptions options = new RecognizeOptions.Builder().continuous(true).interimResults(true).model("es-ES_BroadbandModel")
				// .inactivityTimeout(5) // use this to stop listening when the
				// speaker pauses, i.e. for 5s
				.contentType(HttpMediaType.AUDIO_RAW + "; rate=" + sampleRate).build();

		service.recognizeUsingWebSocket(audio, options, new BaseRecognizeCallback() {
			public void onTranscription(SpeechResults speechResults) {
				
				List<Transcript> V1 = speechResults.getResults();
				Transcript V2 = V1.get(0);
				if (V2.isFinal() != false) {
					List<SpeechAlternative> V3 = V2.getAlternatives();
					SpeechAlternative V4 = V3.get(0);
					System.out.println(V4.getTranscript() + " ");
					Conversacion.add(V4.getTranscript());
				}
			}
		});

		System.out.println("Listening to your voice for the next 30s...");
		Thread.sleep(60 * 1000);

		// closing the WebSockets underlying InputStream will close the
		// WebSocket itself.

		line.stop();
		line.close();
		System.out.println("Fin.");
		for(int i=0;i<Conversacion.size();i++){
			Resultado += Conversacion.get(i);
		}
		System.out.println(Resultado);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}