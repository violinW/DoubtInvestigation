package culcModels;

//使用云币增值
public class culcCashTwo {
	//public static double initPut = 15000;
	public static double initCloudBill = 101000;
	public static int initDays = 365*2;
	public static double initMadeMoney = 0;
	public static double rate = 0.15;
	public static double cushRate = 1;
	public static double cushDays = 30;
	
	public static void main(String[] Args) {
		dtType data = culcProfite(initDays, initCloudBill, initMadeMoney, 0, 0);
		System.out.println("remainCloudBill: " + data.remainCloudBill + ", madeMoney: " + data.madeMoney + ", cash: " + data.cash);
	}
	
	public static dtType culcProfite(int days, double remainCloudBill, double madeMoney, double cash, double lastMadeCush) {
		dtType dt = new dtType();
		dt.remainCloudBill = remainCloudBill;
		dt.madeMoney = madeMoney+ remainCloudBill * 0.0005 * 0.88;
		dt.cash = cash;
		days--;
		double d = (float)(initDays - days)/cushDays;
		if((int)d == d & d > 0) {
			double canCush = Math.floor(dt.madeMoney/100)*100;
			dt.cash = cash + canCush*cushRate;
			dt.remainCloudBill = dt.remainCloudBill*(1 + 0.01) - dt.madeMoney/0.88 + canCush*(1-cushRate)/rate;
			dt.madeMoney = dt.madeMoney - canCush;
			System.out.println("remainCloudBill month " + (int)d + ": " + dt.remainCloudBill + ", cash: " + dt.cash + ", madeBill: "+ remainCloudBill*0.01);
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
