package ci.gouv.dgbf.system.usermanagement.server.representation.impl;

import java.io.Serializable;

import org.cyk.utility.instance.AbstractInstanceBuilderImpl;

@ci.gouv.dgbf.system.usermanagement.server.annotation.System @Deprecated
public class InstanceBuilderImpl extends AbstractInstanceBuilderImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * @Override protected void __copy__(Object source, Object destination,
	 * Properties properties) {
	 * System.out.println("InstanceBuilderImpl.__copy__() ::: ");
	 * System.out.println(source.getClass());
	 * System.out.println(destination.getClass()); //Persistence to Representation
	 * if(source instanceof Scope && destination instanceof ScopeDto) { Scope
	 * persistence = (Scope) source; ScopeDto representation = (ScopeDto)
	 * destination; representation.setIdentifier(persistence.getIdentifier());
	 * representation.setName(persistence.getName());
	 * //representation.setType(InstanceHelper.buildOne(ScopeTypeDto.class,
	 * persistence.getType())); }else if(source instanceof FunctionType &&
	 * destination instanceof FunctionTypeDto) { FunctionType persistence =
	 * (FunctionType) source; FunctionTypeDto representation = (FunctionTypeDto)
	 * destination; representation.setIdentifier(persistence.getIdentifier());
	 * representation.setCode(persistence.getCode());
	 * representation.setName(persistence.getName()); }else if(source instanceof
	 * Function && destination instanceof FunctionDto) { Function persistence =
	 * (Function) source; FunctionDto representation = (FunctionDto) destination;
	 * representation.setIdentifier(persistence.getIdentifier());
	 * representation.setCode(persistence.getCode());
	 * representation.setName(persistence.getName());
	 * //representation.setType(InstanceHelper.buildOne(FunctionTypeDto.class,
	 * persistence.getType())); }else if(source instanceof FunctionScope &&
	 * destination instanceof FunctionScopeDto) { FunctionScope persistence =
	 * (FunctionScope) source; FunctionScopeDto representation = (FunctionScopeDto)
	 * destination; representation.setIdentifier(persistence.getIdentifier());
	 * representation.setCode(persistence.getCode());
	 * representation.setName(persistence.getName());
	 * //representation.setFunction(InstanceHelper.buildOne(FunctionDto.class,
	 * persistence.getFunction()));
	 * //representation.setScope(InstanceHelper.buildOne(ScopeDto.class,
	 * persistence.getScope())); }else if(source instanceof Profile && destination
	 * instanceof ProfileDto) { Profile persistence = (Profile) source; ProfileDto
	 * representation = (ProfileDto) destination;
	 * representation.setIdentifier(persistence.getIdentifier());
	 * representation.setCode(persistence.getCode());
	 * representation.setName(persistence.getName());
	 * if(CollectionHelper.isNotEmpty(persistence.getFunctions())) for(Function
	 * index : persistence.getFunctions())
	 * representation.addFunctions(InstanceHelper.buildOne(FunctionDto.class,
	 * index)); }else if(source instanceof ProfileFunction && destination instanceof
	 * ProfileFunctionDto) { ProfileFunction persistence = (ProfileFunction) source;
	 * ProfileFunctionDto representation = (ProfileFunctionDto) destination;
	 * representation.setIdentifier(persistence.getIdentifier());
	 * 
	 * @SuppressWarnings("unchecked") Collection<String> fields =
	 * (Collection<String>) properties.getFields();
	 * if(CollectionHelper.contains(fields, ProfileFunction.FIELD_PROFILE))
	 * representation.setProfile(InstanceHelper.buildOne(ProfileDto.class,
	 * persistence.getProfile())); if(CollectionHelper.contains(fields,
	 * ProfileFunction.FIELD_FUNCTION))
	 * representation.setFunction(InstanceHelper.buildOne(FunctionDto.class,
	 * persistence.getFunction())); }else if(source instanceof UserAccount &&
	 * destination instanceof UserAccountDto) { UserAccount persistence =
	 * (UserAccount) source; UserAccountDto representation = (UserAccountDto)
	 * destination; representation.setIdentifier(persistence.getIdentifier());
	 * //representation.setUser(InstanceHelper.buildOne(UserDto.class,
	 * persistence.getUser()));
	 * //representation.setAccount(InstanceHelper.buildOne(AccountDto.class,
	 * persistence.getAccount()));
	 * if(CollectionHelper.isNotEmpty(persistence.getFunctionScopes()))
	 * for(FunctionScope index : persistence.getFunctionScopes())
	 * representation.addFunctionScopes(InstanceHelper.buildOne(FunctionScopeDto.
	 * class, index)); if(CollectionHelper.isNotEmpty(persistence.getProfiles()))
	 * for(Profile index : persistence.getProfiles())
	 * representation.addProfiles(InstanceHelper.buildOne(ProfileDto.class, index));
	 * } //Representation to Persistence else if(source instanceof UserAccountDto &&
	 * destination instanceof UserAccount) { UserAccountDto representation =
	 * (UserAccountDto) source; UserAccount persistence = (UserAccount) destination;
	 * persistence.setIdentifier(representation.getIdentifier());
	 * persistence.setUser(InstanceHelper.buildOne(User.class,
	 * representation.getUser()));
	 * persistence.setAccount(InstanceHelper.buildOne(Account.class,
	 * representation.getAccount()));
	 * persistence.setFunctionScopes(null).setProfiles(null);
	 * if(representation.getFunctionScopes()!=null &&
	 * CollectionHelper.isNotEmpty(representation.getFunctionScopes()))
	 * for(FunctionScopeDto index : representation.getFunctionScopes()) {
	 * FunctionScope functionScope = null;
	 * if(StringHelper.isBlank(index.getIdentifier())) functionScope =
	 * __inject__(FunctionScopePersistence.class).readByBusinessIdentifier(index.
	 * getCode()); else functionScope =
	 * __inject__(FunctionScopePersistence.class).readBySystemIdentifier(index.
	 * getIdentifier()); if(functionScope != null)
	 * persistence.getFunctionScopes(Boolean.TRUE).add(functionScope); }
	 * if(representation.getProfiles()!=null &&
	 * CollectionHelper.isNotEmpty(representation.getProfiles())) for(ProfileDto
	 * index : representation.getProfiles()) { Profile profile = null;
	 * if(StringHelper.isBlank(index.getIdentifier())) profile =
	 * __inject__(ProfilePersistence.class).readByBusinessIdentifier(index.getCode()
	 * ); else profile =
	 * __inject__(ProfilePersistence.class).readBySystemIdentifier(index.
	 * getIdentifier()); if(profile != null)
	 * persistence.getProfiles(Boolean.TRUE).add(profile); } }else if(source
	 * instanceof ProfileDto && destination instanceof Profile) { ProfileDto
	 * representation = (ProfileDto) source; Profile persistence = (Profile)
	 * destination; persistence.setIdentifier(representation.getIdentifier());
	 * persistence.setCode(representation.getCode());
	 * persistence.setName(representation.getName());
	 * persistence.setFunctions(null); if(representation.getFunctions()!=null &&
	 * CollectionHelper.isNotEmpty(representation.getFunctions())) for(FunctionDto
	 * index : representation.getFunctions()) { Function function = null;
	 * if(StringHelper.isBlank(index.getIdentifier())) function =
	 * __inject__(FunctionPersistence.class).readByBusinessIdentifier(index.getCode(
	 * )); else function =
	 * __inject__(FunctionPersistence.class).readBySystemIdentifier(index.
	 * getIdentifier()); if(function != null)
	 * persistence.getFunctions(Boolean.TRUE).add(function); } }else
	 * super.__copy__(source, destination, properties); }
	 */
	
}
