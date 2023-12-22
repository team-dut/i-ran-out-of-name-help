package Game.Entity;

import Engine.Object.BaseEntity;


/**
 * Represents a character entity on the screen.
 */
public class Player extends BaseEntity {
    // Animation paths for different directions
    String animationIdleUp;
    String animationIdleDown;
    String animationIdleLeft;
    String animationIdleRight;
    String animationRunUp;
    String animationRunDown;
    String animationRunLeft;
    String animationRunRight;
    private String characterAsset;
    private String characterAnimation;
private boolean alive = true;
    private final String animationHit;

    // Current direction and state of the character
    private Direction currentDirection;
    private State currentState;

    public Player(int x, int y) {
        super(x, y);
        this.characterAsset = "/Players/elf_m_idle_anim_f0.png";

        // Set animation paths for idle state
        this.animationIdleUp = "/Players/elf_m_idle_anim_f0.png";
        this.animationIdleDown = "/Players/elf_m_idle_anim_f1.png";
        this.animationIdleLeft = "/Players/elf_m_idle_anim_f2.png";
        this.animationIdleRight = "/Players/elf_m_idle_anim_f3.png";

        // Set animation paths for running state
        this.animationRunUp = "/Players/elf_m_run_anim_f0.png";
        this.animationRunDown = "/Players/elf_m_run_anim_f1.png";
        this.animationRunLeft = "/Players/elf_m_run_anim_f2.png";
        this.animationRunRight = "/Players/elf_m_run_anim_f3.png";

        // Set animation path for being hit state
        this.animationHit = "/Players/elf_m_hit_anim_f0.png";

        // Set the initial direction and state to, for example, right and idle
        this.currentDirection = Direction.RIGHT;
        this.currentState = State.IDLE;

        // Set the character animation based on the initial direction and state
        updateAnimationPath();
    }

    public void setDirection(Direction direction) {
        this.currentDirection = direction;
        // Update the character animation based on the new direction and state
        updateAnimationPath();
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setState(State state) {
        this.currentState = state;
        // Update the character animation based on the new direction and state
        updateAnimationPath();
    }

    public State getCurrentState() {
        return currentState;
    }

    private void updateAnimationPath() {
        switch (currentState) {
            case IDLE:
                setAnimationPathBasedOnDirection(getIdleAnimationPath());
                break;
            case RUN:
                setAnimationPathBasedOnDirection(getRunAnimationPath());
                break;
            case HIT:
                characterAnimation = animationHit;
                break;
        }
    }

    private String getIdleAnimationPath() {
        switch (currentDirection) {
            case UP:
                return animationIdleUp;
            case DOWN:
                return animationIdleDown;
            case LEFT:
                return animationIdleLeft;
            case RIGHT:
                return animationIdleRight;
            default:
                return "";
        }
    }

    private String getRunAnimationPath() {
        switch (currentDirection) {
            case UP:
                return animationRunUp;
            case DOWN:
                return animationRunDown;
            case LEFT:
                return animationRunLeft;
            case RIGHT:
                return animationRunRight;
            default:
                return "";
        }
    }

    private void setAnimationPathBasedOnDirection(String animationPath) {
        characterAnimation = animationPath;
    }

    public String getCharacterAsset() {
        return characterAsset;
    }

    public void setCharacterAsset(String characterAsset) {
        this.characterAsset = characterAsset;
    }

    public String getCharacterAnimation() {
        return characterAnimation;
    }

    public void setCharacterAnimation(String characterAnimation) {
        this.characterAnimation = characterAnimation;
    }

    // Placeholder for character assets
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    enum State {
        IDLE, RUN, HIT
    }

    // Additional methods as needed

  /*  public static void main(String[] args) {
        // Example usage
        Player MyPlayer = new Player(0, 0);
        MyPlayer.setDirection(Direction.DOWN); // Set the direction
        MyPlayer.setState(State.RUN); // Set the state
    }    */
}