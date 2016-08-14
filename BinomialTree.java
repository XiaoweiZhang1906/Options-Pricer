package assignment3;

public class BinomialTree {
	private double assetprice;
	private double volatility;
	private double interestrate;
	private double time;
	private double strikeprice;
	private int steps;
	private double[][] store;
	
	private double t1;
	private double u;
	private double d;
	private double p;
	
	public BinomialTree(double assetprice, double volatility, double interestrate, double time, double strikeprice, int steps) {
		this.assetprice = assetprice;
		this.volatility = volatility;
		this.interestrate = interestrate;
		this.time = time;
		this.strikeprice = strikeprice;
		this.steps = steps;
		store = new double[steps + 1][steps + 1];
		t1 = time / steps;
		u = Math.exp(volatility * Math.sqrt(t1));
		d = 1 / u;
		p = (Math.exp(interestrate * t1) - d) / (u - d);
		store[0][0] = assetprice;
		for(int i = 1; i <= steps; i ++){
			for(int j = 0; j < i; j ++){
				store[i][j] = store[i - 1][j] * u;
			}
			store[i][i] = store[i - 1][i - 1] * d;
		}
	}
	
	
	public double americanCall() {
		double[] calculated = new double[steps + 1];
		for(int i = 0; i <= steps; i ++){
			calculated[i] = Math.max(store[steps][i] - strikeprice, 0);
		}
		
		for(int i = steps; i > 0; i --){
			double[] temp = new double[steps + 1];
			temp = calculated.clone();
			
			for(int j = 0; j < i; j ++){
				calculated[j] = Math.max(Math.exp(- interestrate * t1) * (p * temp[j] + (1-p) * temp[j + 1]), 
						Math.max(store[i - 1][j] - strikeprice, 0));
			}
		}
		Blackscholes call = new Blackscholes(assetprice, volatility, interestrate, time, strikeprice);
		return calculated[0] + call.calloption() - europeanCall();
	}
	
	public double europeanCall(){
		double[] calculated = new double[steps + 1];
		for(int i = 0; i <= steps; i ++){
			calculated[i] = Math.max(store[steps][i] - strikeprice, 0);
		}
		
		
		for(int i = steps; i > 0; i --){
			double[] temp = new double[steps + 1];
			temp = calculated.clone();
			
			for(int j = 0; j < i; j ++){
				calculated[j] = Math.exp(- interestrate * t1) * (p * temp[j] + (1-p) * temp[j + 1]);
			}
		}
		return calculated[0];
	}
	
	public double ameticanPut() {
		double[] calculated = new double[steps + 1];
		for(int i = 0; i <= steps; i ++){
			calculated[i] = Math.max(strikeprice - store[steps][i], 0);
		}
		
		for(int i = steps; i > 0; i --){
			double[] temp = new double[steps + 1];
			temp = calculated.clone();
			for(int j = 0; j < i; j ++){
				calculated[j] = Math.max(Math.exp(- interestrate * t1) * (p * temp[j] + (1-p) * temp[j + 1]), 
						Math.max(strikeprice - store[i - 1][j], 0));
			}
		}
		Blackscholes put = new Blackscholes(assetprice, volatility, interestrate, time, strikeprice);
		return calculated[0] + put.putoption() - europeanPut();
	}
	
	public double europeanPut(){
		double[] calculated = new double[steps + 1];
		for(int i = 0; i <= steps; i ++){
			calculated[i] = Math.max(strikeprice - store[steps][i], 0);
		}
		
		for(int i = steps; i > 0; i --){
			double[] temp = new double[steps + 1];
			temp = calculated.clone();
			
			for(int j = 0; j < i; j ++){
				calculated[j] = Math.exp(- interestrate * t1) * (p * temp[j] + (1-p) * temp[j + 1]);
			}
		}
		
		return calculated[0];
	}
}
