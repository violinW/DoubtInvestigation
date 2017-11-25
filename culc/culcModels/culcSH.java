package culcModels;

public class culcSH {
	public static int balanceDayAlart = 1;
	public static double subRate = 0.03;
	public static double rate1 = 0.0003;
	public static double rate2 = 0.0003;
	public static double secondRate = 0.12;
	public static double deductRate = 0.15;
	public static double consumption = 1000;
	public static double balanceRate = 0.15;
	public static int initDays = 90;
	
	public static void main(String[] args) {
		//商户
		double upRate = 1 + balanceRate;
		
		dtSHType rs = culcSH(initDays, consumption*upRate, 0, 0);

		System.out.println("ownCloudBill: " + rs.ownCloudBill + ", cost: " + rs.cost);
		//配送方
//
//		dtPSType rs1 = culcPS(initDays, 0, 0, 0);
//
//		System.out.println("ownCloudBill: " + rs1.ownCloudBill + ", makeMoneyNormal: " + rs1.makeMoneyNormal + ", makeMoneyByYFT: " + rs1.makeMoneyByYFT);
	}

	
	public static dtSHType culcSH(int days, double dailyConsumption, double  remainCloudBill, double cost) {
		dtSHType dt = new dtSHType();
		
		dt.cost = cost + dailyConsumption - remainCloudBill*rate2*(1-secondRate);
		dt.ownCloudBill = remainCloudBill + dailyConsumption - remainCloudBill*rate2;

		if(balanceDayAlart > 0 & (dt.ownCloudBill*rate2 *(1-secondRate) >= consumption*balanceRate)) {
			System.out.println("balanceDay: " + (initDays - days) + ", balanceBill: " + dt.ownCloudBill);
			balanceDayAlart--;
		}
		days--;
		if(days > 0) {
			return culcSH(days, dailyConsumption, dt.ownCloudBill, dt.cost);
		}else {
			return dt;
		}
	}

	
	public static dtPSType culcPS(int days, double  remainCloudBill, double makeMoneyNormal, double makeMoneyByYFT) {
		dtPSType dt = new dtPSType();
		
		dt.makeMoneyNormal = makeMoneyNormal + consumption;
		dt.makeMoneyByYFT = makeMoneyByYFT + consumption*(1 + balanceRate)*(1 - deductRate) + remainCloudBill*rate2*(1-secondRate);
		dt.ownCloudBill = remainCloudBill + consumption*(1 + balanceRate)*deductRate + consumption*(1 + balanceRate)*subRate - remainCloudBill*rate2;

		days--;
		if(days > 0) {
			return culcPS(days, dt.ownCloudBill, dt.makeMoneyNormal, dt.makeMoneyByYFT);
		}else {
			return dt;
		}
	}
	

	public static class dtSHType{
		double cost;
		double ownCloudBill;
	}
	
	public static class dtPSType{
		double makeMoneyNormal;
		double makeMoneyByYFT;
		double ownCloudBill;
	}
}
