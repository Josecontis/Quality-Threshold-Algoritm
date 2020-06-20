
public class ServerException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServerException(String result)
	{
		System.err.println(result);
	}

}
