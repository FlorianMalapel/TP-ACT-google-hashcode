package tp.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tp.pb.Certificate;
import tp.pb.Part;
import tp.pb.Pizza;

public class FileOperation {
	private Certificate certificate;
	private Pizza pizza;
	
	/** Default Constructor **/
	public FileOperation() {}
	
	
	/**
	 * M�hode qui permet de lire les donn�es d'un fichier texte et d'initialiser une instance du probl�me de pizza avec ces donn�es
	 * @param pathFile : String -> chemin du fichier � lire
	 * @return Retourne une instance de l'objet Pizza
	 */
	public Pizza readFile(String pathFile) {
		Pizza pizza = null;
		int height = 0, width = 0, maxSurface = 0, nJambon = 0, i = 0, j = 0;
		char pizzaTab[][] = null;
		try{
            File ips = new File(pathFile);
            FileReader ipsr = new FileReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            /** Lecture des données (1ère ligne) **/
            String[] parts = (br.readLine()).split(" ");
            height = Integer.parseInt(parts[0]);
            width = Integer.parseInt(parts[1]);
            nJambon = Integer.parseInt(parts[2]);
            maxSurface = Integer.parseInt(parts[3]);
            /** Lecture du tableau (la pizza) **/
            pizzaTab = new char[height][width];
            String line;
            while ((line = br.readLine()) != null) {
				if(line.length() > 0) {
					for (j = 0; j < width; j++ ){
						pizzaTab[i][j] = line.charAt(j);   
				    }
					i++;
				}
            }
            br.close();
        }  
        catch (Exception e){
            System.out.println(e.toString());
        }
		pizza = new Pizza(height, width, maxSurface, nJambon, pizzaTab);
		return pizza;
	}
	
	/** 
	 * Méthode qui permet d'écrire dans un fichier
	 * @param pathTargetFile : String -> chemin + nom du fichier à créer
	 */
	public void writeFile(String nameTargetFile) {
		File f = new File (nameTargetFile);
		try {
			String name = System.getProperty ( "os.name" );
			boolean windows = name.contains("Windows") || name.contains("WINDOWS");
		    FileWriter fw = new FileWriter(f);
		    int nbParts = certificate.getRoyalParts().size();
		    fw.write(String.valueOf(nbParts));
		    if(windows)
	        	fw.write ("\r\n");
	        else 
	        	fw.write ("\n");
		    for (Part part : certificate.getRoyalParts()){
		        fw.write (part.getLigne() + " " + part.getColonne() + " " + part.getLigneEnd() + " " + part.getColonneEnd());
		        if(windows)
		        	fw.write ("\r\n");
		        else 
		        	fw.write ("\n");
		    }
		    fw.close();
		}
		catch (IOException exception) {
		    System.out.println ("Error : " + exception.getMessage());
		}
	}
	
	
	/** Getters and Setters **/
	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificat(Certificate _certificate) {
		certificate = _certificate;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
}
