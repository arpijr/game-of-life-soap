package packages.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import packages.calculations.GameOfLifeRequestProcessor;
import packages.service.jaxb.GetTableStateRequest;
import packages.service.jaxb.GetTableStateResponse;


@Endpoint
public class ServerEndpoint {
    private static final String NAMESPACE_URI = "http://arpi.io/game/gol-web-service";

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerEndpoint.class);
    
	private GameOfLifeRequestProcessor gameOfLifeRequestProcessor;
	
	@Autowired
	public ServerEndpoint() {
		this.gameOfLifeRequestProcessor = new GameOfLifeRequestProcessor();
	}
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTableStateRequest")
    @ResponsePayload
    public GetTableStateResponse getTableState(@RequestPayload GetTableStateRequest request) {
        
       LOGGER.info("Incoming SOAP request: getTableStateRequest" );
        
       return gameOfLifeRequestProcessor.processGetTableStateRequest(request); 
       
    }
}