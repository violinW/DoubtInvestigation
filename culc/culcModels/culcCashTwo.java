package culcModels;

public class culcCashTwo {
	public static double initPut = 15000;
	public static double initCloudBill = 573621;
	public static int initDays = 365;
	public static double rate = 0.15;
	public static double cushRate = 0.1;
	public static double cushDays = 30;
	
	public static void main(String[] Args) {
		dtType data = culcProfite(initDays, initCloudBill, 0, 0);
		System.out.println("remainCloudBill: " + data.remainCloudBill + ", madeMoney: " + data.madeMoney + ", cash: " + data.cash);
	}
	
	public static dtType culcProfite(int days, double remainCloudBill, double madeMoney, double cash) {
		dtType dt = new dtType();
		dt.remainCloudBill = remainCloudBill*(1-0.0005);
		dt.madeMoney = madeMoney+ remainCloudBill * 0.0005 * 0.88;
		dt.cash = cash;
		days--;
		double d = (float)(initDays - days)/cushDays;
		if((int)d == d & d > 0) {
			dt.madeMoney = 0;
			dt.cash = cash + madeMoney*cushRate;
			dt.remainCloudBill = remainCloudBill + madeMoney*(1 - cushRate)/rate + madeMoney*cushRate;
			System.out.println("remainCloudBill month " + (int)d + ": " + dt.remainCloudBill + ", cash: " + dt.cash);
		}
		if(days > 0) {
			return culcProfite(days, dt.remainCloudBill, dt.madeMoney,  dt.cash);
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
