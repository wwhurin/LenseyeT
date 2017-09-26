package kr.hs.emirim.wwhurin.lenseye5;

/**
 * Created by LG on 2017-09-25.
 */

public class User {
    private String userName;
    private String leftEye;
    private String rightEye;
    private String userAge;
    private String startCase;

    public String getStartCase() {
        return startCase;
    }

    public void setstartCase(String startCase) {
        this.startCase = startCase;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLeftEye() {
        return leftEye;
    }

    public void setLeftEye(String leftEye) {
        this.leftEye = leftEye;
    }

    public String getRightEye() {
        return rightEye;
    }

    public void setRightEye(String rightEye) {
        this.rightEye = rightEye;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public User(){}
    public User(String userName, String leftEye, String rightEye, String userAge,String startCase){

        this.userName = userName;
        this.leftEye = leftEye;
        this.rightEye = rightEye;
        this.userAge = userAge;
        this.startCase = startCase;
        // this.userDate = userDate;

    }
}
