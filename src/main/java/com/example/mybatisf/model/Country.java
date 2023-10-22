package com.example.mybatisf.model;

public class Country {
    private Long id;
    private String countryname;
    private String countrycode;

    public void setId(Long id){this.id=id;}
    public Long getId(){return this.id;}

    public void setCountryname(String countryname){this.countryname=countryname;}
    public String getCountryname(){return this.countryname;}

    public void setCountrycode(String countrycode){this.countrycode=countrycode;}
    public String getCountrycode(){return this.countrycode;}
}
