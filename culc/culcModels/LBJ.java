package culcModels;
//洛贝集
public class LBJ {
	public static double CB = 10000;//钓鱼成本
	public static double Target = 10000*100000;//钓鱼目标
	public static double singleDayIncreaseTimes = 5;//每天增长资金倍数
	public static double goodsNumber = 100;//第一天销售商品数量
	public static double goodsDJ = 2980;//商品单价
	public static double CJ = 80;//退款差价
	public static double FLSingle = 15;//单件商品返利
	public static int initDays = 90;
	public static int TKDays = 2;//退款日期
	
	public static void main(String[] args) {
		dtType rs = culcLBJ(1, CB, 0, 0,goodsNumber);
		System.out.println("ZJ: " + rs.ZJ + ", TK: " + rs.TK + ", FLDaily: " + rs.FLDaily);
	}
	
	public static dtType culcLBJ(int days, double ZJ, double FLDaily, double TK, double XL) {
		if(ZJ<Target&&(initDays-days)>0){
			double ZJNext = ZJ + goodsNumber*goodsDJ*days*singleDayIncreaseTimes;
			if(days>TKDays) {
				TK = goodsNumber*(days-TKDays)*singleDayIncreaseTimes*(goodsDJ-CJ);//今天需要退款的数额
				ZJNext = ZJNext - TK;
			}
			System.out.println("round " + days + ": " + ZJ + ", TK: " + TK + ", FLDaily: " + FLDaily);
			FLDaily = XL*FLSingle;
			XL = XL + goodsNumber*days*singleDayIncreaseTimes;
			ZJNext = ZJNext - FLDaily;
			days++;
			return culcLBJ(days, ZJNext, FLDaily, TK, XL);
		}else {
			dtType dt = new dtType();
			dt.ZJ = ZJ;
			dt.TK = TK;
			dt.FLDaily = FLDaily;
			return dt;
		}
	}

	public static class dtType{
		double ZJ;//资金;
		double TK;//需要退款的金额
		double FLDaily;//每天需要返利的数量
	}
}
