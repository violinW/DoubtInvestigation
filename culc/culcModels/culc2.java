package culcModels;

import culcModels.culc1.dtType;

//在culc1的基础上增加对提前一次性成本的考虑
public class culc2 {
	public static double subRate = 0.03;
	public static double rate = 0.0005;
	public static double secondRate = 0.12;
	public static double deductRate = 0.15;
	public static int initDays = 365;
	public static double investmentOneTime = 60000;//一次性投入
	public static double investmentValidity = 365*3;//一次性投入有效期
	
	public static void main(String[] args) {
		//计算单次采购
//		double startCloudBill = 10000*(1+0.15)+300;
//		double startMoney = 0;
//
//		dtType result = culcPurchase(days, startCloudBill, startMoney);
//		System.out.println("remainCloudBill: " + result.remainCloudBill +  ", madeMoney: " + result.madeMoney);
		
		//计算用云付通经营情况
		int initDailySales = 20;//初始日销量
		double saleUpRate = 0.01;//销量增长幅度
		double saleUpNum = 10*2;//销量增长个数
		double price = 12;//单个商品售价
		double singlePurchaseRate = 0.45*(1 + deductRate);//单个商品原料成本比例(由于系统要抽成15%)
		double singlePersonalRate = 0.1;//单个商品人力及其他成本(收限量影响0.2是200销量的时候)
		double singleOtherRate = 0.15;//单个商品人力及其他成本
		double startCloudBill = 0;//初始云币数
		double initMadeMoney = 0;//初始赚钱数量
		double initSales = 0;//初始总销量为0
		dtType result = culcDailyYFT(initDays, initDailySales, saleUpNum, price, singlePurchaseRate, singleOtherRate, singlePersonalRate, startCloudBill, -investmentOneTime, initSales);
		System.out.println("remainCloudBill: " + result.remainCloudBill +  ", madeMoney: " + result.madeMoney + ", sales: " + result.sales);
		
		//计算正常销售经营情况
//		int initDailySales = 20;//初始日销量
//		double saleUpRate = 0.01;//销量增长幅度
//		double saleUpNum = 10;//销量增长个数
//		double price = 12;//单个商品售价
//		double singlePurchaseRate = 0.4;//单个商品原料成本比例
//		double singlePersonalRate = 0.1;//单个商品运送人力及其他成本(收限量影响0.2是200销量的时候)
//		double singleOtherRate = 0.15;//单个商品人力及其他成本
//		double initMadeMoney = 0;//初始赚钱数量
//		double initSales = 0;//初始总销量为0
//		dtType result = culcDailyClassic(initDays, initDailySales, saleUpNum, price, singlePurchaseRate, singleOtherRate, singlePersonalRate, -investmentOneTime, initSales);
//		System.out.println("madeMoney: " + result.madeMoney + ", sales: " + result.sales);
	}

	
	public static dtType culcDailyClassic(int days, double DailySales, double saleUpNum, double price, double singlePurchaseRate, double singleOtherRate, 
			double singlePersonalRate, double startMoney, double sales) {
		dtType dt = new dtType();
		//计算当天正常盈利(人民币)
		double profite = price*(1 - singleOtherRate - (singlePersonalRate*(200/DailySales)) - singlePurchaseRate) * DailySales - investmentOneTime/investmentValidity;
		double oneTimeInvestment = 0;
		if(oneTimeInvestment/(365*3)==(int)oneTimeInvestment/(365*3) && oneTimeInvestment/(365*3)>0) {
			oneTimeInvestment = investmentOneTime;
		}
		dt.madeMoney = startMoney + profite - oneTimeInvestment;
		dt.sales = sales + DailySales;
		days--;
		if(days > 0) {
			return culcDailyClassic(days, DailySales + (initDays - days > 90 ? 0 : saleUpNum), saleUpNum, price, singlePurchaseRate, singleOtherRate, singlePersonalRate, 
					dt.madeMoney, dt.sales);
		}else {
			return dt;
		}
	}

	
	public static dtType culcDailyYFT(int days, double DailySales, double saleUpNum, double price, double singlePurchaseRate, double singleOtherRate, double singlePersonalRate, 
			double startCloudBill, double startMoney, double sales) {
		dtType dt = new dtType();
		//计算当天采购产生的云币
		double purchaseBill = DailySales*price*singlePurchaseRate*(1 + deductRate);
		//计算云付通下游产生的云币
		double subordinateBill = DailySales*price*subRate;
		double complexOtherRate = singleOtherRate + investmentOneTime/investmentValidity;
		//计算当天正常盈利(人民币)
		double profite = price*(1 - singleOtherRate - singlePurchaseRate - (singlePersonalRate*(200/DailySales))) * DailySales - investmentOneTime/investmentValidity;
		dt.remainCloudBill = startCloudBill + purchaseBill + subordinateBill;
		double oneTimeInvestment = 0;
		if(oneTimeInvestment/(365*3)==(int)oneTimeInvestment/(365*3) && oneTimeInvestment/(365*3)>0){
			oneTimeInvestment = investmentOneTime;
		}
		dt.madeMoney = startMoney + profite + dt.remainCloudBill*rate*(1-secondRate) - DailySales*price*singlePurchaseRate*secondRate - oneTimeInvestment;
		dt.sales = sales + DailySales;
		days--;
		if(days > 0) {
			return culcDailyYFT(days, DailySales + (initDays - days > 90 ? 0 : saleUpNum), saleUpNum, price, singlePurchaseRate, singleOtherRate, singlePersonalRate, 
					dt.remainCloudBill*(1-rate), dt.madeMoney, dt.sales);
		}else {
			return dt;
		}
	}
	
	/**
	 * 计算用云付通采购的返还机制
	 * @param days 返还第几天 
	 * @param startCloudBill 当前剩余云币
	 * @param startMoney 当前收益人民币
	 * @param rate 返还率（0.0005）
	 * @param secondRate 提现收费率（0.12）
	 * @return
	 */
	public static dtType culcPurchase(int days, double startCloudBill, double startMoney) {
		dtType dt = new dtType();
		dt.remainCloudBill = startCloudBill * (1-rate);
		dt.madeMoney=startMoney + startCloudBill * rate * (1 - secondRate);
		days--;
		if(days > 0) {
			return culcPurchase(days, dt.remainCloudBill, dt.madeMoney);
		}else {
			return dt;
		}
		
	};

	public static class dtType{
		double remainCloudBill;
		double madeMoney;
		double sales;
	}
}
