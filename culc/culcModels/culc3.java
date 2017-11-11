package culcModels;

public class culc3 {
	public static double rate = 0.0003;
	public static double initCloudBill = 360;
	public static int initDays = 365*3;
	
	public static void main(String[] Args) {
		double startMoney = 0;
		dtType data = culcPurchase(initDays, initCloudBill, startMoney, initCloudBill);
		System.out.println("remainCloudBill: " + data.remainCloudBill + ", totalConsunm: " + data.totalConsunm + ", madeMoney: " + data.madeMoney);
	}
	
	public static dtType culcPurchase(int days, double startCloudBill, double startMoney, double totalConsunm) {
		dtType dt = new dtType();
		dt.totalConsunm = totalConsunm;
		dt.remainCloudBill = startCloudBill*(1 - 0.0003);
		dt.madeMoney = startCloudBill*0.0003 + startMoney;
		days--;
		double d = (float)(initDays - days)/90;
		if((int)d == d & d > 0) {
			dt.totalConsunm += initCloudBill;
			dt.remainCloudBill += initCloudBill;
		}
		if(days > 0) {
			return culcPurchase(days, dt.remainCloudBill, dt.madeMoney, dt.totalConsunm);
		}else {
			return dt;
		}
	}

	public static class dtType{
		double totalConsunm;
		double remainCloudBill;
		double madeMoney;
	}

}
