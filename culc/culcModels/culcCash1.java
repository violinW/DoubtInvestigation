package culcModels;

public class culcCash1 {
	public static double initCloudBill = 115000;
	public static int initDays = 365*5;
	
	public static void main(String[] Args) {
		dtType data = culcProfite(initDays, initCloudBill, 0);
		System.out.println("remainCloudBill: " + data.remainCloudBill + ", madeMoney: " + data.madeMoney);
	}
	
	public static dtType culcProfite(int days, double remainCloudBill, double madeMoney) {
		dtType dt = new dtType();
		dt.remainCloudBill = remainCloudBill*(1-0.0005);
		dt.madeMoney = madeMoney+ remainCloudBill * 0.0005 * 0.88;
		days--;
		if(days > 0) {
			return culcProfite(days, dt.remainCloudBill, dt.madeMoney);
		}else {
			return dt;
		}
	}

	public static class dtType{
		double remainCloudBill;
		double madeMoney;
	}
}
