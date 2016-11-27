package TravelFxConvert.TravelFxConvertCore;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import TravelFxConvert.Model.FXQuote;
import TravelFxConvert.Model.FxDAG;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() 
    {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("FxRefresherConfig.xml");
        TravelFxConvert.Controller.ApiLayerFxRateRefresher refresher = (TravelFxConvert.Controller.ApiLayerFxRateRefresher) context.getBean("ApiLayerFxRefresher");
        Set<String> ccyLst=new HashSet<String>();
        ccyLst.add("EUR");
        ccyLst.add("JPY");
        try{
        	FXQuote q=refresher.getFx(ccyLst, "USD");
        	FxDAG d = new FxDAG();
        	d.updateFxRate(q);
        	
        	
        	List path =   DijkstraShortestPath.findPathBetween(d, "EUR","JPY");
        	path.forEach( (v) -> 
        	{
        		DefaultWeightedEdge w = (DefaultWeightedEdge)v;
        		System.out.println(w + "\n");
        	}
        	);
        	
        }catch(Exception e){
        	e.printStackTrace();
        }
        assertTrue( true );
    }
}
