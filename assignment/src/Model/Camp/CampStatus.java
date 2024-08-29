package Model.Camp;
/**
 * The {@code CampStatus} enum represents the visibility status of a camp.
 * It can have two possible values: {@code VISIBLE} and {@code HIDDEN}.
 * 
 * <p>{@code VISIBLE} indicates that the camp is currently visible and can be seen by users.
 * {@code HIDDEN} indicates that the camp is hidden and not visible to users.
 * 
 * <p>This enum is typically used to control the visibility of camps in a system or application.
 * 
 * @author Alicia
 * @version 1.0
 * @since 2023-11-19
 */
public enum CampStatus {
    /**
     * Represents that the camp is currently visible and can be seen by users.
     */
    VISIBLE,

    /**
     * Represents that the camp is hidden and not visible to users.
     */
    HIDDEN
}
