package me.chiefbeef.core.user;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.chiefbeef.core.user.assets.UserExtensionAssets;
import me.chiefbeef.core.user.assets.UserExtensionRegistry;
import me.chiefbeef.core.utility.assets.AssetBuildPack;
import me.chiefbeef.core.utility.assets.AssetHolder;
import me.chiefbeef.core.utility.assets.TypeAssets;

/**
 * A {@link UserExtension} is an object that extends the {@link UserCore} objects
 * functionality into another plugin.
 * 
 * @author Kevin
 *
 */
public abstract class UserExtension implements AssetHolder<UserExtension> {
	
	private static UserExtensionRegistry registry = new UserExtensionRegistry();
	
	public static UserExtensionRegistry getRegistry() {
		return registry;
	}
	
	private UserCore user;

	/**
	 * Get the {@link UserCore} for this extension.
	 * @return The {@link UserCore} managing this {@link UserExtension}
	 */
	public UserCore getUserCore() {
		return user;
	}
	
	/**
	 * lazy method
	 * @return the player
	 */
	public Player getPlayer() {
		return getUserCore().getPlayer();
	}
	
	/**
	 * lazy method.
	 * @return The location of the player.
	 */
	public Location getLocation() {
		return getUserCore().getLocation();
	}
	
	/**
	 * UserExtension does not require an {@link AssetBuildPack}
	 */
	@Override
	public UserExtension build(AssetBuildPack pack) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Sets the user for this extension.
	 * @param user
	 */
	protected void setUser(UserCore user) {
		 this.user = user;
	}
	
	@Override
	public TypeAssets<UserExtension> createAssets() {
		return new UserExtensionAssets(this.getClass(), getLabel());
	}

	@Override
	public TypeAssets<UserExtension> getAssets() {
		return (UserExtensionAssets) getRegistry().getAssets(this.getClass());
	}

	@Override
	public boolean hasConfig() {
		return false;
	}
	
	/**
	 * Invoked when the {@link Player} disconects from the server.
	 */
	public abstract void onQuit();
	
	/**
	 * Invoked when data pertaining to the user should be saved, such as on disconnect or
	 * autosave interval.
	 */
	public abstract void save();
	
	/**
	 * Invoked under these circumstances;
	 * - The server shuts down
	 * - The Core plugin is disabled
	 * - The UserExtension is unregistered
	 */
	public abstract void onDisable();

}
