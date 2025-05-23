package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EntForm;

@Repository
public class ChatDao {
	private final JdbcTemplate db;

	public ChatDao(JdbcTemplate db) {
		this.db = db;
	}

	public void insertDb(EntForm entform) {
		db.update("INSERT INTO chat (name,comment) VALUES(?,?)", entform.getName(), entform.getComment());
	}

	public List<EntForm> searchDb() {
		String sql = "SELECT * FROM chat";
		//データベースから取り出したデータをresultDB1に入れる
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);
		
		//画面に表示しやすい形のList(resultDB2)を用意
		List<EntForm> resultDb2 = new ArrayList<EntForm>();
		
		//1件ずつピックアップ
		for (Map<String, Object> result1 : resultDb1) {
			//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
			EntForm entformdb = new EntForm();
			//id、nameのデータをentformdbに移す
			entformdb.setId((int) result1.get("id"));
			entformdb.setName((String) result1.get("name"));
			entformdb.setComment((String) result1.get("comment"));
			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			resultDb2.add(entformdb);
		}
		//Controllerに渡す
		return resultDb2;
	}

}
