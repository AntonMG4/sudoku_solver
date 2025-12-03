package si2024.antonmaestrealu.p05;

import java.util.ArrayList;

public class Variable {
    ArrayList<Integer> dominio = new ArrayList<Integer>();
	public int idc;
	public int idf;
	
	public Variable(int idf, int idc) {
		this.idc = idc;
		this.idf = idf;
		for(int i=1; i<10; i++) {
			dominio.add(i);
		}
		
	}
	
	public ArrayList<Integer> getDominio() {
		return dominio;
	}
	
	
}
