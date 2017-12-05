package culcModels;

import culcModels.culcCash1.dtType;

public class ZGRSThreeYear {
	public static int TotalYear = 3;  //年限
	public static double BE = (TotalYear==3 ? 2330 : 3220); //保额
	public static double BF = 100000;  //保费
	public static int initMonths = 12*21;  //天数
	

	public static double TZRate = (double)0.047;  //年通货膨胀率
	
	
	public static void main(String[] Args) {
		dtType data = culcPurchase(initMonths, 0, 0, BE, BF, TZRate);
		System.out.println("总资产：" + data.ZZC + "成本： " + data.CB);
	}
	
	public static dtType culcPurchase(int months, double CB, double ZZC, double BE, double BF, double TZRate) {
		dtType dt = new dtType();
		dt.CB = CB;
		dt.ZZC = ZZC;
//	    //每月变动值计算
//		double month = (double)(initDays - days)/30;  //假设都是30天一个月
//		if((int)month == month & month > 0 & month < 4) {
//			//dt.CB += BF;
//		}
	
        //三年成本计算	
		double year = (double)(initMonths - months)/12;
		if((int)year == year & year > 0) {
			int currYear = (int)year;
			double currRate = Math.pow(1-TZRate, currYear);
			System.out.println("currRate：" + currRate);
			switch(currYear) {
				case 1: dt.CB += BF; dt.ZZC -=BF; dt.ZZC +=1500;break;
				case 2: dt.CB += BF*(1-TZRate); dt.ZZC -=(BF-3376)*(1-TZRate);break;
				case 3: dt.CB += BF*(1-TZRate); dt.ZZC -=(BF-5420)*(1-TZRate);break;
				case 4: dt.ZZC +=5420*(1-TZRate);break;
				case 5: dt.ZZC += ((BF*(currYear==3 ? 0.48 : 0.6)+5556)*currRate);break;
				case 6: dt.ZZC += ((BF*(currYear==3 ? 0.12 : 0.4)+5694)*currRate);break;
				case 7:case 8:case 9:case 10:case 11:case 12:case 13:case 14:case 15:case 16:case 17:case 18:case 19: dt.ZZC += (BE+5200)*currRate;break;
				case 20: dt.ZZC += (TotalYear*BF+5853)*currRate;break;
	            default:
	                System.out.println("打印默认值");
	                break;
			}
			System.out.println("年：" + currYear + "， 资产：" + Math.round(dt.ZZC) );
			
		}
		months--;
		double CB_my = Math.round(dt.CB);
		double ZZC_my = Math.round(dt.ZZC);
		if(months > 0) {
			return culcPurchase(months, CB_my, ZZC_my, BE, BF, TZRate);
		}else {
			return dt;
		}
	}

	public static class dtType{
		double ZZC; //总资产
		double CB;  //成本
		double GDAcount;   //固定账户
		double XAccount;    //尊享账户
	}
}
