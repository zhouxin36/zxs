package com.zx.microservice.microservice.spel;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 实体类
 *
 * @author zhouxin
 * @since 2019/3/7
 */
public class Inventor {
  private String name;
  private String nationality;
  private String[] inventions;
  private Date birthdate;
  private PlaceOfBirth placeOfBirth;

  public Inventor(String name, String nationality) {
    GregorianCalendar c = new GregorianCalendar();
    this.name = name;
    this.nationality = nationality;
    this.birthdate = c.getTime();
  }

  public Inventor(String name, Date birthdate, String nationality) {
    this.name = name;
    this.nationality = nationality;
    this.birthdate = birthdate;
  }

  public Inventor() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public PlaceOfBirth getPlaceOfBirth() {
    return placeOfBirth;
  }

  public void setPlaceOfBirth(PlaceOfBirth placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }

  public String[] getInventions() {
    return inventions;
  }

  public void setInventions(String[] inventions) {
    this.inventions = inventions;
  }
}
