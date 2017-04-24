package core;

public class Tuple<T1, T2> {
	public T1 item1;
	public T2 item2;
	public Tuple(T1 item1, T2 item2) {
		this.item1 = item1;
		this.item2 = item2;
	}
	public boolean equals(Object o) {
		Tuple<T1, T2> object = (Tuple<T1, T2>)o;
		return object.item1 == this.item1 && object.item2 == this.item2;		
	}
	public int hashCode() {	
		int hash = 17;
		if (this.item1 != null) {
			hash = hash * 31 + this.item1.hashCode();
		}
		if (this.item2 != null) {
			hash = hash * 31 + this.item2.hashCode();
		}
		return hash;
	}
}
