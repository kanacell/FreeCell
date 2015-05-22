package constantesPackage;

public class Constantes {
	public static class Carte {
		public static class Famille {
			public static final String coe = "coe";
			public static final String car = "car";
			public static final String piq = "piq";
			public static final String tre = "tre";
			public static final String nul = "nul";
			
			public static final String[] tabFam = { coe, car, piq, tre };
		}
		
		public static class Couleur {
			public static final String blanche = "blanche";
			public static final String rouge = "rouge";
			public static final String noire = "noire";
		}
	}
	
	public static class Plateau {
		public static final int nombreColonnes = 8;
		public static final int nombreCartes = 13;
		public static final int nombreFamilles = 4;
		public static final int nombreCellules = 4;
		public static final int totalCartes = nombreCartes * nombreFamilles;
	}
}
