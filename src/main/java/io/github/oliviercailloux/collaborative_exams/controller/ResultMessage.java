package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.Objects;

public class ResultMessage {
	
	private String message;
	private int valeur;
	
	ResultMessage(String message, int valeur){
		this.message= Objects.requireNonNull(message);
		this.valeur= Objects.requireNonNull(valeur);
	}
	
    /**
     * 
     * getter 
     */
	public String message(){
		return this.message;
	}
	public int valeur(){
		return this.valeur;
	}
}    

