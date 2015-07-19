package WordConversion;

public class Branch<T> {

	public Branch(T data, Branch<T> parent) {
		super();
		this.data = data;
		this.parent = parent;
	}

	private T data;
	private Branch<T> parent;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Branch<T> getParent() {
		return parent;
	}

	public void setParent(Branch<T> parent) {
		this.parent = parent;
	}
}
