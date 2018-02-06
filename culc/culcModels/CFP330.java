package culcModels;

public class CFP330 {
	public static int startNumber = 500;
	public static double increaseRate = 0.02;
	public static double startMoney = 330;
	public static double FLRate = 0.02;//返利利率
	public static int initDays = 360; //天数
	public static double [] TXL = new double[] {0.0003,0.0003,0.0006,0.0009,0.002,0.006,0.01,0.012,0.014,0.016,0.02,0.03};
	
	public static void main(String[] args) {
		dtType rs = culcCFP(1, startNumber, startNumber*startMoney);
		System.out.println("ZJ: " + rs.ZJ);
	}
	
	public static dtType culcCFP(int days,int number, double ZJ) {
		if(initDays-days>0){
			System.out.println("day: " + days + ", ZJ: " + ZJ);
			int nextNumber = (int)(number*(increaseRate+1));
			double nextZJ = nextZJ(ZJ,number, days);
			days++;
			return culcCFP(days, nextNumber, nextZJ);
		}else {
			dtType dt = new dtType();
			dt.ZJ = ZJ;
			return dt;
		}
	}
	public static double nextZJ(double ZJ, int number, int day) {
		double nextZJ = ZJ +Math.floor(number*(increaseRate+1))*startMoney;
		for(int i=1; i<=day;i++) {
			int indexNumber =(int)Math.floor(i/30) ;
			nextZJ -= startNumber * Math.pow(increaseRate+1, i-1) * startMoney * TXL[indexNumber];
		}
		return nextZJ;
	}
	
	public static class dtType{
		double ZJ;   //资金池资金量
	}
}
