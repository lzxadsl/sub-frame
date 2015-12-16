package com.frame.ehcache;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * ehcache测试
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-16 上午11:47:29
 */
public class EhcacheDemo {
	
	//默认缓存名称
	private final String DEFAULT_CACHE_NAME = "defaultCache";
	//缓存管理器
	private CacheManager manager = null;
	
	public EhcacheDemo(){
		this(null);
	}
	
	/**
	 * 设置缓存配置文件路径
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-16 下午2:24:47
	 * @param path
	 */
	public EhcacheDemo(String path){
		initCacheManager(path);
	}

	/**
	 * 根据名称获取配置文件中的缓存
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-16 上午11:52:51
	 * @param name 缓存名称
	 * @return Cache
	 */
	public Cache getCache(String name){
		Cache cache = manager.getCache(name);
		return cache;
	}
	
	/**
	 * 获取默认换成
	 * 名称为defaultCache的缓存
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-16 上午11:52:51
	 * @return Cache
	 */
	public Cache getDefaultCache(){
		return getCache(DEFAULT_CACHE_NAME);
	}
	
	/**
	 * 添加缓存,使用的是默认缓存
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-16 下午1:11:57
	 * @param key
	 * @param value
	 */
	public void addItem(String key,Object value){
		Cache cache = getDefaultCache();
		Element element = new Element(key, value);
		cache.put(element);
	}
	
	/**
	 * 添加缓存,使用的是默认缓存
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-16 下午1:11:57
	 * @param cacheName xml配置中的缓存名称
	 * @param key
	 * @param value
	 */
	public void addItem(String cacheName,String key,Object value){
		Cache cache = getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}
	
	/**
	 * 根据key获取缓存,使用的是默认缓存
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-16 下午1:11:57
	 * @param key
	 */
	public Object getItem(String key){
		Cache cache = getDefaultCache();
		Element element = cache.get(key);
		Object obj = element != null ? element.getObjectValue() : null;
		return obj;
	}
	
	/**
	 * 在指定缓存中，根据key获取缓存
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-16 下午1:11:57
	 * @param key
	 * @param cacheName 指定缓存名称
	 */
	public Object getItem(String cacheName,String key){
		Cache cache = getDefaultCache();
		Element element = cache.get(key);
		Object obj = element != null ? element.getObjectValue() : null;
		return obj;
	}
	
	/**
	 * 移除,使用的是默认缓存
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-16 下午2:30:17
	 * @param key
	 */
	public boolean removeItem(String key){
		Cache cache = getDefaultCache();
		return cache.remove(key);
	}
	
	/**
	 * 从制定缓存中移除
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-16 下午2:30:17
	 * @param cacheName 指定缓存名称
	 * @param key
	 */
	public boolean removeItem(String cacheName,String key){
		Cache cache = getDefaultCache();
		return cache.remove(key);
	}
	
	/**
	 * 关闭缓存管理器
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-16 下午2:55:29
	 */
	public void shutDown(){
		manager.shutdown();
	}
	
	/**
	 * 初始化管理器
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-16 下午3:05:53
	 * @param path 配置文件路径
	 */
	private void initCacheManager(String path){
		if(path == null || "".equals(path)){
			//默认加载src目录下的ehcache.xml
			manager = CacheManager.create();
		}else{
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			//相对于src目录
			URL url = loader.getResource(path);
			manager = CacheManager.create(url);
		}
	}
	
	public static void main(String[] args) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("lzx");
		list.add("lsl");
		EhcacheDemo demo = new EhcacheDemo();
		demo.addItem("list",list);
		System.out.println("直接获取："+demo.getItem("list"));
		
		Thread.sleep(5000);
		System.out.println("第一次获取："+demo.getItem("list"));
		Thread.sleep(9000);
		System.out.println("第二次获取："+demo.getItem("list"));
		Thread.sleep(16000);
		System.out.println("第三次获取："+demo.getItem("list"));
		demo.shutDown();
		EhcacheDemo demo1 = new EhcacheDemo();
		demo1.addItem("name", "lzx");
		System.out.println(demo1.getItem("name"));
		demo1.shutDown();
	}
}
