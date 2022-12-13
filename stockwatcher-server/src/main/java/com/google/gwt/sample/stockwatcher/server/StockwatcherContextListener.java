package com.google.gwt.sample.stockwatcher.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebListener
public class StockwatcherContextListener implements ServletContextListener, MapDBConstants {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
			if(!new File(DB_FILENAME).exists()) {
				try {
					List<String> stocks = new ArrayList<>();
					ObjectMapper objectMapper = new ObjectMapper();
					stocks = objectMapper.readValue(new File("stock.json"), new TypeReference<List<String>>(){});
					DB db = DBMaker.fileDB(new File(DB_FILENAME)).make();
					Map<Integer, String> map = db.treeMap(SYMBOLS_TREEMAP_NAME, Serializer.INTEGER, Serializer.STRING).create();
					int cnt = 0;
					for(String stock : stocks) {
						map.put(cnt++, stock);
					}
					db.close();
				} catch (IOException e) {
					Logger.getGlobal().warning(e.toString());
				}		
			}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
