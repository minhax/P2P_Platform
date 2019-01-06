package com.lo23.communication.Messages;

import com.lo23.common.user.UserIdentity;

/**
 * Classe abstraite qui definit l'identite de l'utilisateur
 */
public abstract class UserMessage extends Message
{
	/**
	 * user : l'identite de l'utilisateur
	 */
	protected UserIdentity user;

	/**
	 * l'accesseur (getter) de user
	 * @return objet de type UserIdentity
	 */
	public UserIdentity getUser()
	{
		return user;
	}

	/**
	 * treatment : méthode abstraite à utiliser dans les sous classes
	 */
	public abstract void treatment();
}
