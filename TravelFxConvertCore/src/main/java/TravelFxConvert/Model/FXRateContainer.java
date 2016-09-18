package TravelFxConvert.Model;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import TravelFxConvert.Controller.ExternalFxRefresherFactory;
import TravelFxConvert.Controller.FxRateRefresherInterface;

public class FXRateContainer {
	static volatile ConcurrentHashMap<String, Double> fxMap=null;
	
	
	public static  ConcurrentHashMap<String, Double> getExtFxRresher(){
		if(fxMap==null){
			synchronized(ExternalFxRefresherFactory.class){
				if(fxMap==null){
					fxMap= new ConcurrentHashMap<String, Double>();
				}
			}
		}
		return fxMap;
	}
}
