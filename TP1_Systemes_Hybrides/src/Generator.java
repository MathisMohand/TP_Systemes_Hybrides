import java.util.ArrayList;
import java.util.List;

public class Generator extends AtomicComposant {

	public Generator(List inputs, List outputs) {
		super(inputs, outputs, null);
		
		List<Double> states = new ArrayList<>() ;
		states.add(2.0) ;
		
		this.states = states ;		
	}

	@Override
	public void deltaExt() {}
	
	@Override
	protected void deltaInt() {}

	@Override
	public List<String> lambda() {
		if (this.tr == 0) {
			return this.outputs ;
		}
		return null ;
			
	}

}
