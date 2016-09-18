package TravelFxConvert.TravelFxConvertRestful;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import TravelFxConvert.Daemon.FxRateUpdaterThread;
import TravelFxConvert.Model.FXRateContainer;
import TravelFxConvert.TravelFxConvertRestful.hello.Greeting;

@RestController
public class FxRestfulController {
	private static final Logger log = LogManager.getLogger(FxRateUpdaterThread.class);
	//private static final Logger log= Logger.getLogger(FxRestfulController.class);
	@RequestMapping("/greeting2")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(100,
                            "TEST");
    }
	
	@RequestMapping("/allfx")
    public Map<String,Double>  allFxRates(@RequestParam(value="ccy", defaultValue="JPY") String ccy) {
		Map<String,Double> result =new HashMap();
		log.info("AllFxRate called");
		ConcurrentHashMap<String, Double>  fxContainer=FXRateContainer.getExtFxRresher();
		
		fxContainer.forEach( (key,value) -> result.put(key, value) );
		
        return result;
    }
}
