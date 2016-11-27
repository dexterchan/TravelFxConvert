package TravelFxConvert.Model;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class FxDAG extends SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>{
	private final double threshold=0.00000000000001;
	public FxDAG(){
		super(DefaultWeightedEdge.class);
	}
	
	public double setFxQuote(String ccy1, String ccy2, double rate){
		DefaultWeightedEdge  ed=null;
		if(rate<threshold){
			rate=threshold;
		}
		double  lr = Math.log(rate);
		this.addVertex(ccy1);
		this.addVertex(ccy2);
		ed=this.getEdge(ccy1, ccy2);
		if(ed==null){
			ed=this.addEdge(ccy1, ccy2);
		}
		this.setEdgeWeight(ed, lr);
		
		ed=this.getEdge(ccy2, ccy1);
		if(ed==null){
			ed=this.addEdge(ccy2, ccy1);
		}
		this.setEdgeWeight(ed,lr*-1.0);
		return rate;
	}
	
	public boolean updateFxRate(FXQuote fxquote){
		boolean ok=false;
		
		//this.addVertex(fxquote.baseCcy);
		fxquote.quote.forEach((k,v) -> 
		{
			if(k.length()>3){
				k = k.substring(3, k.length());
			}
			this.setFxQuote(fxquote.baseCcy, k,v);
		}		
		);
		
		return ok;
	}
}
