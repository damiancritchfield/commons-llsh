package org.lscode.commons.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtil {
	
	/**
	 * 对比所有数字是否互相相等
	 * @param number
	 * @return
	 */
	public static boolean equal(Number ...number) {
		for (Number num : number) {
			if(!equalNumberArray(number, num)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 对比Number是否与数组中的每一个数字相等
	 * @param numbers
	 * @param number
	 * @return
	 */
	public static boolean equalNumberArray(Number[] numbers, Number number) {
		for (Number num : numbers) {
			if(num == null) {
				if(number != null) {
					return false;
				}
			} else {
				if(number == null) {
					return false;
				}
				if(num.doubleValue() != number.doubleValue()) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 判断数字是否是空或者0
	 * @param num
	 * @return
	 */
	public static boolean nullOrZero(Number number) {
		if(number == null || number.doubleValue()==0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 格式化数字
	 * @param number
	 * @param pattern #.##
	 * @return
	 */
	public static String format(Number number, String pattern) {
		if(number == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(number);
	}
	
	/**
	 * 小数位四舍五入法保留指定位数
	 * @param number
	 * @param decimal
	 * @return
	 */
	public static Double decimalRound(Double number, Integer decimal) {
		return new BigDecimal(number).setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 小数位四舍五入法保留指定位数
	 * @param number
	 * @param decimal
	 * @return
	 */
	public static Float decimalRound(Float number, Integer decimal) {
		return new BigDecimal(number).setScale(decimal, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	/**
	 * 小数位四舍五入法保留指定位数
	 * @param number
	 * @param decimal
	 * @return
	 */
	public static BigDecimal decimalRound(BigDecimal number, Integer decimal) {
		return number.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	}
}
