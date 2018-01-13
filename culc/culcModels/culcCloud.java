package culcModels;

public class culcCloud {
	public static double consumeIncreaseRate = 0.0025;//消费日增长率
	public static double systemSJRate = 0.15;//系统向商家收取提成比例
	public static double systemTXRate = 0.12;//系统提现收取利率
	public static double exchangeRate = 0.0003;//云币兑换人民币日利率
	public static double deductRate = 0.03;//推荐人提成率
	public static double finalRate = 0.02;//被推荐人为商户提成率
	public static int startDays = 365*5;
	
	public static void main(String[] args) {
		double startMoney = 0;
		double startOwnCloudBill = 0;
		double dailyConsume = 10000;
		dtType rs = culcCloud(startDays, startMoney, startOwnCloudBill, dailyConsume);
		System.out.println("remainMoney: " + (int)rs.remainMoney + ", ownCloudBill: " + rs.ownCloudBill);
	}
	
	public static dtType culcCloud(int days, double startMoney, double startOwnCloudBill, double dailyConsume) {
		double tomorrowStartMoney = startMoney + dailyConsume * systemSJRate - startOwnCloudBill * exchangeRate * (1 - systemTXRate);
		double tomorrowOwnCloudBill = startOwnCloudBill + dailyConsume *(1 + deductRate + finalRate + systemSJRate);
		days--;
		if(days > 0) {
			return culcCloud(days, tomorrowStartMoney, tomorrowOwnCloudBill, dailyConsume*(1 + consumeIncreaseRate));
		}else {
			dtType data = new dtType();
			data.remainMoney = tomorrowStartMoney;
			data.ownCloudBill = tomorrowOwnCloudBill;
			return data;
		}
	}

	public static class dtType{
		double remainMoney;
		double ownCloudBill;
	}
}
