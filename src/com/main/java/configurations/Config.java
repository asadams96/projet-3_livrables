package com.main.java.configurations;

public enum Config {
	
	GameParameters(4,100,4,true); // 100 pour les test
	
	private int nbrCombi;
	private int nbrTry;
	private int nbrUsableFigures;
	private boolean devMode;
	
	Config(int nbrCombi, int nbrTry, int nbrUsableFigures, boolean devMode){
		this.nbrCombi = nbrCombi;
		this.nbrTry = nbrTry;
		this.nbrUsableFigures = nbrUsableFigures;
		this.devMode = devMode;
	}

	public int getNbrCombi() {
		return nbrCombi;
	}

	public void setNbrCombi(int nbrCombi) {
		this.nbrCombi = nbrCombi;
	}

	public int getNbrTry() {
		return nbrTry;
	}

	public void setNbrTry(int nbrTry) {
		this.nbrTry = nbrTry;
	}

	public int getNbrUsableFigures() {
		return nbrUsableFigures;
	}

	public void setNbrUsableFigures(int nbrUsableFigures) {
		this.nbrUsableFigures = nbrUsableFigures;
	}

	public boolean isDevMode() {
		return devMode;
	}

	public void setDevMode(boolean devMode) {
		this.devMode = devMode;
	}

}
