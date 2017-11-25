package culcModels;

//不进行云币增值
public class culcCash3 {
	public static double initCloudBill = 101000;
	public static int initDays = 365;
	public static double initMadeMoney = 0;
	public static double rate = 0.15;
	public static double cushRate = 1;
	public static double cushDays = 1;
	
	public static void main(String[] Args) {
		dtType data = culcProfite(initDays, initCloudBill, initMadeMoney, 0, 0);
		System.out.println("remainCloudBill: " + data.remainCloudBill + ", madeMoney: " + data.madeMoney + ", cash: " + data.cash);
	}
	
	public static dtType culcProfite(int days, double remainCloudBill, double madeMoney, double cash, double lastMadeCush) {
		dtType dt = new dtType();
		dt.remainCloudBill = remainCloudBill*(1-0.0005);
		dt.madeMoney = madeMoney+ remainCloudBill * 0.0005 * 0.88;
		dt.cash = cash;
		days--;
		double d = (float)(initDays - days)/cushDays;
		if((int)d == d & d > 0) {
			//double canCush = Math.floor(dt.madeMoney/100)*100;
			double canCush = dt.madeMoney;
			dt.cash = cash + canCush*cushRate;
			dt.remainCloudBill = dt.remainCloudBill + canCush*(1-cushRate)/rate;
			dt.madeMoney = dt.madeMoney - canCush;
			System.out.println("remainCloudBill round " + (int)d + ": " + dt.remainCloudBill + ", cash: " + dt.cash + ", madeMoney: " + dt.madeMoney);
		}
		if(days > 0) {
			return culcProfite(days, dt.remainCloudBill, dt.madeMoney,  dt.cash, lastMadeCush);
		}else {
			return dt;
		}
	}

	public static class dtType{
		double remainCloudBill;
		double madeMoney;
		double cash;
	}
}
