package assignment3;

import org.apache.commons.math3.distribution.NormalDistribution;

public class Volatility {
	private double assetprice;
	private double interestrate;
	private double reporate;
	private double time;
	private double strikeprice;
	private double preminum;
	private static NormalDistribution norm = new NormalDistribution(0,1);
	
	public Volatility(double assetprice, double interestrate, double reporate, double time, double strikeprice, double premium) {
		this.assetprice = assetprice;
		this.interestrate = interestrate;
		this.reporate = reporate;
		this.time = time;
		this.strikeprice = strikeprice;
		this.preminum = premium;
	}
	
	private double f1_volatility(double val){
		double d1 = (Math.log(assetprice / strikeprice) + (interestrate - reporate) * time) / (val * Math.sqrt(time))
				+ 0.5 * val * Math.sqrt(time);
		double d2 = d1 - val * Math.sqrt(time);
		
		return assetprice * Math.exp(- reporate * time) * norm.cumulativeProbability(d1)
				- strikeprice * Math.exp(- interestrate * time) * norm.cumulativeProbability(d2) - preminum;
	}
	
	private double f2_volatility(double val){
		double d1 = (Math.log(assetprice / strikeprice) + (interestrate - reporate) * time) / (val * Math.sqrt(time))
				+ 0.5 * val * Math.sqrt(time);
		
		return assetprice * Math.exp(- reporate * time) * Math.sqrt(time) * norm.density(d1);
	}
	
	public double calloption(){
		double ans = Math.sqrt(2 * Math.abs((Math.log(assetprice / strikeprice) 
				+ (interestrate - reporate) * time) / time));
		
		double temp = ans - f1_volatility(ans) / f2_volatility(ans);
		while(Math.abs(ans - temp) > 0.000001){
			ans = temp;
			temp = ans - f1_volatility(ans) / f2_volatility(ans);
		}
		
		return ans;
	}
	
	public double putoption() {
		preminum = callputparity(preminum);
		if(preminum < 0){
			return 0.0;
		}
		return calloption();
	}
	
	private double callputparity(double put){
		return assetprice * Math.exp(- reporate * time) - strikeprice * Math.exp(- interestrate * time) + put;
	}
	
	
}
