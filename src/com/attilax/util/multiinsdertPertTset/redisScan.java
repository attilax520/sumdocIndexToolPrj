package com.attilax.util.multiinsdertPertTset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.commons.lang3.RandomStringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.attilax.db.ResultSetUtil;
import com.cnhis.cloudhealth.commons.util.oplogutil.AsynUtil;
import com.google.common.collect.Lists;

import redis.clients.jedis.Jedis;

public class redisScan {
	private static final int pool_count = 20;
	public static List conn_li = Lists.newArrayList();;

	public synchronized static void ini_pool_lev1(int lev_pool_count) {

		for (int i = 0; i < pool_count; i++) {
			FutureTask<Object> FutureTask_updateCviBdj = new FutureTask(new Callable<Object>() {

				@Override
				public Object call() {

					return null;
				}
			});
			pool_ExecutorService_hiprid_lev1.submit(FutureTask_updateCviBdj, "threadName");
		}

	}

	public static void main(String[] args)
			throws ClassNotFoundException, SQLException, InterruptedException, ExecutionException {
		Jedis jedis = new Jedis("127.0.0.1");
		jedis.auth("cnhis");// password
		jedis.select(6);
		long l1 = System.currentTimeMillis();

		Set<String> keys = jedis.keys("dpt1:*"); // from dpt1
		List  rs=Lists.newArrayList();
		for (String k : keys) {
		//	System.out.println(jedis.get(k));
			String e = jedis.get(k);
		JSONArray ja=	JSON.parseArray(e);
			rs.addAll(ja);
		}

		long l2 = System.currentTimeMillis();
		System.out.println(l2 - l1 + "ms");

	}

	static ExecutorService pool_ExecutorService_hiprid_lev1 = Executors.newFixedThreadPool(pool_count);

	private static void multi(Connection connection, Connection connection2, Connection connection3)
			throws SQLException, InterruptedException, ExecutionException {
		for (int i = 0; i < 1000; i++) {

			String pk_s = RandomStringUtils.randomNumeric(9);
			String sql11 = "INSERT INTO VAF1(VAF01,vaa01,bda01)values(@pk@,1,1)"; // vaf01
																					// //
																					// pk
			sql11 = sql11.replaceAll("@pk@", pk_s);
			String sql_final = sql11;

			String sdql2 = " insert into veh1 (vaf01)values(@pk@)";
			sdql2 = sdql2.replaceAll("@pk@", pk_s);
			String sql2_final = sdql2;

			String sql3 = "  insert into vfa1 (vaf01)values(@pk@) ";
			sql3 = sql3.replaceAll("@pk@", pk_s);

			String Sql_join = sql11 + ";" + sdql2 + ";" + sql3;

			FutureTask ft1 = new FutureTask<>(new Callable() {

				@Override
				public Object call() throws Exception {
					Statement stt = null;
					try {
						// Connection connection2=conn_li.get(index)
						stt = connection2.createStatement();
						stt.executeUpdate(sql_final);
						stt.close();
						// connection2.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return stt;
				}
			});
			pool_ExecutorService_hiprid_lev1.submit(ft1);

			FutureTask ft2 = new FutureTask<>(new Callable() {

				@Override
				public Object call() throws Exception {

					Statement stt = null;
					try {
						Statement stt2 = connection3.createStatement();
						stt2.executeUpdate(sql2_final);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return stt;
				}
			});
			pool_ExecutorService_hiprid_lev1.submit(ft2);

			Statement stt3 = connection.createStatement();
			stt3.executeUpdate(sql3);
			ft1.get();
			ft2.get();
		}
	}

	private static void splt(Connection connection) throws SQLException {
		for (int i = 0; i < 1000; i++) {

			String pk_s = RandomStringUtils.randomNumeric(9);
			String sql11 = "INSERT INTO VAF1(VAF01,vaa01,bda01)values(@pk@,1,1)"; // vaf01
																					// pk
			sql11 = sql11.replaceAll("@pk@", pk_s);
			String sdql2 = " insert into veh1 (vaf01)values(@pk@)";
			sdql2 = sdql2.replaceAll("@pk@", pk_s);

			String sql3 = "  insert into vfa1 (vaf01)values(@pk@) ";
			sql3 = sql3.replaceAll("@pk@", pk_s);

			String Sql_join = sql11 + ";" + sdql2 + ";" + sql3;

			Statement stt = connection.createStatement();
			stt.executeUpdate(sql11);
			Statement stt2 = connection.createStatement();
			stt2.executeUpdate(sdql2);
			Statement stt3 = connection.createStatement();
			stt3.executeUpdate(sql3);
		}
	}

	private static void join(Connection connection) throws SQLException {
		for (int i = 0; i < 1000; i++) {

			String pk_s = RandomStringUtils.randomNumeric(9);
			String sql11 = "INSERT INTO VAF1(VAF01,vaa01,bda01)values(@pk@,1,1)"; // vaf01
																					// pk
			sql11 = sql11.replaceAll("@pk@", pk_s);
			String sdql2 = " insert into veh1 (vaf01)values(@pk@)";
			sdql2 = sdql2.replaceAll("@pk@", pk_s);

			String sql3 = "  insert into vfa1 (vaf01)values(@pk@) ";
			sql3 = sql3.replaceAll("@pk@", pk_s);

			String Sql_join = sql11 + ";" + sdql2 + ";" + sql3;

			Statement stt = connection.createStatement();
			stt.executeUpdate(Sql_join);
		}
	}

}
