package com.lo23.communication.Messages;

import com.lo23.common.user.UserStats;

/**
 * Classe abstraite
 */
public abstract class Authentication extends Message
{
	/**
	 * userStats : l'utilisateur avec ses statistiques
	 */
	protected UserStats userStats;

	/**
	 * treatment : méthode abstraite à utiliser dans les sous classes
	 */
	public abstract void treatment();
}
