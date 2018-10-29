package com.wecash.nevermore.common;

/**
 * Created by guankai.wang on 2016/11/29.
 */
public class ComputeDistanceByLongitudeAndLatitude {
    private static double EARTH_RADIUS = 6377.830;//地球半径(千米)

    /// <summary>
    /// 角度换算成弧度
    /// </summary>
    /// <param name="d">角度</param>
    /// <returns>弧度</returns>
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    /// <summary>
    /// 计算距离
    /// </summary>
    /// <param name="lat1">纬度1</param>
    /// <param name="lng1">经度1</param>
    /// <param name="lat2">纬度2</param>
    /// <param name="lng2">经度2</param>
    /// <returns>距离（千米）</returns>
    public static double getDistance(double lat1, double lng1, double lat2, double lng2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double radLng1 = rad(lng1);
        double radLng2 = rad(lng2);
        double s = Math.acos(Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radLng1 - radLng2) + Math.sin(radLat1) * Math.sin(radLat2));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }


}
