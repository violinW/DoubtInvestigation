package culcModels;

import culcModels.ZGRSThreeYear.dtType;

//鑫账户复利计算
public class FLJS {
	public static int XZH = 1034836;
	public static int initYear = 20;
	public static double monthRate = 0.0045;
	
	
	public static void main(String[] Args) {
		System.out.println("年利率：5.1%, 账户起始金额： "+ 15673 + ", 月复利算法结果如下：");
		dtType data = culcPurchase(initYear*12, XZH, monthRate);
	}
	
	public static dtType culcPurchase(int months,double ZZC, double monthRate) {
		dtType dt = new dtType();
		dt.ZZC = ZZC*(1 + monthRate);
		if(months%12==0) {
			System.out.println("月份：" + (initYear*12-months) + ", 资产： " + dt.ZZC);
		}
		months--;
		if(months > 0) {
			return culcPurchase(months, dt.ZZC, monthRate);
		}else {
			return dt;
		}
	}

	public static class dtType{
		double ZZC; //总资产
	}
}
