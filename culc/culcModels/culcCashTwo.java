package culcModels;

//使用云币增值
public class culcCashTwo {
	public static double initPut = 10000;
	public static double initCloudBill = 67626;
	public static int initDays = 365*5;
	public static double initMadeMoney = 0;
	public static double rate = 0.15;
	public static double cushRate = 0;
	public static double cushDays = 30;
	
	public static int i = 1;
	
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
			dt.remainCloudBill = dt.remainCloudBill + Math.floor(dt.remainCloudBill/100)*100*0.01 - dt.madeMoney/0.88 + canCush*(1-cushRate)/rate;
			dt.madeMoney = dt.madeMoney - canCush;
			System.out.println("remainCloudBill round " + (int)d + ": " + dt.remainCloudBill + ", canCush: " + canCush + ", cush: " + dt.cash + ", madeBill: "+ remainCloudBill*0.01);
			if(dt.cash >= initPut  && i > 0) {
				System.out.println("回本：" + dt.cash);
				i--;
			}
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
