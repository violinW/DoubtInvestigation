package culcModels;


//聚汇天下
public class JHTX {	
	public static double initConsume = 10000;//初始消费
	public static double singleDayIncreaseTimes = 0.04;//每天增长资金倍数
	public static int initDays = 400;
	public static double FLRate = 0.015;//返利利率
	
	public static void main(String[] args) {
		dtType rs = culcLBJ(1, initConsume, 0, initConsume*2);
		System.out.println("ZJ: " + rs.ZJ + ", totalBill: " + rs.totalBill + ", dailyFL: " + rs.dailyFL);
	}
	
	public static dtType culcLBJ(int days, double dailyConsume, double totalBill, double ZJ) {
		if(initDays-days>0){
			double ZJNext = ZJ + dailyConsume - totalBill*FLRate;
			double billNext = totalBill + dailyConsume*(2+0.1+0.1+0.06);
//			double d = (float)days/30;
//			if((int)d == d & d > 0) {
			System.out.println("round " + days + ", dailyConsume: " + dailyConsume + ", totalBill: " + totalBill + ", ZJ: " + ZJ);
//			}
			days++;
			return culcLBJ(days, dailyConsume*(1+singleDayIncreaseTimes), billNext, ZJNext);
		}else {
			dtType dt = new dtType();
			dt.ZJ = ZJ;
			dt.totalBill = totalBill;
			dt.dailyFL = dailyConsume*(2+0.1+0.1+0.06);
			return dt;
		}
	}
	
	public static class dtType{
		double totalBill;   //平台总共发行电子货币
		double ZJ;   //资金池资金量
		double dailyFL;   //每日返利总额
	}
}
