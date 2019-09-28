import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenBufProcModel {
	private List<AtomicComposant> C ;
	private List<Link> links ;
	private List<List<String>>io_handler ;
	
	public GenBufProcModel() {
		this.C = new ArrayList<>() ;
		this.io_handler = new ArrayList<>() ;
		this.links = new LinkedList<>() ;
		this.links.add(new Link<Integer, Integer>(0, 2)) ;
		this.links.add(new Link<Integer, Integer>(1, 2)) ;
		this.links.add(new Link<Integer, Integer>(2, 1)) ;
		this.C.add(new Processor(new ArrayList<List<String>>(), new ArrayList<List<String>>())) ;
		this.C.add(new Generator(new ArrayList<List<String>>(), new ArrayList<List<String>>())) ;
		this.C.add(new Buffer(new ArrayList<List<String>>(), new ArrayList<List<String>>())) ;
	}
	
	public void ordonnancement() {
		int j = 0 ;
		double t = 0, tr = 0 ;
		double t_fin = 10 ;
		double tr_min = Double.POSITIVE_INFINITY ;
		List<AtomicComposant> imms = new ArrayList<>() ;
		
		while (t < t_fin) {
			for (AtomicComposant c : this.C)
				tr_min = c.tr < tr_min ? c.tr : tr_min ;
			for (AtomicComposant c : this.C)
				if(c.tr <= tr_min)
					imms.add(c) ;
			for (AtomicComposant c : imms)
				io_handler.add(c.lambda()) ;
			for (int i = 0 ; i < C.size() ; i++) {
				if (io_handler.get(i) != null)
					j = 0 ;
					for (Link<Integer, Integer> l : this.links) {
						if (l.getA() == i)
							this.C.get(l.getB()).addInput(io_handler.get(i).get(j)) ;
						j ++ ;
					}
			}
			
		}
		
		
	}
}
