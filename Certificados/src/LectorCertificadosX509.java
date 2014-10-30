import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;


public class LectorCertificadosX509 {
	private String path;
	
	public LectorCertificadosX509(){
		this.path = new String("certificados/");
	}
	
	private String leerNombreCertificado(){
		InputStreamReader flujo = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(flujo);
		System.out.print("Introduzca el nombre del certificado:");
		String nameFile = new String();
		try {
			nameFile = teclado.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return nameFile;
	}
	
	public void leerCertificado(){
		String nameFile = this.leerNombreCertificado();
		FileInputStream fr;
		try {
			fr = new FileInputStream(this.path + nameFile+".crt");
			CertificateFactory cf = CertificateFactory.getInstance("X509");
			X509Certificate c = (X509Certificate) cf.generateCertificate(fr);
			fr.close();
			this.mostrarInformacion(c);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void mostrarInformacion(X509Certificate c){
		System.out.println("Propietario :"+c.getSubjectX500Principal());
		System.out.println("Emisor :"+c.getIssuerX500Principal());
		System.out.println("Validez :"+c.getNotAfter());
		System.out.println("Nº Serie :"+c.getSerialNumber());
	}
	public static void main(String [] args){
		LectorCertificadosX509 lector = new LectorCertificadosX509();
		lector.leerCertificado();		
		
	}
}
