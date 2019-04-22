package com.cni.safira.api.security.service;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import com.cni.safira.api.security.entity.User;
import com.cni.safira.api.security.jwt.JwtUserFactory;
import com.oracle.cmgenidvald.CmGenIdVald;
import com.oracle.ouaf.spl.xaixapp.xaiserver.cmgenidvald.CmGenIdValdPortType;
import com.oracle.ouaf.spl.xaixapp.xaiserver.cmgenidvald.CmGenIdValdService;

@Service
@Endpoint
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String cpfResponsavel) {

		User user = new User();
		try {
			//Desativa a verificação SSL com certificado autoassinado.
			disableSslVerification();

			//Instancia o serviço e a porta - gerados por JAX-WS
			CmGenIdValdService service = new CmGenIdValdService();
			CmGenIdValdPortType port = service.getCmGenIdValdPort();

			//Configura usuário / passa dados e URI Endpoint
			setupHeader(port);

			//Instancia objetos de parâmetros
			CmGenIdVald cmGenIdVald = new CmGenIdVald();
			CmGenIdVald.Request req = new CmGenIdVald.Request();

			//Preenche os dados da solicitação
			req.setIdNumber(cpfResponsavel);//"087.248.218-96");
			req.setIdType("CPF");
			
			cmGenIdVald.setRequest(req);

			Holder<CmGenIdVald> body = new Holder<>(cmGenIdVald);

			//Chama o método WS
			port.cmGenIdVald(body);

			//Retorna o response
			//System.out.println("PersonId: " + body.value.getResponse().getPersonId());
			String personId = body.value.getResponse().getPersonId();
			
			if(personId.equals("")) {
				//return "usuario nao existe";
			} else {
				//exite envio o id envio o id e recebo a senha criptografada
				//comparo com a senha de login. se for valido true 
				//senao senha incorreta 
			}
			
			user.setId(personId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return JwtUserFactory.create(user);

	}

	private static void disableSslVerification() {
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String s, SSLSession sslSession) {
				return true;
			}
		});
	}

	private static void setupHeader(CmGenIdValdPortType port) {
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				"https://144.22.109.36:6502/ouaf/XAIApp/xaiserver/CmGenIdVald");
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "psrmsysuser");
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "psrm@1cni");
	}

//	public User valorSimulado() {
//		User result = new User();
//		result.setId("1");
//		result.setCpfResponsavel("93602987191");
//		result.setPassword("$2a$10$ZBm6Kfc5VkACuD3naOYHxOAPcUi6odBdeYbwLm2XB69IoIzEO3tHC");
//		result.setProfile(ProfileEnum.ROLE_ADMIN);
//
//		return result;
//	}

	/*
	 * public static void main(String[] args) throws MalformedURLException {
	 * 
	 * URL url = new
	 * URL("https://144.22.109.36:6502/ouaf/XAIApp/xaiserver/CmGenIdVald?WSDL");
	 * QName serviceName = new
	 * QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CmGenIdVald",
	 * "CmGenIdValdService"); QName portName = new
	 * QName("http://ouaf.oracle.com/spl/XAIXapp/xaiserver/CmGenIdVald",
	 * "CmGenIdValdPort");
	 * 
	 * javax.xml.ws.Service service = javax.xml.ws.Service.create(url, serviceName);
	 * Dispatch<SOAPMessage> dispatch = service.createDispatch(portName,
	 * SOAPMessage.class, javax.xml.ws.Service.Mode.MESSAGE);
	 * 
	 * 
	 * Map<String, Object> req_ctx =((BindingProvider)portName).getRequestContext();
	 * req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
	 * 
	 * Map<String, List<String>> headers = new HashMap<String, List<String>>();
	 * headers.put("psrmsysuser",Collections.singletonList("psrmsysuser"));
	 * headers.put("psrm@1cni", Collections.singletonList("psrm@1cni"));
	 * 
	 * req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers); }
	 */

	/**
	 * public static void http_client() throws Exception {
	 * System.out.println("Invoke service using direct HTTP call with Basic Auth");
	 * 
	 * httpPost("https://" + "144.22.109.36:6502/ouaf/" + "?invoke=",
	 * "https://144.22.109.36:6502/ouaf/XAIApp/xaiserver/CmGenIdVald",
	 * "psrmsysuser:psrm@1cni"); }
	 * 
	 * private static String httpPost(String destUrl, String postData, String
	 * authStr) throws Exception {
	 * 
	 * URL url = new URL(destUrl); HttpURLConnection conn =
	 * (HttpURLConnection)url.openConnection();
	 * 
	 * if (conn == null) { return null; }
	 * 
	 * conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
	 * conn.setDoOutput(true); conn.setDoInput(true); conn.setUseCaches(false);
	 * conn.setFollowRedirects(true); conn.setAllowUserInteraction(false);
	 * conn.setRequestMethod("GET");
	 * 
	 * byte[] authBytes = authStr.getBytes("UTF-8"); String auth =
	 * com.sun.org.apache.xml.internal.security.utils.Base64.encode(authBytes);
	 * conn.setRequestProperty("Authorization", "Basic " + auth);
	 * 
	 * System.out.println("post data size:" + postData.length());
	 * 
	 * OutputStream out = conn.getOutputStream(); OutputStreamWriter writer = new
	 * OutputStreamWriter(out, "UTF-8"); writer.write(postData); writer.close();
	 * out.close();
	 * 
	 * System.out.println("connection status: " + conn.getResponseCode() + ";
	 * connection response: " + conn.getResponseMessage());
	 * 
	 * InputStream in = conn.getInputStream(); InputStreamReader iReader = new
	 * InputStreamReader(in); BufferedReader bReader = new BufferedReader(iReader);
	 * 
	 * String line; String response = "";
	 * System.out.println("==================Service response: ================ ");
	 * 
	 * while ((line = bReader.readLine()) != null) { System.out.println(line);
	 * response += line; }
	 * 
	 * iReader.close(); bReader.close(); in.close(); conn.disconnect();
	 * 
	 * 
	 * return response; }
	 */

	/**
	 * public static void main(String[] args) throws Exception {
	 * 
	 * CmGenIdValdService cmGenIdValdService = new CmGenIdValdService();
	 * CmGenIdValdPortType cmGenIdVald = cmGenIdValdService.getCmGenIdValdPort();
	 * 
	 * 
	 * Holder<CmGenIdVald> req = new Holder<CmGenIdVald>();
	 * req.value.getRequest().setIdType("CPF");
	 * req.value.getRequest().setIdNumber("00099988877");
	 * 
	 * cmGenIdVald.cmGenIdVald(req); // }
	 */

	/**
	 * public static void main(String[] args) { System.out.println("chegamos aqui");
	 * //System.out.println(cmGenIdValdService.getWSDLDocumentLocation());
	 * CmGenIdValdService cmGenIdValdService = new CmGenIdValdService();
	 * CmGenIdValdPortType cmGenIdVald = cmGenIdValdService.getCmGenIdValdPort();
	 * 
	 * WSBindingProvider wsbp = (WSBindingProvider)cmGenIdValdService; Map<String,
	 * Object> requestContext = wsbp.getRequestContext();
	 * 
	 * requestContext.put(WSBindingProvider.USERNAME_PROPERTY, "psrmsysuser");
	 * 
	 * requestContext.put(WSBindingProvider.PASSWORD_PROPERTY, "psrm@1cni");
	 * 
	 * }
	 */

	/**
	 * public static void main(String... args){ //acessa request CmGenIdValdService
	 * cmGenIdValdService = new CmGenIdValdService(); CmGenIdValdPortType
	 * cmGenIdVald = cmGenIdValdService.getCmGenIdValdPort();
	 * 
	 * HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver();
	 * cmGenIdValdService.setHandlerResolver(handlerResolver);
	 * 
	 * //valor de request CmGenIdVald.Request request = new CmGenIdVald.Request();
	 * request.setIdNumber("11122233344"); request.setIdType("CPF");
	 * 
	 * //CmGenIdVald.Response response;
	 * 
	 * ((BindingProvider)cmGenIdVald).getRequestContext().put(BindingProvider.USERNAME_PROPERTY,
	 * "psrmsysuser");
	 * ((BindingProvider)cmGenIdVald).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,
	 * "psrm@1cni");
	 * 
	 * //System.out.println("RESULTADO: "+cmGenIdVald.getOrders());
	 * //System.out.println("Order added: " + orderService.addOrder("Keyboard")); }
	 */

//	public static void main(String[] args) throws Exception {
//
//
//        //qualifier name ...
//        CmGenIdValdService cmGenIdValdService = new CmGenIdValdService();
//
//        //WebServiceInterface sayHello = service.getPort(WebServiceInterface.class);
//        CmGenIdValdPortType cmGenIdVald = cmGenIdValdService.getCmGenIdValdPort();
//
//        Map<String, Object> requestContext = ((BindingProvider)cmGenIdVald).getRequestContext();
//
//        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "https://144.22.109.36:6502/ouaf/XAIApp/xaiserver/CmGenIdVald?WSDL");
//
//        Map<String, List<String>> requestHeaders = new HashMap<String, List<String>>();
//
//        requestHeaders.put("username", Collections.singletonList("psrmsysuser"));
//        requestHeaders.put("Password", Collections.singletonList("psrm@1user"));
//
//        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);
//
//        //System.out.println(cmGenIdVald.cmGenIdVald(Holder<CmGenIdVald>));
//
//    }

}