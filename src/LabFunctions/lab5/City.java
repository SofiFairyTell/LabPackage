package LabFunctions.lab5;

public class City {
    private String country;
    private String name;
    private String mayor;
    private int population;
    private double area;
    private int qualityOfLife;

    public City(String country, String name, String mayor, int population, double area, int qualityOfLife) {
        this.country = country;
        this.name = name;
        this.mayor = mayor;
        this.population = population;
        this.area = area;
        this.qualityOfLife = qualityOfLife;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getMayor() {
        return mayor;
    }

    public int getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public int getQualityOfLife() {
        return qualityOfLife;
    }

    @Override
    public String toString() {
        return "Город{" +
                "Страна='" + country + '\'' +
                ", Название='" + name + '\'' +
                ", Мэр='" + mayor + '\'' +
                ", Численность=" + population +
                ", Площадь=" + area +
                ", Уровень жизни=" + qualityOfLife +
                '}';
    }
}

