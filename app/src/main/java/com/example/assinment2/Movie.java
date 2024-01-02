package com.example.assinment2;

import android.os.Parcel;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private int year;
    private List<String> genre;
    private double rating;
    private String director;
    private List<String> actors;
    private String plot;
    private String poster;
    private String trailer;
    private int runtime;
    private String awards;
    private String country;
    private String language;
    private String boxOffice;
    private String production;
    private String website;

    public Movie(int id, String title, int year, List<String> genre, double rating, String director, List<String> actors, String plot, String poster, String trailer, int runtime, String awards, String country, String language, String boxOffice, String production, String website) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.poster = poster;
        this.trailer = trailer;
        this.runtime = runtime;
        this.awards = awards;
        this.country = country;
        this.language = language;
        this.boxOffice = boxOffice;
        this.production = production;
        this.website = website;
    }

    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        year = in.readInt();
        genre = in.createStringArrayList();
        rating = in.readDouble();
        director = in.readString();
        actors = in.createStringArrayList();
        plot = in.readString();
        poster = in.readString();
        trailer = in.readString();
        runtime = in.readInt();
        awards = in.readString();
        country = in.readString();
        language = in.readString();
        boxOffice = in.readString();
        production = in.readString();
        website = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return id == movie.id;
    }



    @Override
    public String toString() {
        return
                "\n   Id=" + id +
                "\n   Title='" + title + '\'' +
                "\n   Year=" + year +
                "\n   Genre=" + genre +
                "\n   Rating=" + rating +
                "\n   Director='" + director + '\'' +
                "\n   Actors=" + actors +
                "\n   Plot='" + plot + '\'' +
                "\n   Poster='" + poster + '\'' +
                "\n   Trailer='" + trailer + '\'' +
                "\n   Runtime=" + runtime +
                "\n   Awards='" + awards + '\'' +
                "\n   Country='" + country + '\'' +
                "\n   Language='" + language + '\'' +
                "\n   BoxOffice='" + boxOffice + '\'' +
                "\n   Production='" + production + '\'' +
                "\n   Website='" + website + '\'' +
                "\n";
    }

}
