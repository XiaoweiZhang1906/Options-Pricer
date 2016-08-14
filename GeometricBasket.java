package assignment3;

import org.apache.commons.math3.distribution.NormalDistribution;

public class GeometricBasket {
	private double interestrate;
	private double time;
	private double strikeprice;
	
	private double volatility;
	private double mu;
	private double assetprice;
	
	private static NormalDistribution norm = new NormalDistribution(0,1);
	
	public GeometricBasket(double assetprice1, double assetprice2, double volatility1, double volatility2, 
			double interestrate, double time, double strikeprice, double correlation) {
		this.interestrate = interestrate;
		this.time = time;
		this.strikeprice = strikeprice;
		
		volatility = Math.sqrt(2 * volatility1 * volatility2 * correlation + volatility1 * volatility1 + volatility2 * volatility2) / 2;
		mu = interestrate - 0.25 * (volatility1 * volatility1 + volatility2 * volatility2) + 0.5 * volatility * volatility;
		assetprice = Math.sqrt(assetprice1 * assetprice2);
	}
	
	public double calloption() {
		double d1 = (Math.log(assetprice / strikeprice) + mu * time) / (volatility * Math.sqrt(time)) + 0.5 * volatility * Math.sqrt(time);
		double d2 = d1 - volatility * Math.sqrt(time);
		
		return Math.exp(- interestrate * time) * (assetprice * Math.exp(mu * time) * norm.cumulativeProbability(d1) 
				- strikeprice * norm.cumulativeProbability(d2));
	}
	
	public double putoption() {
		double d1 = (Math.log(assetprice / strikeprice) + mu * time) / (volatility * Math.sqrt(time)) + 0.5 * volatility * Math.sqrt(time);
		double d2 = d1 - volatility * Math.sqrt(time);
		
		return Math.exp(- interestrate * time) * (strikeprice * norm.cumulativeProbability(- d2) 
				- assetprice * Math.exp(mu * time) * norm.cumulativeProbability(- d1));
	}
}
