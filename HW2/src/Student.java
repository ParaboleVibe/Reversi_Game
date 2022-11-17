public class Student {
    private final String name;
    private int state;
    private int grade;

    public Student() {
        name = "";
        state = 0;
        grade = 0;
    }

    public Student(String name) {
        this.name = name;
        state = 0;
        grade = 0;
    }

    public String getName() {
        return name;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getState() {
        return state;
    }

    public void setState(boolean state) {
        if (state)
            this.state = 1;
        else
            this.state = -1;
    }

    @Override
    public String toString() {
        return name + ": " + (
                (state == 1) ? ("+, оценка: " + grade)
                : ((state == -1) ? "-" : "?"));
    }
}
