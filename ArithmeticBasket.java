package assignment3;

import java.util.Random;

public class ArithmeticBasket {
	private double assetprice1;
	private double assetprice2;
	private double volatility1;
	private double volatility2;
	private double interestrate;
	private double time;
	private double strikeprice;
	private double correlation;
	private int number;
	private boolean control;
	
	private Random r;
	
	public ArithmeticBasket(double assetprice1, double assetprice2, double volatility1, double volatility2, 
			double interestrate, double time, double strikeprice, double correlation, int number, boolean control) {
		this.assetprice1 = assetprice1;
		this.assetprice2 = assetprice2;
		this.volatility1 = volatility1;
		this.volatility2 = volatility2;
		this.interestrate = interestrate;
		this.time = time;
		this.strikeprice = strikeprice;
		this.correlation = correlation;
		this.number = number;
		this.control = control;
		
		r = new Random(1);
	}
	
	public double[] calloption(){
		double[] ans = new double[2];
		
		if(control){
			double[] arithmetic = new double[number];
			double[] geometric = new double[number];
			double arithmeticmean = 0;
			double geometricmean = 0;
			double arithmetic_geometricmean = 0;
			
			for(int i = 0; i < number; i ++){
				double rand1 = r.nextGaussian();
				double rand2 = r.nextGaussian();
				double rand3 = correlation * rand1 + Math.sqrt(1 - correlation * correlation) * rand2;
				double tempval1 = assetprice1 * Math.exp((interestrate - 0.5 * volatility1 * volatility1) * time + 
						volatility1 * Math.sqrt(time) * rand1);
				double tempval2 = assetprice2 * Math.exp((interestrate - 0.5 * volatility2 * volatility2) * time + 
						volatility2 * Math.sqrt(time) * rand3);
				arithmetic[i] = Math.exp(- interestrate * time) * Math.max(0.5 * (tempval1 + tempval2) - strikeprice, 0);
				geometric[i] = Math.exp(- interestrate * time) * Math.max(Math.sqrt(tempval1 * tempval2) - strikeprice, 0);
				arithmeticmean += arithmetic[i] / number;
				geometricmean += geometric[i] / number;
				arithmetic_geometricmean += arithmetic[i] * geometric[i] / number;
			}
			double cov = arithmetic_geometricmean - arithmeticmean * geometricmean;
			double theta = cov / Math.pow(standard(geometric, geometricmean), 2);
			
			GeometricBasket geometricBasket = new GeometricBasket(assetprice1, assetprice2, volatility1, volatility2, interestrate, time, strikeprice, correlation);
			double geometricCall = geometricBasket.calloption();
			
			double mean = 0;
			for(int i = 0; i < number; i ++){
				mean += (arithmetic[i] + theta * (geometricCall - geometric[i])) / number;
			}
			
			double segma = Math.sqrt(Math.pow(standard(arithmetic, arithmeticmean), 2) - cov * cov / Math.pow(standard(geometric, geometricmean), 2));
			segma = segma / Math.sqrt(number);
			ans[0] = mean - 1.96 * segma;
			ans[1] = mean + 1.96 * segma;
		}
		else{
			double[] arithmetic = new double[number];
			double mean = 0;
			for(int i = 0; i < number; i ++){
				double rand1 = r.nextGaussian();
				double rand2 = r.nextGaussian();
				double rand3 = correlation * rand1 + Math.sqrt(1 - correlation * correlation) * rand2;
				double tempval1 = assetprice1 * Math.exp((interestrate - 0.5 * volatility1 * volatility1) * time + 
						volatility1 * Math.sqrt(time) * rand1);
				double tempval2 = assetprice2 * Math.exp((interestrate - 0.5 * volatility2 * volatility2) * time + 
						volatility2 * Math.sqrt(time) * rand3);
				arithmetic[i] = Math.exp(- interestrate * time) * Math.max(0.5 * (tempval1 + tempval2) - strikeprice, 0);
				mean += arithmetic[i] / number;
			}
			
			double segma = standard(arithmetic, mean) / Math.sqrt(number);
			ans[0] = mean - 1.96 * segma;
			ans[1] = mean + 1.96 * segma;
		}
		
		return ans;
	}
	
	public double[] putoption(){
		double[] ans = new double[2];
		
		if(control){
			double[] arithmetic = new double[number];
			double[] geometric = new double[number];
			double arithmeticmean = 0;
			double geometricmean = 0;
			double arithmetic_geometricmean = 0;
			
			for(int i = 0; i < number; i ++){
				double rand1 = r.nextGaussian();
				double rand2 = r.nextGaussian();
				double rand3 = correlation * rand1 + Math.sqrt(1 - correlation * correlation) * rand2;
				double tempval1 = assetprice1 * Math.exp((interestrate - 0.5 * volatility1 * volatility1) * time + 
						volatility1 * Math.sqrt(time) * rand1);
				double tempval2 = assetprice2 * Math.exp((interestrate - 0.5 * volatility2 * volatility2) * time + 
						volatility2 * Math.sqrt(time) * rand3);
				arithmetic[i] = Math.exp(- interestrate * time) * Math.max(strikeprice - 0.5 * (tempval1 + tempval2), 0);
				geometric[i] = Math.exp(- interestrate * time) * Math.max(strikeprice - Math.sqrt(tempval1 * tempval2), 0);
				arithmeticmean += arithmetic[i] / number;
				geometricmean += geometric[i] / number;
				arithmetic_geometricmean += arithmetic[i] * geometric[i] / number;
			}
			double cov = arithmetic_geometricmean - arithmeticmean * geometricmean;
			double theta = cov / Math.pow(standard(geometric, geometricmean), 2);
			
			GeometricBasket geometricBasket = new GeometricBasket(assetprice1, assetprice2, volatility1, volatility2, interestrate, time, strikeprice, correlation);
			double geometricPut = geometricBasket.putoption();
			
			double mean = 0;
			for(int i = 0; i < number; i ++){
				mean += (arithmetic[i] + theta * (geometricPut - geometric[i])) / number;
			}
			
			double segma = Math.sqrt(Math.pow(standard(arithmetic, arithmeticmean), 2) - cov * cov / Math.pow(standard(geometric, geometricmean), 2));
			segma = segma / Math.sqrt(number);
			ans[0] = mean - 1.96 * segma;
			ans[1] = mean + 1.96 * segma;
		}
		else{
			double[] arithmetic = new double[number];
			double mean = 0;
			for(int i = 0; i < number; i ++){
				double rand1 = r.nextGaussian();
				double rand2 = r.nextGaussian();
				double rand3 = correlation * rand1 + Math.sqrt(1 - correlation * correlation) * rand2;
				double tempval1 = assetprice1 * Math.exp((interestrate - 0.5 * volatility1 * volatility1) * time + 
						volatility1 * Math.sqrt(time) * rand1);
				double tempval2 = assetprice2 * Math.exp((interestrate - 0.5 * volatility2 * volatility2) * time + 
						volatility2 * Math.sqrt(time) * rand3);
				arithmetic[i] = Math.exp(- interestrate * time) * Math.max(strikeprice - 0.5 * (tempval1 + tempval2), 0);
				mean += arithmetic[i] / number;
			}
			
			double segma = standard(arithmetic, mean) / Math.sqrt(number);
			ans[0] = mean - 1.96 * segma;
			ans[1] = mean + 1.96 * segma;
		}
		
		return ans;
	}
	
	private double standard(double[] numbers, double mean){
		int length = numbers.length;
		double square = 0;
		for(int i = 0; i < length; i ++){
			square += (numbers[i] - mean) * (numbers[i] - mean) / (length - 1);
		}
		return Math.sqrt(square);
	}
}
