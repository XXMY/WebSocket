package origin.enums;

/**
 * Created by Cfw on 2016/10/2.
 */
public enum MessageType {

    USER_FIRST_IN(1);

    private int code;

    MessageType(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
