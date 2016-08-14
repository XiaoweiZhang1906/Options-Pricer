package assignment3;

import java.util.Random;

public class ArithmeticAsian {
	private double assetprice;
	private double volatility;
	private double interestrate;
	private double time;
	private double strikeprice;
	private int observation;
	private int number;
	private boolean control;
	
	private Random r;
	private double t1;
		
	public ArithmeticAsian(double assetprice, double volatility, double interestrate, double time, 
			double strikeprice, int observation, int number, boolean control) {
		
		this.assetprice = assetprice;
		this.volatility = volatility;
		this.interestrate = interestrate;
		this.time = time;
		this.strikeprice = strikeprice;
		this.observation = observation;
		this.number = number;
		this.control = control;
		
		r = new Random(1);
		t1 = time / observation;
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
				double tempval = assetprice;
				double temparithmetic = 0;
				double tempgeometric = 0;
				for(int j = 0; j < observation; j ++){
					tempval = tempval * Math.exp((interestrate - 0.5 * volatility * volatility) * t1 + 
							volatility * Math.sqrt(t1) * r.nextGaussian());
					temparithmetic += tempval / observation;
					tempgeometric += Math.log(tempval) / observation;
				}
				arithmetic[i] = Math.exp(- interestrate * time) * Math.max(temparithmetic - strikeprice, 0);
				geometric[i] = Math.exp(- interestrate * time) * Math.max(Math.exp(tempgeometric) - strikeprice, 0);
				arithmeticmean += arithmetic[i] / number;
				geometricmean += geometric[i] / number;
				arithmetic_geometricmean += arithmetic[i] * geometric[i] / number;
			}
			
			double cov = arithmetic_geometricmean - arithmeticmean * geometricmean;
			double theta = cov / Math.pow(standard(geometric, geometricmean), 2);
			
			GeometricAsian geometricAsian = new GeometricAsian(assetprice, volatility, interestrate, time, strikeprice, observation);
			double geometricCall = geometricAsian.calloption();

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
			double[] result = new double[number];
			double mean = 0;
			for(int i = 0; i < number; i ++){
				double sum = 0;
				double tempval = assetprice;
				for(int j = 0; j < observation; j ++){
					tempval = tempval * Math.exp((interestrate - 0.5 * volatility * volatility) * t1 + 
							volatility * Math.sqrt(t1) * r.nextGaussian());
					sum +=  tempval / observation;
				}
				result[i] = Math.exp(- interestrate * time) * Math.max(sum - strikeprice, 0);
				mean += result[i] / number;
			}
		
			double segma = standard(result, mean) / Math.sqrt(number);
			ans[0] = mean - 1.96 * segma;
			ans[1] = mean + 1.96 * segma;
		}
		
		return ans;
	}
	
	public double[] putoption() {
		double[] ans = new double[2];
		
		if(control){
			double[] arithmetic = new double[number];
			double[] geometric = new double[number];
			double arithmeticmean = 0;
			double geometricmean = 0;
			double arithmetic_geometricmean = 0;
			
			for(int i = 0; i < number; i ++){
				double tempval = assetprice;
				double temparithmetic = 0;
				double tempgeometric = 0;
				for(int j = 0; j < observation; j ++){
					tempval = tempval * Math.exp((interestrate - 0.5 * volatility * volatility) * t1 + 
							volatility * Math.sqrt(t1) * r.nextGaussian());
					temparithmetic += tempval / observation;
					tempgeometric += Math.log(tempval) / observation;
				}
				arithmetic[i] = Math.exp(- interestrate * time) * Math.max(strikeprice - temparithmetic, 0);
				geometric[i] = Math.exp(- interestrate * time) * Math.max(strikeprice - Math.exp(tempgeometric), 0);
				arithmeticmean += arithmetic[i] / number;
				geometricmean += geometric[i] / number;
				arithmetic_geometricmean += arithmetic[i] * geometric[i] / number;
			}
			
			
			double cov = arithmetic_geometricmean - arithmeticmean * geometricmean;
			double theta = cov / Math.pow(standard(geometric, geometricmean), 2);
			
			GeometricAsian geometricAsian = new GeometricAsian(assetprice, volatility, interestrate, time, strikeprice, observation);
			double geometricPut = geometricAsian.putoption();

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
			double[] result = new double[number];
			double mean = 0;
			for(int i = 0; i < number; i ++){
				double sum = 0;
				double tempval = assetprice;
				for(int j = 0; j < observation; j ++){
					tempval = tempval * Math.exp((interestrate - 0.5 * volatility * volatility) * t1 + 
							volatility * Math.sqrt(t1) * r.nextGaussian());
					sum +=  tempval / observation;
				}
				result[i] = Math.exp(- interestrate * time) * Math.max(strikeprice - sum, 0);
				mean += result[i] / number;
			}
			
			double segma = standard(result, mean) / Math.sqrt(number);
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
