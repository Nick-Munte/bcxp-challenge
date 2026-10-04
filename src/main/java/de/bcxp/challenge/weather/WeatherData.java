package de.bcxp.challenge.weather;

public class WeatherData{
    private final int day;
    private final int mxT;
    private final int mnT;
    private final int acT;
    private final double avDP;
    private final int oneHrp_TPcpn;
    private final int pDir;
    private final double AvSp;
    private final int dir;
    private final int mxS;
    private final double skyC;
    private final int mxR;
    private final int mn;
    private final double r_AvSLP;

    public WeatherData(int day, int mxT, int mnT, int acT, double avDP, int oneHrp_TPcpn, int pDir, double avSp, int dir, int mxS, double skyC, int mxR, int mn, double r_AvSLP) {
        this.day = day;
        this.mxT = mxT;
        this.mnT = mnT;
        this.acT = acT;
        this.avDP = avDP;
        this.oneHrp_TPcpn = oneHrp_TPcpn;
        this.pDir = pDir;
        this.AvSp = avSp;
        this.dir = dir;
        this.mxS = mxS;
        this.skyC = skyC;
        this.mxR = mxR;
        this.mn = mn;
        this.r_AvSLP = r_AvSLP;
    }

    public int getDay() {
        return day;
    }

    public int getMxT() {
        return mxT;
    }

    public int getMnT() {
        return mnT;
    }

    public int getAcT() {
        return acT;
    }

    public double getAvDP() {
        return avDP;
    }

    public int getOneHrp_TPcpn() {
        return oneHrp_TPcpn;
    }

    public int getpDir() {
        return pDir;
    }

    public double getAvSp() {
        return AvSp;
    }

    public int getDir() {
        return dir;
    }

    public int getMxS() {
        return mxS;
    }

    public double getSkyC() {
        return skyC;
    }

    public int getMxR() {
        return mxR;
    }

    public int getMn() {
        return mn;
    }

    public double getR_AvSLP() {
        return r_AvSLP;
    }
}
