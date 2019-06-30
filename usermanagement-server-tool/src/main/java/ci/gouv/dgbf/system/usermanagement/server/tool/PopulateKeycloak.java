package ci.gouv.dgbf.system.usermanagement.server.tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.__kernel__.object.dynamic.AbstractObject;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.array.ArrayInstanceTwoDimensionString;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.file.excel.FileExcelSheetDataArrayReader;
import org.cyk.utility.string.StringHelper;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;

public class PopulateKeycloak extends AbstractObject {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		//Weld weld = new Weld();
	    //weld.initialize();
	    
		String url = "http://localhost:8230/auth/";
		String realmName = "TestPopulate";
		String clientIdentifier = "admin-cli";
		String clientSecret = "99592008-3bd6-4fda-9b57-09fbc6befa86";
		String username = "admin";
		String password = "admin";
		
		System.out.println("KEYCLOAK CLIENT TO BE CREATED\nurl:"+url+"\nrealm:"+realmName+"\nclient identifier:"+clientIdentifier+"\nclient secret:"+clientSecret
				+"\nusername:"+username+"\npassword:"+password);
		
		Keycloak client = KeycloakBuilder.builder().serverUrl(url).realm(realmName).grantType(OAuth2Constants.PASSWORD).clientId(clientIdentifier) //
				.clientSecret(clientSecret).username(username).password(password).build();
		
		FileExcelSheetDataArrayReader reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(PopulateKeycloak.class.getResourceAsStream("data.xlsx")).setSheetName("role");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(2);
		ArrayInstanceTwoDimensionString roleArrayInstance = reader.execute().getOutput();
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(PopulateKeycloak.class.getResourceAsStream("data.xlsx")).setSheetName("ministère");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		ArrayInstanceTwoDimensionString ministereArrayInstance = reader.execute().getOutput();
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(PopulateKeycloak.class.getResourceAsStream("data.xlsx")).setSheetName("programme");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		ArrayInstanceTwoDimensionString programmeArrayInstance = reader.execute().getOutput();
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(PopulateKeycloak.class.getResourceAsStream("data.xlsx")).setSheetName("unité administrative");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		ArrayInstanceTwoDimensionString uaArrayInstance = reader.execute().getOutput();
		
		PopulateKeycloak populateKeycloak = new PopulateKeycloak();
		RolesResource rolesResource = client.realm(realmName).roles();
		
		populateKeycloak.deleteRoles(rolesResource);
		//populateKeycloak.saveRoles(roleArrayInstance, ministereArrayInstance,programmeArrayInstance,uaArrayInstance,rolesResource);
		
		reader = DependencyInjection.inject(FileExcelSheetDataArrayReader.class);
		reader.setWorkbookInputStream(PopulateKeycloak.class.getResourceAsStream("data.xlsx")).setSheetName("service");
		reader.getRowInterval(Boolean.TRUE).getLow(Boolean.TRUE).setValue(1);
		ArrayInstanceTwoDimensionString serviceArrayInstance = reader.execute().getOutput();
		
		//populateKeycloak.deleteClients(client.realm(realmName).clients());
		//populateKeycloak.saveClients(serviceArrayInstance,client.realm(realmName).clients());
		
		//weld.shutdown();
	}
	
	private void saveRole(RolesResource rolesResource,String code,String name,String type,Properties properties) {
		RoleResource roleResource = rolesResource.get(code);
		RoleRepresentation roleRepresentation = null;
		if(roleResource == null)
			roleRepresentation = new RoleRepresentation();
		else
			try{
				roleRepresentation = roleResource.toRepresentation();
			}catch(NotFoundException exception) {
				roleResource = null;
				roleRepresentation = new RoleRepresentation();
			}
		
		Map<String,List<String>> attributes = new LinkedHashMap<>();
		attributes.put("nom", Arrays.asList(name));
		attributes.put("type", Arrays.asList(type));
		if(properties != null) {
			Map<Object,Object> map = properties.__getMap__();
			if(map != null) {
				for(Map.Entry<Object, Object> index : map.entrySet())
					if(index.getKey()!=null && index.getValue()!=null)
						attributes.put(index.getKey().toString(), Arrays.asList(index.getValue().toString()));
			}
		}

		roleRepresentation.setName(code);
		roleRepresentation.setDescription(name);
		
		System.out.print("\t"+type+"/"+code+"/"+name+"... ");
		if(roleResource == null) {
			rolesResource.create(roleRepresentation);
			roleResource = rolesResource.get(code);
			roleRepresentation = roleResource.toRepresentation();
			roleRepresentation.setAttributes(attributes);
		}else {
			roleRepresentation.setAttributes(attributes);
		}
		
		roleResource.update(roleRepresentation);
		
		System.out.println(roleResource == null ? " Created" : "Updated");
	}
	
	private void saveRoleComposites(RolesResource rolesResource,String code,String parents) {
		if(Boolean.TRUE.equals(DependencyInjection.inject(StringHelper.class ).isNotBlank(parents))) {
			List<RoleRepresentation> composites = new ArrayList<>();
			for(String indexParent : parents.split(",")) {
				RoleResource roleResource = rolesResource.get(indexParent);
				if(roleResource!=null) {
					System.out.println("PopulateKeycloak.saveRoleComposites() ::: "+indexParent);
					composites.add(roleResource.toRepresentation());
				}
			}
			if(DependencyInjection.inject(CollectionHelper.class).isNotEmpty(composites)) {
				System.out.print("\t"+code+"... ");
				rolesResource.get(code).addComposites(composites);	
				System.out.println("OK");
			}
		}
	}
	
	private void saveRoles(ArrayInstanceTwoDimensionString functionsArrayInstance,ArrayInstanceTwoDimensionString ministriesArrayInstance,ArrayInstanceTwoDimensionString programmeArrayInstance,ArrayInstanceTwoDimensionString uaArrayInstance,RolesResource rolesResource) {
		System.out.println("Synchronising functions and their categories");
		for(Integer index  = 0; index < functionsArrayInstance.getFirstDimensionElementCount(); index = index + 1) {
			String code = functionsArrayInstance.get(index, 0);
			String type = functionsArrayInstance.get(index, 2);
			String name = functionsArrayInstance.get(index, 1);
			saveRole(rolesResource, code, name, type,null);
			/*
			if("FONCTION".equals(type) && StringUtils.startsWith(functionsArrayInstance.get(index, 4), "oui"))
				savePosts(code, name, "MINISTERE", " du ministère ", ministriesArrayInstance, rolesResource);
			
			if("FONCTION".equals(type) && StringUtils.startsWith(functionsArrayInstance.get(index, 5), "oui"))
				savePosts(code, name, "PROGRAMME", " du programme ", programmeArrayInstance, rolesResource);
			
			if("FONCTION".equals(type) && StringUtils.startsWith(functionsArrayInstance.get(index, 8), "oui"))
				savePosts(code, name, "UNITE_ADMINISTRATIVE", " de l'unité administrative ", uaArrayInstance, rolesResource);
			*/
		}
		
		System.out.println("Synchronising compositions");
		for(Integer index  = 0; index < functionsArrayInstance.getFirstDimensionElementCount(); index = index + 1) {
			String code = functionsArrayInstance.get(index, 0);
			String parents = functionsArrayInstance.get(index, 3);
			saveRoleComposites(rolesResource, code, parents);
		}
	}
	/*
	private void savePosts(String functionCode,String functionName,String locationCode,String locationName,ArrayInstanceTwoDimensionString locationArrayInstance,RolesResource rolesResource) {
		System.out.println("Synchronising posts of function "+functionCode);		
		for(Integer index  = 0; index < locationArrayInstance.getFirstDimensionElementCount(); index = index + 1) {
			String locationIdentifier = locationArrayInstance.get(index, 0);
			String code = functionCode+ConstantCharacter.UNDESCORE+locationCode+ConstantCharacter.UNDESCORE+locationIdentifier;
			String type = "POSTE";
			String name = functionName+locationName+locationIdentifier;
			saveRole(rolesResource, code, name, type,new Properties().set(locationCode,locationIdentifier));
			rolesResource.get(code).addComposites(Arrays.asList(rolesResource.get(functionCode).toRepresentation()));
			//TODO to be removed. it is there just for test
			break;
		}
	}
	*/
	private void saveClients(ArrayInstanceTwoDimensionString serviceArrayInstance,ClientsResource clientsResource) {
		System.out.println("Synchronising services");
		for(Integer index  = 0; index < serviceArrayInstance.getFirstDimensionElementCount(); index = index + 1) {
			String code = serviceArrayInstance.get(index, 0);
			String name = serviceArrayInstance.get(index, 1);
			String url = serviceArrayInstance.get(index, 2);
			String uuid = serviceArrayInstance.get(index, 3);
			saveClient(clientsResource, code, name, url,uuid);
		}
	}
	
	private void saveClient(ClientsResource clientsResource,String code,String name,String url,String uuid) {
		if(DependencyInjection.inject(StringHelper.class).isBlank(code)) {
			System.err.println("Service named <<"+name+">> has been skipped because it does not have a code");
			return;
		}
		ClientResource clientResource = clientsResource.get(code);
		ClientRepresentation clientRepresentation = null;
		if(clientResource == null)
			clientRepresentation = new ClientRepresentation();
		else
			try{
				clientRepresentation = clientResource.toRepresentation();
			}catch(NotFoundException exception) {
				clientResource = null;
				clientRepresentation = new ClientRepresentation();
			}
		
		Map<String,String> attributes = new LinkedHashMap<>();
		attributes.put("uuid", uuid);

		clientRepresentation.setClientId(code);
		clientRepresentation.setName(name);
		clientRepresentation.setDescription(name);
		clientRepresentation.setAttributes(attributes);
		clientRepresentation.setBaseUrl(url);
		
		System.out.print("\t"+code+"/"+name+"... ");
		if(clientResource == null)
			clientsResource.create(clientRepresentation);
		else
			clientResource.update(clientRepresentation);
		System.out.println(clientResource == null ? " Created" : "Updated");
	}
	
	private void deleteRoles(RolesResource rolesResource) {
		Integer count = rolesResource.list().size();
		System.out.println("Deleting all ("+count+") roles... ");
		Integer deleted = 0;
		for(RoleRepresentation index : rolesResource.list()) {
			rolesResource.deleteRole(index.getName());
			deleted++;
			if(deleted % 100 == 0) {
				count -= 100;
				System.out.println( ((deleted / count) * 100)+"% , remaining="+count );
				
			}
		}
		System.out.println("OK");
	}

	private void deleteClients(ClientsResource clientsResource) {
		System.out.print("Deleting all clients... ");
		for(ClientRepresentation index : clientsResource.findAll())
			if(index.getAttributes()!=null && __inject__(StringHelper.class).isNotBlank(index.getAttributes().get("uuid")))
				clientsResource.get(index.getId()).remove();
		System.out.println("OK");
	}
	
	/*private void deleteScopes(ClientsResource clientsResource) {
		clientsResource.get("").
		System.out.print("Deleting all clients... ");
		for(ClientRepresentation index : clientsResource.findAll())
			if(index.getAttributes()!=null && __inject__(StringHelper.class).isNotBlank(index.getAttributes().get("uuid")))
				clientsResource.get(index.getId()).remove();
		System.out.println("OK");
	}*/
}
