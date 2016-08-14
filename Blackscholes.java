package assignment3;

import org.apache.commons.math3.distribution.NormalDistribution;

public class Blackscholes {
	private double assetprice;
	private double volatility;
	private double interestrate;
	private double time;
	private double strikeprice;
	private static NormalDistribution norm = new NormalDistribution(0,1);
	
	public Blackscholes(double assetprice, double volatility, double interestrate, double time, double strikeprice) {
		
		this.assetprice = assetprice;
		this.volatility = volatility;
		this.interestrate = interestrate;
		this.time = time;
		this.strikeprice = strikeprice;
	}
	
	public double calloption(){
		double d1 = (Math.log(assetprice / strikeprice) + interestrate * time) / (volatility * Math.sqrt(time)) + 0.5 * volatility * Math.sqrt(time);
		double d2 = d1 - volatility * Math.sqrt(time);
		
		return assetprice * norm.cumulativeProbability(d1) - strikeprice * Math.exp(- interestrate * time) * norm.cumulativeProbability(d2);
	}
	
	public double putoption(){
		return putcallparity(calloption());
	}
	
	private double putcallparity(double call){
		return strikeprice * Math.exp(- interestrate * time) + call - assetprice;
	}
}
