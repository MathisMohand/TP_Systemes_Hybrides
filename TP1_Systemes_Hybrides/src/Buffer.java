import java.util.ArrayList;
import java.util.List;

public class Buffer extends AtomicComposant {
	private int q ;

	public Buffer(List inputs, List outputs) {
		super(inputs, outputs, null);
		
		this.q = 0 ;
		List<Double> states = new ArrayList<>() ;
		states.add(Double.POSITIVE_INFINITY) ;
		states.add(0.0) ;
		states.add(Double.POSITIVE_INFINITY) ;
		this.states = states ;
	}

	@Override
	public void deltaExt() {
		if (this.inputs.get((int) this.global_time).equals("job")) {
			this.q ++ ;
			this.current_state = (this.current_state == 0)?1:this.current_state ; 
		}
		if (this.current_state == 2 && this.inputs.get((int) this.global_time).equals("done"))
			if (q == 0)
				this.current_state = 0 ;
			else
				this.current_state = 1 ;
	}

	@Override
	protected void deltaInt() {
		if (this.current_state == 1) {
			this.q -- ;
			this.current_state = 2 ; 
		}

	}

	@Override
	public List<String> lambda() {
		if (this.current_state == 1 && this.tr == 0) {
			return this.outputs ;
		}
		return null ;
	}

}
