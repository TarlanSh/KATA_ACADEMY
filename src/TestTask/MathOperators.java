package TestTask;

enum MathOperators {

    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDE("/");

    private  String mathValue = "";

    MathOperators(String mathValue) {
        this.mathValue = mathValue;
    }

    public  String getMathValue() {
        return mathValue;
    }


}
