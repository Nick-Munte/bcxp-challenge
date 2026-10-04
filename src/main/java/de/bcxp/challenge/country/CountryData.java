package de.bcxp.challenge.country;

public class CountryData {
    private final String name;
    private final String capital;
    private final String accession;
    private final int population;
    private final int area_km2;
    private final int gDP_MUSD;
    private final float mEPs;
    private final int hDI;

    public CountryData(String name, String capital, String accession, int population, int area_km2, int gDP_MUSD, float mEPs, int hDI) {
        this.name = name;
        this.capital = capital;
        this.accession = accession;
        this.population = population;
        this.area_km2 = area_km2;
        this.gDP_MUSD = gDP_MUSD;
        this.mEPs = mEPs;
        this.hDI = hDI;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getAccession() {
        return accession;
    }

    public int getPopulation() {
        return population;
    }

    public int getArea_km2() {
        return area_km2;
    }

    public int getgDP_MUSD() {
        return gDP_MUSD;
    }

    public float getmEPs() {
        return mEPs;
    }

    public int gethDI() {
        return hDI;
    }
}
