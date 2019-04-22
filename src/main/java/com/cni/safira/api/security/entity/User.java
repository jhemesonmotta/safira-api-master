package com.cni.safira.api.security.entity;

import com.cni.safira.api.security.enums.ProfileEnum;

public class User {

	private String id;
	private String cnpj;
	private String cnae;
	private String nomeEmpresa;
	private String nomeEmpresaFantasia;
	private String enderecoEmpresa;
	private String telefoneEmpresa;
	private String nomeResponsavel;
	private String cpfResponsavel;
	private String emailResponsavel;
	private String password;
	private ProfileEnum profile;
	

	public User() {
		super();
	}

	public User(String id, String cpfResponsavel, String password, ProfileEnum profile) {
		super();
		this.id = id;
		this.cpfResponsavel = cpfResponsavel;
		this.password = password;
		this.profile = profile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNomeEmpresaFantasia() {
		return nomeEmpresaFantasia;
	}

	public void setNomeEmpresaFantasia(String nomeEmpresaFantasia) {
		this.nomeEmpresaFantasia = nomeEmpresaFantasia;
	}

	public String getEnderecoEmpresa() {
		return enderecoEmpresa;
	}

	public void setEnderecoEmpresa(String enderecoEmpresa) {
		this.enderecoEmpresa = enderecoEmpresa;
	}

	public String getTelefoneEmpresa() {
		return telefoneEmpresa;
	}

	public void setTelefoneEmpresa(String telefoneEmpresa) {
		this.telefoneEmpresa = telefoneEmpresa;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}

}
