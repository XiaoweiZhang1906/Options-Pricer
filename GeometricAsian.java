package assignment3;

import org.apache.commons.math3.distribution.NormalDistribution;

public class GeometricAsian {
	private double assetprice;
	private double interestrate;
	private double time;
	private double strikeprice;
	
	private double volatility1;
	private double mu;
	
	private static NormalDistribution norm = new NormalDistribution(0,1);
	
	public GeometricAsian(double assetprice, double volatility, double interestrate, double time, double strikeprice, int observation) {
		this.assetprice = assetprice;
		this.interestrate = interestrate;
		this.time = time;
		this.strikeprice = strikeprice;
		
		volatility1 = volatility * Math.sqrt((double)(observation + 1) * (2 * observation + 1) / (6 * observation * observation));
		mu = (interestrate - 0.5 * volatility * volatility) * (observation + 1) / (2 * observation) + 0.5 * volatility1 * volatility1;
	}
	
	public double calloption(){
		double d1 = (Math.log(assetprice / strikeprice) + (mu + 0.5 * volatility1 * volatility1) * time) / (volatility1 * Math.sqrt(time));
		double d2 = d1 - volatility1 * Math.sqrt(time);
		
		return Math.exp(- interestrate * time) * (assetprice * Math.exp(mu * time) * norm.cumulativeProbability(d1) 
				- strikeprice * norm.cumulativeProbability(d2));
	}
	
	public double putoption(){
		double d1 = (Math.log(assetprice / strikeprice) + (mu + 0.5 * volatility1 * volatility1) * time) / (volatility1 * Math.sqrt(time));
		double d2 = d1 - volatility1 * Math.sqrt(time);
		
		return Math.exp(- interestrate * time) * (strikeprice * norm.cumulativeProbability(- d2)
				- assetprice * Math.exp(mu * time) * norm.cumulativeProbability(- d1));
	}
}
